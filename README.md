# auth-sample

This project contains two types of APIs.

1. Authentication provider API : Authenticates user based on user name and password , Updated to use spring security based authentication.

2. Rest APIs for users,posts and comments secured by JWT token received form the Authentication provider in the first step.

Please find the postman collection file i.e. 'jwt-spring-sec-apis.postman_collection.json' available at the root of the project for these APIs invocation.

Note : Please use the 'token' response parameter value received from authenticate API resposne as Bearer token in users/post/comments APIs for authorization.

