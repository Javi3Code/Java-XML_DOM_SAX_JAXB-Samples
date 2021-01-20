package com.jeycode.xmljaxb;

import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import com.jeycode.xmljaxb.pojos.JXClient;
import com.jeycode.xmljaxb.pojos.JXEmployee;
import com.jeycode.xmljaxb.pojos.JXPerson;

import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public final class PersonManager
{

      private static PersonManager instance;

      @XmlElementWrapper(name = "persons")
      @XmlElements({@XmlElement(type = JXPerson.class, name = "person"),@XmlElement(type = JXClient.class, name = "client"),
                    @XmlElement(type = JXEmployee.class, name = "employee")})
      @Getter
      @Setter
      private List<JXPerson> lstPerson;

      private PersonManager()
      {}

      public static PersonManager instance()
      {
            instance = Objects.isNull(instance) ? new PersonManager() : instance;
            return instance;
      }

}
