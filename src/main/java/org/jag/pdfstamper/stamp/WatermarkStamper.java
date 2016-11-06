/*
 * (c) 2013 - Jose A. Garcia Sanchez
 */
package org.jag.pdfstamper.stamp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.itextpdf.text.pdf.PdfReader;

/**
 * @author Jose A. Garcia
 */
class WatermarkStamper implements Stamper {
    private final WatermarkInfoStamp infoStamp;

    /**
     * @param stampFilename
     * @throws FileNotFoundException
     * @throws IOException
     */
    public WatermarkStamper(final String stampFilename) throws FileNotFoundException, IOException {
        final Properties properties = new Properties();
        properties.load(new FileInputStream(stampFilename));

        infoStamp = new WatermarkInfoStamp(properties.getProperty("watermark"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.areva.pdfstamper.stamp.Stamper#stamp(com.itextpdf.text.pdf.PdfReader)
     */
    @Override
    public StampWriter stamp(final PdfReader pdfReader) {
        return new WatermarkStampWriter(infoStamp, pdfReader);
    }
}
