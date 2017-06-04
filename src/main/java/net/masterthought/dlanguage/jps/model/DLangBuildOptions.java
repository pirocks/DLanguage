package net.masterthought.dlanguage.jps.model;

import com.intellij.util.xmlb.annotations.Tag;

/**
 * Serialization object for communicating build settings with the build server.
 */
public class DLangBuildOptions {
    public static final String DEFAULT_DMD_PATH = "dmd";
    public static final String DEFAULT_RDMD_PATH = "rdmd";
    @Tag("dmdPath")
    public String myDmdPath = DEFAULT_DMD_PATH;
    @Tag("rdmdPath")
    public String myrDmdPath = DEFAULT_RDMD_PATH;

    public DLangBuildOptions() {
    }

    public DLangBuildOptions(DLangBuildOptions options) {
        myDmdPath = options.myDmdPath;
        myrDmdPath = options.myrDmdPath;
    }

    @Override
    public String toString() {
        return "DLangBuildOptions{" +
            "myDmdPath=" + myDmdPath +
            ", myrDmdPath=" + myrDmdPath +
            '}';
    }
}

