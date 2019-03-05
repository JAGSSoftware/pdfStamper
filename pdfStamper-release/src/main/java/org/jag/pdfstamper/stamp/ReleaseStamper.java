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
import org.jag.pdfstamper.conf.ReleaseConfigurationFactory;
import org.jag.pdfstamper.conf.StamperBundle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Jose A. Garcia
 */
class ReleaseStamper implements Stamper {
    private static final Logger LOGGER = Logger.getLogger("pdfStamper");
    private static final StamperBundle CONFIGURATION = ReleaseConfigurationFactory.getInstance().getStamperBundle();
    private final ReleaseInfoStamp infoStamp;
    private final String watermark;

    /**
     * @param stampFilename
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ReleaseStamper(final String stampFilename) throws FileNotFoundException, IOException {
        final Properties properties = new Properties();
        properties.load(new FileInputStream(stampFilename));

        // infoStamp = new ReleaseInfoStamp.Builder().creator(properties.getProperty("creator"))
        // .reviewer(properties.getProperty("reviewer")).approver(properties.getProperty("approver"))
        // .itemId(properties.getProperty("itemId")).itemRevisionId(properties.getProperty("itemRevisionId"))
        // .approvalDate(getApprovalDateFromProperties(properties)).build();
        infoStamp = new ReleaseInfoStamp(properties);
        watermark = properties.getProperty("watermark");
    }

    /**
     * @param properties
     * @return
     */
    public Date getApprovalDateFromProperties(final Properties properties) {
        Date approvalDate;
        try {
            approvalDate = new SimpleDateFormat(CONFIGURATION.getProperty("input.date.FORMAT"))
                    .parse(properties.getProperty("approvalDate"));
        } catch (ParseException e) {
            LOGGER.warning(e.getMessage());
            approvalDate = new Date();
        }

        return approvalDate;
    }

    /**
     * @param pdfReader
     * @return
     */
    @Override
    public StampWriter stamp(final PdfReader pdfReader) {
        return new ReleaseStampWriter(infoStamp, pdfReader, createDecorator(pdfReader));
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
