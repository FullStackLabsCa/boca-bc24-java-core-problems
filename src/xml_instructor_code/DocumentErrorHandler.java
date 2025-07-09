package xml_instructor_code;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class DocumentErrorHandler implements ErrorHandler {

    private boolean isValid = true;

    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("Warning: " + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("Error: " + exception.getMessage());
        isValid = false;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("Fatal error: " + exception.getMessage());
        isValid = false;
    }

    public boolean isValid() {
        return isValid;
    }
}

