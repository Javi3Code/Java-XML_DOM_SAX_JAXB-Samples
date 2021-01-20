package common.jeycode.xml.domsample.map;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jeycode.pojos.Employee;

public final class XMLDOM_RW_SAMPLE
{

      private static final String SALARY = "salary";
      private static final String AGE = "age";
      private static final String SURNAME = "surname";
      private static final String NAME = "name";
      private static final String EMPLOYEE_CODE = "employeeCode";
      private static final String CATEGORY = "category";
      private static final String EMPLOYEE = "employee";
      private static final String EMPLOYEES = "employees";
      private static final File XML_FILE = new File("Employees.xml");

      public static void main(String[] args) throws SAXException,IOException,ParserConfigurationException,TransformerConfigurationException,
                              TransformerFactoryConfigurationError,TransformerException
      {
            var domDocument = DocumentBuilderFactory.newInstance()
                                                    .newDocumentBuilder()
                                                    .parse(XML_FILE);

            var employeeLst = domDocument.getElementsByTagName(EMPLOYEE);
            loadList(employeeLst).forEach(System.out::println);

            var emp1 = Employee.builder()
                               .name("Pepe")
                               .category("Limpieza")
                               .build();

            var emp2 = Employee.builder()
                               .name("Bengano")
                               .surname("Benito")
                               .build();

            System.out.println("----");
            appendEmployee(emp1,domDocument);
            appendEmployee(emp2,domDocument);
            System.out.println("Se añadieron 2 empleados");

            createNewXML(domDocument);

      }

      private static void appendEmployee(Employee emp,Document domDocument)
      {
            var employee = domDocument.createElement(EMPLOYEE);

            tryToAddCategory(emp,domDocument,employee);

            tryToAddEmplCode(emp,domDocument,employee);

            tryToAddEmpName(emp,domDocument,employee);

            tryToAddEmpSname(emp,domDocument,employee);

            tryToAddEmpSalary(emp,domDocument,employee);

            tryToAddEmpAge(emp,domDocument,employee);

            domDocument.getElementsByTagName(EMPLOYEES)
                       .item(0)
                       .appendChild(employee);

      }

      private static void tryToAddEmpAge(Employee newEmp,Document domDocument,Element employee)
      {
            var age = newEmp.getAge();
            if (age != 0)
            {
                  var ag = domDocument.createElement(AGE);
                  ag.appendChild(domDocument.createTextNode(age + ""));
                  employee.appendChild(ag);
            }
      }

      private static void tryToAddEmpSalary(Employee newEmp,Document domDocument,Element employee)
      {
            var salary = newEmp.getSalary();
            if (salary != 0)
            {
                  var sal = domDocument.createElement(SALARY);
                  sal.appendChild(domDocument.createTextNode(salary + ""));
                  employee.appendChild(sal);
            }
      }

      private static void tryToAddEmpSname(Employee newEmp,Document domDocument,Element employee)
      {
            var surname = newEmp.getSurname();
            if (surname != null)
            {
                  var sm = domDocument.createElement(SURNAME);
                  sm.appendChild(domDocument.createTextNode(surname));
                  employee.appendChild(sm);
            }
      }

      private static void tryToAddEmpName(Employee newEmp,Document domDocument,Element employee)
      {
            var name = newEmp.getName();
            if (name != null)
            {
                  var nm = domDocument.createElement(NAME);
                  nm.appendChild(domDocument.createTextNode(name));
                  employee.appendChild(nm);
            }
      }

      private static void tryToAddEmplCode(Employee newEmp,Document domDocument,Element employee)
      {
            var empCode = newEmp.getEmployeeCode();
            if (empCode != 0)
            {
                  employee.setAttribute(EMPLOYEE_CODE,empCode + "");
            }
      }

      private static void tryToAddCategory(Employee newEmp,Document domDocument,Element employee)
      {
            var category = newEmp.getCategory();
            if (category != null)
            {
                  employee.setAttribute(CATEGORY,category);
            }
      }

      private static List<Employee> loadList(NodeList employeeLst)
      {
            return IntStream.range(0,employeeLst.getLength())
                            .mapToObj(index-> createEmployee(employeeLst.item(index)))
                            .collect(Collectors.toList());

      }

      private static Employee createEmployee(Node item)
      {
            var employee = Employee.builder();
            var att = item.getAttributes();
            var nodes = item.getChildNodes();

            var attCat = att.getNamedItem(CATEGORY);
            if (Objects.nonNull(attCat))
            {
                  employee.category(attCat.getNodeValue());
            }

            var employeeCode = att.getNamedItem(EMPLOYEE_CODE);
            if (Objects.nonNull(employeeCode))
            {
                  employee.employeeCode(Integer.parseInt(employeeCode.getNodeValue()));
            }

            IntStream.range(0,nodes.getLength())
                     .mapToObj(index-> nodes.item(index))
                     .filter(node-> node.getNodeType() == Node.ELEMENT_NODE)
                     .forEach(node->
                           {
                                 switch (node.getNodeName())
                                 {
                                       case NAME:
                                             employee.name(node.getTextContent());
                                             break;
                                       case SURNAME:
                                             employee.surname(node.getTextContent());
                                             break;
                                       case AGE:
                                             employee.age(Integer.parseInt(node.getTextContent()));
                                             break;
                                       case SALARY:
                                             employee.salary(Float.parseFloat(node.getTextContent()));
                                             break;
                                       default:
                                             break;
                                 }

                           });

            return employee.build();
      }

      private static void createNewXML(Document domDoc)
                              throws TransformerConfigurationException,TransformerFactoryConfigurationError,TransformerException
      {
            var domSource = new DOMSource(domDoc);
            var result = new StreamResult(XML_FILE);
            var transformer = TransformerFactory.newInstance()
                                                .newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,"no");
            transformer.transform(domSource,result);
      }

}
