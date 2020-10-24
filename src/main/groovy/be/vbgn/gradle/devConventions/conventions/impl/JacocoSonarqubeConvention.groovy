package be.vbgn.gradle.devConventions.conventions.impl

import be.vbgn.gradle.devConventions.conventions.Convention
import org.gradle.api.Project

class JacocoSonarqubeConvention implements Convention {
    @Override
    void apply(Project project) {
        project.plugins.withId("jacoco") {
            project.plugins.withId("org.sonarqube") {
                project.tasks.named("sonarqube").configure {
                    dependsOn("jacocoTestReport", "check")
                }
                project.tasks.named("jacocoTestReport").configure {
                    reports {
                        xml.enabled = true
                    }
                }
            }
        }
    }
}
