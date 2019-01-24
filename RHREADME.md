## Course

https://app.pluralsight.com/player?course=spring-boot-first-application&author=dan-bunker&name=spring-boot-first-application-m2&clip=6&mode=live

## From scratch to a springboot rest app with angularjs gui and stubbed DB - all to first git commit
### simple instructions:

Creating your first springbok app (second actually)

intellij but Dan is using STS spring tool suite

maven from archetype org.apache.maven.srchetypes maven-archetype-quickstart
groupie com.boot
artifactid das-boot
~/workspace-spring-tool-suite/creating-first-app-with-springboot/dasboot

pom.xml
add
<packaging>jar</packaging>

<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>1.3.1.RELEASE</version>
</parent>
<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>


@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}

add following as using jdk 10 (or 9)

<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.2.11</version>
</dependency>
<dependency>
    <groupId>com.sun.xml.bind</groupId>
    <artifactId>jaxb-core</artifactId>
    <version>2.2.11</version>
</dependency>
<dependency>
    <groupId>com.sun.xml.bind</groupId>
    <artifactId>jaxb-impl</artifactId>
    <version>2.2.11</version>
</dependency>
<dependency>
    <groupId>javax.activation</groupId>
    <artifactId>activation</artifactId>
    <version>1.1.1</version>
</dependency>

@RestController
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "Das Boot, reporting for duty!";
    }
}

boom - app already runs http://localhost:8080/
sweet
BOM Bill Of Materials - groups package versions that work together well
https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples
cd /Users/robinjohnhopkins/workspace-spring-tool-suite/creating-first-app-with-springboot
git clone https://github.com/dlbunker/ps-spring-boot-resources
The angular web client included in this project is based off of the 'angular-bootstrap-starter' located here: https://www.npmjs.com/package/angular-bootstrap-starter
The built angular project is located in 'client/www' and can be copied directly to your Spring Boot app and used as is. If you prefer to build the angular client using Gulp, follow the angular-bootstrap-starter instructions located on their web page or you can view the readme file at 'client/README.md' in this project.

in finder copy all dirs and files under
/Users/robinjohnhopkins/workspace-spring-tool-suite/creating-first-app-with-springboot/ps-spring-boot-resources/client/www
mkdir /Users/robinjohnhopkins/workspace-spring-tool-suite/creating-first-app-with-springboot/dasboot/src/main/resources
mkdir /Users/robinjohnhopkins/workspace-spring-tool-suite/creating-first-app-with-springboot/dasboot/src/main/resources/public
paste files and dirs into area to be setup to serve static content!

in STS refresh project and project RMC maven update project
in intellij synchronise
run App
http://localhost:8080/index.html#/				#at this point the web page is displayed! springboot found the content! sweet

@RequestMapping("api/v1/")
@RestController
public class ShipwreckController {
//    @Autowired
//    private ShipwreckRepository shipwreckRepository;
    @RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
    public List<Shipwreck> list(){
        return ShipwreckStub.list();
    }
    @RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
    public Shipwreck create(@RequestBody Shipwreck shipwreck) {
        return ShipwreckStub.create(shipwreck);
    }
    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
    public Shipwreck get(@PathVariable Long id) {
        return ShipwreckStub.get(id);
    }
    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
    public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
        return ShipwreckStub.update(id, shipwreck);
    }
    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
    public Shipwreck delete(@PathVariable Long id) {
        return ShipwreckStub.delete(id);
    }
}

application.properties placed on class path root



========================


## Next add properties file
application.properties created at .../creating-first-app-with-springboot/dasboot/src/main/resources/application.properties
    logging.level.org.springframework.web=DEBUG
    server.port=8181

reran - it used given port


In intellij edit config and set VM options to
-Dspring.profiles.active=test

this changes run to use src/main/resources/application-test.properties

@ this works commandline - seen by the debug listening for a different port
mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=test"

in google search spring boot common application properties
https://docs.spring.io/spring-boot/docs/1.1.6.RELEASE/reference/html/common-application-properties.html
