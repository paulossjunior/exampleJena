package br.edu.ifes.jena.example.rdf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;

public class NavigatingModel {

	public static void main(String[] args) {

		Model m2 = ModelFactory.createDefaultModel();

		m2.read("vc-db-1.rdf");

		Resource vcard = m2.getResource("http://somewhere/JohnSmith/");

		System.out.println(vcard.toString());

		Resource name = (Resource) vcard.getProperty(VCARD.N).getObject();

		System.out.println(name.toString());

		name = vcard.getProperty(VCARD.N).getResource();

		System.out.println(name.toString());

		String fullName = vcard.getProperty(VCARD.FN).getString();

		System.out.println(fullName);

		// add two nickname properties to vcard
		vcard.addProperty(VCARD.NICKNAME, "Smithy").addProperty(VCARD.NICKNAME,
				"Adman");

		// set up the output
		System.out.println("The nicknames of \"" + fullName + "\" are:");
		// list the nicknames
		StmtIterator iter = vcard.listProperties(VCARD.NICKNAME);
		while (iter.hasNext()) {
			System.out.println("    "
					+ iter.nextStatement().getObject().toString());
		}

	}

}
