package be.vbgn.gradle.devconventions.conventions;

import java.util.Arrays;
import org.gradle.api.Project;

public interface Opinion extends BaseConventionPlugin {

    default boolean isEnabled(Project project) {
        String disableProp = (String) project.findProperty("be.vbgn.dev-conventions.disable");
        if (disableProp == null) {
            return true;
        }
        return Arrays.stream(disableProp.split(",")).noneMatch(name -> getName().equals(name));
    }
}
