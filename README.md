# Book Management Project
This project practices building a jenkins pipeline to deploy AWS cloud resources that
includes an EC2 instance running a tomcat webserver that hosts a book management application
that accesses an RDS database. 


## Project Screenshots
The infrastructure for this project is not currently running but included
below are some images of portions of the build in action.

- An example of the pipeline that deploys specificed infrastructure 
using cloudformation templates. Leveraging ansible, tests are performed 
after resources are built to check availibility and stop building if a host 
is not available. 
![alt text](https://github.com/jtakaki-matc/Pipeline-Build/blob/main/images/project2-step3-infrastructure-test.jpg)

- Data added through RDS database will populate application.
![alt text](https://github.com/jtakaki-matc/Pipeline-Build/blob/main/images/project2-step2-book-management-staging.jpg)

- Example of successful web stack and database build in aws console. Templates can be uploaded through the console or deployed via command line. We are able to deploy 
multiple stacks at a time with a bash script.
![alt text](https://github.com/jtakaki-matc/Pipeline-Build/blob/main/images/project2-step1-web-stack-output-dev.jpg)

- Example of pipeline that includes Blue Green resource deployment
![alt text](https://github.com/jtakaki-matc/Pipeline-Build/blob/main/images/project3-step1-pipeline.jpg)

- Infrastructure Diagram 
![alt text](https://github.com/jtakaki-matc/Pipeline-Build/blob/main/images/Book%20Management%20Inf.png)
