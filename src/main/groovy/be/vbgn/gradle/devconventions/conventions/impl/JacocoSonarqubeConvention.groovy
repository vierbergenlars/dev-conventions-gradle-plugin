package be.vbgn.gradle.devconventions.conventions.impl

import be.vbgn.gradle.devconventions.conventions.Convention
import org.gradle.api.Project
import org.gradle.testing.jacoco.tasks.JacocoReport

class JacocoSonarqubeConvention implements Convention {
    @Override
    void apply(Project project) {
        project.plugins.withId("jacoco") {
            project.plugins.withId("org.sonarqube") {
                project.tasks.named("sonarqube").configure {
                    dependsOn(project.tasks.withType(JacocoReport.class))
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
