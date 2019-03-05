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
import com.itextpdf.text.FontFactory;

import java.text.DateFormat;
import java.util.logging.Logger;

/**
 * Implementation of the StamperBundle interface following the
 * <a href="http://en.wikipedia.org/wiki/Null_Object_pattern" target="_blank">Null Object</a> pattern, providing default
 * values for the case the real bundle could not be created.
 *
 * @author Jose A. Garcia Sanchez
 * @note Null Object pattern
 */
final class NullStamperBundle implements StamperBundle {
    private static final Logger LOGGER = Logger.getLogger("pdfstamper");

    /**
     * Writes a "Properties not loaded" warning message in log
     */
    private void warn() {
        LOGGER.warning("NullStamperBundle: properties not loaded");
    }

    /**
     * @return Blank value {@inheritDoc}
     */
    @Override
    public String getProperty(final String name) {
        warn();
        return getProperty(name, "");
    }

    /**
     * @return defaultValue {@inheritDoc}
     */
    @Override
    public String getProperty(final String name, final String defaultValue) {
        warn();
        return defaultValue;
    }

    /**
     * @return false {@inheritDoc}
     */
    @Override
    public boolean getBooleanProperty(final String name) {
        warn();
        return false;
    }

    /**
     * @return 0 {@inheritDoc}
     */
    @Override
    public int getIntProperty(final String name) {
        warn();
        return 0;
    }

    /**
     * @return defaultValue {@inheritDoc}
     */
    @Override
    public int getIntProperty(final String name, final int defaultValue) {
        warn();
        return defaultValue;
    }

    /**
     * @return empty array {@inheritDoc}
     */
    @Override
    public int[] getIntArrayProperty(final String name) {
        warn();
        return new int[0];
    }

    /**
     * @return 0.0 {@inheritDoc}
     */
    @Override
    public float getFloatProperty(final String name) {
        warn();
        return 0f;
    }

    /**
     * @return defaultValue {@inheritDoc}
     */
    @Override
    public float getFloatProperty(final String name, final float defaultValue) {
        warn();
        return defaultValue;
    }

    /**
     * @return empty array {@inheritDoc}
     */
    @Override
    public float[] getFloatArrayProperty(final String name) {
        warn();
        return new float[0];
    }

    /**
     * @return default {@link DateFormat} instance from system {@inheritDoc}
     */
    @Override
    public DateFormat getDateFormat(final String name) {
        warn();
        return DateFormat.getDateInstance();
    }

    /**
     * @return default {@link Font} using Helvetica font family {@inheritDoc}
     */
    @Override
    public Font getFont(final String name) {
        warn();
        final Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setFamily(FontFactory.HELVETICA);
        return font;
    }

    /**
     * @return {@link BaseColor.BLACK} color {@inheritDoc}
     */
    @Override
    public BaseColor getColor(final String name) {
        warn();
        return BaseColor.BLACK;
    }
}
