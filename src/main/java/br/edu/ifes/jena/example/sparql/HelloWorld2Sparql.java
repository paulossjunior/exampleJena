package br.edu.ifes.jena.example.sparql;

import br.edu.ifes.jena.example.sparql.util.SPARQLUtil;

public class HelloWorld2Sparql extends AbstractQuery{
	
	@SuppressWarnings("static-access")
	public static void main (String[] args)
	{
		String query = "SELECT ?s ?p ?o WHERE { ?s ?p ?o }";
		
		SPARQLUtil.INSTANCE.printSPARQLQuery(query,inputFileName1);
	}

}
