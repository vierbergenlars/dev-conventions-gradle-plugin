package be.vbgn.gradle.devConventions.conventions.impl


import be.vbgn.gradle.devConventions.conventions.Opinion
import org.gradle.api.Project

class ReckonOpinion implements Opinion {
    @Override
    void apply(Project project) {
        project.plugins.withId("org.ajoberstar.reckon") {
            project.reckon {
                scopeFromProp()
                snapshotFromProp()
            }

            project.plugins.withId("base") {
                project.tasks.named("reckonTagCreate").configure {
                    dependsOn("check")
                }
            }
        }
    }
}
