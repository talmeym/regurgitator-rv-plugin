/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.rvplugin;

import net.sf.json.JSONObject;
import uk.emarte.regurgitator.core.JsonLoader;
import uk.emarte.regurgitator.core.Log;
import uk.emarte.regurgitator.core.RegurgitatorException;

import java.util.Set;

import static uk.emarte.regurgitator.core.CoreConfigConstants.SOURCE;
import static uk.emarte.regurgitator.core.CoreConfigConstants.VALUE;
import static uk.emarte.regurgitator.core.JsonConfigUtil.loadId;
import static uk.emarte.regurgitator.core.JsonConfigUtil.loadOptionalStr;
import static uk.emarte.regurgitator.rvplugin.RvPluginConstants.TEMPLATE;

public class ReverseVelocityJsonLoader extends ReverseVelocityLoader implements JsonLoader<ReverseVelocity> {
    private static final Log log = Log.getLog(ReverseVelocityLoader.class);

    @Override
    public ReverseVelocity load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
        String source = loadOptionalStr(jsonObject, SOURCE);
        String value = loadOptionalStr(jsonObject, VALUE);
        String template = loadOptionalStr(jsonObject, TEMPLATE);
        return loadReverseVelocity(loadId(jsonObject, allIds), source, value, template, log);
    }
}
