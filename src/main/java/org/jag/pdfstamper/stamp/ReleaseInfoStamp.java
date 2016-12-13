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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

/**
 * @author Jose A. Garcia
 */
public class ReleaseInfoStamp {
    private static final Logger LOGGER = LoggerFactory.getLogger("pdfStamper");
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

    public ReleaseInfoStamp(final Properties properties) {
        this(new ReleaseInfoStamp.Builder(properties));
    }

    public String getCreator() {
        return this.creator;
    }

    public String getReviewer() {
        return this.reviewer;
    }

    public String getApprover() {
        return this.approver;
    }

    public String getItemId() {
        return this.itemId;
    }

    public String getItemRevisionId() {
        return this.itemRevisionId;
    }

    public Date getApprovalDate() {
        if (approvalDate == null) {
            return null;
        }
        return new Date(approvalDate.getTime());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("creator", creator).add("reviewer", reviewer)
                .add("approver", approver).add("itemId", itemId).add("itemRevisionId", itemRevisionId)
                .add("approvalDate", approvalDate).toString();
    }

    static class Builder {
        private static final String CREATOR_KEY = "creator";
        private static final String REVIEWER_KEY = "reviewer";
        private static final String APPROVER_KEY = "approver";
        private static final String ITEM_ID_KEY = "itemId";
        private static final String ITEM_REVISION_ID_KEY = "itemRevisionId";
        private static final String APPROVAL_DATE_KEY = "approvalDate";

        private String creator;
        private String reviewer;
        private String approver;
        private String itemId;
        private String itemRevisionId;
        private Date approvalDate;

        private Builder(final Properties properties) {
            this();
            creator(properties.getProperty(CREATOR_KEY));
            reviewer(properties.getProperty(REVIEWER_KEY));
            approver(properties.getProperty(APPROVER_KEY));
            itemId(properties.getProperty(ITEM_ID_KEY));
            revisionId(properties.getProperty(ITEM_REVISION_ID_KEY));
            try {
                final Date date = new SimpleDateFormat("yyyy-MM-dd").parse(properties.getProperty(APPROVAL_DATE_KEY));
                approvalDate(date);
            } catch (ParseException | NullPointerException e) {
                LOGGER.warn(e.getMessage(), e);
                approvalDate(null);
            }
        }

        public Builder() {
            // Empty body
        }

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

        public Builder revisionId(final String itemRevisionId) {
            this.itemRevisionId = itemRevisionId;

            return this;
        }

        public Builder approvalDate(final Date date) {
            this.approvalDate = date;

            return this;
        }

        public ReleaseInfoStamp build() {
            return new ReleaseInfoStamp(this);
        }
    }
}
