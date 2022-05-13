import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.6.21"
    application
}

group = "org.kotlin.json"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val kotlinVersion = "1.6.21"
    implementation(kotlin("bom", version = kotlinVersion))

    testImplementation(kotlin("test", version = kotlinVersion))
    testImplementation(kotlin("test-junit", version = kotlinVersion))

    implementation("org.openjdk.jmh:jmh-core:1.35")
    kapt("org.openjdk.jmh:jmh-generator-annprocess:1.35")

    // benchmark libraries

    // fastjson2 just use v2, in alibaba's case, version 2 has a huge improvement
    implementation("com.alibaba:fastjson:2.0.2")

    // gson
    implementation("com.google.code.gson:gson:2.9.0")

    // moshi
    implementation("com.squareup.moshi:moshi:1.13.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation("com.squareup.moshi:moshi-adapters:1.13.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")

    // jackson
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.2")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.2.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}