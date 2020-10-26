package be.vbgn.gradle.devconventions.integration;

import java.io.IOException;
import org.junit.Test;

public class AllTestsWithCoverageConventionTest extends AbstractIntegrationTest {

    @Test
    public void conventionConfiguresJacoco() throws IOException {
        createGradleRunner(integrationTests.resolve("AllTestsWithCoverageConvention/conventionConfiguresJacoco"))
                .withArguments("jacocoTestReport", "jacocoIntegrationTestReport")
                .build();
    }

    @Test
    public void conventionConfiguresJacocoWithoutJava() throws IOException {
        createGradleRunner(
                integrationTests.resolve("AllTestsWithCoverageConvention/conventionConfiguresJacocoWithoutJava"))
                .withArguments("jacocoTestReport", "jacocoIntegrationTestReport")
                .build();
    }
}
