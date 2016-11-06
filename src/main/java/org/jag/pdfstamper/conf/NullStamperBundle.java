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

import java.text.DateFormat;
import java.util.logging.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

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
