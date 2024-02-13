/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.rvplugin;

import org.w3c.dom.Element;
import uk.emarte.regurgitator.core.*;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static uk.emarte.regurgitator.core.CoreConfigConstants.SOURCE;
import static uk.emarte.regurgitator.core.CoreConfigConstants.VALUE;
import static uk.emarte.regurgitator.core.FileUtil.getInputStreamForFile;
import static uk.emarte.regurgitator.core.FileUtil.streamToString;
import static uk.emarte.regurgitator.core.XmlConfigUtil.loadId;
import static uk.emarte.regurgitator.core.XmlConfigUtil.loadOptionalStr;

public class ReverseVelocityXmlLoader implements XmlLoader<ReverseVelocity> {
    private static final Log log = Log.getLog(ReverseVelocityXmlLoader.class);
    private static final String TEMPLATE = "template";

    @Override
    public ReverseVelocity load(Element element, Set<Object> allIds) throws RegurgitatorException {
        String source = loadOptionalStr(element, SOURCE);
        String value = loadOptionalStr(element, VALUE);
        String template = loadOptionalStr(element, TEMPLATE);
        String templateContent;

        try {
            templateContent = streamToString(getInputStreamForFile(template));
        } catch (IOException e) {
            throw new RegurgitatorException("Error loading template file: " + template, e);
        }

        ContextLocation location = source != null ? new ContextLocation(source) : null;

        String id = loadId(element, allIds);
        log.debug("Loaded reverse velocity '" + id + "'");
        return new ReverseVelocity(id, new File(template).getName(), templateContent, new ValueSource(location, value));
    }
}
