package xml_instructor_code;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLValidatorWithDTD {
    public static void main(String[] args) {

        try {
            // 1. Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // 2. Enable validation and DTD processing
            factory.setValidating(true);
            factory.setNamespaceAware(false); // DTD doesn't use namespaces

            // 3. Create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();


            // 4. Set an ErrorHandler to capture validation errors
            DocumentErrorHandler errorHandler = new DocumentErrorHandler();

            builder.setErrorHandler(errorHandler);

            // project home 
            File projectHome = new File(System.getProperty("user.dir"));

            // 5. Parse the XML file (employee.xml)
            File xmlFile = new File(projectHome.getAbsolutePath()+"/src/xml_instructor_code/resources/employee-using-dtd.xml");
            Document doc = builder.parse(xmlFile);

            if (!errorHandler.isValid()) {
                System.out.println("Error validating XML");
            }

            // If no exception, XML is valid
        } catch (SAXException e) {
            System.out.println("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}