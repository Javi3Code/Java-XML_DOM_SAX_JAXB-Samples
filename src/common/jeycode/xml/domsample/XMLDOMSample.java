package common.jeycode.xml.domsample;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class XMLDOMSample
{

						private static final File XML_FILE = new File("Employees.xml");

						public static void main(String[] args) throws SAXException,IOException,ParserConfigurationException
						{
												// Parsear
												var domDocument = DocumentBuilderFactory.newInstance()
																																																				.newDocumentBuilder()
																																																				.parse(XML_FILE);

												// Obtener elementos por etiqueta
												var employees = domDocument.getElementsByTagName("employee");

												browseNodeLst(employees);

						}

						private static void browseNodeLst(NodeList employees)
						{
												var count = IntStream.range(0,employees.getLength())
																																	.mapToObj(index-> employees.item(index))
																																	.filter(node-> node.getNodeType() == Node.ELEMENT_NODE)
																																	.peek(XMLDOMSample::printData)
																																	.count();

												if (count != 0)
												{
																		System.out.println("Procesados " + count + " nodos");
												}
						}

						private static void printData(Node node)
						{

												if (!node.getNodeName()
																					.equals("employee"))
												{
																		System.out.println("Nombre nodo: " + node.getNodeName() + ", Contenido: " + node.getTextContent());
												}
												else
												{
																		System.out.println("Empleado");
																		System.out.println();
												}

												browseAttLst(node.getAttributes());
												browseNodeLst(node.getChildNodes());
						}

						private static void browseAttLst(NamedNodeMap attributes)
						{
												if (attributes != null)
												{
																		IntStream.range(0,attributes.getLength())
																											.mapToObj(index-> attributes.item(index))
																											.forEach(att-> System.out.println("AttNombre: " + att.getNodeName() + " // Contenido: " + att.getNodeValue()));
												}
						}

}
