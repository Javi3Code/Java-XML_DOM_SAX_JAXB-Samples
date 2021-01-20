package common.jeycode.xml.saxsample.map;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class SAX_R_Sample
{

      private static final String XML_FILE_PATH = "Employees.xml";

      public static void main(String[] args) throws SAXException,ParserConfigurationException,IOException
      {

            var readerSAX = SAXParserFactory.newInstance()
                                            .newSAXParser()
                                            .getXMLReader();            
            
            var handler = new SAXHandler();
            readerSAX.setContentHandler(handler);
            readerSAX.parse(XML_FILE_PATH);

      }

}
