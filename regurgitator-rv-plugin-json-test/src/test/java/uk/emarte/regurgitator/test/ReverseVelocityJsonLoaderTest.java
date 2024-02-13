/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.RegurgitatorException;
import uk.emarte.regurgitator.rvplugin.ReverseVelocityJsonLoader;

import static uk.emarte.regurgitator.core.ConfigurationFile.loadFile;

public class ReverseVelocityJsonLoaderTest extends JsonLoaderTest {
    public ReverseVelocityJsonLoaderTest() {
        super(new ReverseVelocityJsonLoader());
    }

    @Test
    public void testValue() throws Exception {
        assertExpectation("classpath:/ReverseVelocity.json", "uk.emarte.regurgitator.rvplugin.ReverseVelocity:['reverse-velocity','template.xml','<product><name>${product-name}</name></product>',uk.emarte.regurgitator.core.ValueSource:[uk.emarte.regurgitator.core.ContextLocation:['param-name'],'value']]");
    }

    @Test(expected = RegurgitatorException.class)
    public void testMissingSourceAndValue() throws Exception {
        loadFile("classpath:/ReverseVelocity_missingSourceAndValue.json");
    }
}
