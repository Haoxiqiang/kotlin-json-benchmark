import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("kapt") version "1.9.25"
    application
}

group = "org.kotlin.json"
version = "1.5"

repositories {
    mavenCentral()
}

dependencies {
    val kotlinVersion = "1.9.25"
    implementation(kotlin("bom", version = kotlinVersion))
    testImplementation(kotlin("test", version = kotlinVersion))

    implementation("org.openjdk.jmh:jmh-core:1.37")
    kapt("org.openjdk.jmh:jmh-generator-annprocess:1.37")

    // benchmark libraries
    implementation("org.json:json:20231013")

    // fastjson2 just use v2, in alibaba's case, version 2 has a huge improvement
    implementation("com.alibaba:fastjson:2.0.21")

    // gson
    implementation("com.google.code.gson:gson:2.10")

    // moshi
    val moshiVersion = "1.15.2"
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation("com.squareup.moshi:moshi-adapters:$moshiVersion")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    // jackson
    val jacksonVersion = "2.18.2"
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    // dsl-json
    implementation("com.dslplatform:dsl-json:2.0.2")
    kapt("com.dslplatform:dsl-json:2.0.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
    kotlinOptions.apiVersion = "1.8"
    kotlinOptions.languageVersion = "1.8"
}

application {
    mainClass.set("MainKt")
}