package be.vbgn.gradle.devConventions;

import be.vbgn.gradle.devConventions.conventions.Convention;
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
