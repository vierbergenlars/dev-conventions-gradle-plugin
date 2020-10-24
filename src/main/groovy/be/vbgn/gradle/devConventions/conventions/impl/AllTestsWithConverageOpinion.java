package be.vbgn.gradle.devConventions.conventions.impl;

import be.vbgn.gradle.devConventions.conventions.Opinion;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskCollection;
import org.gradle.api.tasks.testing.Test;
import org.gradle.testing.jacoco.tasks.JacocoReport;

public class AllTestsWithConverageOpinion implements Opinion {

    @Override
    public void apply(Project project) {
        project.getTasks().named("check").configure(check -> {
            check.dependsOn(project.getTasks().withType(Test.class));
        });

        project.getPlugins().withId("jacoco", plugin -> {
            project.getTasks().named("jacocoTestReport", JacocoReport.class, jacocoReport -> {
                TaskCollection<Test> testTasks = project.getTasks().withType(Test.class);
                jacocoReport.executionData(testTasks);
                jacocoReport.dependsOn(testTasks);
            });
        });
    }
}
