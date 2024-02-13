/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.rvplugin;

import uk.emarte.regurgitator.core.*;
import uk.emarte.reversevelocity.ParseException;
import uk.emarte.reversevelocity.Parser;
import uk.emarte.reversevelocity.ParserFactory;

import java.io.ByteArrayInputStream;
import java.util.Map;

import static uk.emarte.regurgitator.core.StringType.stringify;

public class ReverseVelocity extends Identifiable implements Step {
    private final Log log = Log.getLog(this);
    private final String templateName;
    private final String template;
    private final ValueSource valueSource;

    public ReverseVelocity(Object id, String templateName, String template, ValueSource valueSource) {
        super(id);
        this.templateName = templateName;
        this.template = template;
        this.valueSource = valueSource;
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        try {
            String sourceText = stringify(valueSource.getValue(message, log));
            Parser parser = ParserFactory.createParserFromTemplateStream(templateName, new ByteArrayInputStream(template.getBytes()));
            Map<String, Object> result = parser.parse(sourceText);

            for (String key: result.keySet()) {
                ContextLocation contextLocation = contextLocation(key);
                message.getContext(contextLocation.getContext()).setValue(contextLocation.getName(), stringify(result.get(key)));
            }
        } catch (ParseException e) {
            throw new RegurgitatorException("Error parsing text", e);
        }
    }

    private ContextLocation contextLocation(String key) {
        if(key.contains(":")) {
            return new ContextLocation(key.substring(0, key.indexOf(':')), key.substring(key.indexOf(':') + 1));
        }

        return new ContextLocation(key);
    }
}
