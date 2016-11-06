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
