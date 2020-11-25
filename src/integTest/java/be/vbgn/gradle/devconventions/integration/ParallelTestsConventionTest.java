package be.vbgn.gradle.devconventions.integration;

import java.io.IOException;
import org.junit.Test;

public class ParallelTestsConventionTest extends AbstractIntegrationTest {

    @Test
    public void conventionConfigures() throws IOException {
        createGradleRunner(integrationTests.resolve("ParallelTestsConvention/conventionConfigures")).build();
    }

}
