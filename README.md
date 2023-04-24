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

<H1> Swagger UI

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


<H1>Authentication controller

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

<H2> - Function get admin

![image](https://user-images.githubusercontent.com/85630559/233852046-672ea2e0-e77c-477c-80d9-4f62486a5cdd.png)

<H2> - Function get all sellers

![image](https://user-images.githubusercontent.com/85630559/233852121-5d2b9a3f-ffb8-491d-aaff-56cd7d1ff5af.png)

<H2> - Function get all buyers

![image](https://user-images.githubusercontent.com/85630559/233852173-a16dc8f8-e74e-4422-88af-26fa5f0bc020.png)

<H2> - Function get all shippers

![image](https://user-images.githubusercontent.com/85630559/233852216-f82193fe-b549-481c-b429-3967130701a1.png)

<H2> - Function get all sellers and shippers

![image](https://user-images.githubusercontent.com/85630559/233852289-5eaa0aa9-bf94-4e1c-af9f-829fa0bbb93e.png)

<H2> - Function get all users

![image](https://user-images.githubusercontent.com/85630559/233852361-f584adff-e9b0-48d9-9a10-49793db72687.png)

<H2> - Function add new category

![image](https://user-images.githubusercontent.com/85630559/233852469-26c326f2-5aa4-4ec1-a776-188095b2e427.png)

<H2> - Function update category

![image](https://user-images.githubusercontent.com/85630559/233852590-1cfc44a7-6751-4c99-b770-ba84c3cc23ad.png)
<H2> - Function delete category

![image](https://user-images.githubusercontent.com/85630559/233852613-1b85ec21-f28c-4945-8ce8-2ee219cf4c5e.png)

<H2> - Function add new brand

![image](https://user-images.githubusercontent.com/85630559/233852718-12a550ca-9701-4593-b46d-5ca692653baf.png)

<H2> - Function update brand

![image](https://user-images.githubusercontent.com/85630559/233852931-38bde0e9-ff6e-4db2-b5df-1e35772fe964.png)
<H2> - Function delete brand

![image](https://user-images.githubusercontent.com/85630559/233852983-1247df2e-1b0e-4b61-ae90-a1b8d4b4ad87.png)

<H2> - Function get all orders

![image](https://user-images.githubusercontent.com/85630559/233853159-ab55e9ef-a58e-4ad3-a517-d19640539548.png)

<H2> - Function get all items order by sellers

![image](https://user-images.githubusercontent.com/85630559/233853233-1b56e21f-9bab-4c81-8f47-a1ed2cc2a232.png)

<H2> - Function add new role

![image](https://user-images.githubusercontent.com/85630559/233853870-eee2a053-592e-4cbc-9fbf-d3b948bdc436.png)

<H2> - Function get all roles

![image](https://user-images.githubusercontent.com/85630559/233853925-778ea947-7a87-46f3-a0d8-7a06c732696c.png)

<H2> - Function get all spending

![image](https://user-images.githubusercontent.com/85630559/233854067-9b962d2e-539b-4c5c-89a3-9a6addec1b4e.png)

<H2> - Function update status users

![image](https://user-images.githubusercontent.com/85630559/233854298-16265f08-826d-4897-b5a7-71f11842f6ce.png)

![image](https://user-images.githubusercontent.com/85630559/233854322-1ffcb57c-6274-4d3e-ab45-7455bf8e46cb.png)

<H2> - Function block user

![image](https://user-images.githubusercontent.com/85630559/233854390-a8bb0a68-8825-438b-a025-43c7c34eb8fe.png)

<H2> - Function all users block

![image](https://user-images.githubusercontent.com/85630559/233854415-573aa587-8d50-4fb0-a9bc-6264020cb4fd.png)

<H2> - Function unblock users

![image](https://user-images.githubusercontent.com/85630559/233854492-c29d8834-793a-40e3-9a39-6c64e9289c7e.png)

![image](https://user-images.githubusercontent.com/85630559/233854507-8070fd91-564d-4597-874a-2725013fc3e9.png)


<H1> Seller controller

<H2> - Function add new product

![image](https://user-images.githubusercontent.com/85630559/233855082-95cd9542-bfb2-4344-a8db-e52f52003d90.png)

<H2> - Function update product

![image](https://user-images.githubusercontent.com/85630559/233855183-f4f89ecb-de9a-45a4-b553-07c7edf4e4fb.png)

<H2> - Function delete product

![image](https://user-images.githubusercontent.com/85630559/233855227-9de8b331-adcb-4a31-9aed-4dec24432890.png)

<H2> - Function all items order

![image](https://user-images.githubusercontent.com/85630559/233855632-b0fe25f1-c5b9-46ab-8c48-860435b4d964.png)

<H2> - Function approve orders by buyer

![image](https://user-images.githubusercontent.com/85630559/233855735-4f6727c6-b0cf-4719-b92b-973f8c8f676c.png)

![image](https://user-images.githubusercontent.com/85630559/233855763-5f7a3c97-03c5-488b-88ee-a64ae53be648.png)


<H1> Buyer controller

<H2> - Function add products into cart

![image](https://user-images.githubusercontent.com/85630559/233904634-372202d4-b3bb-4932-af77-3b70a42915eb.png)

<H2> - Function update quantity product in the cart

![image](https://user-images.githubusercontent.com/85630559/233906240-e717f589-17cb-4556-8eb3-964229a7728e.png)

<H2> - Function delete product in the cart

![image](https://user-images.githubusercontent.com/85630559/233908929-6d85276f-3063-441e-8428-6a1da499d0ee.png)

<H2> - Function get all products in the cart

![image](https://user-images.githubusercontent.com/85630559/233905873-12bc8833-b9ba-4840-b4a2-e9e061280030.png)

<H2> - Function add detail order: consignee

![image](https://user-images.githubusercontent.com/85630559/233914268-df430c1f-b8fa-41e8-9f31-16b6ef7bbeea.png)

<H2> - Function get all orders item of buyer

![image](https://user-images.githubusercontent.com/85630559/233914612-d6155430-56f6-46c8-8496-02ebdedb0b58.png)

![image](https://user-images.githubusercontent.com/85630559/233914744-caf3b1e2-cfec-401f-8a7d-d1e06d64bb06.png)

<H2> - Function add cartItem into order

<H3> Add cartItem with ID=12 and detail order with ID=5 into order

![image](https://user-images.githubusercontent.com/85630559/233915623-1d511c51-f65a-4a3d-9286-a642c5a0f7c6.png)

<H2> - Function get all orders by buyer

![image](https://user-images.githubusercontent.com/85630559/233915770-f6bad6fa-c9e5-467e-875f-868e2368395a.png)

![image](https://user-images.githubusercontent.com/85630559/233915874-dbdf9ca3-4bc5-4196-9adc-5821d095f03a.png)

<H2> - Function Cancel orders if status of order is pending

![image](https://user-images.githubusercontent.com/85630559/233916107-cb992f53-8322-4dad-8928-65ba635f7411.png)

<H2> - Function Update status order item success if status of order is delivered

![image](https://user-images.githubusercontent.com/85630559/233916385-0c2f46a2-080e-44de-be9e-1e6f7e9b9c39.png)

<H2> - Function review product in order success

![image](https://user-images.githubusercontent.com/85630559/233917968-3083703a-2a9a-4fcf-a7c6-24d6db4fddf0.png)

<H2> - Function Feedback of seller for review of buyyer

![image](https://user-images.githubusercontent.com/85630559/233918278-f2d44431-7d45-4009-a1fb-02b2dcb1b191.png)

<H1> Shipper controller

<H2> Function get all items order by status

![image](https://user-images.githubusercontent.com/85630559/233856009-36d09b2f-1456-4976-ae0d-8f934c11fd40.png)

![image](https://user-images.githubusercontent.com/85630559/233857469-e3b75b5f-71ce-42c2-a968-a4b3efa5e565.png)

![image](https://user-images.githubusercontent.com/85630559/233857620-5e8b2a81-d533-42c1-a049-0d1056f051a7.png)

![image](https://user-images.githubusercontent.com/85630559/233857675-3889bfc5-6bd3-4cb1-8546-456265672420.png)

<H2> Update status order

![image](https://user-images.githubusercontent.com/85630559/233857517-f7d22528-a7e9-4b44-8ae6-7cd19e1e32aa.png)

![image](https://user-images.githubusercontent.com/85630559/233857552-5f3afdc2-30dc-4704-b858-37ca6495cae4.png)

![image](https://user-images.githubusercontent.com/85630559/233857580-c723312d-f964-4760-8c0d-1ccb460f52b2.png)

<H1> All user 

<H2> - Function get profile 

![image](https://user-images.githubusercontent.com/85630559/233854691-ffd85751-9b7b-446a-a7cd-a3037376e1a1.png)

<H2> - Function message to users

![image](https://user-images.githubusercontent.com/85630559/233918595-6e811d8e-ef2a-43bf-8609-6840fe994fe0.png)

<H2> - Function get all messages between 2 user

![image](https://user-images.githubusercontent.com/85630559/233918807-cbe9c37e-7b60-42f4-aabf-4389b908265c.png)

<H2> -Function get profile user

![image](https://user-images.githubusercontent.com/85630559/233919393-8f035eb0-2410-4e70-bc15-a39e2517a2f5.png)









