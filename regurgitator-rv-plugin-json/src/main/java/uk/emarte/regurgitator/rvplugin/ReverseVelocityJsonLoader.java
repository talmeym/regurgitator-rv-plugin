/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.rvplugin;

import uk.emarte.regurgitator.core.ContextLocation;
import uk.emarte.regurgitator.core.Log;
import uk.emarte.regurgitator.core.RegurgitatorException;
import uk.emarte.regurgitator.core.ValueSource;
import uk.emarte.regurgitator.core.JsonLoader;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static uk.emarte.regurgitator.core.CoreConfigConstants.SOURCE;
import static uk.emarte.regurgitator.core.CoreConfigConstants.VALUE;
import static uk.emarte.regurgitator.core.FileUtil.getInputStreamForFile;
import static uk.emarte.regurgitator.core.FileUtil.streamToString;
import static uk.emarte.regurgitator.core.JsonConfigUtil.loadId;
import static uk.emarte.regurgitator.core.JsonConfigUtil.loadOptionalStr;

public class ReverseVelocityJsonLoader implements JsonLoader<ReverseVelocity> {
    private static final Log log = Log.getLog(ReverseVelocityJsonLoader.class);
    private static final String TEMPLATE = "template";

    @Override
    public ReverseVelocity load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
        String source = loadOptionalStr(jsonObject, SOURCE);
        String value = loadOptionalStr(jsonObject, VALUE);
        String template = loadOptionalStr(jsonObject, TEMPLATE);
        String templateContent;

        try {
            templateContent = streamToString(getInputStreamForFile(template));
        } catch (IOException e) {
            throw new RegurgitatorException("Error loading template file: " + template, e);
        }

        ContextLocation location = source != null ? new ContextLocation(source) : null;

        String id = loadId(jsonObject, allIds);
        log.debug("Loaded reverse velocity '" + id + "'");
        return new ReverseVelocity(id, new File(template).getName(), templateContent, new ValueSource(location, value));
    }
}
