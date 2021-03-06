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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jose A. Garcia Sanchez
 */
public class ReleaseInfoStampTest {
    private ReleaseInfoStamp.Builder builder;

    @Before
    public void setUp() {
        builder = new ReleaseInfoStamp.Builder();
    }

    @Test
    public void createEmptyInfo() {
        final ReleaseInfoStamp info = builder.build();

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createEmptyInfoFromProperties() {
        final Properties properties = new Properties();
        final ReleaseInfoStamp info = new ReleaseInfoStamp(properties);

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithCreator() {
        final ReleaseInfoStamp info = builder.creator("Creator").build();

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isNotEmpty();
        assertThat(info.getCreator()).isEqualTo("Creator");
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithCreatorFromProperties() {
        final Properties properties = new Properties();
        properties.setProperty("creator", "Creator");
        final ReleaseInfoStamp info = new ReleaseInfoStamp(properties);

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isNotEmpty();
        assertThat(info.getCreator()).isEqualTo("Creator");
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithReviewer() {
        final ReleaseInfoStamp info = builder.reviewer("Reviewer").build();

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isNotEmpty();
        assertThat(info.getReviewer()).isEqualTo("Reviewer");
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithReviewerFromProperties() {
        final Properties properties = new Properties();
        properties.setProperty("reviewer", "Reviewer");
        final ReleaseInfoStamp info = new ReleaseInfoStamp(properties);

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isNotEmpty();
        assertThat(info.getReviewer()).isEqualTo("Reviewer");
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithApprover() {
        final ReleaseInfoStamp info = builder.approver("Approver").build();

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isNotEmpty();
        assertThat(info.getApprover()).isEqualTo("Approver");
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithApproverFromProperties() {
        final Properties properties = new Properties();
        properties.setProperty("approver", "Approver");
        final ReleaseInfoStamp info = new ReleaseInfoStamp(properties);

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isNotEmpty();
        assertThat(info.getApprover()).isEqualTo("Approver");
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithItemId() {
        final ReleaseInfoStamp info = builder.itemId("ITEM-ID#12").build();

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isNotEmpty();
        assertThat(info.getItemId()).isEqualTo("ITEM-ID#12");
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithItemIdFromProperties() {
        final Properties properties = new Properties();
        properties.setProperty("itemId", "ITEM-ID#12");
        final ReleaseInfoStamp info = new ReleaseInfoStamp(properties);

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isNotEmpty();
        assertThat(info.getItemId()).isEqualTo("ITEM-ID#12");
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithItemRevisionId() {
        final ReleaseInfoStamp info = builder.revisionId("ITEM-REVID#12").build();

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isNotEmpty();
        assertThat(info.getItemRevisionId()).isEqualTo("ITEM-REVID#12");
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithItemRevisionIdFromProperties() {
        final Properties properties = new Properties();
        properties.setProperty("itemRevisionId", "ITEM-REVID#23");
        final ReleaseInfoStamp info = new ReleaseInfoStamp(properties);

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isNotEmpty();
        assertThat(info.getItemRevisionId()).isEqualTo("ITEM-REVID#23");
        assertThat(info.getApprovalDate()).isNull();
    }

    @Test
    public void createInfoWithApprovalDate() {
        final Date approvalDate = new Date();
        final ReleaseInfoStamp info = builder.approvalDate(approvalDate).build();

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNotNull();
        assertThat(info.getApprovalDate()).isEqualTo(approvalDate);
        assertThat(info.getApprovalDate()).isNotSameAs(approvalDate);
    }

    @Test
    public void createInfoWithApprovalDateFromProperties() {
        final Properties properties = new Properties();
        properties.setProperty("approvalDate", "2013-03-12");
        final ReleaseInfoStamp info = new ReleaseInfoStamp(properties);

        final Calendar approvalDate = new GregorianCalendar(2013, Calendar.MARCH, 12);
        approvalDate.set(Calendar.HOUR, 0);
        approvalDate.set(Calendar.MINUTE, 0);
        approvalDate.set(Calendar.SECOND, 0);
        approvalDate.set(Calendar.MILLISECOND, 0);

        assertThat(info).isNotNull();
        assertThat(info.getCreator()).isEmpty();
        assertThat(info.getReviewer()).isEmpty();
        assertThat(info.getApprover()).isEmpty();
        assertThat(info.getItemId()).isEmpty();
        assertThat(info.getItemRevisionId()).isEmpty();
        assertThat(info.getApprovalDate()).isNotNull();
        assertThat(info.getApprovalDate()).isEqualTo(approvalDate.getTime());
        assertThat(info.getApprovalDate()).isNotSameAs(approvalDate.getTime());
    }
}
