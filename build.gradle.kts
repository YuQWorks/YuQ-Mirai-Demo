plugins {
    java
    id("com.github.johnrengelman.shadow") version "5.2.0"
    eclipse
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    // Gradle 的 扫包是从上至下的。
    // YuQ-Mirai 的依赖位于中央库和 jcenter。
    // Dev 包均位于 IceCream 的 Maven 仓库。
//    mavenCentral()
    mavenLocal()
    maven("https://maven.IceCreamQAQ.com/repository/maven-public/")
}

dependencies {
    implementation("com.IceCreamQAQ.YuQ:YuQ-Mirai:0.1.0.0-DEV24")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    jar {
        finalizedBy(shadowJar)
    }

    shadowJar {
        manifest {
            attributes["Main-Class"] = "wiki.IceCream.yuq.demo.Start"
        }

        from("./") {
            include("build.gradle")
        }
    }
}