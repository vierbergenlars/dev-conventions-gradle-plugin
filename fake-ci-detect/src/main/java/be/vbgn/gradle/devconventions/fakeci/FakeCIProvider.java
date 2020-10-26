package be.vbgn.gradle.devconventions.fakeci;

import be.vbgn.gradle.cidetect.CiInformation;
import be.vbgn.gradle.cidetect.provider.CiInformationProvider;
import javax.annotation.Nullable;
import org.gradle.api.Project;

public class FakeCIProvider implements CiInformationProvider {

    @Override
    public int getPriority() {
        return 10;
    }

    @Nullable
    @Override
    public CiInformation newCiInformation(@Nullable Project project) {
        return new FakeCIInformation();
    }
}
