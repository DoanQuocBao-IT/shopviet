package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.Model.OrderItem;
import com.project.shopviet.Model.Review;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.OrderItemRepository;
import com.project.shopviet.Repository.ReviewRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Override
    public Review addReview(int orderItem_id,Review review) {
        OrderItem orderItem=orderItemRepository.findById(orderItem_id).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userBuyer = userRepository.findUserByName(authentication.getName());
        review.setProduct(orderItem.getProduct());
        review.setOrderItem(orderItem);
        review.setUserBuyer(userBuyer);
        review.setCreateTime(new Date());
        return reviewRepository.save(review);
    }

    @Override
    public Review addReviewFeedback(int review_id,String feedback) {
        Review review=reviewRepository.findById(review_id).get();
        if (review.getFeedback()==null){
            review.setFeedback(feedback);
        }

        return reviewRepository.save(review);
    }
}
