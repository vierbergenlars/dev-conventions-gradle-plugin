package be.vbgn.gradle.devconventions.integration;

import java.io.IOException;
import org.junit.Test;

public class ParallelTestsConventionTest extends AbstractIntegrationTest {

    @Test
    public void conventionConfigures() throws IOException {
        createGradleRunner(integrationTests.resolve("ParallelTestsConvention/conventionConfigures")).build();
    }

    @Test
    public void propertyConfigures() throws IOException {
        createGradleRunner(integrationTests.resolve("ParallelTestsConvention/propertyConfigures")).build();
    }

    @Test
    public void withCi() throws IOException {
        createGradleRunner(integrationTests.resolve("ParallelTestsConvention/withCi"))
                .withArguments("-Dfakeci=true")
                .build();
    }

    @Test
    public void withoutCi() throws IOException {
        createGradleRunner(integrationTests.resolve("ParallelTestsConvention/withoutCi"))
                .withArguments("-Dfakeci=false")
                .build();
    }
}
