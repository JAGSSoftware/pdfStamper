/*
 * (c) 2013 - Jose A. Garcia Sanchez
 */
package org.jag.pdfstamper.conf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;

/**
 * @author Jose A. Garcia
 */
public class NullStamperBundleTest {
    private static final float PRECISION = 0.00001f;
    private NullStamperBundle bundle;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bundle = new NullStamperBundle();
    }

    /**
     * Test method for {@link NullStamperBundle#getProperty(java.lang.String)}.
     */
    @Test
    public void testGetPropertyString() {
        final String property = bundle.getProperty("property");
        assertNotNull(property);
        assertEquals("", property);
    }

    /**
     * Test method for {@link NullStamperBundle#getProperty(java.lang.String)}.
     */
    @Test
    public void testGetPropertyStringNull() {
        final String property = bundle.getProperty("propertyNull");
        assertNotNull("is null", property);
        assertEquals("not blank", "", property);
    }

    /**
     * Test method for {@link NullStamperBundle#getProperty(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testGetPropertyStringString() {
        final String defaultValue = "defaultValue";
        final String property = bundle.getProperty("property", defaultValue);
        assertNotNull("is null", property);
        assertEquals("is not the default value", defaultValue, property);
    }

    /**
     * Test method for {@link NullStamperBundle#getBooleanProperty(java.lang.String)}.
     */
    @Test
    public void testGetBooleanProperty() {
        assertFalse(bundle.getBooleanProperty("property"));
    }

    /**
     * Test method for {@link NullStamperBundle#getIntProperty(java.lang.String)}.
     */
    @Test
    public void testGetIntPropertyString() {
        assertEquals(0, bundle.getIntProperty("property"));
    }

    /**
     * Test method for {@link NullStamperBundle#getIntProperty(java.lang.String, int)}.
     */
    @Test
    public void testGetIntPropertyStringInt() {
        final int defaultValue = 10;
        assertEquals(defaultValue, bundle.getIntProperty("property", defaultValue));
    }

    /**
     * Test method for {@link NullStamperBundle#getIntArrayProperty(java.lang.String)}.
     */
    @Test
    public void testGetIntArrayProperty() {
        assertEquals(0, bundle.getIntArrayProperty("property").length);
    }

    /**
     * Test method for {@link NullStamperBundle#getFloatProperty(java.lang.String)}.
     */
    @Test
    public void testGetFloatPropertyString() {
        assertEquals(0f, bundle.getFloatProperty("property"), PRECISION);
    }

    /**
     * Test method for {@link NullStamperBundle#getFloatProperty(java.lang.String, float)}.
     */
    @Test
    public void testGetFloatPropertyStringFloat() {
        final float defaultValue = 1.23456f;
        assertEquals(defaultValue, bundle.getFloatProperty("property", defaultValue), PRECISION);
    }

    /**
     * Test method for {@link NullStamperBundle#getFloatArrayProperty(java.lang.String)}.
     */
    @Test
    public void testGetFloatArrayProperty() {
        assertEquals(0, bundle.getFloatArrayProperty("property").length);
    }

    /**
     * Test method for {@link NullStamperBundle#getDateFormat(java.lang.String)}.
     */
    @Test
    public void testGetDateFormat() {
        final DateFormat dateFormat = bundle.getDateFormat("property");

        assertNotNull("is null", dateFormat);
        assertEquals("is not system DateFormat", DateFormat.getDateInstance(), dateFormat);
    }

    /**
     * Test method for {@link NullStamperBundle#getFont(java.lang.String)}.
     */
    @Test
    public void testGetFont() {
        final Font font = bundle.getFont("property");
        assertNotNull(font);
        assertEquals(FontFamily.HELVETICA, font.getFamily());
    }

    /**
     * Test method for {@link NullStamperBundle#getColor(java.lang.String)}.
     */
    @Test
    public void testGetColor() {
        final BaseColor color = bundle.getColor("property");
        assertNotNull("is null", color);
        assertEquals("is not BLACK", BaseColor.BLACK, color);
    }

}
