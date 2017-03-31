## Introduction

Plugins for managing [Flyway](https://flywaydb.org/) migrations in [Spring Boot](https://projects.spring.io/spring-boot/) projects. 

Includes commands to **generate** [Flyway](https://flywaydb.org/) migrations from changes to your [Hibernate](http://hibernate.org/) entities. 

Any feedback very welcome!

## Installation

**Maven**

Add the plugin and JitPack plugin repository to your POM:

```xml
<project>
  ...
  <plugins>
    ...
    <plugin>
      <groupId>com.github.tom-power.spring-boot-flyway-migrate</groupId>
      <artifactId>maven-plugin</artifactId>
      <version>0.2.2</version>
    </plugin>
  </plugins>
  ...
  <pluginRepositories>
    ...
    <pluginRepository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
    </pluginRepository>
  </pluginRepositories>
</project>
```

**Gradle**

TODO 
<!---
Add the plugin, buildscript and JitPack repository to your build.gradle:

```gradle
apply plugin: 'spring-boot-flyway-migrate-gradle-plugin'
```

```gradle
buildscript {
    dependencies {
        classpath("com.github.tom-power:spring-boot-flyway-migrate-gradle-plugin:0.2.2")
    }
}
```

```gradle
repositories {
    ...
    maven { url "https://jitpack.io" }
}	
```
-->

## Configuration

Configuration for the plugin is picked up from your Spring Boot [configuration files](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-application-property-files), specifically the database configuration and flyway.locations properties.

## Running

**Maven**

On command line: 

```bash
mvn spring-boot-flyway-migrate:goal -Dproperty=value
```

**Gradle**

TODO

<!---
```bash
gradle flyway<Goal> -Dproperty=value
```
--->

## Goals

**generate**: generates Flyway migrations between your project's Hibernate entities and database

**migrate**: runs the Flyway [migrate](https://flywaydb.org/documentation/maven/migrate) command

**validate**: runs the Flyway [validate](https://flywaydb.org/documentation/maven/validate) command

## Properties

**profile**: if set the goal will use the profile specific properties file for database configuration [link](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-profile-specific-properties)

## Examples

**Maven**

```bash
mvn spring-boot-flyway-migrate:generate -Dprofile=dev
```

**Gradle**

TODO

## Notes

Migrations are generated using hbm2ddl.auto update, because of this they *should be reviewed before running against your database*. [link](http://stackoverflow.com/questions/221379/hibernate-hbm2ddl-auto-update-in-production)

Generated migrations won't contain destructive commands. If you want to use these you'll need to add them manually, though please see Flyway documentation about *lack of support for downward migrations* and *maintaining backwards compatibility* between your project and database. [link](http://flywaydb.org/documentation/faq.html#downgrade)

Migrations are generated in the format/location expected by Spring Boot. [link](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-execute-flyway-database-migrations-on-startup)

Because of this generated migrations will be *run against your database automatically* on Spring Boot project startup if you have Flyway as a project dependency. [link](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-execute-flyway-database-migrations-on-startup)

The plugin's commands use your project's compiled classes and files, so please ensure your project is built before running them.

## Database support

**Hibernate** https://developer.jboss.org/wiki/SupportedDatabases2<br/>
**Flyway** http://flywaydb.org/documentation/

Dependencies for MySQL and H2 are included, additional dependencies can be added to your project as needed. [maven](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_dependencies_Tag)

## Links

Please see these repositories for [samples](https://github.com/tom-power/spring-boot-flyway-migrate-samples) and [integration tests](https://github.com/tom-power/spring-boot-flyway-migrate-integration-tests).