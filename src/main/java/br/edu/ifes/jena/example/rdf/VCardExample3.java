package br.edu.ifes.jena.example.rdf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

public class VCardExample3 {
	// some definitions
	static String personURI    = "http://somewhere/JohnSmith";
	static String givenName    = "John";
	static String familyName   = "Smith";
	static String fullName     = givenName + " " + familyName;

	public static void main(String[] args) {
		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		// create the resource
		Resource johnSmith = model.createResource(personURI);
		// add the property
		johnSmith.addProperty(VCARD.FN, fullName);
	
		Resource n = model.createResource();
		
		n.addProperty(VCARD.Given, givenName);
		n.addProperty(VCARD.Family,familyName);
		
		johnSmith.addProperty(VCARD.N, n);
		 
		 model.write(System.out,"RDF/XML-ABBREV");
		 
		 System.out.println("----------");
		 
		 model.write(System.out,"N-TRIPLE");
		 
		 System.out.println("----------");
		 
		 model.write(System.out,"Turtle");
		 
	}
}
