/*
 * (c) 2013 - Jose A. Garcia Sanchez
 */
package org.jag.pdfstamper.stamp;

/**
 * @author Jose A. Garcia
 */
public class WatermarkInfoStamp {
    private final String watermark;

    /**
     * @param watermark
     */
    public WatermarkInfoStamp(final String watermark) {
        this.watermark = watermark;
    }

    /**
     * @return
     */
    public String watermark() {
        return this.watermark;
    }
}
