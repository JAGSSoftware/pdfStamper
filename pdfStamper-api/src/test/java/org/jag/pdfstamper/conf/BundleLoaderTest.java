/*
 * Copyright (C) 2013 Jose A. Garcia Sanchez
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.jag.pdfstamper.conf;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jose A. Garcia
 */
public class BundleLoaderTest {
    private static final float PRECISION = .0001f;
    private BundleLoader bundle;

    @Before
    public void setUp() throws Exception {
        bundle = new BundleLoader("test");
    }

    @Test
    public void getPropertyString() {
        assertThat(bundle.getProperty("stringProperty")).isEqualTo("Property String");
    }

    @Test
    public void getPropertyStringNull() {
        assertThat(bundle.getProperty("stringPropertyNull")).isNull();
    }

    @Test
    public void getPropertyStringString() {
        assertThat(bundle.getProperty("stringProperty", "Default value")).isEqualTo("Property String");
    }

    @Test
    public void getPropertyStringStringNull() {
        assertThat(bundle.getProperty("stringPropertyNull", "Default value")).isEqualTo("Default value");
    }

    @Test
    public void getIntPropertyString() {
        assertThat(bundle.getIntProperty("intProperty")).isEqualTo(1);
    }

    @Test(expected = NumberFormatException.class)
    public void getIntPropertyStringNull() {
        bundle.getIntProperty("intPropertyNull");
    }

    @Test
    public void getIntPropertyStringInt() {
        assertThat(bundle.getIntProperty("intProperty")).isEqualTo(1);
    }

    @Test
    public void getIntPropertyStringIntNull() {
        assertThat(bundle.getIntProperty("intPropertyNull", 100)).isEqualTo(100);
    }

    @Test
    public void getIntArrayProperty() {
        final int[] expected = {1, 2, 3};

        assertThat(bundle.getIntArrayProperty("intArrayProperty")).isEqualTo(expected);
    }

    @Test
    public void getIntArrayPropertyNull() {
        assertThat(bundle.getIntArrayProperty("intArrayPropertyNull")).isNotNull();
        assertThat(bundle.getIntArrayProperty("intArrayPropertyNull")).isEmpty();
    }

    @Test
    public void getIntArrayProperty1() {
        final int[] expected = {1977};

        assertThat(bundle.getIntArrayProperty("intArrayProperty1")).isEqualTo(expected);
    }

    @Test
    public void tokensFromArrayProperty() {
        final List<String> tokens = bundle.tokensFromArrayProperty("123,456, 789");
        final String[] expected = new String[]{"123", "456", "789"};

        assertThat(tokens.toArray(new String[tokens.size()])).isEqualTo(expected);
    }

    @Test
    public void getBooleanProperty() {
        assertThat(bundle.getBooleanProperty("trueProperty_true")).isTrue();
        assertThat(bundle.getBooleanProperty("trueProperty_TRUE")).isTrue();
        assertThat(bundle.getBooleanProperty("falseProperty_false")).isFalse();
        assertThat(bundle.getBooleanProperty("falseProperty_FALSE")).isFalse();
    }

    @Test(expected = NullPointerException.class)
    public void getBooleanPropertyNull() {
        bundle.getBooleanProperty("booleanPropertyNull");
    }

    @Test
    public void getBooleanPropertyOtherValue() {
        assertThat(bundle.getBooleanProperty("booleanPropertyOther")).isFalse();
    }

    @Test
    public void getFloatPropertyString() {
        assertThat(bundle.getFloatProperty("floatProperty")).isWithin(PRECISION).of(1.2f);
    }

    @Test(expected = NullPointerException.class)
    public void getFloatPropertyStringNull() {
        bundle.getFloatProperty("floatPropertyNull");
    }

    @Test(expected = NumberFormatException.class)
    public void getFloatPropertyStringFormatException() {
        bundle.getFloatProperty("floatProperty_FormatException");
    }

    @Test
    public void getFloatPropertyStringFloat() {
        assertThat(bundle.getFloatProperty("floatProperty", 2.3f)).isWithin(PRECISION).of(1.2f);
    }

    @Test
    public void getFloatPropertyStringFloatFormatException() {
        assertThat(bundle.getFloatProperty("floatProperty_FormatException", 2.3f)).isWithin(PRECISION).of(2.3f);
    }

    @Test
    public void getFloatPropertyStringFloatNull() {
        assertThat(bundle.getFloatProperty("floatPropertyNull", 2.3f)).isWithin(PRECISION).of(2.3f);
    }

    @Test
    public void getFloatArrayProperty() {
        final float[] expected = {1.1f, 2.2f, 3.3f};

        assertThat(bundle.getFloatArrayProperty("floatArrayProperty")).hasValuesWithin(0f).of(expected);
    }

    @Test
    public void getFloatArrayPropertyNull() {
        assertThat(bundle.getFloatArrayProperty("floatArrayPropertyNull")).isNotNull();
        assertThat(bundle.getFloatArrayProperty("floatArrayPropertyNull")).isEmpty();
    }

    @Test
    public void getDateFormat() {
        assertThat(bundle.getDateFormat("dateformatProperty")).isNotNull();
    }

    @Test(expected = NullPointerException.class)
    public void getDateFormatNull() {
        assertThat(bundle.getDateFormat("dateformatPropertyNull")).isNotNull();
    }

    @Test
    public void getFont() {
        assertThat(bundle.getFont("fontProperty")).isNotNull();
    }

    @Test
    public void getFontNull() {
        assertThat(bundle.getFont("fontPropertyNull")).isNull();
    }

    @Test
    public void getColor() {
        assertThat(bundle.getColor("colorProperty")).isNotNull();
    }

    @Test
    public void getColorNull() {
        assertThat(bundle.getColor("colorPropertyNull")).isNull();
    }

    @Test
    public void configurationFileDoesNotExist() throws IOException {
        final BundleLoader loader = new BundleLoader("doesNotExist");

        assertThat(loader).isNotNull();
    }
}
