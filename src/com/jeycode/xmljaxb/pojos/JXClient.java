package com.jeycode.xmljaxb.pojos;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class JXClient extends JXPerson
{

      @XmlAttribute
      private int clientId;
      @XmlElement(name = "product")
      private String purchasedProduct;

      public JXClient()
      {
            super();
      }

      public JXClient(String name,String surname,LocalDate birthdate,int clientId,String purchasedProduct)
      {
            super(name,surname,birthdate);
            this.clientId = clientId;
            this.purchasedProduct = purchasedProduct;
      }

      @Override
      public String toString()
      {
            StringBuilder builder = new StringBuilder();
            builder.append("Client [clientId=")
                   .append(clientId)
                   .append(", ");
            if (purchasedProduct != null)
            {
                  builder.append("purchasedProduct=")
                         .append(purchasedProduct)
                         .append(", ");
            }
            if (super.toString() != null)
            {
                  builder.append("toString()=")
                         .append(super.toString());
            }
            builder.append("]");
            return builder.toString();
      }

}
