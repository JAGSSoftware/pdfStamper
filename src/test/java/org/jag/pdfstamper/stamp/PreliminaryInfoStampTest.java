/*
 * Copyright (C) 2013 Jose A. Garcia Sanchez
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
package org.jag.pdfstamper.stamp;

import static com.google.common.truth.Truth.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jose A. Garcia
 */
public class PreliminaryInfoStampTest {
    private PreliminaryInfoStamp.Builder underTest;

    @Before
    public void setUp() {
        underTest = new PreliminaryInfoStamp.Builder();
    }

    @Test
    public void createEmptyInfo() {
        final PreliminaryInfoStamp infoStamp = underTest.build();

        assertThat(infoStamp).isNotNull();
        assertThat(infoStamp.itemId()).isEmpty();
        assertThat(infoStamp.itemRevisionId()).isEmpty();
        assertThat(infoStamp.creationDate()).isNull();
    }

    @Test
    public void createInfoWithItemId() {
        final PreliminaryInfoStamp infoStamp = underTest.itemId("ITEM-ID#13").build();

        assertThat(infoStamp).isNotNull();
        assertThat(infoStamp.itemId()).isNotEmpty();
        assertThat(infoStamp.itemId()).isEqualTo("ITEM-ID#13");
        assertThat(infoStamp.itemRevisionId()).isEmpty();
        assertThat(infoStamp.creationDate()).isNull();
    }

    @Test
    public void createInfoWithItemRevisionId() {
        final PreliminaryInfoStamp infoStamp = underTest.itemRevisionId("ITEM-REVID#45").build();

        assertThat(infoStamp).isNotNull();
        assertThat(infoStamp.itemId()).isEmpty();
        assertThat(infoStamp.itemRevisionId()).isNotEmpty();
        assertThat(infoStamp.itemRevisionId()).isEqualTo("ITEM-REVID#45");
        assertThat(infoStamp.creationDate()).isNull();
    }

    @Test
    public void createInfoWithCreationDate() {
        final Date creationDate = new Date();
        final PreliminaryInfoStamp infoStamp = underTest.creationDate(creationDate).build();

        assertThat(infoStamp).isNotNull();
        assertThat(infoStamp.itemId()).isEmpty();
        assertThat(infoStamp.itemRevisionId()).isEmpty();
        assertThat(infoStamp.creationDate()).isNotNull();
        assertThat(infoStamp.creationDate()).isEqualTo(creationDate);
        assertThat(infoStamp.creationDate()).isNotSameAs(creationDate);
    }
}
