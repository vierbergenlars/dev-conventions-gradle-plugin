package be.vbgn.gradle.devconventions.conventions.impl

import be.vbgn.gradle.devconventions.conventions.Opinion
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin

class AutomaticallyPublishToGithubPackagesOpinion implements Opinion {
    private static final Logger LOGGER = Logging.getLogger(AutomaticallyPublishToGithubPackagesOpinion.class)

    @Override
    void apply(Project project) {
        project.plugins.withType(MavenPublishPlugin.class) {
            project.getPlugins().withId("be.vbgn.ci-detect") {
                project.extensions.configure(PublishingExtension.class) {
                    if (project.ci.isCi() && project.ci.platform == "GithubActions") {
                        configureRepository(it)
                    }
                }
            }
        }
    }

    private void configureRepository(PublishingExtension publishingExtension) {
        String githubActor = System.getenv("GITHUB_ACTOR")
        String githubToken = System.getenv("GITHUB_TOKEN")
        String githubRepo = System.getenv("GITHUB_REPOSITORY")

        LOGGER.debug("GITHUB_REPOSITORY={}; GITHUB_ACTOR={}", githubRepo, githubActor)

        if (!githubRepo || !githubActor) {
            return;
        }

        if (!githubToken) {
            LOGGER.warn("Github Actions CI was detected, but GITHUB_TOKEN is missing. Automatic configuration of publishing is not possible.")
            return;
        }

        publishingExtension.repositories {
            maven {
                name = "GithubPackages"
                url = "https://maven.pkg.github.com/${githubRepo.toLowerCase()}"
                credentials {
                    username = githubActor
                    password = githubToken
                }
            }
        }
    }
}
