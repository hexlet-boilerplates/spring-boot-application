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
    alias(libs.plugins.versions)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.shadow)
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

// Spring Boot приносит свою версию junit-bom и перебивает нашу платформу.
// Переопределяем управляемое свойство значением из каталога, иначе артефакты
// junit-platform разъезжаются с junit-jupiter.
ext["junit-jupiter.version"] = libs.versions.junit.bom.get()

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
            // Версия берётся из каталога, иначе подставится дефолт Gradle
            // и агрегат junit-jupiter уедет ниже остальных артефактов junit
            useJUnitJupiter(libs.versions.junit.bom)
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

