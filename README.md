# TO-DO Tasks App

## About the application
* It contains REST endpoints to operate with "ToDoTask" entities, which are stored in a Redis DB.
* All REST operations are secured with an implementation of OAuth2.
* The pre-set security data (users, roles, permissions, etc.) are stored in a H2 DB. 
* It eliminates the need of usage of a 3rd party tool (like Postman) to test the REST calls, by integrating in the same app the Swagger toolset.
* The Swagger integration is configured to be secured with OAuth2 as well.

## Pre-requisites
* Java 11
* Docker & docker-compose

## Using the API
1. Build the project with `mvn clean install`
2. Start the application and its dependencies (H2, Redis) with `docker-compose up --build -d`
3. Access the application at `http://localhost:9080/swagger-ui.html#/`
   1. You will be prompted with a login pop-up form. The credentials are `admin/admin1234`.
      ![login](https://github.com/victor-stelea/to-do-tasks-app/blob/master/screenshots/login.png)
4. Even after the login, it is required to ask for "read/write" permissions in order to make any REST operations.
   1. First, click on the "Authorize" button on the right.
      ![authorize](https://github.com/victor-stelea/to-do-tasks-app/blob/master/screenshots/authorize.png)
   2. Check the required permissions.
      ![select-permissions](https://github.com/victor-stelea/to-do-tasks-app/blob/master/screenshots/select-permissions.png)
   3. Grant access for those permissions and just close the new informative pop-up.
      ![grant-access](https://github.com/victor-stelea/to-do-tasks-app/blob/master/screenshots/grant-access.png)
      ![close-info](https://github.com/victor-stelea/to-do-tasks-app/blob/master/screenshots/close-info.png)
5. Now, with all the permissions in place, you can make any operation from the displayed controller:
   1. Insert new TO-DO task(s)
      ![insert-task](https://github.com/victor-stelea/to-do-tasks-app/blob/master/screenshots/insert-task.png)
   2. Find all TO-DO tasks
      ![all-tasks](https://github.com/victor-stelea/to-do-tasks-app/blob/master/screenshots/all-tasks.png)
   3. Find a TO-DO task by its ID
      ![task-id](https://github.com/victor-stelea/to-do-tasks-app/blob/master/screenshots/task-id.png)
   4. Return a 404NotFound when searching for a non-existing TO-DO task
      ![task-404](https://github.com/victor-stelea/to-do-tasks-app/blob/master/screenshots/task-404.png)