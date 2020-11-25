package be.vbgn.gradle.devconventions.conventions.impl;

import be.vbgn.gradle.devconventions.conventions.Convention;
import org.gradle.api.Project;
import org.gradle.api.tasks.testing.Test;

public class ParallelTestsConvention implements Convention {

    @Override
    public void apply(Project project) {
        project.getTasks().withType(Test.class).configureEach(this::configureParallelTest);
    }

    private void configureParallelTest(Test testTask) {
        testTask.setMaxParallelForks(Runtime.getRuntime().availableProcessors());
    }
}
