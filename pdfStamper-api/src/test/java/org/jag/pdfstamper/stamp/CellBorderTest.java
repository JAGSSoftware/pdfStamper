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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.itextpdf.text.pdf.PdfPCell;

/**
 * @author Jose A. Garcia
 */
public class CellBorderTest {

    @Test
    public void left() {
        assertEquals(PdfPCell.LEFT, CellBorder.LEFT.value());
    }

    @Test
    public void right() {
        assertEquals(PdfPCell.RIGHT, CellBorder.RIGHT.value());
    }

    @Test
    public void top() {
        assertEquals(PdfPCell.TOP, CellBorder.TOP.value());
    }

    @Test
    public void bottom() {
        assertEquals(PdfPCell.BOTTOM, CellBorder.BOTTOM.value());
    }
}
