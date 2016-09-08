package org.uva.sea.ql;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.value.Value;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ExportToXML {
	Form form;
	ValueMap valueMap;
	
	public ExportToXML(Form form, ValueMap valueMap) {
		this.form = form;
		this.valueMap = valueMap;
	}
	
	public void outputToXML(File path) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("ql");
			doc.appendChild(rootElement);
			Attr formAttr = doc.createAttribute("name");
			formAttr.setValue(form.getName());
			
			// populate form elements
			for (String identifier : valueMap.getValueMap().keySet()) {
				// form elements (questions)
				Element formElement = doc.createElement("question");
				rootElement.appendChild(formElement);
				
				Element questionIdentifier = doc.createElement("identifier");
				questionIdentifier.appendChild(doc.createTextNode(identifier));
				formElement.appendChild(questionIdentifier);
				
				Element identifierValue = doc.createElement("value");
				Value value = valueMap.getValueFromMap(identifier);
				String result = String.valueOf(value.getValue());
				identifierValue.appendChild(doc.createTextNode(result));
				formElement.appendChild(identifierValue);
			}
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path.getAbsolutePath() + "\\" + "myFile.xml"));
			System.out.println("oh ows");
			// Output to console for testing 
		    //StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);

			System.out.println("File saved!");

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  } finally {
			  //
		  }
		
	}
}
