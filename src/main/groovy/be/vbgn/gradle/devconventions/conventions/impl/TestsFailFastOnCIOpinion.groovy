package be.vbgn.gradle.devconventions.conventions.impl

import be.vbgn.gradle.devconventions.conventions.Opinion
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class TestsFailFastOnCIOpinion implements Opinion {
    @Override
    void apply(Project project) {
        project.plugins.withId("be.vbgn.ci-detect") {
            if (project.ci.isCi()) {
                project.tasks.withType(Test.class).configureEach {
                    failFast = true
                }
            }
        }
    }
}
