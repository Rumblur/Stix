import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "by.rumblur"
version = "0.1"

repositories {
    mavenCentral()
    maven { setUrl("https://repo.aikar.co/nexus/content/groups/aikar/") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0")

    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
    implementation("club.minnced:discord-webhooks:0.7.5")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}