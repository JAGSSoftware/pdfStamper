# PDF Stamper

Utility to stamp PLM information on PDF's

It is inspired by the requirement from a customer to stamp some relevant PLM information on PDF's generated documents.

The PLM system used by the customer was [Teamcenter](https://www.plm.automation.siemens.com/de_de/products/teamcenter/).
There were three scenarios in which some information should be stamped on PDF's, either watermark or some other
interesting info on a small box located in the left-bottom corner of the document.

These three stamp options are:
* watermark: a watermark is written as background of the document
* preliminary: in a preliminary state of the document, __itemId__, __itemRevisionId__ and **creationDate** of the
document is stamped. A watermark is optional.
* release: in a release state of the document, __creator__, __reviewer__, __approver__, __itemId__, __itemRevisionId__,
__approvalDate__ of the document is stamped. A watermark is optional.

Initially it was planned to use the OOTB solution from Teamcenter to make this stamp, but the implementation was
complex and hard to maintain, and the final solution was a bad performance. While using this solution working
directly on generated PDF using the library [iText](http://itextpdf.com/) allows an easier implementation and performs
really good.

## How to use it
The package `pdfStamper.jar` can be created using [Apache Maven](https://maven.apache.org/):

    $> mvn clean package
The package can be found in `target/pdfStamper.jar`.
To run it:

    $> java -jar pdfStamper.jar
Without any argument the program writes in the console:

    nov 06, 2016 4:22:53 PM org.jag.pdfstamper.Main validateArguments
    ADVERTENCIA: Missing argument: [in]
    nov 06, 2016 4:22:53 PM org.jag.pdfstamper.Main validateArguments
    ADVERTENCIA: Missing argument: [out]
    nov 06, 2016 4:22:53 PM org.jag.pdfstamper.Main validateArguments
    ADVERTENCIA: Missing argument: [stampfile]
    nov 06, 2016 4:22:53 PM org.jag.pdfstamper.Main validateArguments
    ADVERTENCIA: Missing argument: [stamptype]
    usage: pdfStamp
     -in,--input <arg>     input file
     -out,--output <arg>   output file
     -stampfile <arg>      properties file with stamping information
     -stamptype <arg>      type of stamp: {release | preliminary | watermark}

The argument `stampfile` is the name of a properties file belonging to the corresponding `stamptype`. These are the
different expected formats.

* Watermark

    watermark=<Watermark text>

* Preliminary

    itemId=<Id of the item to whom the document belongs>
    itemRevisionId=<RevisionId of the item to whom the document belongs>
    creationDate=<Creation date of the document, in format yyyy-MM-dd HH:mm>
    watermark=<Watermark text, optional>

* Release

    creator=<Creator name of the item>
    reviewer=<Reviewer name of the item>
    approver=<Approver name of the item>
    itemId=<Id of the item to whom the document belongs>
    itemRevisionId=<RevisionId of the item to whom the document belongs>
    approvalDate=<Approval date of the document, in format yyyy-MM-dd>
    watermark=<Watermark text, optional>
