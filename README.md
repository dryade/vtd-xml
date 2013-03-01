# VTD-XML

 XimpleWare's VTD-XML is, far and away, the industry's most advanced and powerful XML processing model  for SOA and Cloud Computing! It is simultaneously:
- The world's most memory-efficient (1.3x~1.5x the size of an XML document) random-access XML parser.
- The world's fastest XML parser: On a Core2 2.5Ghz Desktop, VTD-XML outperforms DOM parsers by 5x~12x, delivering 150~250 MB/sec per core sustained throughput.
- The world's fastest XPath 1.0 implementation.
- The world's most efficient XML indexer that seamlessly integrates with your XML applications.
- The world's only incremental-update capable XML parser capable of cutting, pasting, splitting and assembling XML documents with max efficiency.
- The world's only XML parser that allows you to use XPath to process 256 GB XML documents.

The XML technology that they don't want you to know about.

The latest version is 2.11, available in C, C++, C# and Java, and can be downloaded here.

VTD-XML can be viewed a suite of innovative XML processing technologies centered around a non-extractive XML parsing technique called Virtual Token Descriptor (VTD). 

Depending on the perspective, VTD-XML can be viewed as one of the following:
- A "document-centric" XML parser
- A native XML indexer or a file format that uses binary data to enhance the text XML
- An incremental XML content modifier
- An XML slicer/splicer/assembler
- An XML editor/eraser
- A way to port XML processing on chip

A good starting point to understand how VTD-XML works is to view the demo. You can also visit VTD-XML blog for more code samples and related information.
If you have any questions on VTD-XML, please join our discussion.

If you want to know more go http://vtd-xml.sourceforge.net/

Install in Java
---------------

Install the gem via maven:

```
<dependencies>
    ....
    <dependency>
        <groupId>com.ximpleware</groupId>
        <artifactId>vtd-xml</artifactId>
        <version>2.11</version>
    </dependency>
    ....
</dependencies>
<repositories>
    <repository>
        <id>mvn-repo</id>
        <url>https://github.com/dryade/mvn-repo/raw/master/releases</url>
    </repository>
</repositories>
```



