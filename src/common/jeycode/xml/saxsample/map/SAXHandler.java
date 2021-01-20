package common.jeycode.xml.saxsample.map;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.jeycode.pojos.Employee;
import com.jeycode.pojos.Employee.EmployeeBuilder;

public class SAXHandler implements SAXSAMPLEAdatper
{

      private static final String SALARY = "salary";
      private static final String AGE = "age";
      private static final String SURNAME = "surname";
      private static final String NAME = "name";
      private static final String EMPLOYEE_CODE = "employeeCode";
      private static final String CATEGORY = "category";
      private static final String EMPLOYEE = "employee";

      private static EmployeeBuilder employeeBuilder;
      private List<Employee> lstEmployee;
      private static String QUALIFIED_NAME;

      @Override
      public void startDocument() throws SAXException
      {
            lstEmployee = new LinkedList<>();
      }

      @Override
      public void startElement(String uri,String localName,String qName,Attributes atts) throws SAXException
      {
            switch (qName)
            {
                  case EMPLOYEE:
                        employeeBuilder = Employee.builder();

                        for (var i = 0; i < atts.getLength(); i++)
                        {
                              switch (atts.getQName(i))
                              {
                                    case CATEGORY:
                                          employeeBuilder.category(atts.getValue(i));
                                          break;
                                    case EMPLOYEE_CODE:
                                          employeeBuilder.employeeCode(Integer.parseInt(atts.getValue(i)));
                                          break;
                              }
                        }

                        break;
                  default:
                        QUALIFIED_NAME = qName;
            }
      }

      @Override
      public void characters(char[] ch,int start,int length) throws SAXException
      {
            if (length > 0)
            {
                  var content = String.valueOf(ch,start,length);
                  if (!content.isBlank())
                  {

                        switch (QUALIFIED_NAME)
                        {
                              case NAME:
                                    employeeBuilder.name(content);
                                    break;
                              case SURNAME:
                                    employeeBuilder.surname(content);
                                    break;
                              case AGE:
                                    employeeBuilder.age(Integer.parseInt(content));
                                    break;
                              case SALARY:
                                    employeeBuilder.salary(Float.parseFloat(content));
                                    break;
                        }

                  }

            }
      }

      @Override
      public void endElement(String uri,String localName,String qName) throws SAXException
      {
            if (qName.equals(EMPLOYEE))
            {
                  lstEmployee.add(employeeBuilder.build());
                  employeeBuilder = null;
            }
      }

      @Override
      public void endDocument() throws SAXException
      {
            lstEmployee.forEach(System.out::println);
      }

}
