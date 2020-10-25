package be.vbgn.gradle.devConventions.integration;

import java.io.IOException;
import org.junit.Test;

public class AllTestsWithCoverageOpinionTest extends AbstractIntegrationTest {

    @Test
    public void opinionConfiguresJacoco() throws IOException {
        createGradleRunner(integrationTests.resolve("AllTestsWithCoverageOpinion/opinionConfiguresJacoco"))
                .withArguments("jacocoTestReport")
                .build();
    }

    @Test
    public void conventionDoesNotConfigureJacoco() throws IOException {
        createGradleRunner(integrationTests.resolve("AllTestsWithCoverageOpinion/conventionDoesNotConfigureJacoco"))
                .withArguments("jacocoTestReport")
                .build();
    }
}
