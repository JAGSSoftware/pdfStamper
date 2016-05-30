/*
 * (c) 2013 - Areva Wind DE
 */
package org.jag.pdfstamper.stamp;

import java.text.SimpleDateFormat;
import java.util.EnumSet;
import java.util.logging.Logger;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;

import org.jag.pdfstamper.conf.Configuration;

/**
 * @author Jose A. Garcia
 */
public class ReleaseStampWriter extends AbstractStampWriter {
    private static final Logger LOGGER = Logger.getLogger("pdfStamper");
    private static final Configuration CONFIGURATION = Configuration.INSTANCE_RELEASE;
    private static final float X_POSITION = CONFIGURATION.getFloatProperty("points.per.cm", 28.35f)
            * CONFIGURATION.getFloatProperty("table.xpos");
    private static final float Y_POSITION = CONFIGURATION.getFloatProperty("points.per.cm", 28.35f)
            * CONFIGURATION.getFloatProperty("table.ypos");
    private final ReleaseInfoStamp infoStamp;

    private final transient PdfPTable stampTable;
    private final transient PdfGState gState;

    private final WatermarkDecorator decorator;

    /**
     * @param infoStamp
     * @param pdfReader
     */
    public ReleaseStampWriter(final ReleaseInfoStamp infoStamp, final PdfReader pdfReader, final WatermarkDecorator decorator) {
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

            table.addCell(newCell(CONFIGURATION.getProperty("approver.TITLE"), infoStamp.approver(),
                    EnumSet.of(CellBorder.TOP, CellBorder.LEFT)));
            table.addCell(newCell(CONFIGURATION.getProperty("approvalDate.TITLE"),
                    new SimpleDateFormat(CONFIGURATION.getProperty("output.date.FORMAT")).format(infoStamp
                            .approvalDate()), EnumSet.of(CellBorder.TOP, CellBorder.RIGHT)));
            table.addCell(newCell(CONFIGURATION.getProperty("reviewer.TITLE"), infoStamp.reviewer(),
                    EnumSet.of(CellBorder.LEFT)));
            table.addCell(newCell(CONFIGURATION.getProperty("itemRevisionId.TITLE"), infoStamp.itemRevisionId(),
                    EnumSet.of(CellBorder.RIGHT)));
            table.addCell(newCell(CONFIGURATION.getProperty("creator.TITLE"), infoStamp.creator(),
                    EnumSet.of(CellBorder.LEFT, CellBorder.BOTTOM)));
            table.addCell(newCell(CONFIGURATION.getProperty("itemId.TITLE"), infoStamp.itemId(),
                    EnumSet.of(CellBorder.RIGHT, CellBorder.BOTTOM)));

        } catch (DocumentException e) {
            LOGGER.warning(e.getMessage());
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
