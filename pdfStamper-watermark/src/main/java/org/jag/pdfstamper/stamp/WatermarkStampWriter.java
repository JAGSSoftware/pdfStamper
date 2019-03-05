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

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.jag.pdfstamper.conf.StamperBundle;
import org.jag.pdfstamper.conf.WatermarkConfigurationFactory;

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
