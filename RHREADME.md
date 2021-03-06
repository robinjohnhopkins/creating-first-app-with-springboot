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


== h2 db
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-jpa</artifactId>
      </dependency>

      <dependency>
          <groupId>com.h2database</groupId>
          <artifactId>h2</artifactId>
      </dependency>

#add following properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2

rerun app with NO vm arguments

then in browser goto http://localhost:8080/h2
and you will see a default h2 db login page
you can login and see whats what!

## change name of db

spring.datasource.url=jdbc:h2:file:~/dasboot
spring.datasource.username=sa
spring.dataspource.password=
spring.datasource.driver-class-name=org.h2.Driver

now file is ~/dasboot rather than the default ~/test


# added some db pooling properties
spring.datasource.max-active=10
spring.datasource.max-idle=8
spring.datasource.min-idle=8
spring.datasource.time-between-eviction-runs-millis=1
spring.datasource.min-evictable-idle-time-millis=1000
spring.datasource.max-wait=10000

flyway.baseline-on-migrate=true
spring.jpa.hibernate.ddl-auto=false;


# add alt db
      <dependency>
          <groupId>org.flywaydb</groupId>
          <artifactId>flyway-core</artifactId>
      </dependency>

add .../dasboot/src/main/resources/db/migration/V2__create_shipwreck.sql

CREATE TABLE SHIPWRECK(
    ID INT AUTO_INCREMENT,
    NAME VARCHAR(255),
    DESCRIPTION VARCHAR(2000),
    CONDITION VARCHAR(255),
    DEPTH INT,
    LATITUDE DOUBLE,
    LONGITUDE DOUBLE,
    YEAR_DISCOVERED INT
    );

NB now you still have h2 named file db that persists but a created db table

## add second db bean

add these properties

datasource.flyway.url=jdbc:h2:file:~/dasboot
datasource.flyway.username=sa
datasource.flyway.password=
datasource.flyway.driver-class-name=org.h2.Driver

.../creating-first-app-with-springboot/dasboot/src/main/java/com/boot/config/PersistenceConfiguration.java
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties(prefix = "datasource.flyway")
    @FlywayDataSource
    public DataSource flywayDataSource(){
        return DataSourceBuilder.create().build();
    }
}

both beans are instantiated at boot!

## add model to db

the model.Shipwreck class before had no annotations - add the following

        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;

        @Entity
        public class Shipwreck {

            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            Long id;
            String name;
            String description;
            String condition;
            Integer depth;
            Double latitude;
            Double longitude;
            Integer yearDiscovered;

## add interface
        package com.boot.repository;

        import com.boot.model.Shipwreck;
        import org.springframework.data.jpa.repository.JpaRepository;

        public interface ShipwreckRepository extends JpaRepository<Shipwreck, Long> {
        }
NB at this point the app can be run without error

## make app use db - update ShipwreckController
    package com.boot.controller;

    import com.boot.model.Shipwreck;
    import com.boot.repository.ShipwreckRepository;
    import org.springframework.beans.BeanUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import java.util.List;

    @RequestMapping("api/v1/")
    @RestController
    public class ShipwreckController {
        @Autowired
        private ShipwreckRepository shipwreckRepository;
        @RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
        public List<Shipwreck> list() {
            return shipwreckRepository.findAll();
        }
        @RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
        public Shipwreck create(@RequestBody Shipwreck shipwreck) {
            return shipwreckRepository.saveAndFlush(shipwreck);
        }
        @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
        public Shipwreck get(@PathVariable Long id) {
            return shipwreckRepository.findOne(id);
        }
        @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
        public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
            Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
            BeanUtils.copyProperties(shipwreck, existingShipwreck);
            return shipwreckRepository.saveAndFlush(existingShipwreck);
        }
        @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
        public Shipwreck delete(@PathVariable Long id) {
            Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
            shipwreckRepository.delete(existingShipwreck);
            return existingShipwreck;
        }
    }

NB now app is fully functional using CRUD with db.

## added some mockito and mock testing

        package com.boot;

        import com.boot.controller.ShipwreckController;
        import com.boot.model.Shipwreck;
        import com.boot.repository.ShipwreckRepository;
        import org.junit.Before;
        import org.junit.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;
        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.Matchers.*;
        import static org.junit.Assert.assertEquals;
        import static org.mockito.Mockito.times;
        import static org.mockito.Mockito.verify;
        import static org.mockito.Mockito.when;

        public class ShipwreckControllerTest {

            @InjectMocks
            private ShipwreckController sc;

            @Mock
            ShipwreckRepository shipwreckRepository;

            @Before
            public void init(){
                MockitoAnnotations.initMocks(this);
            }

            @Test
            public void testShipwreckedGet(){
                Shipwreck sw = new Shipwreck();
                sw.setId(1L);

                when(shipwreckRepository.findOne(1L)).thenReturn(sw);

                Shipwreck wreck = sc.get(1L);
                Shipwreck wreck1b = sc.get(1L);
                Shipwreck wreck2 = sc.get(2L);

                //verify(shipwreckRepository).findOne(1L); this verifies shipwreckRepository.findOne(1L) is called once
                // the following verifies it is called twice
                verify(shipwreckRepository, times(2)).findOne(1L);

                assertEquals("check id of first wreck", 1L, wreck.getId().longValue());
                //add hamcrest test
                assertThat(wreck.getId(), is(1L));
            }
        }

## added integration test for persistent db

        package com.boot;

        import com.boot.model.Shipwreck;
        import com.boot.repository.ShipwreckRepository;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.SpringApplicationConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

        import java.util.List;

        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.Matchers.*;

        @RunWith(SpringJUnit4ClassRunner.class)
        @SpringApplicationConfiguration(App.class)
        public class ShipwreckRepositoryIntegrationTest {

            @Autowired
            private ShipwreckRepository shipwreckRepository;

            @Test
            public void testFindAll(){
                List<Shipwreck> wrecks = shipwreckRepository.findAll();
                System.out.println("integration test number of wrecks is " + wrecks.size());

                assertThat(wrecks.size(), is(greaterThanOrEqualTo(0)));
            }

        }

## add ShipwreckControllerWebIntegrationTest using test1 db and http rest calls
        package com.boot;

        import com.fasterxml.jackson.databind.JsonNode;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.boot.test.SpringApplicationConfiguration;
        import org.springframework.boot.test.TestRestTemplate;
        import org.springframework.boot.test.WebIntegrationTest;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
        import org.springframework.web.client.RestTemplate;
        import java.io.IOException;

        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.hamcrest.Matchers.equalTo;
        import static org.hamcrest.Matchers.is;

        //@WebIntegrationTest is using default profile. See below on how to set test profile
        // which means spring uses application-test.properties not application.properties

        @RunWith(SpringJUnit4ClassRunner.class)
        @SpringApplicationConfiguration(App.class)
        @WebIntegrationTest({"spring.profiles.active=test"})
        public class ShipwreckControllerWebIntegrationTest {
            @Test
            public void testListAll() throws IOException {
                RestTemplate restTemplate = new TestRestTemplate();
                ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/shipwrecks", String.class);

                assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode responseJson = objectMapper.readTree(response.getBody());

                assertThat(responseJson.isMissingNode(), is(false));
                assertThat(responseJson.toString(), equalTo("[]"));
            }
        }
