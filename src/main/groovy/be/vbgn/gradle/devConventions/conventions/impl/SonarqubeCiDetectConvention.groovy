package be.vbgn.gradle.devConventions.conventions.impl

import be.vbgn.gradle.devConventions.conventions.Convention
import org.gradle.api.Project

class SonarqubeCiDetectConvention implements Convention {
    @Override
    void apply(Project project) {
        project.plugins.withId("org.sonarqube") {
            project.plugins.withId("be.vbgn.ci-detect") {
                if (project.ci.isCi()) {
                    project.sonarqube {
                        properties {
                            if (project.ci.isPullRequest()) {
                                property "sonar.pullrequest.branch", project.ci.branch
                                property "sonar.pullrequest.key", project.ci.pullRequest
                                property "sonar.pullrequest.base", project.ci.pullRequestTargetBranch
                            } else {
                                property "sonar.branch.name", project.ci.branch
                            }
                        }
                    }
                }
            }
        }

    }
}
