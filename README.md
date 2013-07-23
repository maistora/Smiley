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
    |
    /build.xml # Ant script
    /build.properties # Ant script properties. Here the default ivy settings can be overriden.
    /local.build.properties # Ant local script properties (ex. contains things like local path to server).

