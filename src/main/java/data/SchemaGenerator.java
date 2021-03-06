package data;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.SchemaOutputResolver;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SchemaGenerator {

    public static void main(String[] args) throws JAXBException, IOException {
        Giraf giraf = new Giraf();
        giraf.setName("Melman");
        JAXBContext jaxbContext = JAXBContext.newInstance(Giraf.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(giraf,System.out);

        ArrayList<DOMResult> results = new ArrayList<DOMResult>();
        jaxbContext.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String ns, String file) throws IOException {
                DOMResult result = new DOMResult();
                result.setSystemId(file);
                results.add(result);
                return result;
            }
        });

        DOMResult result = results.get(0);
        System.out.println(result.toString());
        Document doc = (Document) result.getNode();
        System.out.println(doc);
        OutputFormat outputFormat = new OutputFormat(doc);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/resources/giraf.xsd"));
        XMLSerializer xmlSerializer = new XMLSerializer(fileOutputStream, outputFormat);
        xmlSerializer.serialize(doc);

    }
}
