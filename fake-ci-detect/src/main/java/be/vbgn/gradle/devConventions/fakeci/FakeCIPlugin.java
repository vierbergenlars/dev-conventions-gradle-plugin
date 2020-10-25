package be.vbgn.gradle.devConventions.fakeci;

import be.vbgn.gradle.cidetect.provider.CiInformationProvider;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class FakeCIPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        CiInformationProvider.registerProvider(FakeCIProvider.class);
    }
}
