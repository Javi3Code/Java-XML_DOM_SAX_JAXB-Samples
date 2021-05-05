package common.jeycode.xml.domsample;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class XMLDOMCreateSample
{

      private static final String SALARY = "salary";
      private static final String AGE = "age";
      private static final String SURNAME = "surname";
      private static final String NAME = "name";
      private static final String EMPLOYEE_CODE = "employeeCode";
      private static final String CATEGORY = "category";
      private static final String EMPLOYEE = "employee";
      private static final String EMPLOYEES = "employees";

      private static final File XML_FILE = new File("Employeesv2.xml");

      private static final String comment = "xml para ejemplo parser DOM y SAX";

      private static final String[] employee0 = {"Operario","7070","Pepe","Dominguez","21","1450.75"};
      private static final String[] employee1 = {"Operario Temporal","7100","María","García","19","1008.9"};
      private static final String[] employee2 = {"Jefe de Sección","1001","Pedro","Rolando","38","1893.34"};
      private static final String[] employee3 = {"Maquinista","7022","Juan","de Benito","54","1858.68"};
      private static final String[] externalEmployee = {"Seguridad","Lola","Flores"};

      public static void main(String[] args)
                              throws ParserConfigurationException,TransformerFactoryConfigurationError,TransformerException
      {

            var document = DocumentBuilderFactory.newInstance()
                                                 .newDocumentBuilder()
                                                 .newDocument();

            document.setXmlVersion("1.0");
            document.appendChild(document.createComment(comment));

            var employeesEl = document.createElement(EMPLOYEES);
            document.appendChild(employeesEl);

            createEmployee(document,employeesEl,employee0);
            createEmployee(document,employeesEl,employee1);
            createEmployee(document,employeesEl,employee2);
            createEmployee(document,employeesEl,employee3);
            createEcternalEmployee(document,employeesEl,externalEmployee);

            var domSource = new DOMSource(document);
            var result = new StreamResult(XML_FILE);
            var transformer = TransformerFactory.newInstance()
                                                .newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,"no");
            transformer.transform(domSource,result);

      }

      private static void createEcternalEmployee(Document document,Element empDad,String[] externalemployee)
      {
            var employeeEl = document.createElement(EMPLOYEE);
            employeeEl.setAttribute(CATEGORY,externalemployee[0]);
            empDad.appendChild(employeeEl);

            var name = document.createElement(NAME);
            name.appendChild(document.createTextNode(externalemployee[1]));
            employeeEl.appendChild(name);

            var surname = document.createElement(SURNAME);
            surname.appendChild(document.createTextNode(externalemployee[2]));
            employeeEl.appendChild(surname);
      }

      private static void createEmployee(Document document,Element empDad,String[] employee)
      {
            var employeeEl = document.createElement(EMPLOYEE);
            employeeEl.setAttribute(CATEGORY,employee[0]);
            employeeEl.setAttribute(EMPLOYEE_CODE,employee[1]);
            empDad.appendChild(employeeEl);

            var name = document.createElement(NAME);
            name.appendChild(document.createTextNode(employee[2]));
            employeeEl.appendChild(name);

            var surname = document.createElement(SURNAME);
            surname.appendChild(document.createTextNode(employee[3]));
            employeeEl.appendChild(surname);

            var age = document.createElement(AGE);
            age.appendChild(document.createTextNode(employee[4]));
            employeeEl.appendChild(age);

            var salary = document.createElement(SALARY);
            salary.appendChild(document.createTextNode(employee[5]));
            employeeEl.appendChild(salary);

      }

}
