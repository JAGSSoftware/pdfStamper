/*
 * (c) 2013 - Jose A. Garcia
 */
package org.jag.pdfstamper.conf;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * @author Jose A. Garcia
 */
public class BundleLoaderTest {
    private static final float PRECISION = .0001f;
    private BundleLoader bundle;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bundle = new BundleLoader("test");
    }

    /**
     * Test method for {@link BundleLoader#getProperty(java.lang.String)}.
     */
    @Test
    public void testGetPropertyString() {
        assertEquals("Property String", bundle.getProperty("stringProperty"));
    }

    /**
     * Test method for {@link BundleLoader#getProperty(java.lang.String)}.
     */
    @Test
    public void testGetPropertyStringNull() {
        assertNull(bundle.getProperty("stringPropertyNull"));
    }

    /**
     * Test method for {@link BundleLoader#getProperty(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testGetPropertyStringString() {
        assertEquals("Property String", bundle.getProperty("stringProperty", "Default value"));
    }

    /**
     * Test method for {@link BundleLoader#getProperty(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testGetPropertyStringStringNull() {
        assertEquals("Default value", bundle.getProperty("stringPropertyNull", "Default value"));
    }

    /**
     * Test method for {@link BundleLoader#getIntProperty(java.lang.String)}.
     */
    @Test
    public void testGetIntPropertyString() {
        assertEquals(1, bundle.getIntProperty("intProperty"));
    }

    /**
     * Test method for {@link BundleLoader#getIntProperty(java.lang.String)}.
     */
    @Test(expected = NumberFormatException.class)
    public void testGetIntPropertyStringNull() {
        bundle.getIntProperty("intPropertyNull");
    }

    /**
     * Test method for {@link BundleLoader#getIntProperty(java.lang.String, int)}.
     */
    @Test
    public void testGetIntPropertyStringInt() {
        assertEquals(1, bundle.getIntProperty("intProperty", 100));
    }

    /**
     * Test method for {@link BundleLoader#getIntProperty(java.lang.String, int)}.
     */
    @Test
    public void testGetIntPropertyStringIntNull() {
        assertEquals(100, bundle.getIntProperty("intPropertyNull", 100));
    }

    /**
     * Test method for {@link BundleLoader#getIntArrayProperty(java.lang.String)}.
     */
    @Test
    public void testGetIntArrayProperty() {
        final int[] expected = {1, 2, 3};

        assertArrayEquals(expected, bundle.getIntArrayProperty("intArrayProperty"));
    }

    /**
     * Test method for {@link BundleLoader#getIntArrayProperty(java.lang.String)}.
     */
    @Test
    public void testGetIntArrayPropertyNull() {
        assertArrayEquals(new int[0], bundle.getIntArrayProperty("intArrayPropertyNull"));
    }

    /**
     * Test method for {@link BundleLoader#getIntArrayProperty(java.lang.String)}.
     */
    @Test
    public void testGetIntArrayProperty1() {
        final int[] expected = {1977};

        assertArrayEquals(expected, bundle.getIntArrayProperty("intArrayProperty1"));
    }

    @Test
    public void testTokensFromArrayProperty() {
        final List<String> tokens = bundle.tokensFromArrayProperty("123,456, 789");
        final String[] expected = new String[]{"123", "456", "789"};

        assertArrayEquals(expected, tokens.toArray(new String[tokens.size()]));
    }

    /**
     * Test method for {@link BundleLoader#getBooleanProperty(java.lang.String)}.
     */
    @Test
    public void testGetBooleanProperty() {
        assertTrue("true", bundle.getBooleanProperty("trueProperty_true"));
        assertTrue("TRUE", bundle.getBooleanProperty("trueProperty_TRUE"));
        assertFalse("false", bundle.getBooleanProperty("falseProperty_false"));
        assertFalse("FALSE", bundle.getBooleanProperty("falseProperty_FALSE"));
    }

    /**
     * Test method for {@link BundleLoader#getBooleanProperty(java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testGetBooleanPropertyNull() {
        bundle.getBooleanProperty("booleanPropertyNull");
    }

    /**
     * Test method for {@link BundleLoader#getBooleanProperty(java.lang.String)}.
     */
    @Test
    public void testGetBooleanPropertyOtherValue() {
        assertFalse(bundle.getBooleanProperty("booleanPropertyOther"));
    }

    /**
     * Test method for {@link BundleLoader#getFloatProperty(java.lang.String)}.
     */
    @Test
    public void testGetFloatPropertyString() {
        assertEquals(1.2f, bundle.getFloatProperty("floatProperty"), PRECISION);
    }

    /**
     * Test method for {@link BundleLoader#getFloatProperty(java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testGetFloatPropertyStringNull() {
        bundle.getFloatProperty("floatPropertyNull");
    }

    @Test(expected = NumberFormatException.class)
    public void testGetFloatPropertyStringFormatException() {
        bundle.getFloatProperty("floatProperty_FormatException");
    }

    /**
     * Test method for {@link BundleLoader#getFloatProperty(java.lang.String, float)}.
     */
    @Test
    public void testGetFloatPropertyStringFloat() {
        assertEquals(1.2f, bundle.getFloatProperty("floatProperty", 2.3f), PRECISION);
    }

    /**
     * Test method for {@link BundleLoader#getFloatProperty(java.lang.String, float)}.
     */
    @Test
    public void testGetFloatPropertyStringFloatFormatException() {
        assertEquals(2.3f, bundle.getFloatProperty("floatProperty_FormatException", 2.3f), PRECISION);
    }

    /**
     * Test method for {@link BundleLoader#getFloatProperty(java.lang.String, float)}.
     */
    @Test
    public void testGetFloatPropertyStringFloatNull() {
        assertEquals(2.3f, bundle.getFloatProperty("floatPropertyNull", 2.3f), PRECISION);
    }

    /**
     * Test method for {@link BundleLoader#getFloatArrayProperty(java.lang.String)}.
     */
    @Test
    public void testGetFloatArrayProperty() {
        final float[] expected = {1.1f, 2.2f, 3.3f};
        assertArrayFloatEquals(expected, bundle.getFloatArrayProperty("floatArrayProperty"));
    }

    /**
     * Test method for {@link BundleLoader#getFloatArrayProperty(java.lang.String)}.
     */
    @Test
    public void testGetFloatArrayPropertyNull() {
        assertArrayFloatEquals(new float[0], bundle.getFloatArrayProperty("floatArrayPropertyNull"));
    }

    /**
     * Test method for {@link BundleLoader#getDateFormat(java.lang.String)}.
     */
    @Test
    public void testGetDateFormat() {
        assertNotNull(bundle.getDateFormat("dateformatProperty"));
    }

    /**
     * Test method for {@link BundleLoader#getDateFormat(java.lang.String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testGetDateFormatNull() {
        assertNotNull(bundle.getDateFormat("dateformatPropertyNull"));
    }

    /**
     * Test method for {@link BundleLoader#getFont(java.lang.String)}.
     */
    @Test
    public void testGetFont() {
        final Font font = bundle.getFont("fontProperty");
        assertNotNull(font);
    }

    /**
     * Test method for {@link BundleLoader#getFont(java.lang.String)}.
     */
    @Test
    public void testGetFontNull() {
        final Font font = bundle.getFont("fontPropertyNull");
        assertNull(font);
    }

    /**
     * Test method for {@link BundleLoader#getColor(java.lang.String)}.
     */
    @Test
    public void testGetColor() {
        final BaseColor color = bundle.getColor("colorProperty");
        assertNotNull(color);
    }

    /**
     * Test method for {@link BundleLoader#getColor(java.lang.String)}.
     */
    @Test
    public void testGetColorNull() {
        final BaseColor color = bundle.getColor("colorPropertyNull");
        assertNull(color);
    }

    /**
     * @param expected
     * @param actual
     */
    private void assertArrayFloatEquals(final float[] expected, final float[] actual) {
        assertEquals("size", expected.length, actual.length);
        for (int i = 0, size = expected.length; i < size; i++) {
            assertEquals("[" + i + "]", expected[i], actual[i], PRECISION);
        }
    }
}
