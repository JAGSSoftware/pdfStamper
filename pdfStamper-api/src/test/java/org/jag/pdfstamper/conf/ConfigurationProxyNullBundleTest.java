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

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author Jose A. Garcia
 *
 */
public class ConfigurationProxyNullBundleTest {
    private ConfigurationProxy underTest;
    @Mock
    private StamperBundle bundle;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        underTest = new ConfigurationProxy(null);
    }

    @Test
    public void getStringProperty() {
        underTest.getProperty("property");

        verify(bundle, never()).getProperty("property");
    }

    @Test
    public void getStringPropertyWithDefaultValue() {
        underTest.getProperty("property", "defaultValue");

        verify(bundle, never()).getProperty("property", "defaultValue");
    }

    @Test
    public void getBooleanProperty() {
        underTest.getBooleanProperty("property");

        verify(bundle, never()).getBooleanProperty("property");
    }

    @Test
    public void getIntProperty() {
        underTest.getIntProperty("property");

        verify(bundle, never()).getIntProperty("property");
    }

    @Test
    public void getIntPropertyWithDefaultValue() {
        underTest.getIntProperty("property", 73);

        verify(bundle, never()).getIntProperty("property", 73);
    }

    @Test
    public void getIntArrayProperty() {
        underTest.getIntArrayProperty("property");

        verify(bundle, never()).getIntArrayProperty("property");
    }

    @Test
    public void getFloatProperty() {
        underTest.getFloatProperty("property");

        verify(bundle, never()).getFloatProperty("property");
    }

    @Test
    public void getFloatPropertyWithDefaultValue() {
        underTest.getFloatProperty("property", 34.56f);

        verify(bundle, never()).getFloatProperty("property", 34.56f);
    }

    @Test
    public void getFloatArrayProperty() {
        underTest.getFloatArrayProperty("property");

        verify(bundle, never()).getFloatArrayProperty("property");
    }

    @Test
    public void getDateFormat() {
        underTest.getDateFormat("property");

        verify(bundle, never()).getDateFormat("property");
    }

    @Test
    public void getFont() {
        underTest.getFont("property");

        verify(bundle, never()).getFont("property");
    }

    @Test
    public void getColor() {
        underTest.getColor("property");

        verify(bundle, never()).getColor("property");
    }
}
