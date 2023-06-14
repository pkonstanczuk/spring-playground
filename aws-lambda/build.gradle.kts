plugins {
  BuiltInCommonPlugins
    .plus(ExternalCommonPlugins)
    .toSet()
    .map { id(it.entry) }
}

dependencies {
  basicDependencies
    .filter { it.type == BuildDependencyType.IMPLEMENTATION }
    .map { implementation(it.entry) }
    .toSet()
  basicDependencies
    .filter { it.type == BuildDependencyType.TEST_IMPLEMENTATION }
    .map { testImplementation(it.entry) }
}

tasks.test {
  finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

ktlint {
  disabledRules.set(setOf("no-wildcard-imports"))
}
