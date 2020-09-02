buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.github.jengelman.gradle.plugins:shadow:5.2.0")
    }
}

plugins {
    java
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("com.IceCreamQAQ.YuQ:YuQ-Mirai:0.0.6.10")
}

tasks {

    jar {
        finalizedBy(shadowJar)
    }

    shadowJar {
        manifest {
            attributes["Main-Class"] = "wiki.IceCream.yuq.demo.Start"
        }

        from("./"){
            include("build.gradle")
        }
    }
}