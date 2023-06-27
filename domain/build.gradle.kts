plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    //id("org.jetbrains.kotlin.kapt")
    kotlin("kapt")
}

dependencies {
    customImplementation(Dependencies.domain)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}