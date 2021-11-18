name := "SpringRestAPI"

version := "0.1"

scalaVersion := "2.13.7"

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-parent" % "2.5.6" pomOnly()

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-web" % "2.5.6"

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-security" % "2.5.6"

// https://mvnrepository.com/artifact/org.keycloak/keycloak-spring-boot-starter
libraryDependencies += "org.keycloak" % "keycloak-spring-boot-starter" % "15.0.2"

// https://mvnrepository.com/artifact/org.keycloak.bom/keycloak-adapter-bom
libraryDependencies += "org.keycloak.bom" % "keycloak-adapter-bom" % "15.0.2" pomOnly()

// https://mvnrepository.com/artifact/junit/junit
libraryDependencies += "junit" % "junit" % "4.13.2" % Test

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-test" % "2.5.6" % Test
