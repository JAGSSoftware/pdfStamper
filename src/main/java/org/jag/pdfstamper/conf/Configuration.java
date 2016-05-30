/*
 * (c) 2013 - Autoneum AG
 */
package org.jag.pdfstamper.conf;

import java.io.IOException;
import java.text.DateFormat;
import java.util.logging.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * Enumeration with the configuration for the different modes of operation of the utility. Each value loads its own
 * configuration properties and makes them available to the utility.
 * In case of a property loading error, it internally loads a default configuration.
 * To load the appropriate configuration, just make use of the corresponding enumeration value:
 * <code><pre>
 * Configuration configuration = Configuration.INSTANCE_RELEASE;
 * </pre></code>
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
     * @param configurationFile Configuration file
     */
    Configuration(final String configurationFile) {
        try {
            bundle = new BundleLoader(configurationFile);
        } catch (IOException e) {
            Logger.getLogger("pdfStamper").warning(e.getMessage());
            bundle = new NullStamperBundle();
        }
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getProperty(java.lang.String)
     */
    @Override
    public String getProperty(final String name) {
        return bundle.getProperty(name);
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getProperty(java.lang.String, java.lang.String)
     */
    @Override
    public String getProperty(final String name, final String defaultValue) {
        return bundle.getProperty(name, defaultValue);
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getBooleanProperty(java.lang.String)
     */
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
     * @param propertyName Name of the falling property
     */
    private void warning(final String propertyName) {
        LOGGER.warning(String.format("Property [%s] not found in the configuration properties", propertyName));
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getIntProperty(java.lang.String)
     */
    @Override
    public int getIntProperty(final String name) {
        return bundle.getIntProperty(name);
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getIntProperty(java.lang.String, int)
     */
    @Override
    public int getIntProperty(final String name, final int defaultValue) {
        return bundle.getIntProperty(name, defaultValue);
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getIntArrayProperty(java.lang.String)
     */
    @Override
    public int[] getIntArrayProperty(final String name) {
        return bundle.getIntArrayProperty(name);
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getFloatProperty(java.lang.String)
     */
    @Override
    public float getFloatProperty(final String name) {
        try {
            return bundle.getFloatProperty(name);
        } catch (NullPointerException e) {
            warning(name);
            throw e;
        }
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getFloatProperty(java.lang.String, float)
     */
    @Override
    public float getFloatProperty(final String name, final float defaultValue) {
        return bundle.getFloatProperty(name, defaultValue);
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getFloatArrayProperty(java.lang.String)
     */
    @Override
    public float[] getFloatArrayProperty(final String name) {
        return bundle.getFloatArrayProperty(name);
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getDateFormat(java.lang.String)
     */
    @Override
    public DateFormat getDateFormat(final String name) {
        try {
            return bundle.getDateFormat(name);
        } catch (NullPointerException e) {
            warning(name);
            throw e;
        }
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getFont(java.lang.String)
     */
    @Override
    public Font getFont(final String name) {
        return bundle.getFont(name);
    }

    /*
     * (non-Javadoc)
     * @see com.autoneum.plm2.pdfstamper.conf.StamperBundle#getColor(java.lang.String)
     */
    @Override
    public BaseColor getColor(final String name) {
        return bundle.getColor(name);
    }
}
