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
package org.jag.pdfstamper;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.jag.pdfstamper.stamp.StampType;
import org.jag.pdfstamper.stamp.StampWriter;
import org.jag.pdfstamper.stamp.StamperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;

/**
 * Entry point of the tool to stamp PDF's.
 *
 * @author Jose A. Garcia
 */
public class Main {
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /** Name for the input file argument (short). */
    private static final String IN_ARGUMENT = "in";

    /** Name for the input file argument. */
    private static final String INPUT_ARGUMENT = "input";

    /** Name for the output file argument (short). */
    private static final String OUT_ARGUMENT = "out";

    /** Name for the output file argument. */
    private static final String OUTPUT_ARGUMENT = "output";

    /** Name for the properties stampfile argument. */
    private static final String STAMPFILE_ARGUMENT = "stampfile";

    /** Name for the stamp type argument. */
    private static final String STAMPTYPE_ARGUMENT = "stamptype";

    /** Array of command line arguments for the utility. */
    private static final Option[] OPTION_ARRAY = new Option[]{
            new Option(IN_ARGUMENT, INPUT_ARGUMENT, true, "input file"),
            new Option(OUT_ARGUMENT, OUTPUT_ARGUMENT, true, "output file"),
            new Option(STAMPFILE_ARGUMENT, true, "properties file with stamping information"),
            new Option(STAMPTYPE_ARGUMENT, true, "type of stamp: {release | preliminary | watermark}"),};

    /** Collection of program options. */
    private final Options options = new Options();

    /** Flag to say if the provided arguments to the program are correct. */
    private boolean argumentValid = true;

    /** Command line processor. */
    private CommandLine commandLine;

    /** Name of the PDF to be stamped. */
    private String inputFile;

    /** Name of the stamped PDF file. */
    private String outputFile;

    /** PDF reader object. */
    private PdfReader pdfReader;

    /** PDF Stamp writer object. */
    private StampWriter stampWriter;

    /**
     * Constructor.
     */
    Main() {
        for (final Option option : getOptions()) {
            options.addOption(option);
        }
    }

    /**
     * @param args Arguments from command line
     * @throws DocumentException
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException, DocumentException {
        LOGGER.debug(Main.class.getCanonicalName(), "main");

        final Main stamperMain = new Main();
        try {
            stamperMain.checkArguments(args);
            if (stamperMain.argumentValid) {
                stamperMain.open();
                stamperMain.stamp();
                stamperMain.close();
            } else {
                stamperMain.printHelp();
            }
        } catch (ParseException e) {
            LOGGER.warn(e.getMessage(), e);
            stamperMain.printHelp();
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }

    /**
     * @return Array with the command line arguments
     */
    public final Option[] getOptions() {
        return OPTION_ARRAY;
    }

    /**
     * @param arguments Arguments from the command line passed to the program
     * @throws ParseException
     */
    private void checkArguments(final String[] arguments) throws ParseException {
        parseArguments(arguments);
        validateArguments();
        loadInfoStamp();
    }

    /**
     * @param arguments Arguments from the command line passed to the program
     * @throws ParseException
     */
    private void parseArguments(final String[] arguments) throws ParseException {
        final CommandLineParser parser = new PosixParser();
        commandLine = parser.parse(options, arguments, false);
    }

    /**
     * Validate the provided arguments to the program against the expected.
     */
    private void validateArguments() {
        for (Option option : getOptions()) {
            final boolean validOption = commandLine.hasOption(option.getOpt());
            if (!validOption) {
                LOGGER.warn("Missing argument: [" + option.getOpt() + "]");
            }
            argumentValid &= validOption;
        }
    }

    /**
     * Load the stamping information to be written in the StampTable.
     */
    public final void loadInfoStamp() {
        inputFile = commandLine.getOptionValue(INPUT_ARGUMENT);
        outputFile = commandLine.getOptionValue(OUTPUT_ARGUMENT);
    }

    /**
     * Print the help information for command line.
     */
    public final void printHelp() {
        new HelpFormatter().printHelp("pdfStamp", options);
    }

    /**
     * @throws IOException
     * @throws DocumentException
     */
    public final void open() throws IOException, DocumentException {
        LOGGER.debug(getClass().getCanonicalName(), "open");
        pdfReader = new PdfReader(inputFile);
        stampWriter = StamperFactory.newInstance(StampType.find(commandLine.getOptionValue(STAMPTYPE_ARGUMENT)),
                commandLine.getOptionValue(STAMPFILE_ARGUMENT)).stamp(pdfReader);
    }

    /**
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws IOException
     */
    public final void stamp() throws FileNotFoundException, DocumentException, IOException {
        stampWriter.write(outputFile);
    }

    /**
     * @throws DocumentException
     * @throws IOException
     */
    public final void close() throws DocumentException, IOException {
        LOGGER.debug(getClass().getCanonicalName(), "close");
        stampWriter.close();

        if (pdfReader != null) {
            pdfReader.close();
        }
    }
}
