package com.emarte.regurgitator.rvplugin;

import com.emarte.regurgitator.core.AbstractYmlPackageMap;

import java.util.Collections;
import java.util.List;

public class RegurgitatorRvPluginPackageMap extends AbstractYmlPackageMap {
    private static final List<String> kinds = Collections.singletonList("reverse-velocity");

    public RegurgitatorRvPluginPackageMap() {
        addPackageMapping(kinds, "com.emarte.regurgitator.rvplugin");
    }
}
