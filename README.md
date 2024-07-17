# rewards
 Fetch Backend take home assignment


## Instructions to run the Spring Boot application

1. Clone the repository
2. Go the root folder of the repository
3. Ensure that Docker and Postman are installed
4. Run the command ``docker build -t receipt-processor:latest .`` on the root folder
5. Ensure that above command is ran successfully without any issues
6. Now run the command ``docker run -p 8080:8080 receipt-processor:latest``
7. Go to postman and create two new HTTP requests for processing receipts and getting points for processed receipt
