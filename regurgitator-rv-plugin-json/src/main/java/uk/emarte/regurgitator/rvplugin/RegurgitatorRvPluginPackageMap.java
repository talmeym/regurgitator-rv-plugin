/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.rvplugin;

import uk.emarte.regurgitator.core.AbstractJsonPackageMap;

import java.util.Collections;
import java.util.List;

public class RegurgitatorRvPluginPackageMap extends AbstractJsonPackageMap {
    private static final List<String> kinds = Collections.singletonList("reverse-velocity");

    public RegurgitatorRvPluginPackageMap() {
        addPackageMapping(kinds, "uk.emarte.regurgitator.rvplugin");
    }
}
