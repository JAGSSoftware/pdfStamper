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

import java.text.DateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * @author Jose A. Garcia
 */
public class ConfigurationProxy implements StamperBundle {
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
