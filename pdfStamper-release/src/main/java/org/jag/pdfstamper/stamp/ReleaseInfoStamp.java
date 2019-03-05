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

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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
