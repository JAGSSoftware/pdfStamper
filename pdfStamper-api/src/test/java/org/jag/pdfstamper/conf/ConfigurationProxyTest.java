/*
 * Copyright (C) 2017 Jose A. Garcia Sanchez
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
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DateFormat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * @author Jose A. Garcia
 */
public class ConfigurationProxyTest {
    private ConfigurationProxy underTest;
    @Mock
    private StamperBundle bundle;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        underTest = new ConfigurationProxy(bundle);
    }

    @Test
    public void getStringProperty() {
        when(bundle.getProperty(anyString())).thenReturn("value");

        assertThat(underTest.getProperty("property")).isEqualTo("value");

        verify(bundle).getProperty("property");
    }

    @Test
    public void getStringPropertyWithDefaultValue() {
        when(bundle.getProperty(anyString(), anyString())).thenReturn("value");

        assertThat(underTest.getProperty("property", "defaultValue")).isEqualTo("value");

        verify(bundle).getProperty("property", "defaultValue");
    }

    @Test
    public void getBooleanProperty() {
        when(bundle.getBooleanProperty(anyString())).thenReturn(true);

        assertThat(underTest.getBooleanProperty("property")).isTrue();

        verify(bundle).getBooleanProperty("property");
    }

    @Test
    public void getIntProperty() {
        when(bundle.getIntProperty(anyString())).thenReturn(12);

        assertThat(underTest.getIntProperty("property")).isEqualTo(12);

        verify(bundle).getIntProperty("property");
    }

    @Test
    public void getIntPropertyWithDefaultValue() {
        when(bundle.getIntProperty(anyString(), anyInt())).thenReturn(45);

        assertThat(underTest.getIntProperty("property", 73)).isEqualTo(45);

        verify(bundle).getIntProperty("property", 73);
    }

    @Test
    public void getIntArrayProperty() {
        when(bundle.getIntArrayProperty(anyString())).thenReturn(new int[] { 10, 20, 30 });

        assertThat(underTest.getIntArrayProperty("property")).isEqualTo(new int[] { 10, 20, 30 });

        verify(bundle).getIntArrayProperty("property");
    }

    @Test
    public void getFloatProperty() {
        when(bundle.getFloatProperty(anyString())).thenReturn(12.34f);

        assertThat(underTest.getFloatProperty("property")).isWithin(0.00f).of(12.34f);

        verify(bundle).getFloatProperty("property");
    }

    @Test
    public void getFloatPropertyWithDefaultValue() {
        when(bundle.getFloatProperty(anyString(), anyFloat())).thenReturn(12.34f);

        assertThat(underTest.getFloatProperty("property", 34.56f)).isWithin(0.00f).of(12.34f);

        verify(bundle).getFloatProperty("property", 34.56f);
    }

    @Test
    public void getFloatArrayProperty() {
        when(bundle.getFloatArrayProperty(anyString())).thenReturn(new float[] { 12.34f, 23.45f, 34.56f });

        assertThat(underTest.getFloatArrayProperty("property")).hasValuesWithin(0f).of(12.34f, 23.45f, 34.56f);

        verify(bundle).getFloatArrayProperty("property");
    }

    @Test
    public void getDateFormat() {
        final DateFormat dateFormat = DateFormat.getDateInstance();
        when(bundle.getDateFormat(anyString())).thenReturn(dateFormat);

        assertThat(underTest.getDateFormat("property")).isEqualTo(dateFormat);

        verify(bundle).getDateFormat("property");
    }

    @Test
    public void getFont() {
        final Font font = new Font();
        when(bundle.getFont(anyString())).thenReturn(font);

        assertThat(underTest.getFont("property")).isEqualTo(font);

        verify(bundle).getFont("property");
    }

    @Test
    public void getColor() {
        final BaseColor color = new BaseColor(23);
        when(bundle.getColor(anyString())).thenReturn(color);

        assertThat(underTest.getColor("property")).isEqualTo(color);

        verify(bundle).getColor("property");
    }
}
