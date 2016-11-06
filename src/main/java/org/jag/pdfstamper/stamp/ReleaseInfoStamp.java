/*
 * (c) 2013 - Jose A. Garcia Sanchez
 */
package org.jag.pdfstamper.stamp;

import java.util.Date;

/**
 * @author Jose A. Garcia
 */
public class ReleaseInfoStamp {
    private final String creator;
    private final String reviewer;
    private final String approver;
    private final String itemId;
    private final String itemRevisionId;
    private final Date approvalDate;

    private ReleaseInfoStamp(final ReleaseInfoStamp.Builder builder) {
        this.creator = builder.creator;
        this.reviewer = builder.reviewer;
        this.approver = builder.approver;
        this.itemId = builder.itemId;
        this.itemRevisionId = builder.itemRevisionId;
        this.approvalDate = builder.approvalDate;
    }

    /**
     * @return
     */
    public String creator() {
        return this.creator;
    }

    /**
     * @return
     */
    public String reviewer() {
        return this.reviewer;
    }

    /**
     * @return
     */
    public String approver() {
        return this.approver;
    }

    /**
     * @return
     */
    public String itemId() {
        return this.itemId;
    }

    /**
     * @return
     */
    public String itemRevisionId() {
        return this.itemRevisionId;
    }

    /**
     * @return
     */
    public Date approvalDate() {
        return new Date(approvalDate.getTime());
    }

    static class Builder {
        private String creator;
        private String reviewer;
        private String approver;
        private String itemId;
        private String itemRevisionId;
        private Date approvalDate;

        public Builder creator(final String name) {
            this.creator = name;

            return this;
        }

        public Builder reviewer(final String name) {
            this.reviewer = name;

            return this;
        }

        public Builder approver(final String name) {
            this.approver = name;

            return this;
        }

        public Builder itemId(final String itemId) {
            this.itemId = itemId;

            return this;
        }

        public Builder itemRevisionId(final String itemRevisionId) {
            this.itemRevisionId = itemRevisionId;

            return this;
        }

        public Builder approvalDate(final Date date) {
            this.approvalDate = new Date(date.getTime());

            return this;
        }

        public ReleaseInfoStamp build() {
            return new ReleaseInfoStamp(this);
        }
    }
}
