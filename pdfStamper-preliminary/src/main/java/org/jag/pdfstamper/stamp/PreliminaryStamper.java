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

import com.itextpdf.text.pdf.PdfReader;
import org.jag.pdfstamper.conf.PreliminaryConfigurationFactory;
import org.jag.pdfstamper.conf.StamperBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author Jose A. Garcia
 */
class PreliminaryStamper implements Stamper {
    private static final Logger LOGGER = LoggerFactory.getLogger("pdfStamper");
    private static final StamperBundle CONFIGURATION = PreliminaryConfigurationFactory.getInstance().getStamperBundle();
    private final PreliminaryInfoStamp infoStamp;
    private final String watermark;
    // private final WatermarkInfoStamp watermarkInfoStamp;

    /**
     * @param stampFilename
     * @throws FileNotFoundException
     * @throws IOException
     */
    public PreliminaryStamper(final String stampFilename) throws FileNotFoundException, IOException {
        final Properties properties = new Properties();
        properties.load(new FileInputStream(stampFilename));

        infoStamp = new PreliminaryInfoStamp.Builder().itemId(properties.getProperty("itemId"))
                .itemRevisionId(properties.getProperty("itemRevisionId"))
                .creationDate(getCreationDateFromProperties(properties)).build();

        this.watermark = properties.getProperty("watermark");
    }

    /**
     * @param properties
     * @return
     */
    public Date getCreationDateFromProperties(final Properties properties) {
        Date approvalDate;
        try {
            approvalDate = new SimpleDateFormat(CONFIGURATION.getProperty("input.date.FORMAT"))
                    .parse(properties.getProperty("creationDate"));
        } catch (ParseException e) {
            LOGGER.warn(e.getMessage(), e);
            approvalDate = new Date();
        }

        return approvalDate;
    }

    @Override
    public StampWriter stamp(final PdfReader pdfReader) {
        return new PreliminaryStampWriter(infoStamp, pdfReader, createDecorator(pdfReader));
    }

    /**
     * @param pdfReader
     * @return
     */
    public WatermarkDecorator createDecorator(final PdfReader pdfReader) {
        final WatermarkDecorator decorator;
        if (watermark == null) {
            decorator = new NullWatermarkDecorator();
        } else {
            decorator = new WatermarkStampWriter(new WatermarkInfoStamp(watermark), pdfReader);
        }

        return decorator;
    }
}
