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

import com.google.common.base.Strings;

import java.util.Date;

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
