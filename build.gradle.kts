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
    eclipse
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    // Gradle 的 扫包是从上至下的，按照当前顺序，则为： 冰糕Luminous的仓库 -> 阿里云的仓库 -> 中央库 -> jcenter
    // YuQ-Mirai 的依赖位于中央库和 jcenter。

    // 这是由 冰糕Luminous 提供的 Maven 仓库镜像。
    maven("https://oss.heavenark.com/repository/MavenPublic/")
    // 这是由 阿里云 提供的 Maven 仓库镜像。
    maven("https://maven.aliyun.com/repository/public")
    // 需要同时启用 中央库 及 jcenter。
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

        from("./") {
            include("build.gradle")
        }
    }
}