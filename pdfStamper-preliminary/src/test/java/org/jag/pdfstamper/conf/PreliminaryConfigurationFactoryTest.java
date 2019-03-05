/*
 * Copyright (C) 2017 Jose A. Garcia Sanchez
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

import static com.google.common.truth.Truth.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jose A. Garcia
 */
public class PreliminaryConfigurationFactoryTest {

    private PreliminaryConfigurationFactory underTest;

    @Before
    public void setUp() throws Exception {
        underTest = PreliminaryConfigurationFactory.getInstance();
    }

    @Test
    public void instanceIsNotNull() {
        assertThat(underTest).isNotNull();
    }

    @Test
    public void singletonInstance() {
        final PreliminaryConfigurationFactory instance = PreliminaryConfigurationFactory.getInstance();

        assertThat(underTest).isEqualTo(instance);
        assertThat(underTest).isSameAs(instance);
    }

    @Test
    public void onlyOneStamperBundle() {
        final PreliminaryConfigurationFactory instance = PreliminaryConfigurationFactory.getInstance();

        assertThat(underTest.getStamperBundle()).isNotNull();
        assertThat(underTest.getStamperBundle()).isEqualTo(instance.getStamperBundle());
        assertThat(underTest.getStamperBundle()).isSameAs(instance.getStamperBundle());
    }

    @Test
    public void configurationPropertiesDoesNotExist() {
        final PreliminaryConfigurationFactory instance = PreliminaryConfigurationFactory.getInstance("doesNotExist");

        assertThat(instance).isNotNull();
        assertThat(instance.getStamperBundle()).isNotNull();
    }
}
