package com.jeycode.xmljaxb.pojos;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;

public class JXEmployee extends JXPerson
{

      @XmlAttribute
      private String category;

      @XmlAttribute(name = "code")
      private int employeeCode;

      public JXEmployee()
      {
            super();
      }

      public JXEmployee(String name,String surname,LocalDate birthdate,String category,int employeeCode)
      {
            super(name,surname,birthdate);
            this.category = category;
            this.employeeCode = employeeCode;
      }

      @Override
      public String toString()
      {
            StringBuilder builder = new StringBuilder();
            builder.append("JXEmployee [");
            if (category != null)
            {
                  builder.append("category=")
                         .append(category)
                         .append(", ");
            }
            builder.append("employeeCode=")
                   .append(employeeCode)
                   .append(", ");
            if (super.toString() != null)
            {
                  builder.append("toString()=")
                         .append(super.toString());
            }
            builder.append("]");
            return builder.toString();
      }

}
