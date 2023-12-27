// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}
buildscript {


    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath( "com.android.tools.build:gradle:4.2.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    }

}