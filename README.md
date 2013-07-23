This is a sample project to show HOW TO:
* Use Ant to build your project.
* Use Ivy to manage your dependencies.
* Use Hibernate JPA's implementation - this includes some relations between the tables in the DB.
* Implement simple logic to display the data from the database.

Here are the begining steps I went through:  

1. In Eclipse create "Java Project". (I will not use the Eclipse's facets because I want everithing to be build from scratch)  
2. Create 2 source folders: "src" and "test".  
3. Make your packages (ex. com.simple.example).   
5. Create your Ant related files: build.xml, build.properties and the local.build.properties.  
6. Create folder /ivy. Add the ivy.xml and ivysettings.xml files to it.  
7. Add the WebContent folder - in it wI will put the web pages and resources.  
8. Add WEB-INF and META-INF directories to WebContent. WEB-INF and META-INF are special directories that are not reachable from outside the Web server if they are in the root of the application. This is because of Web servers follow the Web Container's specification (and in it WEB-INF and META-INF are unreachable from outside). Now as you see my WEB-INF and META-INF are not in the root but are under the WebContent folder. Don't worry. Later on we will use the Ant script to build the application and we will copy these folders to the root of the WAR. WAR is the archived web application that is deployed on the server.  
9. Add plain web.xml (*1) deployment descriptor in the WEB-INF folder.  

This is how the structure should look like in the end (without the "lib" and "bin" folders):  

    /src
    | `-com.simple.example
    |	`-web
    |	| `-business
    |	| `-dto
    |	`-db
    |	  `-dao
    |	  `-entity
    |	  `-manager
    |
    /test
    | `- # same structure as /src com.simple.example
    |
    /lib # contains the libs/dependencies, defined in the ivy.xml
    |
    /ivy
    | `-ivy.xml # describe the dependencies and configurations (ex. compile, war, test)
    | `-ivysettings.xml # describe the resolvers - which repositories to look in to and in what order.
    |
    /WebContent # contains the web pages for the project
    | `-WEB-INF
    |   `-unreachable.jsp
    |   `-web.xml # See https://en.wikipedia.org/wiki/Deployment_descriptor for more information.
    | `-META-INF
    | `-index.jsp
    |
    /build.xml # Ant script
    /build.properties # Ant script properties. Here the default ivy settings can be overriden.
    /local.build.properties # Ant local script properties (ex. contains things like local path to server).


(*1) - plain web.xml 
-----------------------------------------------------------
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="WebApp_ID" version="3.0">
	
	<display-name>Smiley</display-name>
	
	<welcome-file-list>
		<welcome-file>index.jsp</welcome-file>
	</welcome-file-list>

</web-app>
-----------------------------------------------------------


After running the "compile" task in the Ant script:
1. /lib folder will be created.
2. /bin/classes folder will be created. There will be the compiled classes.

After running the "package" task in the Ant script:
1. The compile task will be executed.
2. An WAR will be created and deployed to the ${deploy.dir} which is defined in the local.build.properties.
3. The WAR structure should look like:

    `WEB-INF
    | `classes                  # copied from /bin/classes
    | | `com/simple/example ... # same as the "src"
    | |   
    | `unreachable.jsp
    | `web.xml
    |
    `META-INF
    | `MANIFEST.mf
    |
    `index.jsp


Now when we have the war file we should deploy it. In my case I will use JBoss AS 7 as application server.
I have added new user to JBoss AS 7 using the add-user.sh in the JBoss_AS_7_HOME/bin:

> ./add-user.sh 

>What type of user do you wish to add? 
> a) Management User (mgmt-users.properties) 
> b) Application User (application-users.properties)
>(a): a

>Enter the details of the new user to add.
>Realm (ManagementRealm) : 
>Username : root
>Password : 
>Re-enter Password : 
>The username 'root' is easy to guess
>Are you sure you want to add user 'root' yes/no? yes
>About to add user 'root' for realm 'ManagementRealm'
>Is this correct yes/no? yes
>Added user 'root' to file '/home/maistora/programming/Servers/jboss-as-7.1.0.Final/standalone/configuration/mgmt-users.properties'
>Added user 'root' to file '/home/maistora/programming/Servers/jboss-as-7.1.0.Final/domain/configuration/mgmt-users.properties'

So after this you can start the server and login to the Administration.
There in the "Runtime tab" -> "Deployments" -> "Manage Deployments" -> "Add Content" -> Select your WAR file and follow the steps. After that hit "Enable". Now you can reach your application (ex. http://localhost:8080/my-application).
There are other ways to deploy your application to the server:

1. By changing the configuration of the server so it autodeploys the files which are in JBoss_AS_7_HOME/standalone/deployments/. This could be achieved by changing the deployment-scanner element configuration in the JBoss_AS_7_HOME/standalone/configuration/standalone.xml:
    <deployment-scanner scan-interval="5000" relative-to="jboss.server.base.dir" path="deployments" auto-deploy-zipped="true" auto-deploy-exploded="true"/>

2. By adding your-app-name.war.deploy file in the JBoss_AS_7_HOME/standalone/deployments/. This task could be automated by Ant script. See this one http://stackoverflow.com/questions/7013783/how-to-deploy-a-war-file-in-jboss-as-7 .

For more information on this read the JBoss_AS_7_HOME/standalone/deployments/README.txt

