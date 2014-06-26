package br.edu.ifes.jena.example.rdf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;

public class VCardExample2 {
	// some definitions
	static String personURI = "http://somewhere/JohnSmith";
	static String givenName = "John";
	static String familyName = "Smith";
	static String fullName = givenName + " " + familyName;

	public static void main(String[] args) {
		// create an empty Model
		Model model = ModelFactory.createDefaultModel();
		// create the resource
		Resource johnSmith = model.createResource(personURI);
		// add the property
		johnSmith.addProperty(VCARD.FN, fullName);

		Resource n = model.createResource();
		n.addProperty(VCARD.Given, givenName);
		n.addProperty(VCARD.Family, familyName);
		johnSmith.addProperty(VCARD.N, n);

		
	}
}
