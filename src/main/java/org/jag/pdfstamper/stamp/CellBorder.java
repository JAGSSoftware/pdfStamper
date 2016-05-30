/*
 * (c) 2013 - Areva Wind DE
 */
package org.jag.pdfstamper.stamp;

import com.itextpdf.text.pdf.PdfPCell;

/**
 * Enumeration with the four types of cell borders.
 * @author Jose A. Garcia
 */
enum CellBorder {
    LEFT(PdfPCell.LEFT),
    RIGHT(PdfPCell.RIGHT),
    TOP(PdfPCell.TOP),
    BOTTOM(PdfPCell.BOTTOM);

    private final int value;

    /**
     * @param value internal value of the enumeration element
     */
    CellBorder(final int value) {
        this.value = value;
    }

    /**
     * @return internal value of the enumeration element
     */
    public int value() {
        return value;
    }
}
