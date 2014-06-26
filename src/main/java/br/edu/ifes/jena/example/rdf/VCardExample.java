package br.edu.ifes.jena.example.rdf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

public class VCardExample {
	// some definitions
	static String personURI    = "http://somewhere/JohnSmith";
	static String fullName     = "John Smith";

	public static void main(String[] args) 
	{
	
		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
	
		// create the resource
		Resource johnSmith = model.createResource(personURI);
	
		// add the property
		johnSmith.addProperty(VCARD.FN, fullName);
		 
		model.write(System.out, "Turtle");
		
	}
}
