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

import java.text.DateFormat;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

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
