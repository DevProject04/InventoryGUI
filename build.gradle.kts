plugins {
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
    `maven-publish`
}

group = properties["apiGroup"]!!
version = properties["apiVersion"]!!

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.16.3-R0.1-SNAPSHOT")
}

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    create<Jar>("sourceJar") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource)
    }

    shadowJar {
        from("sourceJar")
    }
}

publishing {
    publications {
        create<MavenPublication>("InventoryGUI") {
            artifact(tasks["sourceJar"])
            from(components["java"])
        }
    }
}