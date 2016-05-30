/*
 * (c) 2013 - Autoneum AG
 */
package org.jag.pdfstamper.conf;

import java.text.DateFormat;
import java.util.logging.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

/**
 * Implementation of the StamperBundle interface following the <a
 * href="http://en.wikipedia.org/wiki/Null_Object_pattern" target="_blank">Null Object</a> pattern, providing default
 * values for the case
 * the real bundle could not be created.
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
     * @return Blank value
     * {@inheritDoc}
     */
    @Override
    public String getProperty(final String name) {
        warn();
        return getProperty(name, "");
    }

    /**
     * @return defaultValue
     * {@inheritDoc}
     */
    @Override
    public String getProperty(final String name, final String defaultValue) {
        warn();
        return defaultValue;
    }

    /**
     * @return false
     * {@inheritDoc}
     */
    @Override
    public boolean getBooleanProperty(final String name) {
        warn();
        return false;
    }

    /**
     * @return 0
     * {@inheritDoc}
     */
    @Override
    public int getIntProperty(final String name) {
        warn();
        return 0;
    }

    /**
     * @return defaultValue
     * {@inheritDoc}
     */
    @Override
    public int getIntProperty(final String name, final int defaultValue) {
        warn();
        return defaultValue;
    }

    /**
     * @return empty array
     * {@inheritDoc}
     */
    @Override
    public int[] getIntArrayProperty(final String name) {
        warn();
        return new int[0];
    }

    /**
     * @return 0.0
     * {@inheritDoc}
     */
    @Override
    public float getFloatProperty(final String name) {
        warn();
        return 0f;
    }

    /**
     * @return defaultValue
     * {@inheritDoc}
     */
    @Override
    public float getFloatProperty(final String name, final float defaultValue) {
        warn();
        return defaultValue;
    }

    /**
     * @return empty array
     * {@inheritDoc}
     */
    @Override
    public float[] getFloatArrayProperty(final String name) {
        warn();
        return new float[0];
    }

    /**
     * @return default {@link DateFormat} instance from system
     * {@inheritDoc}
     */
    @Override
    public DateFormat getDateFormat(final String name) {
        warn();
        return DateFormat.getDateInstance();
    }

    /**
     * @return default {@link Font} using Helvetica font family
     * {@inheritDoc}
     */
    @Override
    public Font getFont(final String name) {
        warn();
        final Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setFamily(FontFactory.HELVETICA);
        return font;
    }

    /**
     * @return {@link BaseColor.BLACK} color
     * {@inheritDoc}
     */
    @Override
    public BaseColor getColor(final String name) {
        warn();
        return BaseColor.BLACK;
    }
}
