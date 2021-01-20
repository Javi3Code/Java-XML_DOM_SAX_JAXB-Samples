package com.jeycode.xmljaxb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.jeycode.xmljaxb.pojos.JXClient;
import com.jeycode.xmljaxb.pojos.JXEmployee;
import com.jeycode.xmljaxb.pojos.JXPerson;

public final class JAXBSample
{

      private static final Class<PersonManager> ROOT = PersonManager.class;
      private static final String xmlPath = "person.xml";

      public static void main(String[] args) throws JAXBException,FileNotFoundException
      {
            var persons = PersonManager.instance();

            var lst = ((PersonManager)JAXBContext.newInstance(ROOT)
                                                 .createUnmarshaller()
                                                 .unmarshal(new FileReader(xmlPath))).getLstPerson();

            lst.add(new JXPerson("Juana","de Arco",LocalDate.of(1990,10,10)));
            lst.add(new JXEmployee("Pedro","Picapiedra",LocalDate.of(1980,1,23),"Comercial",4000));
            lst.add(new JXClient("Sofía","Martinez",LocalDate.of(1984,10,1),1,"Pienso Perros"));

            persons.setLstPerson(lst);

            var marshaller = JAXBContext.newInstance(ROOT)
                                        .createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(persons,new File(xmlPath));

            var root = (PersonManager)JAXBContext.newInstance(ROOT)
                                                 .createUnmarshaller()
                                                 .unmarshal(new FileReader(xmlPath));

            root.getLstPerson()
                .forEach(System.out::println);

      }

}
