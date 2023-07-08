plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    `java-library`
    `maven-publish`
}

group = "com.github.nilsen84"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.ow2.asm:asm-tree:9.4")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}