/*
 * (c) 2013 - Jose A. Garcia Sanchez
 */
package org.jag.pdfstamper.stamp;

import java.util.Date;

/**
 * @author Jose A. Garcia
 */
class PreliminaryInfoStamp {
    private final String itemId;
    private final String itemRevisionId;
    private final Date creationDate;

    private PreliminaryInfoStamp(final PreliminaryInfoStamp.Builder builder) {
        this.itemId = builder.itemId;
        this.itemRevisionId = builder.itemRevisionId;
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
        return new Date(creationDate.getTime());
    }

    /**
     * @author Jose A. Garcia
     */
    static class Builder {
        private String itemId = "";
        private String itemRevisionId = "";
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
