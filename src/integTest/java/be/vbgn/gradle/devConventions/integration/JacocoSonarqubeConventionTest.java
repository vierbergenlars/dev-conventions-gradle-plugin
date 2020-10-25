package be.vbgn.gradle.devConventions.integration;

import java.io.IOException;
import org.junit.Test;

public class JacocoSonarqubeConventionTest extends AbstractIntegrationTest {

    @Test
    public void conventionConfiguresJacocoForSonarqube() throws IOException {
        createGradleRunner(integrationTests.resolve("JacocoSonarqubeConvention/conventionConfiguresJacocoForSonarqube"))
                .build();
    }
   
    @Test
    public void conventionConfiguresJacocoForSonarqubeWithoutJava() throws IOException {
        createGradleRunner(
                integrationTests.resolve("JacocoSonarqubeConvention/conventionConfiguresJacocoForSonarqubeWithoutJava"))
                .build();
    }
}
