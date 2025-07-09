package xml_instructor_code;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class XMLValidatorWithXSD {
    public static void main(String[] args) {
        try {
            File projectHome = new File(System.getProperty("user.dir"));

            // 1. Load the XML Schema (employee.xsd)
            File schemaFile = new File(projectHome.getAbsolutePath()+"/src/xml_instructor_code/resources/employee.xsd");
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaFile);

            // 2. Create a Validator
            Validator validator = schema.newValidator();

            // 3. Validate the XML file (employee.xml)
            File xmlFile = new File(projectHome.getAbsolutePath()+"/src/xml_instructor_code/resources/employee-using-xsd.xml");
            validator.validate(new javax.xml.transform.stream.StreamSource(xmlFile));

            // If no exception, XML is valid
            System.out.println("The XML file is valid.");

        } catch (SAXException e) {
            // Validation failed
            System.out.println("Validation failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}