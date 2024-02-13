package uk.emarte.regurgitator.rvplugin;

import uk.emarte.regurgitator.core.HasId;
import uk.emarte.regurgitator.core.ValueSource;
import uk.emarte.regurgitator.test.MockStep;

public class ReverseVelocity extends MockStep implements HasId {
    public ReverseVelocity(Object id, String templateName, String template, ValueSource valueSource) {
        super(id, templateName, template, valueSource);
    }
}
