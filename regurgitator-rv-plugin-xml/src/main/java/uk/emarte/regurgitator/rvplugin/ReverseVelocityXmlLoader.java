/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.rvplugin;

import org.w3c.dom.Element;
import uk.emarte.regurgitator.core.Log;
import uk.emarte.regurgitator.core.RegurgitatorException;
import uk.emarte.regurgitator.core.XmlLoader;

import java.util.Set;

import static uk.emarte.regurgitator.core.CoreConfigConstants.SOURCE;
import static uk.emarte.regurgitator.core.CoreConfigConstants.VALUE;
import static uk.emarte.regurgitator.core.XmlConfigUtil.loadId;
import static uk.emarte.regurgitator.core.XmlConfigUtil.loadOptionalStr;
import static uk.emarte.regurgitator.rvplugin.RvPluginConstants.TEMPLATE;

public class ReverseVelocityXmlLoader extends ReverseVelocityLoader implements XmlLoader<ReverseVelocity> {
    private static final Log log = Log.getLog(ReverseVelocityXmlLoader.class);

    @Override
    public ReverseVelocity load(Element element, Set<Object> allIds) throws RegurgitatorException {
        String source = loadOptionalStr(element, SOURCE);
        String value = loadOptionalStr(element, VALUE);
        String template = loadOptionalStr(element, TEMPLATE);
        return loadReverseVelocity(loadId(element, allIds), source, value, template, log);
    }
}
