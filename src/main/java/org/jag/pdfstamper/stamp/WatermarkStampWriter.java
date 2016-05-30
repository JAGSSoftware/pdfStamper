/*
 * (c) 2013 - Areva Wind DE
 */
package org.jag.pdfstamper.stamp;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import org.jag.pdfstamper.conf.Configuration;

/**
 * @author Jose A. Garcia
 */
class WatermarkStampWriter extends AbstractStampWriter implements WatermarkDecorator {
    private static final Configuration CONFIGURATION = Configuration.INSTANCE_WATERMARK;
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

    /*
     * (non-Javadoc)
     * @see de.areva.pdfstamper.stamp.AbstractStampWriter#write()
     */
    @Override
    protected void write() {
        for (int pageNum = 1; pageNum <= pdfStamper().getReader().getNumberOfPages(); pageNum++) {
            writePage(pdfStamper(), pageNum);
        }
    }

    /* (non-Javadoc)
     * @see de.areva.pdfstamper.stamp.WatermarkDecorator#writePage(int)
     */
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
