/*
 * (c) 2013 - Autoneum Holding AG
 */
package org.jag.pdfstamper.conf;

import java.text.DateFormat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * Definition of the bundle configuration for stamping.
 * It contains different methods to retrieve different properties from configuration.
 *
 * @author Jose A. Garcia Sanchez
 * @opt hide com.itextpdf.*
 */
interface StamperBundle {
    /**
     * Returns the value of the property passed as argument.
     *
     * @param name Name of the property
     * @return value of the property, {@code null} if the property does not exist
     */
    String getProperty(String name);

    /**
     * Returns the value of the property passed as argument, or defaultValue if the property is not loaded.
     *
     * @param name Name of the property
     * @param defaultValue Default value when the property is not found
     * @return value of the property
     */
    String getProperty(String name, String defaultValue);

    /**
     * Returns the value of the boolean property passed as argument.
     *
     * @param name Name of the boolean property to retrieve
     * @return value of the property. If the property is not found or is not a correct boolean value, it returns
     *         {@code false}
     */
    boolean getBooleanProperty(String name);

    /**
     * Returns the value of the integer property passed as argument.
     *
     * @param name Name of the property
     * @return value of the property
     */
    int getIntProperty(String name);

    /**
     * Returns the value of the integer property passed as argument, or defaultValue if the property is not loaded.
     *
     * @param name Name of the property
     * @param defaultValue Default value when the property is not found
     * @return value of the property
     */
    int getIntProperty(String name, int defaultValue);

    /**
     * Returns the value of the integer array property passed as argument. If the property is not found, the returned
     * array has a 0 length.
     *
     * @param name Name of the array property
     * @return array values of the property
     */
    int[] getIntArrayProperty(String name);

    /**
     * Returns the value of the float property passed as argument.
     *
     * @param name Name of the property
     * @return value of the property
     */
    float getFloatProperty(String name);

    /**
     * Returns the value of the float property passed as argument, or defaultValue if the property is not loaded.
     *
     * @param name Name of the property
     * @param defaultValue Default value when the property is not found or is not numeric
     * @return value of the property
     */
    float getFloatProperty(String name, float defaultValue);

    /**
     * Returns the value of the float array property passed as argument. If the property is not found, the returned
     * array has a 0 length.
     *
     * @param name Name of the array property
     * @return array values of the property
     */
    float[] getFloatArrayProperty(String name);

    /**
     * Returns the {@link DateFormat} property passed as argument.
     *
     * @param name Name of the property
     * @return {@link DateFormat} property
     */
    DateFormat getDateFormat(String name);

    /**
     * Returns the {@link Font} property passed as argument.
     *
     * @param name Name of the property
     * @return {@link Font} property or {@code null} if it does not find it
     */
    Font getFont(String name);

    /**
     * Returns the {@link BaseColor} property passed as argument.
     *
     * @param name Name of the property
     * @return {@link BaseColor} property or {@code null} if it does not find it
     */
    BaseColor getColor(String name);
}
