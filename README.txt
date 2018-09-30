****ACCOUNT CRUD OPERATIONS***

Install settings

create maven project in Eclipse (file -> import ->existing maven projects)
Ensure you have Java 8 installed, run as -> run configurations -> libraries -> JRE version check
Ensure maven installed

Now run
mvn clean install
once the build is successful

Run AccountSpringBootApplication

Test the application

Save Account:

method type: post

url :: http://localhost:8080/rest/Account/json

body::

{
	"firstName":"Siva",
	"secondName": "Gopal",
	"accountNumber": "12345"
}

Output:

{
    "message": "Account successfully created"
}


body::

{
	"firstName":"Siva2",
	"secondName": "Gopal2",
	"accountNumber": "123456"
}

Output:

{
    "message": "Account successfully created"
}

Get all accounts:

method type: GET

url :: http://localhost:8080/rest/Account/json

Output:
[
    {
        "id": 1,
        "firstName": "Siva",
        "secondName": "Gopal",
        "accountNumber": "12345"
    },
    {
        "id": 2,
        "firstName": "Siva2",
        "secondName": "Gopal2",
        "accountNumber": "123456"
    }
]

Get Account by id:

method type: GET

url :: http://localhost:8080/rest/Account/json/1

Output:
{
    "id": 1,
    "firstName": "Siva",
    "secondName": "Gopal",
    "accountNumber": "12345"
}

Delete Account:

method type: DELETE

url :: http://localhost:8080/rest/Account/json/1

Output:
{
    "message": "Account successfully deleted"
}

