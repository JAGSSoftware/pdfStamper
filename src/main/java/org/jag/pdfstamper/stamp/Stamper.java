/*
 * (c) 2013 - Jose A. Garcia Sanchez
 */
package org.jag.pdfstamper.stamp;

import com.itextpdf.text.pdf.PdfReader;

/**
 * @author Jose A. Garcia
 */
public interface Stamper {
    /**
     * @param pdfReader
     * @return
     */
    StampWriter stamp(final PdfReader pdfReader);
}
