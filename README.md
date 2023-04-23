ShopViet is a modern e-commerce platform that utilizes a variety of cutting-edge technologies to provide a robust and scalable solution for online businesses. 

Introducing our latest application built using a powerful stack of back-end technologies. Our application leverages the robustness of Java, the flexibility of Spring Boot, and the convenience of Spring Data JPA to provide a seamless and reliable user experience. In addition, Spring Security and JWT have been integrated into the application for efficient authentication and authorization, while Java Mail has been utilized for seamless email communication.

Here's a breakdown of the technologies used in our application:

 - Java: Java is a widely used programming language known for its robustness and scalability. Our application is built using Java to provide a stable and efficient foundation.
 
 - Spring Boot: Spring Boot is a powerful framework for building Java applications. It provides a wide range of features such as auto-configuration, embedded servers, and production-ready code. Our application utilizes Spring Boot to simplify the development process.
 
 - Spring Data JPA: Spring Data JPA is a data access layer that provides convenient abstractions over the underlying database. It reduces the amount of boilerplate code required for database interactions, making development more efficient.
 
 - Spring Security and JWT: Spring Security is a powerful framework for managing authentication and authorization. JWT (JSON Web Tokens) is a standard for securely transmitting information between parties as a JSON object. Our application integrates Spring Security and JWT to provide secure and efficient user authentication and authorization.
 
 - Java Mail: Java Mail is a library that provides an easy-to-use API for sending and receiving email messages. Our application utilizes Java Mail for seamless email communication between users.
  
  - MySQL serves as a scalable and secure relational database management system for storing and managing data. 
  
  - Finally, Swagger 2 generates comprehensive and easy-to-understand documentation for our application's APIs, making it easier to develop and maintain.
 
Together, these technologies provide a robust and reliable application that ensures a seamless and efficient user experience.

<H1> Swagger

![image](https://user-images.githubusercontent.com/85630559/233825348-dbbe9ab9-f87b-4603-92d6-ea37346d3233.png)

<H1> Some main functions: 

<H2> - Function get all categories

![image](https://user-images.githubusercontent.com/85630559/233825502-833513a0-6f76-4136-bccb-f7617dc9653e.png)
<H2> - Function get all brands

![image](https://user-images.githubusercontent.com/85630559/233825556-526099f7-38db-4c32-844a-ce3db36fff80.png)
<H2> - Function get all brands and products in brand

![image](https://user-images.githubusercontent.com/85630559/233825605-4bd6aec7-e0be-44b3-b867-2d75c133d99b.png)
<H2> - Function get image products

![image](https://user-images.githubusercontent.com/85630559/233825841-bd1acd01-578b-4a09-b5f3-a41635ae4286.png)
<H2> - Function get all products

<H3> Sort by name

![image](https://user-images.githubusercontent.com/85630559/233826010-950002ff-9984-4cb3-adc1-153bdccc78a3.png)

<H3> Sort by price

![image](https://user-images.githubusercontent.com/85630559/233826448-471b9805-be72-49d8-a50d-5a4a5a2dd3d0.png)

![image](https://user-images.githubusercontent.com/85630559/233826491-d4e6f82f-fcb2-4991-bef7-a3a034a07fad.png)

<H3> Sort by sold

![image](https://user-images.githubusercontent.com/85630559/233826651-8d2c1262-8fb7-4bdc-ab6c-eec67402fe39.png)

![image](https://user-images.githubusercontent.com/85630559/233826678-4bb2d742-6219-4f00-95e7-b0ab1427f151.png)

<H3> Sort by newest

![image](https://user-images.githubusercontent.com/85630559/233826710-ff23056c-bb58-4820-a0e2-7754038a5d01.png)

<H2> - Function get all products by seller ID

![image](https://user-images.githubusercontent.com/85630559/233826776-60b9f4c4-9d81-4cb7-a940-b4dbb98057c1.png)

<H2> - Function get all products by brand ID

![image](https://user-images.githubusercontent.com/85630559/233826847-586eeca8-d356-40b0-b1a9-8834e0e1fb11.png)

<H2> - Function get all products by category ID

![image](https://user-images.githubusercontent.com/85630559/233826874-b97381fc-9e83-41a8-a279-74ccbaeee97c.png)

<H2> - Function get all brands by category ID

![image](https://user-images.githubusercontent.com/85630559/233827207-3f823134-680d-4787-abcc-aa54263abd06.png)

<H2> - Function search products by name

![image](https://user-images.githubusercontent.com/85630559/233827295-633a3a92-e8d8-4af1-be8f-6efdcfa434bf.png)

<H2> - Function get product detail by ID

![image](https://user-images.githubusercontent.com/85630559/233827729-d1587cec-eef9-4359-b7db-0cc54b16c4d5.png)

<H2> - Function get user 

![image](https://user-images.githubusercontent.com/85630559/233827836-eed516e3-4e8e-42ef-ba78-6f823d8e269d.png)


<H1> Authentication controller

<H2> - Function register

If user register with role seller(shipper), admin will approve account

![image](https://user-images.githubusercontent.com/85630559/233828321-f3e30125-9dea-4647-ade9-11125ddf8303.png)

User register with role buyer, system will respone JWT

![image](https://user-images.githubusercontent.com/85630559/233828503-5ee33546-0c09-40a9-965f-66cdc88b2ecb.png)

<H2> - Function login

![image](https://user-images.githubusercontent.com/85630559/233828687-c6efd6fc-3633-4a39-b83e-2158888534b7.png)

<H2> - Function logout

JWT will add to Back-List but logout should set up in front-end

![image](https://user-images.githubusercontent.com/85630559/233828769-ee54fa50-dbfd-4e61-9815-1c9278dd1886.png)

<H2> - Function forgot-password

![image](https://user-images.githubusercontent.com/85630559/233845945-7dc2c4a4-5488-4012-8bfc-87dc76aae452.png)

![image](https://user-images.githubusercontent.com/85630559/233845988-b88e285e-fa32-450c-bce3-ea71dcd42d0c.png)

![image](https://user-images.githubusercontent.com/85630559/233846084-a44599cc-942c-47b1-92c3-aab518efc8ae.png)

![image](https://user-images.githubusercontent.com/85630559/233846124-c4c7058a-76ea-4b57-84e2-8ef220328ffb.png)

<H1> Admin controller

<H1> Seller controller

<H1> Buyer controller

<H1> Shipper controller

<H1> All user 







