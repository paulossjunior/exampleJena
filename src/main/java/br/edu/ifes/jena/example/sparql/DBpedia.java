package br.edu.ifes.jena.example.sparql;

import br.edu.ifes.jena.example.sparql.util.SPARQLUtil;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Literal;

public class DBpedia {

	public static void main(String[] args) {

		String query1 = " 	PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ " PREFIX : <http://dbpedia.org/resource/>"
				+ " PREFIX d: <http://dbpedia.org/ontology/> "
				+ " SELECT distinct ?albumName ?artistName "
				+ " WHERE "
				+ " { "
				+ " ?album d:producer :Timbaland . "
				+ " ?album d:musicalArtist ?artist ."
				+ " ?album rdfs:label ?albumName . "
				+ " ?artist rdfs:label ?artistName ."
				+ " FILTER ( lang(?artistName) = \"en\")"
				+ " FILTER ( lang(?albumName) = \"en\" )" + " }";

		ResultSet results = SPARQLUtil.INSTANCE.dbpediaQuery(query1);
		ResultSetFormatter.out(System.out, results);

		while (results.hasNext()) {

			QuerySolution soln = results.next();

			Literal albumName = soln.getLiteral("albumName");
			Literal artistName = soln.getLiteral("artistName");
			
			System.out.println(albumName+"--"+artistName);

		}

	}
}
