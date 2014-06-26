package br.edu.ifes.jena.example.sparql;

import br.edu.ifes.jena.example.sparql.util.SPARQLUtil;

public class PadraoBasicoSparql extends AbstractQuery{
	
	@SuppressWarnings("static-access")
	public static void main (String[] args)
	{
		String query = 
				"SELECT ?givenName "
						+ "WHERE "
						+ "{ ?y  <http://www.w3.org/2001/vcard-rdf/3.0#Family>  \"Smith\" . "
						+ "  ?y  <http://www.w3.org/2001/vcard-rdf/3.0#Given>  ?givenName . "
						+ "}";
		
		SPARQLUtil.INSTANCE.printQuery(query,inputFileName1);
		
		query = 
				"SELECT ?givenName "
						+ "WHERE "
						+ "{ ?y  <http://www.w3.org/2001/vcard-rdf/3.0#Family>  \"Smith\" ; "
						+ "    <http://www.w3.org/2001/vcard-rdf/3.0#Given>  ?givenName  "
						+ "}";
		
		SPARQLUtil.INSTANCE.printQuery(query,inputFileName1);
	}

}
