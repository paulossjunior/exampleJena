package br.edu.ifes.jena.example.sparql;

import br.edu.ifes.jena.example.sparql.util.SPARQLUtil;

public class QNameSparql extends AbstractQuery{
	
	@SuppressWarnings("static-access")
	public static void main (String[] args)
	{
		String query = 
				"PREFIX vcard: <http://www.w3.org/2001/vcard-rdf/3.0#> "
						+" SELECT ?givenName"
						+" WHERE "
						+" { ?y vcard:Family \"Smith\" ;"
						+"      vcard:Given  ?givenName "
						+" }";
		
		SPARQLUtil.INSTANCE.printQuery(query,inputFileName1);
	}

}
