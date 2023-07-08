plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.10"
    `java-library`
}a

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.ow2.asm:asm-tree:9.4")
}