/*
 * (c) 2013 - Areva Wind DE
 */
package org.jag.pdfstamper.conf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
//import org.powermock.api.mockito.PowerMockito;

import com.itextpdf.text.Font;

/**
 * @author Jose A. Garcia
 */
public class ConfigurationTest {
    private static final float PRECISION = 0.00001f;
    private Configuration configuration;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        configuration = Configuration.INSTANCE_PRELIMINARY;
    }

    /**
     * Test method for {@link Configuration#Configuration(java.lang.String)}.
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void testConfigurationNotFound() throws Exception {
//        final Configuration localConfiguration = Configuration.INSTANCE_WATERMARK;

//        final Field bundle = PowerMockito.field(Configuration.class, "bundle");
//        assertEquals(NullStamperBundle.class, bundle.getType());
    }

    /**
     * Test method for {@link Configuration#getProperty(java.lang.String)}.
     */
    @Test
    public void testGetPropertyString() {
        final String property = configuration.getProperty("border.color");
        assertNotNull("is null", property);
        assertEquals("not equal", "GRAY", property);
    }

    @Test
    public void testGetPropertyStringNull() {
        assertNull(configuration.getProperty("border.color.null"));
    }

    /**
     * Test method for {@link Configuration#getProperty(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testGetPropertyStringString() {
        final String defaultValue = "defaultValue";
        final String property = configuration.getProperty("border.color", defaultValue);

        assertNotNull("is null", property);
        assertEquals("is not equals", "GRAY", property);
    }

    @Test
    public void testGetPropertyStringStringNull() {
        final String defaultValue = "defaultValue";
        final String property = configuration.getProperty("border.color.null", defaultValue);

        assertNotNull("is null", property);
        assertEquals("is not equal", defaultValue, property);
    }

    /**
     * Test method for {@link Configuration#getBooleanProperty(java.lang.String)}.
     */
    @Test
    public void testGetBooleanProperty() {
        assertFalse(configuration.getBooleanProperty("border.color"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetBooleanPropertyNull() {
        configuration.getBooleanProperty("boolean");
    }

    /**
     * Test method for {@link Configuration#getIntProperty(java.lang.String)}.
     */
    @Test
    public void testGetIntPropertyString() {
        final int property = configuration.getIntProperty("border.width");
        assertNotNull("is null", property);
        assertEquals("is not equal", 1, property);
    }

    @Test(expected = NumberFormatException.class)
    public void testGetIntPropertyStringFormatException() {
        assertNull(configuration.getIntProperty("border.width.null"));
    }

    /**
     * Test method for {@link Configuration#getIntProperty(java.lang.String, int)}.
     */
    @Test
    public void testGetIntPropertyStringInt() {
        final int defaultValue = 54;

        assertEquals(1, configuration.getIntProperty("border.width", defaultValue));
    }

    @Test
    public void testGetIntPropertyStringIntNull() {
        final int defaultValue = 54;
        assertEquals(defaultValue, configuration.getIntProperty("border.width.null", defaultValue));
    }

    /**
     * Test method for {@link Configuration#getIntArrayProperty(java.lang.String)}.
     */
    @Test
    public void testGetIntArrayProperty() {
        final int[] expected = new int[] {128, 128, 128 };
        final int[] current = configuration.getIntArrayProperty("GRAY");

        assertArrayEquals(expected, current);
    }

    @Test
    public void testGetIntArrayPropertyNull() {
        assertEquals(0, configuration.getIntArrayProperty("property").length);
    }

    /**
     * Test method for {@link Configuration#getFloatProperty(java.lang.String)}.
     */
    @Test
    public void testGetFloatPropertyString() {
        assertEquals(0.5f, configuration.getFloatProperty("table.xpos"), PRECISION);
    }

    @Test(expected=NullPointerException.class)
    public void testGetFloatPropertyStringNull() {
        configuration.getFloatProperty("table.xpos.null");
    }

    @Test(expected=NumberFormatException.class)
    public void testGetFloatPropertyStringFormatException() {
        configuration.getFloatProperty("GRAY");
    }

    /**
     * Test method for {@link Configuration#getFloatProperty(java.lang.String, float)}.
     */
    @Test
    public void testGetFloatPropertyStringFloat() {
        final float defaultValue = 10.01f;

        assertEquals(defaultValue, configuration.getFloatProperty("table.xpos.null", defaultValue), PRECISION);
    }

    /**
     * Test method for {@link Configuration#getFloatArrayProperty(java.lang.String)}.
     */
    @Test
    public void testGetFloatArrayProperty() {
        final float[] expected = new float[]{128f, 128f, 128f};
        final float[] current = configuration.getFloatArrayProperty("GRAY");

        assertArrayEquals(expected, current);
    }

    @Test
    public void testGetFloatArrayPropertyNull() {
        assertEquals(0, configuration.getFloatArrayProperty("GRAY.null").length);
    }

    /**
     * Test method for {@link Configuration#getDateFormat(java.lang.String)}.
     */
    @Test
    public void testGetDateFormat() {
        final Calendar referenceDate = new GregorianCalendar(2013, Calendar.DECEMBER, 17, 16, 44);
        final DateFormat expected = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final DateFormat dateFormat = configuration.getDateFormat("input.date.FORMAT");

        assertNotNull("is null", dateFormat);
        assertEquals("not equal to", expected.format(referenceDate.getTime()), dateFormat.format(referenceDate.getTime()));
    }

    @Test(expected = NullPointerException.class)
    public void testGetDateFormatNull() {
        assertNull(configuration.getDateFormat("dateFormatNull"));
    }

    /**
     * Test method for {@link Configuration#getFont(java.lang.String)}.
     */
    @Test
    public void testGetFont() {
        final Font font = configuration.getFont("text");
        assertNotNull(font);
    }

    @Test
    public void testGetFontNull() {
        assertNull(configuration.getFont("fontNull"));
    }

    /**
     * Test method for {@link Configuration#getColor(java.lang.String)}.
     */
    @Test
    public void testGetColor() {
        assertNotNull(configuration.getColor("text.font.color"));
    }

    @Test
    public void testGetColorNull() {
        assertNull(configuration.getColor("colorNull"));
    }

    /**
     * @param expected
     * @param current
     */
    private void assertArrayEquals(final int[] expected, final int[] current) {
        assertEquals("dimension", expected.length, current.length);

        for (int i = 0, n = expected.length; i < n; i++) {
            assertEquals(expected[i], current[i]);
        }
    }

    private void assertArrayEquals(final float[] expected, final float[] current) {
        assertEquals("dimension", expected.length, current.length);

        for (int i = 0, n = expected.length; i < n; i++) {
            assertEquals(expected[i], current[i], PRECISION);
        }
    }
}
