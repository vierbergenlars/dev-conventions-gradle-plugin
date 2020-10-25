package be.vbgn.gradle.devConventions.conventions.impl;

import be.vbgn.gradle.devConventions.conventions.Opinion;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskCollection;
import org.gradle.api.tasks.testing.Test;
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification;
import org.gradle.testing.jacoco.tasks.JacocoReport;

public class AllTestsWithConverageOpinion implements Opinion {

    @Override
    public void apply(Project project) {
        project.getPlugins().withId("base", plugin -> {
            project.getTasks().named("check").configure(check -> {
                check.dependsOn(project.getTasks().withType(Test.class));
            });
        });

        project.getPlugins().withId("java", javaPlugin -> {
            project.getPlugins().withId("jacoco", plugin -> {
                TaskCollection<Test> testTasks = project.getTasks().withType(Test.class);
                project.getTasks().named("jacocoTestReport", JacocoReport.class, jacocoReport -> {
                    jacocoReport.dependsOn(testTasks);
                    jacocoReport.doFirst("Configure execution data", new Action<Task>() {
                        @Override
                        public void execute(Task task) {
                            jacocoReport.executionData(testTasks);
                        }
                    });
                });
                project.getTasks().named("jacocoTestCoverageVerification", JacocoCoverageVerification.class,
                        jacocoCoverageVerification -> {
                            jacocoCoverageVerification.dependsOn(testTasks);
                            jacocoCoverageVerification.doFirst("Configure execution data", new Action<Task>() {
                                @Override
                                public void execute(Task task) {
                                    jacocoCoverageVerification.executionData(testTasks);
                                }
                            });
                        });
            });
        });
    }
}
