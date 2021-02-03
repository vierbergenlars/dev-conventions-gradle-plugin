package be.vbgn.gradle.devconventions;

import be.vbgn.gradle.devconventions.conventions.Convention;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;

public class DevConventionsPlugin implements Plugin<Project> {

    private static final Logger LOGGER = Logging.getLogger(DevConventionsPlugin.class);

    @Override
    public void apply(Project project) {
        for (Convention convention : ConventionLoader.conventions()) {
            LOGGER.info("Applying convention plugin {}", convention.getName());
            convention.apply(project);
        }
    }
}
