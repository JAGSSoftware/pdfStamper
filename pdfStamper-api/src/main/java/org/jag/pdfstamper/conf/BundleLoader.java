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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

        final InputStream configurationResource = ClassLoader.getSystemResourceAsStream(getDecoratedFilename(filename));
        if (configurationResource != null) {
            properties.load(configurationResource);
        }
        final InputStream localeConfigurationResource = ClassLoader
                .getSystemResourceAsStream(getDecoratedFilename(filename + "_locale"));
        if (localeConfigurationResource != null) {
            properties.load(localeConfigurationResource);
        }
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
