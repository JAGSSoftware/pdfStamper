/*
 * MIT License
 *
 * Copyright (c) 2019 José A. García Sánchez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.jag.pdfstamper.conf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Jose A. Garcia
 */
public class NullStamperBundleTest {
    private static final float PRECISION = 0.00001f;
    private NullStamperBundle underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new NullStamperBundle();
    }

    @Test
    public void getPropertyString() {
        final String property = underTest.getProperty("property");

        assertThat(property).isNotNull();
        assertThat(property).isEmpty();
    }

    @Test
    public void getPropertyStringNull() {
        final String property = underTest.getProperty("propertyNull");

        assertThat(property).isNotNull();
        assertThat(property).isEmpty();
    }

    @Test
    public void getPropertyStringString() {
        final String defaultValue = "defaultValue";
        final String property = underTest.getProperty("property", defaultValue);

        assertThat(property).isNotNull();
        assertThat(property).isEqualTo(defaultValue);
    }

    @Test
    public void getBooleanProperty() {
        assertThat(underTest.getBooleanProperty("property")).isFalse();
    }

    @Test
    public void getIntPropertyString() {
        assertThat(underTest.getIntProperty("property")).isEqualTo(0);
    }

    @Test
    public void getIntPropertyStringInt() {
        final int defaultValue = 10;

        assertThat(underTest.getIntProperty("property", defaultValue)).isEqualTo(defaultValue);
    }

    @Test
    public void getIntArrayProperty() {
        assertThat(underTest.getIntArrayProperty("property")).isNotNull();
        assertThat(underTest.getIntArrayProperty("property")).isEmpty();
    }

    @Test
    public void getFloatPropertyString() {
        assertThat(underTest.getFloatProperty("property")).isWithin(PRECISION).of(0f);
    }

    @Test
    public void getFloatPropertyStringFloat() {
        final float defaultValue = 1.23456f;
        assertThat(underTest.getFloatProperty("property", defaultValue)).isWithin(PRECISION).of(defaultValue);
    }

    @Test
    public void getFloatArrayProperty() {
        assertThat(underTest.getFloatArrayProperty("property")).isEmpty();
    }

    @Test
    public void getDateFormat() {
        final DateFormat dateFormat = underTest.getDateFormat("property");

        assertThat(dateFormat).isNotNull();
        assertThat(dateFormat).isEqualTo(DateFormat.getDateInstance());
    }

    @Test
    public void getFont() {
        final Font font = underTest.getFont("property");

        assertThat(font).isNotNull();
        assertThat(font.getFamily()).isEqualTo(FontFamily.HELVETICA);
    }

    @Test
    public void getColor() {
        final BaseColor color = underTest.getColor("property");

        assertThat(color).isNotNull();
        assertThat(color).isEqualTo(BaseColor.BLACK);
    }
}
