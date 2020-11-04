package be.vbgn.gradle.devconventions;

import be.vbgn.gradle.devconventions.conventions.Opinion;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class DevOpinionsPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getPlugins().apply(DevConventionsPlugin.class);
        for (Opinion convention : ConventionLoader.opinions()) {
            convention.apply(project);
        }
    }
}