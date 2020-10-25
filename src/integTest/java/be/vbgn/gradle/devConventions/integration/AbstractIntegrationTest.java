package be.vbgn.gradle.devConventions.integration;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public abstract class AbstractIntegrationTest {

    protected final Path integrationTests;

    {
        try {
            integrationTests = Paths.get(AbstractIntegrationTest.class.getResource("x").toURI()).getParent();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Parameters(name = "Gradle v{0}")
    public static Collection<Object[]> testData() {
        String[] gradleVersions = new String[]{
                "6.4.1",
                "6.3",
                "6.2.2",
                "6.1.1",
                "6.0.1",
                "5.6.4",
                "5.5.1",
                "5.4.1",
                "5.3.1",
        };
        String forceGradleVersion = System.getProperty("be.vbgn.gradle.devConventions.integration.useGradleVersion");
        if (forceGradleVersion != null) {
            gradleVersions = new String[]{forceGradleVersion};
        }

        List<Object[]> parameters = new ArrayList<>();

        for (String gradleVersion : gradleVersions) {
            parameters.add(new Object[]{gradleVersion});
        }
        return parameters;
    }

    @Parameter(0)
    public String gradleVersion;

    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();


    protected GradleRunner createGradleRunner(Path projectFolder) throws IOException {

        FileUtils.copyDirectory(projectFolder.toFile(), testProjectDir.getRoot());
        GradleRunner gradleRunner = GradleRunner.create()
                .withPluginClasspath()
                // This clears all environment variables, including ones set by CI
                // (Because we do some things with CI detection)
                .withEnvironment(new HashMap<>())
                .withArguments("--stacktrace", "--info")
                .withProjectDir(testProjectDir.getRoot())
                .forwardOutput();

        List<File> pluginClasspath = new ArrayList<>(gradleRunner.getPluginClasspath());

        String additionalClassPaths = System
                .getProperty("be.vbgn.gradle.devConventions.integration.additionalPluginClasspath");

        String[] additionalClassPathsSplit = additionalClassPaths.split(":");

        pluginClasspath.addAll(Arrays.stream(additionalClassPathsSplit).map(File::new).collect(
                Collectors.toSet()));

        gradleRunner.withPluginClasspath(pluginClasspath);

        if (System.getProperty("be.vbgn.gradle.buildaspects.integration.forceCurrentGradleVersion") == null) {
            gradleRunner.withGradleVersion(gradleVersion);
        }

        return gradleRunner;

    }

}
