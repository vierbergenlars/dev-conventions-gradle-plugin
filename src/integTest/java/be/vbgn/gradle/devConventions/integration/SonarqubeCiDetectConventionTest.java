package be.vbgn.gradle.devConventions.integration;

import java.io.IOException;
import org.junit.Test;

public class SonarqubeCiDetectConventionTest extends AbstractIntegrationTest {

    @Test
    public void conventionConfiguresSonarQube() throws IOException {
        createGradleRunner(integrationTests.resolve("SonarqubeCiDetectConvention/conventionConfiguresSonarQube"))
                .withArguments("-Dfakeci=true", "-Dfakeci.branch=testbranch123")
                .build();
    }

    @Test
    public void conventionConfiguresSonarQubeForPr() throws IOException {
        createGradleRunner(integrationTests.resolve("SonarqubeCiDetectConvention/conventionConfiguresSonarQubeForPr"))
                .withArguments("-Dfakeci=true", "-Dfakeci.pr=12", "-Dfakeci.pr.target=testbranch123",
                        "-Dfakeci.branch=main123")
                .build();
    }
}
