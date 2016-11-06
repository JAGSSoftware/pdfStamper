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

import com.itextpdf.text.pdf.PdfPCell;

/**
 * Enumeration with the four types of cell borders.
 * 
 * @author Jose A. Garcia
 */
enum CellBorder {
    LEFT(PdfPCell.LEFT), RIGHT(PdfPCell.RIGHT), TOP(PdfPCell.TOP), BOTTOM(PdfPCell.BOTTOM);

    private final int value;

    /**
     * @param value internal value of the enumeration element
     */
    private CellBorder(final int value) {
        this.value = value;
    }

    /**
     * @return internal value of the enumeration element
     */
    public int value() {
        return value;
    }
}
