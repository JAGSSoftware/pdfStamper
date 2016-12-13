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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

/**
 * Implementation of the bundle configuration loader.
 *
 * @author Jose A. Garcia Sanchez
 */
class BundleLoader implements StamperBundle {
    private static final Logger LOGGER = LoggerFactory.getLogger("pdfstamper");
    private static final String SEPARATOR = ",";

    private final Properties properties;

    /**
     * @param filename Filename with the configuration properties
     * @throws IOException Exception when loading the file
     */
    BundleLoader(final String filename) throws IOException {
        properties = new Properties();
        properties.load(ClassLoader.getSystemResourceAsStream(getDecoratedFilename(filename)));
        properties.load(ClassLoader.getSystemResourceAsStream(getDecoratedFilename(filename + "_locale")));
    }

    /**
     * @param filename Filename to be decorated with the extension
     * @return Decorated filename (filename + extension)
     */
    private String getDecoratedFilename(final String filename) {
        return filename + ".properties";
    }

    @Override
    public String getProperty(final String name) {
        final String property = properties.getProperty(name);
        if (property == null) {
            LOGGER.warn(String.format("%s property not found", name));
        }
        return property;
    }

    @Override
    public String getProperty(final String name, final String defaultValue) {
        return properties.getProperty(name, defaultValue);
    }

    @Override
    public int getIntProperty(final String name) {
        return Integer.valueOf(getProperty(name));
    }

    @Override
    public int getIntProperty(final String name, final int defaultValue) {
        int value;
        try {
            value = getIntProperty(name);
        } catch (NumberFormatException e) {
            LOGGER.warn(e.getMessage(), e);
            value = defaultValue;
        }

        return value;
    }

    @Override
    public int[] getIntArrayProperty(final String name) {
        final String property = getProperty(name);

        if (property == null) {
            return new int[0];
        } else {
            final List<String> tokens = tokensFromArrayProperty(getProperty(name));
            final int[] array = new int[tokens.size()];
            for (int i = 0, n = tokens.size(); i < n; i++) {
                array[i] = Integer.valueOf(tokens.get(i));
            }

            return array;
        }
    }

    /**
     * @param property Property value to be tokenized
     * @return List with the tokens from the property
     */
    public final List<String> tokensFromArrayProperty(final String property) {
        final List<String> tokens = new ArrayList<>();

        final StringTokenizer tokenizer = new StringTokenizer(property, SEPARATOR);

        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken().trim());
        }

        return tokens;
    }

    @Override
    public boolean getBooleanProperty(final String name) {
        final boolean value;
        final String property = getProperty(name);
        if (property == null) {
            throw new NullPointerException(String.format("property [%s] is null", name));
        } else {
            value = Boolean.valueOf(getProperty(name));
        }
        return value;
    }

    @Override
    public float getFloatProperty(final String name) {
        return Float.valueOf(getProperty(name));
    }

    @Override
    public float getFloatProperty(final String name, final float defaultValue) {
        float value = defaultValue;
        try {
            value = getFloatProperty(name);
        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.warn(e.getMessage(), e);
        }

        return value;
    }

    @Override
    public float[] getFloatArrayProperty(final String name) {
        final String property = getProperty(name);

        if (property == null) {
            return new float[0];
        } else {
            final List<String> tokens = tokensFromArrayProperty(getProperty(name));
            final float[] array = new float[tokens.size()];

            for (int i = 0, n = tokens.size(); i < n; i++) {
                array[i] = Float.valueOf(tokens.get(i));
            }

            return array;
        }
    }

    @Override
    public DateFormat getDateFormat(final String name) {
        return new SimpleDateFormat(getProperty(name), getLocale());
    }

    /**
     * @return
     */
    private Locale getLocale() {
        return new Locale(System.getProperty("user.language"));
    }

    @Override
    public Font getFont(final String name) {
        final String basefontName = getProperty(name + ".font.family");

        final Font font;
        if (basefontName == null) {
            font = null;
        } else {
            font = new Font(getBaseFont(basefontName));
            font.setSize(getFloatProperty(name + ".font.size"));
            font.setStyle(getIntProperty(name + ".font.style", 0));
            font.setColor(getColor(name + ".font.color"));
        }

        return font;
    }

    /**
     * @param name Name of the Base Font to be loaded
     * @return {@link Font} property
     */
    private Font getBaseFont(final String name) {
        return FontFactory.getFont(getProperty(name), getProperty(name + ".encoding"),
                getBooleanProperty(name + ".embedded"));
    }

    @Override
    public BaseColor getColor(final String name) {
        final String colorName = getProperty(name);

        final BaseColor baseColor;
        if (colorName == null) {
            baseColor = null;
        } else {
            final int[] configuration = getIntArrayProperty(colorName);
            baseColor = new BaseColor(configuration[0], configuration[1], configuration[2]);
        }

        return baseColor;
    }
}
