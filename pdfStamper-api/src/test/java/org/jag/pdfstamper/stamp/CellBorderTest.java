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

import com.itextpdf.text.pdf.PdfPCell;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @author Jose A. Garcia
 */
public class CellBorderTest {

    @Test
    public void left() {
        assertThat(CellBorder.LEFT.value()).isEqualTo(PdfPCell.LEFT);
        assertEquals(CellBorder.LEFT.value(), PdfPCell.LEFT);
    }

    @Test
    public void right() {
        assertThat(CellBorder.RIGHT.value()).isEqualTo(PdfPCell.RIGHT);
        assertEquals(CellBorder.RIGHT.value(), PdfPCell.RIGHT);
    }

    @Test
    public void top() {
        assertThat(CellBorder.TOP.value()).isEqualTo(PdfPCell.TOP);
        assertEquals(CellBorder.TOP.value(), PdfPCell.TOP);
    }

    @Test
    public void bottom() {
        assertThat(CellBorder.BOTTOM.value()).isEqualTo(PdfPCell.BOTTOM);
        assertEquals(CellBorder.BOTTOM.value(), PdfPCell.BOTTOM);
    }
}
