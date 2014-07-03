
package br.edu.ifes.jena.example.owl;

import com.hp.hpl.jena.ontology.OntModel;
import java.io.IOException;
import java.io.InputStream;


import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.FileManager;



public class HelloSemanticWeb {
	private static String defaultNameSpace = "http://org.semwebprogramming/chapter2/people#";
	
	public OntModel _friends = null;
	public OntModel schema = null;
	public InfModel inferredFriends = null;

	public void populateFOAFFriends(){
		_friends = ModelFactory.createOntologyModel();
		InputStream inFoafInstance = FileManager.get().open("Ontologies/FOAFFriends.rdf");
		_friends.read(inFoafInstance,defaultNameSpace);
	}
	
	public void mySelf(Model model){
		//Hello to Me - focused search
		runQuery(" select DISTINCT ?name where{ people:me foaf:name ?name  }", model);  //add the query string

	}
	
	public void myFriends(Model model){
		//Hello to just my friends - navigation
		runQuery(" select DISTINCT ?myname ?name where{  people:me foaf:knows ?friend. ?friend foaf:name ?name } ", model);  //add the query string

	}
	
	public void populateNewFriends() throws IOException {		
		InputStream inFoafInstance = FileManager.get().open("Ontologies/additionalFriends.owl");
		_friends.read(inFoafInstance,defaultNameSpace);
		inFoafInstance.close();


	} 
	
	public void addAlignment(){
		
		// State that :individual is equivalentClass of foaf:Person
		Resource resource = schema.createResource(defaultNameSpace + "Individual");
		Property prop = schema.createProperty("http://www.w3.org/2002/07/owl#equivalentClass");
		Resource obj = schema.createResource("http://xmlns.com/foaf/0.1/Person");
		schema.add(resource,prop,obj);
		
		//State that :hasName is an equivalentProperty of foaf:name
		resource = schema.createResource(defaultNameSpace + "hasName");
		//prop = schema.createProperty("http://www.w3.org/2000/01/rdf-schema#subPropertyOf");
		prop = schema.createProperty("http://www.w3.org/2002/07/owl#equivalentProperty");
		obj = schema.createResource("http://xmlns.com/foaf/0.1/name");
		schema.add(resource,prop,obj);
		
		//State that :hasFriend is a subproperty of foaf:knows
		resource = schema.createResource(defaultNameSpace + "hasFriend");
		prop = schema.createProperty("http://www.w3.org/2000/01/rdf-schema#subPropertyOf");
		obj = schema.createResource("http://xmlns.com/foaf/0.1/knows");
		schema.add(resource,prop,obj);
		
		
		//State that sem web is the same person as Semantic Web
		resource = schema.createResource("http://org.semwebprogramming/chapter2/people#me");
		prop = schema.createProperty("http://www.w3.org/2002/07/owl#sameAs");
		obj = schema.createResource("http://org.semwebprogramming/chapter2/people#Individual_5");
		schema.add(resource,prop,obj);
	}
	
	public void populateFOAFSchema() throws IOException{
		InputStream inFoaf = FileManager.get().open("Ontologies/foaf.rdf");
		schema = ModelFactory.createOntologyModel();
		schema.read(inFoaf, defaultNameSpace);
		inFoaf.close();
		
        }
	
	public void populateNewFriendsSchema() throws IOException {
		InputStream inFoafInstance = FileManager.get().open("Ontologies/additionalFriendsSchema.owl");
		_friends.read(inFoafInstance,defaultNameSpace);
		inFoafInstance.close();
	}
	
	public void bindReasoner(){
	    Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
	    reasoner = reasoner.bindSchema(schema);
	    inferredFriends = ModelFactory.createInfModel(reasoner, _friends);

	}

       public void runQuery(String queryRequest, Model model){
		
		StringBuffer queryStr = new StringBuffer();
		// Establish Prefixes
		//Set default Name space first
		queryStr.append("PREFIX people" + ": <" + defaultNameSpace + "> ");
		queryStr.append("PREFIX rdfs" + ": <" + "http://www.w3.org/2000/01/rdf-schema#" + "> ");
		queryStr.append("PREFIX rdf" + ": <" + "http://www.w3.org/1999/02/22-rdf-syntax-ns#" + "> ");
		queryStr.append("PREFIX foaf" + ": <" + "http://xmlns.com/foaf/0.1/" + "> ");
		
		//Now add query
		queryStr.append(queryRequest);
		Query query = QueryFactory.create(queryStr.toString());
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		try {
		ResultSet response = qexec.execSelect();
		
		while( response.hasNext()){
			QuerySolution soln = response.nextSolution();
			RDFNode name = soln.get("?name");
			if( name != null ){
				System.out.println( "Hello to " + name.toString() );
			}
			else
				System.out.println("No Friends found!");
			}
		} finally { qexec.close();}				
		}
		
	public void runJenaRule(Model model){
		String rules = "[emailChange: (?person <http://xmlns.com/foaf/0.1/mbox> ?email), strConcat(?email, ?lit), regex( ?lit, '(.*@gmail.com)') -> (?person <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://org.semwebprogramming/chapter2/people#GmailPerson>)]";

		Reasoner ruleReasoner = new GenericRuleReasoner(Rule.parseRules(rules));
		ruleReasoner = ruleReasoner.bindSchema(schema);
	    inferredFriends = ModelFactory.createInfModel(ruleReasoner, model);		
	}
        
         
    public void myGmailFriends(Model model){
		runQuery(" select DISTINCT ?name where{  ?sub rdf:type people:GmailPerson. ?sub foaf:name ?name } ", model);  //add the query string
   	
    }
	
    
}
