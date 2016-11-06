/*
 * (c) 2014 - Jose A. Garcia Sanchez
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
