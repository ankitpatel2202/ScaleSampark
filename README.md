# ScaleSampark
In today’s fast-paced world, everything from noodles to communication is expected to be instant. An instant messaging system helps organizations to facilitate secure communication amongst its employees. As part of this project, we developed “ScaleSampark”, the server-side connection manager for an instant messaging system.

In this project, We have used a Service Oriented Architecture (SOA) to built RESTful services so that any other service in the application can consume those services.

To achieve that, we have created a Spring Boot Application to built RESTful endpoints, which is a Rapid Application Development tool based on JAVA. So we have created two RESTful endpoints as:

  1. For account management:  http://localhost:8080/api/account/
  2. For message management:  http://localhost:8080/api/message/ 

Build Tool: gradle

Java Version : 1.8.0_181

In This Application, We have used Hibernate for Data Persistancy, and we used JPA Architection which is nothing but a DAO pattern which works on top of Hibernate to abstract out complex database operations.

For Encryption/Decryption of massages, we used AES – Advanced Encryption Standard which is a symmetric encryption algorithm. AES encryption is used by U.S. for securing sensitive but unclassified material, so we can say it is enough secure.
The Private Key can be configured on application.properties file as: 
 
    scalesamparl.security.privatekey="scalesampark"
    
For Database, as of now we haves supported ms-sql and postgess databases. this can be configured on application.properties as: 

    spring.datasource.url=jdbc:sqlserver://127.0.0.1:1433;databaseName=scalesampark
    spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
    spring.datasource.username=sa
    spring.datasource.password=nexus@123
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect

Also we used LOMBOK (3rd party plugin) to remove boilerplate code in the POJOs. It provides rich annotations for setter/getter, constructor etc..

  You need to Enable Annotation Processing on IntelliJ IDEA

        > Settings > Build, Execution, Deployment > Compiler > Annotation Processors
        
  ref: https://www.baeldung.com/lombok-ide   

For Documentation and testing the endpoints, We have used Swagger 2 for a Spring REST web service, using the Springfox implementation of the Swagger 2 specification. (for more info of swagger, goto: https://swagger.io/ )

  To use Swagger UI: 
  
        1. run the appllication
        2. Now you can test it in your browser by visiting http://localhost:8080/swagger-ui.html
   


