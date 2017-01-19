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

import org.jag.pdfstamper.conf.StamperBundle;
import org.jag.pdfstamper.conf.WatermarkConfigurationFactory;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * @author Jose A. Garcia
 */
class WatermarkStampWriter extends AbstractStampWriter implements WatermarkDecorator {
    private static final StamperBundle CONFIGURATION = WatermarkConfigurationFactory.getInstance().getStamperBundle();
    private final WatermarkInfoStamp infoStamp;

    private final transient Font watermarkFont;
    private final transient PdfGState gState;

    /**
     * @param infoStamp
     * @param pdfReader
     */
    public WatermarkStampWriter(final WatermarkInfoStamp infoStamp, final PdfReader pdfReader) {
        super(pdfReader);
        this.infoStamp = infoStamp;
        this.watermarkFont = CONFIGURATION.getFont("watermark");
        this.gState = createGState();
    }

    private PdfGState createGState() {
        final PdfGState gState = new PdfGState();
        gState.setFillOpacity(CONFIGURATION.getFloatProperty("watermark.opacity"));

        return gState;
    }

    @Override
    protected void write() {
        for (int pageNum = 1; pageNum <= pdfStamper().getReader().getNumberOfPages(); pageNum++) {
            writePage(pdfStamper(), pageNum);
        }
    }

    @Override
    public void writePage(final PdfStamper pdfStamper, final int pageNum) {
        final Rectangle rectangle = pdfStamper.getReader().getPageSizeWithRotation(pageNum);
        final PdfContentByte content = pdfStamper.getOverContent(pageNum);

        content.setGState(gState);
        content.beginText();
        content.setFontAndSize(watermarkFont.getBaseFont(), watermarkFont.getSize());
        content.setColorFill(CONFIGURATION.getColor("watermark.fill"));
        content.showTextAligned(Element.ALIGN_CENTER, infoStamp.watermark(), rectangle.getWidth() / 2,
                rectangle.getHeight() / 2, CONFIGURATION.getFloatProperty("watermark.rotation"));
        content.endText();
    }
}
