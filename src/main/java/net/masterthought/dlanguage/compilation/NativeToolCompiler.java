package net.masterthought.dlanguage.compilation;

import net.masterthought.dlanguage.settings.ToolKey;

/**
 * Created by francis on 8/20/2017.
 */
public interface NativeToolCompiler {


    static NativeToolCompiler getInstance(final ToolKey key) {
        return new DubCompiler();//todo
    }

    void build();
}
