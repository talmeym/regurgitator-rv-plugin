/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.rvplugin;

import uk.emarte.regurgitator.core.Log;
import uk.emarte.regurgitator.core.RegurgitatorException;
import uk.emarte.regurgitator.core.Yaml;
import uk.emarte.regurgitator.core.YmlLoader;

import java.util.Set;

import static uk.emarte.regurgitator.core.CoreConfigConstants.SOURCE;
import static uk.emarte.regurgitator.core.CoreConfigConstants.VALUE;
import static uk.emarte.regurgitator.core.YmlConfigUtil.loadId;
import static uk.emarte.regurgitator.core.YmlConfigUtil.loadOptionalStr;
import static uk.emarte.regurgitator.rvplugin.RvPluginConstants.TEMPLATE;

public class ReverseVelocityYmlLoader extends ReverseVelocityLoader implements YmlLoader<ReverseVelocity> {
    private static final Log log = Log.getLog(ReverseVelocityYmlLoader.class);

    @Override
    public ReverseVelocity load(Yaml yaml, Set<Object> allIds) throws RegurgitatorException {
        String source = loadOptionalStr(yaml, SOURCE);
        String value = loadOptionalStr(yaml, VALUE);
        String template = loadOptionalStr(yaml, TEMPLATE);
        return loadReverseVelocity(loadId(yaml, allIds), source, value, template, log);
    }
}
