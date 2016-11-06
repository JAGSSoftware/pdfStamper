/*
 * (c) 2013 - Jose A. Garcia Sanchez
 */
package org.jag.pdfstamper.stamp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Jose A. Garcia
 */
public final class StamperFactory {
    private static final Logger LOGGER = Logger.getLogger("pdfStamper");

    /** Constructor. */
    private StamperFactory() {
    }

    /**
     * @param stampType Type of stamper
     * @param stampFilename Filename with the stamp information
     * @return stamper
     */
    public static Stamper newInstance(final StampType stampType, final String stampFilename) {
        Stamper stamper = null;
        try {
            switch (stampType) {
            case RELEASE:
                stamper = new ReleaseStamper(stampFilename);
                break;
            case PRELIMINARY:
                stamper = new PreliminaryStamper(stampFilename);
                break;
            case WATERMARK:
                stamper = new WatermarkStamper(stampFilename);
                break;
            default:
                throw new IllegalArgumentException(String.format("StampType [%s] not valid", stampType));
            }
        } catch (FileNotFoundException e) {
            LOGGER.warning(e.getMessage());
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
        return stamper;
    }
}
