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
