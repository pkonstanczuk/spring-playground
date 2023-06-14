plugins {
  ExternalCommonPlugins.map { id(it.entry) version it.version }
  BuiltInCommonPlugins.map { id(it.entry) }
}
dependencies {
  basicDependencies
    .asSequence()
    .plus(springWebDependencies)
    .filter { it.type == BuildDependencyType.IMPLEMENTATION }.map { implementation(it.entry) }
    .toSet()
  basicDependencies.plus(springBasicDependencies).plus(springWebDependencies)
    .filter { it.type == BuildDependencyType.TEST_IMPLEMENTATION }.map { testImplementation(it.entry) }
}

tasks.test {
  finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

ktlint {
  disabledRules.set(setOf("no-wildcard-imports"))
}

springBoot {
  buildInfo()
  mainClass.set("pl.qkon.grocery.Main")
}

java {
  sourceSets["main"].java {
    srcDir("build/rest/src/main/kotlin")
  }
}

kotlin {
  jvmToolchain {
    languageVersion.set(JavaLanguageVersion.of(11))
  }
}

tasks.named<Jar>("jar") {
  enabled = false
}

openApiGenerate {
  generatorName.set("kotlin-spring")
  inputSpec.set("$rootDir/contract.yaml")
  configOptions.put("serviceInterface", "True")
  configOptions.put("useTags", "True")
  configOptions.put("useSwaggerUI", "False")
  configOptions.put("exceptionHandler", "False")
  configOptions.put("serializationLibrary", "jackson")
  configOptions.put("enumPropertyNaming", "original")
  configOptions.put("documentationProvider", "none")
  packageName.set("pl.qkon.grocery.generated.rest")
  outputDir.set("$buildDir/generated/openapi")
}

openApiValidate {
  inputSpec.set("$rootDir/contract.yaml")
}

java {
  sourceSets["main"].java {
    srcDir("build/generated/openapi/src/main/kotlin")
  }
}
