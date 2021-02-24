package be.vbgn.gradle.devconventions.conventions.impl

import be.vbgn.gradle.devconventions.conventions.Convention
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.testing.Test

class ParallelTestsConvention implements Convention {

    private static final Logger LOGGER = Logging.getLogger(ParallelTestsConvention.class);
    private static final String NUM_PARALLEL_PROP = "be.vbgn.dev-conventions.ParallelTestsConvention.max-parallel-forks";

    @Override
    void apply(Project project) {
        project.getTasks().withType(Test.class).configureEach {
            configureParallelTest(project, it)
        };
    }

    private void configureParallelTest(Project project, Test testTask) {
        int maxParallel = Runtime.getRuntime().availableProcessors();
        if (project.hasProperty(NUM_PARALLEL_PROP)) {
            maxParallel = Integer.parseInt(project.property(NUM_PARALLEL_PROP).toString());
        }
        testTask.setMaxParallelForks(maxParallel);

        // If no maximum is configured explicitly, try to detect if we are running on a CI system.
        if (!project.hasProperty(NUM_PARALLEL_PROP)) {
            project.plugins.withId("be.vbgn.ci-detect") {
                if (!project.ci.isCi()) {
                    int maxParallel2 = Math.max(1, (int) (Runtime.getRuntime().availableProcessors() / 2));
                    LOGGER.info("Limiting parallel tests to {} because we are not running on a CI system and the property {} is not configured", maxParallel2, NUM_PARALLEL_PROP);
                    testTask.setMaxParallelForks(maxParallel2);
                }
            }
        }
    }
}
