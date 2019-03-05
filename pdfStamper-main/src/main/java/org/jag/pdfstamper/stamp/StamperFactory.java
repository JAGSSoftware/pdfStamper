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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Jose A. Garcia
 */
public final class StamperFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger("pdfStamper");

    /**
     * Constructor.
     */
    private StamperFactory() {
    }

    /**
     * @param stampType     Type of stamper
     * @param stampFilename Filename with the stamp information
     * @return stamper
     */
    public static Stamper newInstance(final StampType stampType, final String stampFilename) {
        Stamper stamper = null;
        try {
            switch (stampType) {
                case RELEASE:
                    stamper = new ReleaseStamper(stampFilename);
                    break;
                case PRELIMINARY:
                    stamper = new PreliminaryStamper(stampFilename);
                    break;
                case WATERMARK:
                    stamper = new WatermarkStamper(stampFilename);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("StampType [%s] not valid", stampType));
            }
        } catch (FileNotFoundException e) {
            LOGGER.warn(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return stamper;
    }
}
