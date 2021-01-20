package com.jeycode.xmljaxb.pojos;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.jeycode.xmljaxb.LocalDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class JXPerson
{

      private String name;
      private String surname;
      @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
      @XmlAttribute
      private LocalDate birthdate;

      public JXPerson()
      {}

      public JXPerson(String name,String surname,LocalDate birthdate)
      {
            super();
            this.name = name;
            this.surname = surname;
            this.birthdate = birthdate;
      }

      @Override
      public String toString()
      {
            StringBuilder builder = new StringBuilder();
            builder.append("Person [");
            if (name != null)
            {
                  builder.append("name=")
                         .append(name)
                         .append(", ");
            }
            if (surname != null)
            {
                  builder.append("surname=")
                         .append(surname)
                         .append(", ");
            }
            if (birthdate != null)
            {
                  builder.append("birthdate=")
                         .append(birthdate);
            }
            builder.append("]");
            return builder.toString();
      }

}
