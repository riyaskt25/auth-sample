# auth-sample

This project contains to sections.

1. Authentication provider API : Authenticates user based on user name and password.
2. Rest APIs for users,posts and comments secured by JWT token received form the Authentication provider in the first step.

There are three users available in the system
1. Username = admin, password = admin@123 , has access to all the three APIs (i.e users,posts and comments).
2. Username = noaccess, password = noaccess@123 , has no access to all the three APIs (i.e users,posts and comments).
3. Username = post, password = post@123 , This post user has only access to the posts APIs.

cURL Commmands for these APIs

1. Admin API cURL =  curl --location --request POST "http://localhost:8080/authenticate" --form "userName=admin" --form "userPassword=admin@123"

Result :
{"result":{"token":"<JWT_TOKEN>"}}

2. Users API cURL = curl --location --request GET "http://localhost:8080/users" --header "Authorization: <JWT_TOKEN>" --data-raw ""

Result : Users details from  https://gorest.co.in/public/v2/posts API will be returned if authrozation is successfull.

3. Post API cURL = curl --location --request GET "http://localhost:8080/posts" --header "Authorization: <JWT_TOKEN>" --data-raw ""

Result : Post details from  https://gorest.co.in/public/v2/posts API will be returned if authrozation is successfull.

4. Comments API URL = curl --location --request GET "http://localhost:8080/comments" --header "Authorization: <JWT_TOKEN>" --data-raw ""

Result : Comments  from  https://gorest.co.in/public/v2/comments API will be returned  if authrozation is successfull.

Note : Please use the <JWT_TOKEN> received in authenticate API resposne for further API calls.

