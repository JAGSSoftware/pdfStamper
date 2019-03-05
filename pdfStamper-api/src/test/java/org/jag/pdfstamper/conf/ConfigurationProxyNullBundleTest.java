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

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Jose A. Garcia
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
