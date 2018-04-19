package com.emarte.regurgitator.rvplugin;

import com.emarte.regurgitator.core.ContextLocation;
import com.emarte.regurgitator.core.Log;
import com.emarte.regurgitator.core.RegurgitatorException;
import com.emarte.regurgitator.core.ValueSource;
import com.emarte.regurgitator.core.XmlLoader;
import org.w3c.dom.Element;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static com.emarte.regurgitator.core.CoreConfigConstants.SOURCE;
import static com.emarte.regurgitator.core.CoreConfigConstants.VALUE;
import static com.emarte.regurgitator.core.FileUtil.getInputStreamForFile;
import static com.emarte.regurgitator.core.FileUtil.streamToString;
import static com.emarte.regurgitator.core.XmlConfigUtil.loadId;
import static com.emarte.regurgitator.core.XmlConfigUtil.loadOptionalStr;

public class ReverseVelocityXmlLoader implements XmlLoader<ReverseVelocity> {
    private static final Log log = Log.getLog(ReverseVelocityXmlLoader.class);
    private static final String TEMPLATE = "template";

    @Override
    public ReverseVelocity load(Element element, Set<Object> allIds) throws RegurgitatorException {
        String source = loadOptionalStr(element, SOURCE);
        String value = loadOptionalStr(element, VALUE);
        String file = loadOptionalStr(element, TEMPLATE);
        String template;

        try {
            template = streamToString(getInputStreamForFile(file));
        } catch (IOException e) {
            throw new RegurgitatorException("Error loading template file: " + file, e);
        }

        ContextLocation location = source != null ? new ContextLocation(source) : null;

        String id = loadId(element, allIds);
        log.debug("Loaded reverse velocity '" + id + "'");
        return new ReverseVelocity(id, new File(file).getName(), template, new ValueSource(location, value));
    }
}
