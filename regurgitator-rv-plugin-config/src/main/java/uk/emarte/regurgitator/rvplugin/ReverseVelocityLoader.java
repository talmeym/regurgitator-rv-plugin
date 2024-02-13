/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.rvplugin;

import uk.emarte.regurgitator.core.*;

import java.io.File;
import java.io.IOException;

import static uk.emarte.regurgitator.core.FileUtil.getInputStreamForFile;
import static uk.emarte.regurgitator.core.FileUtil.streamToString;

public abstract class ReverseVelocityLoader {
    ReverseVelocity loadReverseVelocity(String id, String source, String value, String template, Log log) throws RegurgitatorException {
        String templateContent;

        try {
            templateContent = streamToString(getInputStreamForFile(template));
        } catch (IOException e) {
            throw new RegurgitatorException("Error loading template file: " + template, e);
        }

        log.debug("Loaded reverse velocity '" + id + "'");
        return new ReverseVelocity(id, new File(template).getName(), templateContent, ValueSourceLoader.loadValueSource(source, value));
    }
}
