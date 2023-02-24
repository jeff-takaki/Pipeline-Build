# Book Management Exercise
This exercise will provide you with the necessary java project to create a pipeline for Cloud Automation/DevOps.  We will use this for our Project 1, 2 and 3.

## Getting Started
To begin using this project:

Step 1: Get the java application from GitHub and open in VSCode
  1. Run git clone <URL from step 1>
  2. Open VS Code
  3. From the File Menu select 'Open Folder'
  4. Select the root folder of your BookManagement repo.  Folder should look something like 'BookManagement-GitHubUserName'

Step 2: Build your java application (creates a WAR file)
  1. Expand the Maven section in the VSCode Explorer
  2. Right click on 'bookmanagement' and select 'Package'

Step 3: Run the java application in Tomcat application server
  1. Expand the Servers section in the VSCode Explorer
  2. Start the Tomcat server (right click and select 'Start')
  4. Expand the 'target' folder in your repository
  5. Right click on 'BookMangement.war' and select 'Run on Server'.
  6. Select Tomcat 9.x if necessary.
  7. Wait for deployment to complete

Step 4: Open the java application
  1. Navigate to http://localhost:8080/BookManagement
