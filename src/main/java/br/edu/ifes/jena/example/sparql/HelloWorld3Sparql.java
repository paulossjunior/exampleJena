package br.edu.ifes.jena.example.sparql;

import br.edu.ifes.jena.example.sparql.util.SPARQLUtil;

public class HelloWorld3Sparql extends AbstractQuery{
	
	@SuppressWarnings("static-access")
	public static void main (String[] args)
	{
		String query = "SELECT ?x ?fname WHERE {?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN>  ?fname}";
		
		SPARQLUtil.INSTANCE.printSPARQLQuery(query,inputFileName1);
	}

}
