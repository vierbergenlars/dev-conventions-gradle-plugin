package be.vbgn.gradle.devconventions;

import be.vbgn.gradle.devconventions.conventions.Opinion;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;

public class DevOpinionsPlugin implements Plugin<Project> {

    private static final Logger LOGGER = Logging.getLogger(DevOpinionsPlugin.class);

    @Override
    public void apply(Project project) {
        project.getPluginManager().apply(DevConventionsPlugin.class);
        for (Opinion convention : ConventionLoader.opinions()) {
            if (convention.isEnabled(project)) {
                LOGGER.info("Applying opinion plugin {}", convention.getName());
                convention.apply(project);
            } else {
                LOGGER.info("Opinion plugin {} is disabled", convention.getName());
            }
        }
    }
}
