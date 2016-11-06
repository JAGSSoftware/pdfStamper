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

import java.io.IOException;
import java.text.DateFormat;
import java.util.logging.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * Enumeration with the configuration for the different modes of operation of the utility. Each value loads its own
 * configuration properties and makes them available to the utility. In case of a property loading error, it internally
 * loads a default configuration. To load the appropriate configuration, just make use of the corresponding enumeration
 * value: <code>
 * Configuration configuration = Configuration.INSTANCE_RELEASE;
 * </code>
 *
 * @author Jose A. Garcia Sanchez
 */
public enum Configuration implements StamperBundle {
    /** Configuration for Release stamping. */
    INSTANCE_RELEASE("release_configuration"),

    /** Configuration for Preliminary stamping. */
    INSTANCE_PRELIMINARY("preliminary_configuration"),

    /** Configuration for Watermark stamping. */
    INSTANCE_WATERMARK("watermark_configuration");

    private static final Logger LOGGER = Logger.getLogger("pdfStamper");
    private StamperBundle bundle;

    /**
     * Constructor.
     * 
     * @param configurationFile Configuration file
     */
    private Configuration(final String configurationFile) {
        try {
            bundle = new BundleLoader(configurationFile);
        } catch (IOException e) {
            Logger.getLogger("pdfStamper").warning(e.getMessage());
            bundle = new NullStamperBundle();
        }
    }

    @Override
    public String getProperty(final String name) {
        return bundle.getProperty(name);
    }

    @Override
    public String getProperty(final String name, final String defaultValue) {
        return bundle.getProperty(name, defaultValue);
    }

    @Override
    public boolean getBooleanProperty(final String name) {
        try {
            return bundle.getBooleanProperty(name);
        } catch (NullPointerException e) {
            warning(name);
            throw e;
        }
    }

    /**
     * Writes a warning entry in log with the name of the property.
     * 
     * @param propertyName Name of the falling property
     */
    private void warning(final String propertyName) {
        LOGGER.warning(String.format("Property [%s] not found in the configuration properties", propertyName));
    }

    @Override
    public int getIntProperty(final String name) {
        return bundle.getIntProperty(name);
    }

    @Override
    public int getIntProperty(final String name, final int defaultValue) {
        return bundle.getIntProperty(name, defaultValue);
    }

    @Override
    public int[] getIntArrayProperty(final String name) {
        return bundle.getIntArrayProperty(name);
    }

    @Override
    public float getFloatProperty(final String name) {
        try {
            return bundle.getFloatProperty(name);
        } catch (NullPointerException e) {
            warning(name);
            throw e;
        }
    }

    @Override
    public float getFloatProperty(final String name, final float defaultValue) {
        return bundle.getFloatProperty(name, defaultValue);
    }

    @Override
    public float[] getFloatArrayProperty(final String name) {
        return bundle.getFloatArrayProperty(name);
    }

    @Override
    public DateFormat getDateFormat(final String name) {
        try {
            return bundle.getDateFormat(name);
        } catch (NullPointerException e) {
            warning(name);
            throw e;
        }
    }

    @Override
    public Font getFont(final String name) {
        return bundle.getFont(name);
    }

    @Override
    public BaseColor getColor(final String name) {
        return bundle.getColor(name);
    }
}
