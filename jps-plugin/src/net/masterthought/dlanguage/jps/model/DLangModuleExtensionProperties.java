package net.masterthought.dlanguage.jps.model;

import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.xmlb.annotations.AbstractCollection;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DLangModuleExtensionProperties {
    @Tag("parseTransforms")
    @AbstractCollection(surroundWithTag = false, elementTag = "transform")
    //should not contain duplicate elements
    public List<String> myParseTransforms = ContainerUtil.newArrayList();

    public DLangModuleExtensionProperties() {
    }

    public DLangModuleExtensionProperties(@NotNull DLangModuleExtensionProperties props) {
        myParseTransforms = ContainerUtil.newArrayList(props.myParseTransforms);
    }
}
