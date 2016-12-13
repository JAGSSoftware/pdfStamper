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

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jose A. Garcia
 */
public final class StamperFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger("pdfStamper");

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
            LOGGER.warn(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return stamper;
    }
}
