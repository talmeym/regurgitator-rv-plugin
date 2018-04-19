package com.emarte.regurgitator.rvplugin;

import com.emarte.regurgitator.core.ContextLocation;
import com.emarte.regurgitator.core.Log;
import com.emarte.regurgitator.core.RegurgitatorException;
import com.emarte.regurgitator.core.ValueSource;
import com.emarte.regurgitator.core.JsonLoader;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static com.emarte.regurgitator.core.CoreConfigConstants.SOURCE;
import static com.emarte.regurgitator.core.CoreConfigConstants.VALUE;
import static com.emarte.regurgitator.core.FileUtil.getInputStreamForFile;
import static com.emarte.regurgitator.core.FileUtil.streamToString;
import static com.emarte.regurgitator.core.JsonConfigUtil.loadId;
import static com.emarte.regurgitator.core.JsonConfigUtil.loadOptionalStr;

public class ReverseVelocityJsonLoader implements JsonLoader<ReverseVelocity> {
    private static final Log log = Log.getLog(ReverseVelocityJsonLoader.class);
    private static final String TEMPLATE = "template";

    @Override
    public ReverseVelocity load(JSONObject jsonObject, Set<Object> allIds) throws RegurgitatorException {
        String source = loadOptionalStr(jsonObject, SOURCE);
        String value = loadOptionalStr(jsonObject, VALUE);
        String file = loadOptionalStr(jsonObject, TEMPLATE);
        String template;

        try {
            template = streamToString(getInputStreamForFile(file));
        } catch (IOException e) {
            throw new RegurgitatorException("Error loading template file: " + file, e);
        }

        ContextLocation location = source != null ? new ContextLocation(source) : null;

        String id = loadId(jsonObject, allIds);
        log.debug("Loaded reverse velocity '" + id + "'");
        return new ReverseVelocity(id, new File(file).getName(), template, new ValueSource(location, value));
    }
}
