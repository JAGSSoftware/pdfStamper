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

import static com.google.common.truth.Truth.assertThat;

import java.util.Date;

import org.junit.Test;

/**
 * @author Jose A. Garcia Sanchez
 */
public class ReleaseInfoStampTest {

    @Test
    public void createEmptyInfo() {
        final ReleaseInfoStamp info = new ReleaseInfoStamp.Builder().build();

        assertThat(info).isNotNull();
        assertThat(info.creator()).isEmpty();
        assertThat(info.reviewer()).isEmpty();
        assertThat(info.approver()).isEmpty();
        assertThat(info.itemId()).isEmpty();
        assertThat(info.itemRevisionId()).isEmpty();
        assertThat(info.approvalDate()).isNull();
    }

    @Test
    public void createInfoWithCreator() {
        final ReleaseInfoStamp info = new ReleaseInfoStamp.Builder().creator("Creator").build();

        assertThat(info).isNotNull();
        assertThat(info.creator()).isNotEmpty();
        assertThat(info.creator()).isEqualTo("Creator");
        assertThat(info.reviewer()).isEmpty();
        assertThat(info.approver()).isEmpty();
        assertThat(info.itemId()).isEmpty();
        assertThat(info.itemRevisionId()).isEmpty();
        assertThat(info.approvalDate()).isNull();
    }

    @Test
    public void createInfoWithReviewer() {
        final ReleaseInfoStamp info = new ReleaseInfoStamp.Builder().reviewer("Reviewer").build();

        assertThat(info).isNotNull();
        assertThat(info.creator()).isEmpty();
        assertThat(info.reviewer()).isNotEmpty();
        assertThat(info.reviewer()).isEqualTo("Reviewer");
        assertThat(info.approver()).isEmpty();
        assertThat(info.itemId()).isEmpty();
        assertThat(info.itemRevisionId()).isEmpty();
        assertThat(info.approvalDate()).isNull();
    }

    @Test
    public void createInfoWithApprover() {
        final ReleaseInfoStamp info = new ReleaseInfoStamp.Builder().approver("Approver").build();

        assertThat(info).isNotNull();
        assertThat(info.creator()).isEmpty();
        assertThat(info.reviewer()).isEmpty();
        assertThat(info.approver()).isNotEmpty();
        assertThat(info.approver()).isEqualTo("Approver");
        assertThat(info.itemId()).isEmpty();
        assertThat(info.itemRevisionId()).isEmpty();
        assertThat(info.approvalDate()).isNull();
    }

    @Test
    public void createInfoWithItemId() {
        final ReleaseInfoStamp info = new ReleaseInfoStamp.Builder().itemId("ITEM-ID#12").build();

        assertThat(info).isNotNull();
        assertThat(info.creator()).isEmpty();
        assertThat(info.reviewer()).isEmpty();
        assertThat(info.approver()).isEmpty();
        assertThat(info.itemId()).isNotEmpty();
        assertThat(info.itemId()).isEqualTo("ITEM-ID#12");
        assertThat(info.itemRevisionId()).isEmpty();
        assertThat(info.approvalDate()).isNull();
    }

    @Test
    public void createInfoWithItemRevisionId() {
        final ReleaseInfoStamp info = new ReleaseInfoStamp.Builder().itemRevisionId("ITEM-REVID#12").build();

        assertThat(info).isNotNull();
        assertThat(info.creator()).isEmpty();
        assertThat(info.reviewer()).isEmpty();
        assertThat(info.approver()).isEmpty();
        assertThat(info.itemId()).isEmpty();
        assertThat(info.itemRevisionId()).isNotEmpty();
        assertThat(info.itemRevisionId()).isEqualTo("ITEM-REVID#12");
        assertThat(info.approvalDate()).isNull();
    }

    @Test
    public void createInfoWithApprovalDate() {
        final Date approvalDate = new Date();
        final ReleaseInfoStamp info = new ReleaseInfoStamp.Builder().approvalDate(approvalDate).build();

        assertThat(info).isNotNull();
        assertThat(info.creator()).isEmpty();
        assertThat(info.reviewer()).isEmpty();
        assertThat(info.approver()).isEmpty();
        assertThat(info.itemId()).isEmpty();
        assertThat(info.itemRevisionId()).isEmpty();
        assertThat(info.approvalDate()).isNotNull();
        assertThat(info.approvalDate()).isEqualTo(approvalDate);
        assertThat(info.approvalDate()).isNotSameAs(approvalDate);
    }
}
