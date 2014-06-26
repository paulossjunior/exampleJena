package br.edu.ifes.jena.example.rdf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class ReadFile {

	
	public static void main (String [] args)
	{
		Model m2 = ModelFactory.createDefaultModel();
		
		m2.read( "vc-db-1.rdf" );
		
		//m2.write( System.out );
		
		StmtIterator iterator = m2.listStatements();

		while (iterator.hasNext()) {
			Statement statement = iterator.next();
			Resource subject = statement.getSubject();
			Property predicate = statement.getPredicate();
			RDFNode object = statement.getObject();

			System.out.println("Subject: "+subject.toString());
			
			System.out.println("Predicate: " + predicate.toString() + " ");
			if (object instanceof Resource) {
				System.out.println("Object: "+object.toString());
			} else {
				// object is a literal
				System.out.println("Object: \"" + object.toString() + "\"");
			}
			
		}
		
	}
}
