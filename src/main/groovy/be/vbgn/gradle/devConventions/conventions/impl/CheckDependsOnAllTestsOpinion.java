package be.vbgn.gradle.devConventions.conventions.impl;

import be.vbgn.gradle.devConventions.conventions.Opinion;
import org.gradle.api.Project;
import org.gradle.api.tasks.testing.Test;

public class CheckDependsOnAllTestsOpinion implements Opinion {

    @Override
    public void apply(Project project) {
        project.getPlugins().withId("base", plugin -> {
            project.getTasks().named("check", check -> {
                check.dependsOn(project.getTasks().withType(Test.class));
            });
        });
    }
}
