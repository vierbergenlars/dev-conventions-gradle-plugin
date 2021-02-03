import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import be.vbgn.gradle.devconventions.conventions.impl.AllTestsWithCoverageConvention;
import be.vbgn.gradle.devconventions.conventions.impl.CheckDependsOnAllTestsOpinion;
import be.vbgn.gradle.devconventions.conventions.impl.TestsFailFastOnCIOpinion;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

public class TestBaseConventionPlugin {

    @Test
    public void testGetName() {
        assertEquals("AllTestsWithCoverageConvention", (new AllTestsWithCoverageConvention()).getName());
    }

    @Test
    public void testIsEnabled() {
        Project project = ProjectBuilder.builder().build();

        assertTrue((new CheckDependsOnAllTestsOpinion()).isEnabled(project));
        assertTrue((new TestsFailFastOnCIOpinion()).isEnabled(project));

        project.getExtensions().getExtraProperties()
                .set("be.vbgn.dev-conventions.disable", "CheckDependsOnAllTestsOpinion");

        assertFalse((new CheckDependsOnAllTestsOpinion()).isEnabled(project));
        assertTrue((new TestsFailFastOnCIOpinion()).isEnabled(project));

        project.setProperty("be.vbgn.dev-conventions.disable",
                "CheckDependsOnAllTestsOpinion,TestsFailFastOnCIOpinion");

        assertFalse((new CheckDependsOnAllTestsOpinion()).isEnabled(project));
        assertFalse((new TestsFailFastOnCIOpinion()).isEnabled(project));
    }

}
