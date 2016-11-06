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
public class ReleaseInfoStamp {
    private final String creator;
    private final String reviewer;
    private final String approver;
    private final String itemId;
    private final String itemRevisionId;
    private final Date approvalDate;

    private ReleaseInfoStamp(final ReleaseInfoStamp.Builder builder) {
        this.creator = Strings.nullToEmpty(builder.creator);
        this.reviewer = Strings.nullToEmpty(builder.reviewer);
        this.approver = Strings.nullToEmpty(builder.approver);
        this.itemId = Strings.nullToEmpty(builder.itemId);
        this.itemRevisionId = Strings.nullToEmpty(builder.itemRevisionId);
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
        if (approvalDate == null) {
            return null;
        }
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
