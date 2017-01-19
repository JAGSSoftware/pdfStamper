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

import java.util.Date;

import com.google.common.base.Strings;

/**
 * @author Jose A. Garcia
 */
class PreliminaryInfoStamp {
    private final String itemId;
    private final String itemRevisionId;
    private final Date creationDate;

    private PreliminaryInfoStamp(final PreliminaryInfoStamp.Builder builder) {
        this.itemId = Strings.nullToEmpty(builder.itemId);
        this.itemRevisionId = Strings.nullToEmpty(builder.itemRevisionId);
        this.creationDate = builder.creationDate;
    }

    /**
     * @return
     */
    public String itemId() {
        return itemId;
    }

    /**
     * @return
     */
    public String itemRevisionId() {
        return itemRevisionId;
    }

    /**
     * @return
     */
    public Date creationDate() {
        if (creationDate == null) {
            return null;
        }
        return new Date(creationDate.getTime());
    }

    /**
     * @author Jose A. Garcia
     */
    static class Builder {
        private String itemId;
        private String itemRevisionId;
        private Date creationDate;

        /**
         * @param itemId
         * @return
         */
        public Builder itemId(final String itemId) {
            this.itemId = itemId;

            return this;
        }

        /**
         * @param itemRevisionId
         * @return
         */
        public Builder itemRevisionId(final String itemRevisionId) {
            this.itemRevisionId = itemRevisionId;

            return this;
        }

        /**
         * @param date
         * @return
         */
        public Builder creationDate(final Date date) {
            creationDate = new Date(date.getTime());

            return this;
        }

        /**
         * @return
         */
        public PreliminaryInfoStamp build() {
            return new PreliminaryInfoStamp(this);
        }
    }
}
