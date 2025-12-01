import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
// import org.gradle.api.tasks.testing.logging.TestExceptionFormat
// import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    alias(libs.plugins.spotless)

    application
    // jacoco
    alias(libs.plugins.lombok)
    // alias(libs.plugins.versions)
    // alias(libs.plugins.spotless)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.shadow)
    // alias(libs.plugins.sonarqube)
}

group = "io.hexlet.blog"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("io.hexlet.blog.Application")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot
    implementation(libs.springBootStarterWebmvc)
    implementation(libs.springBootStarterDataJpa)
    implementation(libs.springBootStarterValidation)
    implementation(libs.springBootStarterActuator)
    implementation(libs.springBootStarterSecurity)
    implementation(libs.springBootDevtools)
    implementation(libs.springBootConfigProcessor)

    // OpenAPI
    implementation(libs.springdocOpenapiUi)

    // Utilities
    implementation(libs.jacksonDatabindNullable)
    implementation(libs.commonsLang3)
    implementation(libs.datafaker)
    implementation(libs.instancioJunit)
    implementation(libs.jsonunitAssertj)

    // MapStruct
    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstructProcessor)

    // DB
    runtimeOnly(libs.h2)

    // Tests
    testImplementation(libs.springBootStarterTest)
    testImplementation(libs.springBootStarterWebmvcTest)
    testImplementation(platform(libs.junitBom))
    testImplementation(libs.junitJupiter)
    testRuntimeOnly(libs.junitPlatformLauncher)
}

// Show deprecation warnings to pinpoint source
// tasks.withType<JavaCompile>().configureEach {
//     options.compilerArgs.add("-Xlint:deprecation")
// }

tasks.test {
    testLogging {
        showStandardStreams = true

        // какие события показывать
        events(
            TestLogEvent.FAILED,
            TestLogEvent.PASSED,
            TestLogEvent.SKIPPED,
            TestLogEvent.STANDARD_OUT,
            TestLogEvent.STANDARD_ERROR,
        )

        // формат исключений
        exceptionFormat = TestExceptionFormat.FULL

        // детали
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter()
        }
    }
}

spotless {
    java {
        importOrder()
        removeUnusedImports()
        eclipse().sortMembersEnabled(true)
        formatAnnotations()
        leadingTabsToSpaces(4)
    }
}

// sonar {
//     properties {
//         property("sonar.projectKey", "hexlet-boilerplates_java-package")
//         property("sonar.organization", "hexlet-boilerplates")
//         property("sonar.host.url", "https://sonarcloud.io")
//     }
// }
