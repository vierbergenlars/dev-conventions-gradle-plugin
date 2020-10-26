package be.vbgn.gradle.devConventions.integration;

import java.io.IOException;
import org.junit.Test;

public class CheckDependsOnAllTestsOpinionTest extends AbstractIntegrationTest {

    @Test
    public void opinionConfiguresCheck() throws IOException {
        createGradleRunner(
                integrationTests.resolve("CheckDependsOnAllTestsOpinion/opinionConfiguresCheck"))
                .withArguments("check")
                .build();
    }

    @Test
    public void opinionDoesNotConfigureCheckWithoutBase() throws IOException {
        createGradleRunner(
                integrationTests.resolve("CheckDependsOnAllTestsOpinion/opinionDoesNotConfigureCheckWithoutBase"))
                .build();
    }

    @Test
    public void conventionDoesNotConfigureCheck() throws IOException {
        createGradleRunner(
                integrationTests.resolve("CheckDependsOnAllTestsOpinion/conventionDoesNotConfigureCheck"))
                .withArguments("check")
                .build();
    }

}
