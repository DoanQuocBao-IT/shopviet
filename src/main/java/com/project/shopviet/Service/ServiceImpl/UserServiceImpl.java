package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.RegisterDto;
import com.project.shopviet.DTO.UserDto;
import com.project.shopviet.DTO.request.LoginRequest;
import com.project.shopviet.DTO.request.RegisterSellerRequest;
import com.project.shopviet.DTO.response.LoginResponse;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.DTO.response.UserSellerResponse;
import com.project.shopviet.JWT.JwtTokenProvider;
import com.project.shopviet.JWT.UserDetailsServiceImpl;
import com.project.shopviet.Model.Area;
import com.project.shopviet.Model.Role;
import com.project.shopviet.Model.Seller;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.*;
import com.project.shopviet.Service.EmailSenderService;
import com.project.shopviet.Service.ImageService;
import com.project.shopviet.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    AreaRepository areaRepository;
    @Autowired
    ImageService imageService;

    @Transactional
    public void addRoleToUser(int userId, int roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found!"));
        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    public ResponseObject login(LoginRequest loginRequest) {
        try {
            Optional<User> user = userRepository.getUserByUsername(loginRequest.getUsername());
            if (user.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Username is not exist")
                        .build();
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            final String accessToken = jwtTokenProvider.generateAccessToken(userDetails);
            final String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);
            if (user.get().isLocked()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Your account is locked")
                        .build();
            }
            if (!user.get().isApproved()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Your account is not approved")
                        .data(LoginResponse.builder()
                                .id(user.get().getId())
                                .full_name(user.get().getFname())
                                .image(user.get().getImage())
                                .roles(user.get().getRoles())
                                .access_token(accessToken)
                                .refresh_token(refreshToken)
                                .build())
                        .build();
            }
            return ResponseObject.builder()
                    .code(200)
                    .message("Login success")
                    .data(LoginResponse.builder()
                            .id(user.get().getId())
                            .full_name(user.get().getFname())
                            .image(user.get().getImage())
                            .roles(user.get().getRoles())
                            .access_token(accessToken)
                            .refresh_token(refreshToken)
                            .build())
                    .build();
        } catch (BadCredentialsException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Username or password is incorrect")
                    .build();
        }
    }

    @Override
    public ResponseObject registerUserBuyer(RegisterDto registerDto) {
        try {
            User user = new User();
            Role role = roleRepository.findRoleByName(registerDto.getRoleName());
            if (userRepository.findUserByName(registerDto.getUsername()) != null) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Username already exists")
                        .build();
            }
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setFname(registerDto.getFname());
            user.setPhone(registerDto.getPhone());
            Optional<User> userOptional = userRepository.findByEmail(registerDto.getEmail());
            if (userOptional.isPresent()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Email already exists")
                        .build();
            }
            user.setEmail(registerDto.getEmail());
            user.addRole(role);
            user.setLocked(false);
            if (registerDto.getRoleName().equals("seller") || registerDto.getRoleName().equals("shipper")) {
                user.setApproved(false);
                userRepository.save(user);
                return ResponseObject.builder()
                        .code(200)
                        .message("Confirm email to register seller or shipper")
                        .build();
            }
            user.setApproved(true);
            userRepository.save(user);
//            String message = "Chúng tôi gửi mail này để xác nhận tài khoản ShopViet của " + user.getFname() + " đã đăng kí tài khoản thành công. Vui lòng bấm vào link bên dưới để xác thực tài khoản của bạn.\n" +
//                    "Vui lòng bỏ qua mail này nếu như bạn chưa từng đăng kí tài khoản tại ShopViet";
//            emailSenderService.sendEmailActive(user.getEmail(), "Thông báo xác thực tài khoản ShopViet thành công!!", message);
            return ResponseObject.builder()
                    .code(200)
                    .message("Register User Success")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Register User Error: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject registerUserSeller(RegisterSellerRequest registerDto) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User userSeller = userRepository.findUserByName(currentPrincipalName);
            if (userSeller == null) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Seller not found")
                        .build();
            }
            Optional<Seller> seller = sellerRepository.findByUserId(userSeller.getId());
            if (seller.isPresent()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Seller already exists")
                        .build();
            }
            Seller newSeller = new Seller();
            newSeller.setNameStore(registerDto.getName_store());
            newSeller.setImage(registerDto.getImage());
            newSeller.setDescription(registerDto.getDescription());
            newSeller.setAddress(registerDto.getAddress());
            Area area = areaRepository.findAreaByProvinceAndDistrictAndPrecinctAndStatus(registerDto.getProvince(), registerDto.getDistrict(), registerDto.getPrecinct(), "1");
            if (area == null) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Area not found")
                        .build();
            }
            newSeller.setArea(area);
            newSeller.setUser(userSeller);
            newSeller.setCreatedAt(new Date());
            sellerRepository.save(newSeller);
            return ResponseObject.builder()
                    .code(200)
                    .message("Register User Success")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Register User Error: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public String addRoleUser(String username, String name) {

        return "Thanh cong";
    }


    @Override
    public Optional<User> getUserByUsername(String username) {
        try {
            return userRepository.getUserByUsername(username);
        } catch (IllegalArgumentException e) {
            System.out.println("Find User Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> findUserByRole(Role role) {
        try {
            return userRepository.findByRolesContaining(role);
        } catch (IllegalArgumentException e) {
            System.out.println("Find User Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> findByRolesIn(Set<Role> roles) {
        try {
            return userRepository.findByRolesIn(roles);
        } catch (IllegalArgumentException e) {
            System.out.println("Find User Error: " + e.getMessage());
            return null;
        }
    }


    @Override
    public UserSellerResponse getUserById(int id) {
        User users = userRepository.findById(id).get();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(users, UserSellerResponse.class);
    }

    @Override
    public List<User> getAllUser() {
        try {
            return (List<User>) userRepository.findAll();
        } catch (IllegalArgumentException e) {
            System.out.println("Get All User Error: " + e.getMessage());
            return null;
        }
    }


    @Override
    public boolean isExistById(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public void approveSellerOrShipper(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setApproved(true);
            String message = "Chúng tôi gửi mail này để xác nhận tài khoản ShopViet của " + user.getFname() + " đã phê duyệt đăng kí tài khoản thành công. Rẩt vui khi trở thành đối tác của bạn và mong bạn ứng xử phù hợp các quy tắc chung tránh tình trạng bị khóa tài khoản.\n" +
                    "Vui lòng bỏ qua mail này nếu như bạn chưa từng đăng kí tài khoản tại ShopViet";
            emailSenderService.sendEmailActive(user.getEmail(), "Thông báo xác thực tài khoản ShopViet thành công!!", message);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void blockSellerOrShipper(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setLocked(true);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public void unBlockSellerOrShipper(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setLocked(false);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public List<User> getAllUserApprove() {
        return userRepository.findByApprovedFalse();
    }

    @Override
    public List<User> getAllUserBlock() {
        return userRepository.findByLockedTrue();
    }

    @Override
    public UserDto getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        int followers = followRepository.countFollowByFollowedUserId(user.getId());
        int followings = followRepository.countFollowByUserId(user.getId());
        return UserDto.builder()
                .id(user.getId())
                .fname(user.getFname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .image(user.getImage())
                .birthday(user.getBirthday())
                .sex(user.getSex())
                .follower(String.valueOf(followers))
                .following(String.valueOf(followings))
                .build();
    }
}
