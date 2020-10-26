package be.vbgn.gradle.devconventions;

import be.vbgn.gradle.devconventions.conventions.Convention;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class DevConventionsPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        for (Convention convention : ConventionLoader.conventions()) {
            convention.apply(project);
        }
    }
}
