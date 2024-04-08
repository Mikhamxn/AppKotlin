buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(libs.google.services)
        classpath ("com.google.gms:google-services:4.4.1")
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}
