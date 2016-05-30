/*
 * (c) 2013 - Areva Wind DE
 */
package org.jag.pdfstamper.stamp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * @author Jose A. Garcia
 */
abstract class AbstractStampWriter implements StampWriter {
    private PdfStamper pdfStamper;
    private final PdfReader pdfReader;

    /**
     * @param pdfReader PdfReader object
     */
    protected AbstractStampWriter(final PdfReader pdfReader) {
        this.pdfReader = pdfReader;
    }

    /*
     * (non-Javadoc)
     * @see de.areva.pdfstamper.stamp.StampWriter#close()
     */
    @Override
    public final void close() throws DocumentException, IOException {
        pdfStamper.close();
    }

    /**
     * Get the pdfStamper object from pdfReader to be written in {@code outputFile}, write the new contents while
     * encrypting them.
     */
    @Override
    public final void write(final String outputFile) throws FileNotFoundException, DocumentException, IOException {
        pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(outputFile));

        write();
    }

    /**
     * @return PdfStamper object
     */
    protected final PdfStamper pdfStamper() {
        return pdfStamper;
    }

    /**
     *
     */
    protected abstract void write();
}
