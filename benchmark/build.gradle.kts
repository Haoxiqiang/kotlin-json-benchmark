import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("kapt") version "1.7.10"
    application
}

group = "org.kotlin.json"
version = "1.1"

repositories {
    mavenCentral()
}

dependencies {
    val kotlinVersion = "1.7.10"
    implementation(kotlin("bom", version = kotlinVersion))
    testImplementation(kotlin("test", version = kotlinVersion))
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

    implementation("org.openjdk.jmh:jmh-core:1.35")
    kapt("org.openjdk.jmh:jmh-generator-annprocess:1.35")

    // benchmark libraries
    implementation("org.json:json:20220320")

    // fastjson2 just use v2, in alibaba's case, version 2 has a huge improvement
    implementation("com.alibaba:fastjson:2.0.7")

    // gson
    implementation("com.google.code.gson:gson:2.9.0")

    // moshi
    implementation("com.squareup.moshi:moshi:1.13.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation("com.squareup.moshi:moshi-adapters:1.13.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")

    // jackson
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.3")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.apiVersion = "1.7"
    kotlinOptions.languageVersion = "1.7"
}

application {
    mainClass.set("MainKt")
}