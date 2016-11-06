/*
 * (c) 2013 - Jose A. Garcia Sanchez
 */
package org.jag.pdfstamper.stamp;

import com.itextpdf.text.pdf.PdfStamper;

/**
 * @author Jose A. Garcia
 *
 */
public interface WatermarkDecorator {
    void writePage(final PdfStamper pdfStamper, final int pageNum);
}
