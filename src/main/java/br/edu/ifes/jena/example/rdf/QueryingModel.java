package br.edu.ifes.jena.example.rdf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;

public class QueryingModel {

	public static void main(String[] args) {
		
		Model model = ModelFactory.createDefaultModel();
		
		model.read( "vc-db-1.rdf" );
		
		System.out.println("Utilizando Model.listSubjectWithProperty");
		
		ResIterator resIterator = model.listSubjectsWithProperty(VCARD.FN);
		
		while (resIterator.hasNext())
		{
			Resource resource = resIterator.next();
			System.out.println(resource.toString());
		}
		
		System.out.println("Utilizando Selector");
		
		Selector selector = new SimpleSelector(null, VCARD.FN,(RDFNode) null);
		
		StmtIterator iter = model.listStatements (selector);
		
		while (iter.hasNext())
		{
			Statement statement = iter.next();
			Resource subject = statement.getSubject();
			Property predicate = statement.getPredicate();
			RDFNode object = statement.getObject();
			
			System.out.println("Sujeito: "+subject.toString());
			System.out.println("Predicado: "+predicate.toString());
			System.out.println("Objeto: "+object.toString());
		}
		

	}

}
