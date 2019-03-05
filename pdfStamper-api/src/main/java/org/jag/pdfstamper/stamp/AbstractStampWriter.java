/*
 * MIT License
 *
 * Copyright (c) 2019 José A. García Sánchez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
