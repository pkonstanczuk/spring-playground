plugins {
  ExternalCommonPlugins
    .plus(ExternalSpringPlugins)
    .toSet()
    .map { id(it.entry) version it.version apply false }
  BuiltInCommonPlugins.map { id(it.entry) apply true }
}

allprojects {
  group = "pl.qkon"
  version = System.getenv("BUILD_VERSION") ?: "0.0.1"
  tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
      events(
        org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
        org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
        org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
        org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR,
        org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT
      )
    }
  }
  repositories {
    mavenCentral()
  }

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = "11"
    }
  }
}
