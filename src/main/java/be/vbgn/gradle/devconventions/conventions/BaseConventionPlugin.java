package be.vbgn.gradle.devconventions.conventions;

import org.gradle.api.Project;

public interface BaseConventionPlugin {

    void apply(Project project);

    default String getName() {
        return getClass().getSimpleName();
    }
}
