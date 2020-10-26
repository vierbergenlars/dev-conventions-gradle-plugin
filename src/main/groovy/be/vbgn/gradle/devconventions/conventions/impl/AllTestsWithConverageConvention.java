package be.vbgn.gradle.devconventions.conventions.impl;

import be.vbgn.gradle.devconventions.conventions.Convention;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskCollection;
import org.gradle.api.tasks.testing.Test;
import org.gradle.language.base.plugins.LifecycleBasePlugin;
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification;
import org.gradle.testing.jacoco.tasks.JacocoReport;
import org.gradle.util.GUtil;

public class AllTestsWithConverageConvention implements Convention {

    @Override
    public void apply(Project project) {
        project.getPlugins().withId("jacoco", plugin -> {
            TaskCollection<Test> testTasks = project.getTasks().withType(Test.class);

            testTasks.all(testTask -> {
                // Do not configure test task with jacoco, as that is already done by the jacoco plugin itself
                if (project.getPlugins().hasPlugin("java") && testTask.getName().equals("test")) {
                    return;
                }
                String taskName = GUtil.toCamelCase(testTask.getName());
                project.getTasks().create("jacoco" + taskName + "Report", JacocoReport.class, jacocoReport -> {
                    jacocoReport.setGroup(LifecycleBasePlugin.VERIFICATION_GROUP);
                    jacocoReport.dependsOn(testTask);
                    jacocoReport.executionData(testTask);
                });
                project.getTasks()
                        .create("jacoco" + taskName + "CoverageVerification", JacocoCoverageVerification.class,
                                coverageVerification -> {
                                    coverageVerification.setGroup(LifecycleBasePlugin.VERIFICATION_GROUP);
                                    coverageVerification.dependsOn(testTask);
                                    coverageVerification.executionData(testTask);
                                });
            });
        });

    }
}
