package be.vbgn.gradle.devconventions.conventions.impl;

import be.vbgn.gradle.devconventions.conventions.Opinion;
import org.gradle.api.Project;
import org.gradle.api.tasks.testing.Test;
import org.gradle.language.base.plugins.LifecycleBasePlugin;

public class CheckDependsOnAllTestsOpinion implements Opinion {

    @Override
    public void apply(Project project) {
        project.getPlugins().withType(LifecycleBasePlugin.class, plugin -> {
            project.getTasks().named(LifecycleBasePlugin.CHECK_TASK_NAME, check -> {
                check.dependsOn(project.getTasks().withType(Test.class));
            });
        });
    }
}
