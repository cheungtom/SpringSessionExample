It is the end to end setup of Java program and AWS ElastiCache Redis.

For Java program, it is based on Spring Framework 4, spring-session, spring-data-redis.
The program compiled by Maven and JDK 1.8 or above, and tested on Tomcat 9

For local test, just use http://localhost:8080/manager/html Tomcat app to deploy the WAR,
surely you need to setup a local Redis server at your laptop

Remarks: You cannot test your Tomcat app at your laptop locally against AWS ElastiCache Redis, as
AWS ElastiCache Redis can only be accessed by resources (e.g. EC2, EKS, Fargate, Lambda etc) within 
the same VPC of it, or any peering VPC. AWS ElastiCache Redis with no internet-facing publlic endpoint
DNS name for internet access. 

For AWS test, I setup an EC2 instance with Amazon Linux AMI, install the Tomcat, use
http://<ec2-public-dns>/manager/admin to deploy the WAR, surely, you need to create a
ElastiCache Redis (non-cluster mode) at your VPC of your AWS account

1. Java Program reference:
https://examples.javacodegeeks.com/enterprise-java/spring/session/spring-session-tutorial/

Thanks for the author of above link. The code at this GitHub is based from above link,
and just with a few change to make it can be compiled by Maven and run on Tomcat, connect
to AWS ElasiCache Redis, also adding some debugging message. 

The program IP right is surely belongs to author of the above link.

- Git clone the program


- Command line, run: 
   mvn clean install

- Import into Eclipse:
  Command line, run mvn eclipse:eclipse

  Import Maven project into Eclipse


2. AWS ElastiCache Redis setup reference:  
https://docs.aws.amazon.com/AmazonElastiCache/latest/red-ug/Clusters.Create.CON.Redis.html
