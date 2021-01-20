package common.jeycode.xml.saxsample.map;

import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public interface SAXSAMPLEAdatper extends ContentHandler
{

      @Override
      default void setDocumentLocator(Locator locator)
      {}

      @Override
      default void startPrefixMapping(String prefix,String uri) throws SAXException
      {}

      @Override
      default void endPrefixMapping(String prefix) throws SAXException
      {}

      @Override
      default void ignorableWhitespace(char ch[],int start,int length) throws SAXException
      {}

      @Override
      default void processingInstruction(String target,String data) throws SAXException
      {}

      @Override
      default void skippedEntity(String name) throws SAXException
      {}
}
