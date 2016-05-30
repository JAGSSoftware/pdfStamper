/*
 * (c) 2013 - Areva Wind DE
 */
package org.jag.pdfstamper.stamp;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

/**
 * @author Jose A. Garcia
 */
public interface StampWriter {
    /**
     * @throws DocumentException
     * @throws IOException
     */
    void close() throws DocumentException, IOException;

    /**
     * @param outputFile
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws IOException
     */
    void write(final String outputFile) throws FileNotFoundException, DocumentException, IOException;
}
