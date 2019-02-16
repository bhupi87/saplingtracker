Project Manager

Fonctionalitées
This application allows the user to create projects. Each project contains tasks to perform. Each task is assigned to a user.

The database
The database used is H2 Database (in memory database). The Fakedata.java class in the config.databaseseed package is used to initialize, at the start of the application, the database with random recordings using DIUS / javafaker ( https://github.com/DiUS/java -faker ).

The users
Users have different functions depending on their roles. A collaborator has the right to confirm his tasks and see the projects he contributes. A manager can create projects, tasks and assign them to collaborators, moreover he can approve the tasks realized by the collaborator. An administrator has the right to read the messages sent to the site and to assign the role manager to a collaborator.

Note on roles
It is assumed that the administrator is a manager and collaborator. Similarly a manager is a collaborator. (The opposite is not true.)

Development tools
Java 8
gradle
Spring boot 1.5.3.RELEASE
H2 database
Sring data jpa
Spring MVC
thymeleaf
Spring security
Installation
Download or clone the source code from git repository
git clone http://github.com/salah3x/project-manager
Navigate to the downloaded folder

cd [Path to project-manager]
Start the application:
./gradlew bootRun
or run the jar directly

./gradlew bootRepackage
java -jar build/libs/project-manager-0.0.1-SNAPSHOT.jar
Go to http: // localhost: 8080 /
Note 1: The ./gradlew command requires an internet connection and may take some time to download dependencies from the application.

Note 2: To start the application under windows use the file gradlew.bat instead of gradlew.

project