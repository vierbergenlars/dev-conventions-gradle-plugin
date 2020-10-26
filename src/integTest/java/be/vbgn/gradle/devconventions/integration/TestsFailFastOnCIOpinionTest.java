package be.vbgn.gradle.devconventions.integration;

import java.io.IOException;
import org.junit.Test;

public class TestsFailFastOnCIOpinionTest extends AbstractIntegrationTest {

    @Test
    public void opinionConfiguresFailFastOnCi() throws IOException {
        createGradleRunner(integrationTests.resolve("TestsFailFastOnCIOpinion/opinionConfiguresFailFastOnCi"))
                .withArguments("-Dfakeci=true")
                .build();
    }

    @Test
    public void opinionDoesNotConfigureFailFastNotCi() throws IOException {
        createGradleRunner(integrationTests.resolve("TestsFailFastOnCIOpinion/opinionDoesNotConfigureFailFastNotCi"))
                .withArguments("-Dfakeci=false")
                .build();
    }

    @Test
    public void conventionDoesNotConfigureFailFast() throws IOException {
        createGradleRunner(integrationTests.resolve("TestsFailFastOnCIOpinion/conventionDoesNotConfigureFailFast"))
                .withArguments("-Dfakeci=true")
                .build();
    }
}
