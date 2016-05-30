/*
 * (c) 2013 - Areva Wind DE
 */
package org.jag.pdfstamper.stamp;

import com.itextpdf.text.pdf.PdfStamper;

/**
 * Implements a NullObject pattern for the interface {@link WatermarkDecorator}
 * @author Jose A. Garcia
 */
final class NullWatermarkDecorator implements WatermarkDecorator {

    @Override
    public void writePage(final PdfStamper pdfStamper, int pageNum) {
        // It doesn't do anything, just an empty body.
    }
}
