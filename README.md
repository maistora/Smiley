This is a sample project to show HOW TO:
* Use Ant to build your project.
* Use Ivy to manage your dependencies.
* Use Hibernate JPA's implementation - this includes some relations between the tables in the DB.
* Implement simple logic to display the data from the database.

Here are some steps you must go through when starting with this:
1. In Eclipse create "Java Project". (I will not use the Eclipse's facets because I want everithing to be build from scratch)
2. Create 2 source folders: "src" and "test".
3. Make your packages (ex. com.simple.example). 
4. Create folder /lib.
5. Create your Ant related files: build.xml, build.properties and the local.build.properties.
6. Create folder /ivy. Add the ivy.xml and ivysettings.xml files to it.
7. Add the WebContent folder - in it wI will put the web pages and resources.
8. Add WEB-INF and META-INF directories to WebContent. WEB-INF and META-INF are special directories that are not reachable from outside the Web server if they are in the root of the application. This is because of Web servers follow the Web Container's specification (and in it WEB-INF and META-INF are unreachable from outside). Now as you see my WEB-INF and META-INF are not in the root but are under the WebContent folder. Don't worry. Later on we will use the Ant script to build the application and we will copy these folders to the root of the WAR. WAR is the archived web application that is deployed on the server.

This is how the structure should look like in the end:
    /src
    | `-com.simple.example
    |   \
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
    | `-META-INF
    |   `-unreachable.jsp
    | `-index.jsp
    |
    /build.xml # Ant script
    /build.properties # Ant script properties. Here the default ivy settings can be overriden.
    /local.build.properties # Ant local script properties (ex. contains things like local path to server).