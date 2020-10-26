package be.vbgn.gradle.devconventions.integration;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import org.gradle.testkit.runner.BuildResult;
import org.junit.Before;
import org.junit.Test;

public class ReckonOpinionTest extends AbstractIntegrationTest {

    @Before
    public void createGitRepo() throws IOException, InterruptedException {
        executeProcess("git", "init");
        executeProcess("git", "commit", "-m", "Initial commit", "--allow-empty");
    }

    private void executeProcess(String... commands) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(commands)
                .directory(testProjectDir.getRoot())
                .redirectOutput(Redirect.INHERIT)
                .redirectError(Redirect.INHERIT);

        Process process = processBuilder.start();
        while (process.isAlive()) {
            process.waitFor();
        }
        process.destroy();
    }

    @Test
    public void opinionConfiguresReckon() throws IOException {
        createGradleRunner(integrationTests.resolve("ReckonOpinion/opinionConfiguresReckon"))
                .withArguments("reckonTagCreate")
                .build();
    }

    @Test
    public void conventionDoesNotConfigureReckon() throws IOException {
        BuildResult buildResult = createGradleRunner(
                integrationTests.resolve("ReckonOpinion/conventionDoesNotConfigureReckon"))
                .buildAndFail();
        assertTrue(buildResult.getOutput().contains("Must provide a scope supplier."));
    }
}
