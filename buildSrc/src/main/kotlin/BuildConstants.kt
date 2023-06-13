import org.gradle.kotlin.dsl.version

private const val kotlinVersion = "1.8.22"
private const val junitVersion = "5.9.1"
private const val springBootVersion = "2.7.6"

enum class BuildDependencyType {
  IMPLEMENTATION, TEST_IMPLEMENTATION
}

data class BuildDependency(val entry: String, val type: BuildDependencyType = BuildDependencyType.IMPLEMENTATION)
class BuildPlugin internal constructor(val entry: String, val version: String? = null)

val basicDependencies = setOf(
  BuildDependency("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"),
  BuildDependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1"),
  BuildDependency("org.junit.jupiter:junit-jupiter:$junitVersion", BuildDependencyType.TEST_IMPLEMENTATION),
  BuildDependency("org.mockito.kotlin:mockito-kotlin:4.1.0", BuildDependencyType.TEST_IMPLEMENTATION),
  BuildDependency("org.mockito:mockito-inline:4.11.0", BuildDependencyType.TEST_IMPLEMENTATION),
  BuildDependency("org.jetbrains.kotlin:kotlin-test", BuildDependencyType.TEST_IMPLEMENTATION),
)

val springBasicDependencies = setOf(
  BuildDependency("org.springframework.boot:spring-boot-starter"),
  BuildDependency("org.springframework.boot:spring-boot-starter-actuator"),
  BuildDependency("org.springframework.boot:spring-boot-starter-logging"),
  BuildDependency("org.springframework.boot:spring-boot-starter-test", BuildDependencyType.TEST_IMPLEMENTATION)
)

val springWebDependencies = setOf(
  BuildDependency("org.springframework.boot:spring-boot-starter-web"),
  BuildDependency("com.google.code.findbugs:jsr305:3.0.2"),
  BuildDependency("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml"),
  BuildDependency("com.fasterxml.jackson.dataformat:jackson-dataformat-xml"),
  BuildDependency("jakarta.validation:jakarta.validation-api"),
)


val ExternalCommonPlugins = setOf(
  BuildPlugin("org.jlleitschuh.gradle.ktlint", "10.2.1"),
  BuildPlugin("org.jetbrains.kotlin.jvm", version = kotlinVersion),
  BuildPlugin("io.spring.dependency-management", "1.0.11.RELEASE"),
  BuildPlugin("org.springframework.boot", springBootVersion),
  BuildPlugin("org.openapi.generator", "6.6.0"),
  BuildPlugin("org.jetbrains.kotlin.plugin.spring", "1.8.21")
)

val BuiltInCommonPlugins = setOf(
  BuildPlugin("org.gradle.idea"),
  BuildPlugin("org.gradle.application"),
  BuildPlugin("org.gradle.java"),
  BuildPlugin("org.gradle.jacoco"),
)
