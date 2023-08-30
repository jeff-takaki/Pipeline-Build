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
