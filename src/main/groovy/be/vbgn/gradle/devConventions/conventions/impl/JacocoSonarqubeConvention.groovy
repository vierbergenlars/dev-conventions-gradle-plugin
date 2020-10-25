package be.vbgn.gradle.devConventions.conventions.impl

import be.vbgn.gradle.devConventions.conventions.Convention
import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport

class JacocoSonarqubeConvention implements Convention {
    @Override
    void apply(Project project) {
        project.plugins.withId("jacoco") {
            project.plugins.withId("org.sonarqube") {
                project.tasks.named("sonarqube").configure {
                    dependsOn(project.tasks.withType(JacocoReport.class), "check")
                }
                project.tasks.withType(JacocoReport.class).configureEach {
                    reports {
                        xml.enabled = true
                    }
                }
            }
        }
    }
}
