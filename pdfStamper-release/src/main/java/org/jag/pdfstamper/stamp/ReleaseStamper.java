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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import org.jag.pdfstamper.conf.ReleaseConfigurationFactory;
import org.jag.pdfstamper.conf.StamperBundle;

import com.itextpdf.text.pdf.PdfReader;

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
