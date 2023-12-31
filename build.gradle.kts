// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false

    id("org.jetbrains.kotlin.android") version "1.7.22" apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.22" apply false
}

buildscript {
    dependencies {
        classpath(ClassPath.hilt)
        classpath(ClassPath.safeArgs)
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}