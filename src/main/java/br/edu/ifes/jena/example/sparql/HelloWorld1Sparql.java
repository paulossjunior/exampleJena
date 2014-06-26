package br.edu.ifes.jena.example.sparql;

import br.edu.ifes.jena.example.sparql.util.SPARQLUtil;

public class HelloWorld1Sparql extends AbstractQuery{
	
	@SuppressWarnings("static-access")
	public static void main (String[] args)
	{
		String query = "SELECT ?x WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN> \"John Smith\" }";
		
		SPARQLUtil.INSTANCE.printSPARQLQuery(query,inputFileName1);
	}

}
