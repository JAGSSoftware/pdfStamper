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
package org.jag.pdfstamper.stamp;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static com.google.common.truth.Truth.assertThat;

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
