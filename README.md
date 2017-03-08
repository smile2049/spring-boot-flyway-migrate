##Introduction

Plugins to generate and run Flyway migrations in Spring Boot projects using Hibernate.

##Installation

**Maven**

Add the plugin and Jitpack plugin repository to your POM:

```xml
<project>
  ...
  <plugins>
    ...
    <plugin>
      <groupId>com.github.tom-power.spring-boot-flyway-migrate</groupId>
      <artifactId>maven-plugin</artifactId>
      <version>0.2</version>
    </plugin>
  </plugins>
</project>
```

```xml
<project>
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
Add the plugin, buildscript and Jitpack repository to your build.gradle:

```gradle
apply plugin: 'spring-boot-flyway-migrate-gradle-plugin'
```

```gradle
buildscript {
    dependencies {
        classpath("com.github.tom-power:spring-boot-flyway-migrate-gradle-plugin:0.3")
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

##Configuration

Database configuration for the plugin is picked up from your Spring Boot configuration files, .properties and .yaml files are currently supported. [link](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-application-property-files)

##Running

On command line: 

```bash
mvn com.github.tom-power.spring-boot-flyway-migrate:maven-plugin:goal -Dkey=value
```

##Goals

**generate**: generates Flyway migrations between your project's Hibernate entities and database<br/>
**migrate**: runs your Flyway migrations against your project's database

##Properties

**profile**: if set the goal will use the profile specific properties file for database configuration [link](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-profile-specific-properties), otherwise application.properties/application.yaml will be used

##Notes on goals

Migrations are generated using hbm2ddl.auto update, because of this they *should be reviewed before running against your database*. [link](http://stackoverflow.com/questions/221379/hibernate-hbm2ddl-auto-update-in-production)

Generated migrations won't contain destructive commands. If you want to use these you'll need to add them manually, though please see Flyway documentation about *lack of support for downward migrations* and *maintaining backwards compatibility* between your project and database. [link](http://flywaydb.org/documentation/faq.html#downgrade)

Migrations are written in the format expected by Spring Boot and to the location specified in the project configuration, including where profile and vendor specific directories are specified  [link](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-execute-flyway-database-migrations-on-startup). 

Migrations will be *run against your database automatically* on Spring Boot project startup if you have Flyway as a dependency. [link](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-execute-flyway-database-migrations-on-startup)

##Database support

**Hibernate** https://developer.jboss.org/wiki/SupportedDatabases2<br/>
**Flyway** http://flywaydb.org/documentation/

Dependencies for MySQL, PostgreSQL are included, additional dependencies can be added in your project as needed. [link](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_dependencies_Tag)

##Samples

See "samples" directory.