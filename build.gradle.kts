plugins {
    java
    id ("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "org.example"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.spongepowered.org/maven")
    maven("https://repo.velocitypowered.com/snapshots/")
    maven("https://libraries.minecraft.net")
    maven("https://jitpack.io")
}

dependencies {
    testCompile("junit", "junit", "4.12")
    implementation("com.github.Minestom:Minestom:-SNAPSHOT")
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "org.example.minestom.Main"
            attributes["Multi-Release"] = true
        }
    }

}