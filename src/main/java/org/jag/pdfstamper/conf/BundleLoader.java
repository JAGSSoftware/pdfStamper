/*
 * (c) 2013 - Jose A. Garcia
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
import java.util.logging.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

/**
 * Implementation of the bundle configuration loader.
 *
 * @author Jose A. Garcia Sanchez
 */
class BundleLoader implements StamperBundle {
    private static final Logger LOGGER = Logger.getLogger("pdfstamper");
    private static final String SEPARATOR = ",";

    private final transient Properties properties;

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

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getProperty(java.lang.String)
     */
    @Override
    public String getProperty(final String name) {
        final String property = properties.getProperty(name);
        if (property == null) {
            LOGGER.warning(String.format("%s property not found", name));
        }
        return property;
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getProperty(java.lang.String, java.lang.String)
     */
    @Override
    public String getProperty(final String name, final String defaultValue) {
        return properties.getProperty(name, defaultValue);
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getIntProperty(java.lang.String)
     */
    @Override
    public int getIntProperty(final String name) {
        return Integer.valueOf(getProperty(name));
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getIntProperty(java.lang.String, int)
     */
    @Override
    public int getIntProperty(final String name, final int defaultValue) {
        int value;
        try {
            value = getIntProperty(name);
        } catch (NumberFormatException e) {
            LOGGER.warning(e.getMessage());
            value = defaultValue;
        }

        return value;
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getIntArrayProperty(java.lang.String)
     */
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
        final List<String> tokens = new ArrayList<String>();

        final StringTokenizer tokenizer = new StringTokenizer(property, SEPARATOR);

        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken().trim());
        }

        return tokens;
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getBooleanProperty(java.lang.String)
     */
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

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getFloatProperty(java.lang.String)
     */
    @Override
    public float getFloatProperty(final String name) {
        return Float.valueOf(getProperty(name));
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getFloatProperty(java.lang.String, float)
     */
    @Override
    public float getFloatProperty(final String name, final float defaultValue) {
        float value = defaultValue;
        try {
            value = getFloatProperty(name);
        } catch (NumberFormatException e) {
            LOGGER.warning(e.getMessage());
        } catch (NullPointerException e) {
            LOGGER.warning(e.getMessage());
        }

        return value;
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getFloatArrayProperty(java.lang.String)
     */
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

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getDateFormat()
     */
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

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getBasefont(java.lang.String)
     */
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

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getColor(java.lang.String)
     */
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
