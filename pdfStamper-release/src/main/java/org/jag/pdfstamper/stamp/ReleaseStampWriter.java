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

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.jag.pdfstamper.conf.ReleaseConfigurationFactory;
import org.jag.pdfstamper.conf.StamperBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.EnumSet;

/**
 * @author Jose A. Garcia
 */
public class ReleaseStampWriter extends AbstractStampWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger("pdfStamper");
    private static final StamperBundle CONFIGURATION = ReleaseConfigurationFactory.getInstance().getStamperBundle();
    private static final float X_POSITION = CONFIGURATION.getFloatProperty("points.per.cm", 28.35f)
            * CONFIGURATION.getFloatProperty("table.xpos");
    private static final float Y_POSITION = CONFIGURATION.getFloatProperty("points.per.cm", 28.35f)
            * CONFIGURATION.getFloatProperty("table.ypos");
    private final ReleaseInfoStamp infoStamp;

    private final PdfPTable stampTable;
    private final PdfGState gState;

    private final WatermarkDecorator decorator;

    /**
     * @param infoStamp
     * @param pdfReader
     */
    public ReleaseStampWriter(final ReleaseInfoStamp infoStamp, final PdfReader pdfReader,
                              final WatermarkDecorator decorator) {
        super(pdfReader);
        this.infoStamp = infoStamp;
        this.stampTable = createStampTable();
        this.gState = createGState();
        this.decorator = decorator;
    }

    /**
     * @return
     */
    private PdfPTable createStampTable() {
        final float[] tableColumnsWidth = CONFIGURATION.getFloatArrayProperty("table.columns.width");
        final PdfPTable table = new PdfPTable(tableColumnsWidth.length);
        try {
            table.setTotalWidth(tableColumnsWidth);

            table.addCell(newCell(CONFIGURATION.getProperty("approver.TITLE"), infoStamp.getApprover(),
                    EnumSet.of(CellBorder.TOP, CellBorder.LEFT)));
            table.addCell(newCell(CONFIGURATION.getProperty("approvalDate.TITLE"),
                    new SimpleDateFormat(CONFIGURATION.getProperty("output.date.FORMAT"))
                            .format(infoStamp.getApprovalDate()),
                    EnumSet.of(CellBorder.TOP, CellBorder.RIGHT)));
            table.addCell(newCell(CONFIGURATION.getProperty("reviewer.TITLE"), infoStamp.getReviewer(),
                    EnumSet.of(CellBorder.LEFT)));
            table.addCell(newCell(CONFIGURATION.getProperty("itemRevisionId.TITLE"), infoStamp.getItemRevisionId(),
                    EnumSet.of(CellBorder.RIGHT)));
            table.addCell(newCell(CONFIGURATION.getProperty("creator.TITLE"), infoStamp.getCreator(),
                    EnumSet.of(CellBorder.LEFT, CellBorder.BOTTOM)));
            table.addCell(newCell(CONFIGURATION.getProperty("itemId.TITLE"), infoStamp.getItemId(),
                    EnumSet.of(CellBorder.RIGHT, CellBorder.BOTTOM)));

        } catch (DocumentException e) {
            LOGGER.warn(e.getMessage(), e);
        }

        return table;
    }

    /**
     * @return
     */
    private PdfGState createGState() {
        final PdfGState gState = new PdfGState();
        gState.setFillOpacity(CONFIGURATION.getFloatProperty("text.opacity"));

        return gState;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.areva.pdfstamper.stamp.AbstractStampWriter#write()
     */
    @Override
    protected void write() {
        for (int pageNum = 1; pageNum <= pdfStamper().getReader().getNumberOfPages(); pageNum++) {
            writePage(pageNum);
        }
    }

    /**
     * @param pageNum
     */
    private void writePage(final int pageNum) {
        final PdfContentByte content = pdfStamper().getOverContent(pageNum);
        content.setGState(gState);
        content.saveState();

        final float height = stampTable.getTotalHeight();

        final Rectangle pageSize = pdfStamper().getReader().getPageSizeWithRotation(pageNum);
        stampTable.writeSelectedRows(0, -1, X_POSITION, pageSize.getBottom() + height + Y_POSITION, content);

        content.restoreState();

        decorator.writePage(pdfStamper(), pageNum);
    }

    /**
     * @param title
     * @param value
     * @param enumerationBorder
     * @return
     */
    private PdfPCell newCell(final String title, final String value, final EnumSet<CellBorder> enumerationBorder) {
        final PdfPCell cell = new PdfPCell(phrase2Cell(title, value));

        cell.setBorder(calculateTotalCellBorder(enumerationBorder));
        cell.setBorderWidth(CONFIGURATION.getFloatProperty("border.width"));
        cell.setBorderColor(CONFIGURATION.getColor("border.color"));
        cell.setPaddingTop(CONFIGURATION.getFloatProperty("text.padding"));
        cell.setIndent(CONFIGURATION.getFloatProperty("text.indent"));
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setRotation(CONFIGURATION.getIntProperty("rotation.degrees"));

        return cell;
    }

    /**
     * @param title
     * @param value
     * @return
     */
    private Phrase phrase2Cell(final String title, final String value) {
        return new Phrase(text2Phrase(title, value), CONFIGURATION.getFont("text"));
    }

    /**
     * @param title
     * @param value
     * @return
     */
    private String text2Phrase(final String title, final String value) {
        return String.format(CONFIGURATION.getProperty("text.cell.format"), title, value);
    }

    /**
     * @param enumerationBorder
     * @return
     */
    private int calculateTotalCellBorder(final EnumSet<CellBorder> enumerationBorder) {
        int sumBorders = PdfPCell.NO_BORDER;
        for (final CellBorder border : enumerationBorder) {
            sumBorders += border.value();
        }
        return sumBorders;
    }

}
