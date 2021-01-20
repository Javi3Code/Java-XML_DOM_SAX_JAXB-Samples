package com.jeycode.xmljaxb;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String,LocalDate>
{

      @Override
      public LocalDate unmarshal(String date) throws Exception
      {
            return LocalDate.parse(date);
      }

      @Override
      public String marshal(LocalDate date) throws Exception
      {
            return date.toString();
      }
}
