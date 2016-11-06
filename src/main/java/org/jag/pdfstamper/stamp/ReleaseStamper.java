/*
 * (c) 2013 - Jose A. Garcia Sanchez
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

import org.jag.pdfstamper.conf.Configuration;

import com.itextpdf.text.pdf.PdfReader;

/**
 * @author Jose A. Garcia
 */
class ReleaseStamper implements Stamper {
    private static final Logger LOGGER = Logger.getLogger("pdfStamper");
    private static final Configuration CONFIGURATION = Configuration.INSTANCE_RELEASE;
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

        infoStamp = new ReleaseInfoStamp.Builder().creator(properties.getProperty("creator"))
                .reviewer(properties.getProperty("reviewer")).approver(properties.getProperty("approver"))
                .itemId(properties.getProperty("itemId")).itemRevisionId(properties.getProperty("itemRevisionId"))
                .approvalDate(getApprovalDateFromProperties(properties)).build();
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
