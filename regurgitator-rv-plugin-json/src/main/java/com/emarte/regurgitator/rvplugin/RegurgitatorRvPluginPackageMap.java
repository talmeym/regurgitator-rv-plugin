package com.emarte.regurgitator.rvplugin;

import com.emarte.regurgitator.core.AbstractJsonPackageMap;

import java.util.Collections;
import java.util.List;

public class RegurgitatorRvPluginPackageMap extends AbstractJsonPackageMap {
    private static final List<String> kinds = Collections.singletonList("reverse-velocity");

    public RegurgitatorRvPluginPackageMap() {
        addPackageMapping(kinds, "com.emarte.regurgitator.rvplugin");
    }
}
