# Development conventions plugin

[![CI](https://github.com/vierbergenlars/dev-conventions-gradle-plugin/workflows/CI/badge.svg)](https://github.com/vierbergenlars/dev-conventions-gradle-plugin/actions?query=workflow%3ACI+branch%3Amaster)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=vierbergenlars_dev-conventions-gradle-plugin&metric=alert_status)](https://sonarcloud.io/dashboard?id=vierbergenlars_dev-conventions-gradle-plugin)
[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/be/vbgn/dev-conventions/be.vbgn.dev-conventions.gradle.plugin/maven-metadata.xml.svg?colorB=007ec6&label=be.vbgn.dev-conventions)](https://plugins.gradle.org/plugin/be.vbgn.dev-conventions)
[![Gradle Plugin Portal](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/be/vbgn/dev-conventions/opinion/be.vbgn.dev-conventions.opinion.gradle.plugin/maven-metadata.xml.svg?colorB=007ec6&label=be.vbgn.dev-conventions.opinion)](https://plugins.gradle.org/plugin/be.vbgn.dev-conventions.opinion)

A gradle plugin that automatically applies conventions and/or opinions based on the plugins that are applied to your
project.

## Installation

You can choose to apply only the development conventions, which are simple integrations between different plugins.

```groovy
plugins {
  // See https://plugins.gradle.org/plugin/be.vbgn.dev-conventions for the latest version
  id "be.vbgn.dev-conventions" version "0.4.0"
}
```

You can also choose to apply the development opinions, which are *my* opinions about how some things should be
configured.

```groovy
plugins {
  // See https://plugins.gradle.org/plugin/be.vbgn.dev-conventions.opinion for the latest version
  id "be.vbgn.dev-conventions.opinion" version "0.4.0"
}
```

## What's in the package?

### Conventions

* Integration between `jacoco` and `org.sonarqube` plugins: makes `sonarqube` task depend on all jacoco reports, and
  ensures that jacoco reports are generated in XML, for sonarqube to consume.
* Integration between `org.sonarqube` and `be.vbgn.ci-detect` plugins: Configures sonarqube extension to have the right
  branch name or pull request information.
* The `jacoco` plugin: `jacoco*Report` and `jacoco*CoverageVerification` tasks are created for all tasks of type `Test`
* All `Test` tasks: run tests in parallel, as many as there are processors in the machine.
  * The number of parallel forks is configurable with
    the `be.vbgn.dev-conventions.ParallelTestsConvention.max-parallel-forks` property.
  * When the `be.vbgn.ci-detect` plugin is applied and it detects that it is not running on a CI system, tests are run
    in parallel with half of the available processors. This is done to avoid bogging down a developer's machine too
    much.

### Opinions

* The `org.ajoberstar.reckon` plugin: Is configured with `scopeFromProp()` and `snapshotFromProp()`. Before a tag can be
  created with `reckonTagCreate`, the `check` task must complete succesfully.
* All `Test`-type tasks are configured in fail-fast mode when a CI environment is detected
* The `check` task depends on all tasks of type `Test`
* The `maven-publish` and `be.vbgn.ci-detect` plugins, running on Github Actions and the `GITHUB_TOKEN` environment
  variable is available: Automatically configure a publication repository named `GithubPackages`.

Opinions can be disabled with the `be.vbgn.dev-conventions.disable` property by listing a comma-separated list of
Opinion names to disable.
