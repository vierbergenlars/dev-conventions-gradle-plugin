package be.vbgn.gradle.devconventions.fakeci;

import be.vbgn.gradle.cidetect.CiInformation;

public class FakeCIInformation implements CiInformation {

    @Override
    public boolean isCi() {
        return Boolean.getBoolean("fakeci");
    }

    @Override
    public String getBuildNumber() {
        return System.getProperty("fakeci.buildNumber");
    }

    @Override
    public String getBranch() {
        return System.getProperty("fakeci.branch");
    }

    @Override
    public String getPullRequest() {
        return System.getProperty("fakeci.pr");
    }

    @Override
    public String getPullRequestTargetBranch() {
        return System.getProperty("fakeci.pr.target");
    }

    @Override
    public String getTag() {
        return System.getProperty("fakeci.tag");
    }
}
