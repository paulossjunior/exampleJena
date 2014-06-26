package br.edu.ifes.jena.example.sparql;

import br.edu.ifes.jena.example.sparql.util.SPARQLUtil;

public class ConsultaDinamica extends AbstractQuery{

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		
		String query = "SELECT ?x WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN> ?name }";
		
		SPARQLUtil.INSTANCE.printQueryParameterizedString(query, inputFileName1, "name","John Smith");
		
		SPARQLUtil.INSTANCE.printQueryMAP(query, inputFileName1, "name","John Smith");
				

	}

}
