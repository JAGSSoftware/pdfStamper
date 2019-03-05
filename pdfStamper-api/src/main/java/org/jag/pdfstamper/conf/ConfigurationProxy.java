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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;

/**
 * @author Jose A. Garcia
 */
class ConfigurationProxy implements StamperBundle {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationProxy.class);
    private final StamperBundle bundle;

    public ConfigurationProxy(final StamperBundle bundle) {
        if (bundle == null) {
            LOGGER.warn("Bundle is null, using a default Configuration bundle.");
            this.bundle = new NullStamperBundle();
        } else {
            this.bundle = bundle;
        }
    }

    @Override
    public final String getProperty(final String name) {
        return bundle.getProperty(name);
    }

    @Override
    public final String getProperty(final String name, final String defaultValue) {
        return bundle.getProperty(name, defaultValue);
    }

    @Override
    public final boolean getBooleanProperty(final String name) {
        return bundle.getBooleanProperty(name);
    }

    @Override
    public final int getIntProperty(final String name) {
        return bundle.getIntProperty(name);
    }

    @Override
    public final int getIntProperty(final String name, final int defaultValue) {
        return bundle.getIntProperty(name, defaultValue);
    }

    @Override
    public final int[] getIntArrayProperty(final String name) {
        return bundle.getIntArrayProperty(name);
    }

    @Override
    public final float getFloatProperty(final String name) {
        return bundle.getFloatProperty(name);
    }

    @Override
    public final float getFloatProperty(final String name, final float defaultValue) {
        return bundle.getFloatProperty(name, defaultValue);
    }

    @Override
    public final float[] getFloatArrayProperty(final String name) {
        return bundle.getFloatArrayProperty(name);
    }

    @Override
    public final DateFormat getDateFormat(final String name) {
        return bundle.getDateFormat(name);
    }

    @Override
    public final Font getFont(final String name) {
        return bundle.getFont(name);
    }

    @Override
    public final BaseColor getColor(final String name) {
        return bundle.getColor(name);
    }
}
