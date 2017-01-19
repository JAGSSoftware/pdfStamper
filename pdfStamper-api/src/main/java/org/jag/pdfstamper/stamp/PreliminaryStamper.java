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

import org.jag.pdfstamper.conf.Configuration;
import org.jag.pdfstamper.conf.StamperBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.pdf.PdfReader;

/**
 * @author Jose A. Garcia
 */
class PreliminaryStamper implements Stamper {
    private static final Logger LOGGER = LoggerFactory.getLogger("pdfStamper");
    private static final StamperBundle CONFIGURATION = Configuration.INSTANCE_PRELIMINARY;
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
