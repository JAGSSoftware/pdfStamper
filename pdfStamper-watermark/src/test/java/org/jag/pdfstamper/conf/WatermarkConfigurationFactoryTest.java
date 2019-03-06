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

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * @author Jose A. Garcia
 */
public class WatermarkConfigurationFactoryTest {

    private WatermarkConfigurationFactory underTest;

    @Before
    public void setUp() throws Exception {
        underTest = WatermarkConfigurationFactory.getInstance();
    }

    @Test
    public void instanceIsNotNull() {
        assertThat(underTest).isNotNull();
    }

    @Test
    public void singletonInstance() {
        final WatermarkConfigurationFactory instance = WatermarkConfigurationFactory.getInstance();

        assertThat(underTest).isEqualTo(instance);
        assertThat(underTest).isSameAs(instance);
    }

    @Test
    public void onlyOneStamperBundle() {
        final WatermarkConfigurationFactory instance = WatermarkConfigurationFactory.getInstance();

        assertThat(underTest.getStamperBundle()).isNotNull();
        assertThat(underTest.getStamperBundle()).isEqualTo(instance.getStamperBundle());
        assertThat(underTest.getStamperBundle()).isSameAs(instance.getStamperBundle());
    }

    @Test
    public void configurationPropertiesDoesNotExist() {
        final WatermarkConfigurationFactory instance = WatermarkConfigurationFactory.getInstance("doesNotExist");

        assertThat(instance).isNotNull();
        assertThat(instance.getStamperBundle()).isNotNull();
    }
}
