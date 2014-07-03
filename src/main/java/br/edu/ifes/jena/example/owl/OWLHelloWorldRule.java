package br.edu.ifes.jena.example.owl;

import java.io.IOException;

public class OWLHelloWorldRule {

	public static void main(String[ ] args) throws IOException{
		
		HelloSemanticWeb hello = new HelloSemanticWeb();
		
		//Load my FOAF friends
		System.out.println("Load my FOAF Friends");
		hello.populateFOAFFriends();
		
		//Add my new friends
		System.out.println("\nadd my new friends");
		hello.populateNewFriends();
		
		//Say hello to my friends - hey my new ones are missing?
		System.out.println("\nSay hello to all my friends - hey the new ones are missing!");
		hello.myFriends(hello._friends);
		
		// Add the ontologies
		System.out.println("\nAdd the Ontologies");
		hello.populateFOAFSchema();
		hello.populateNewFriendsSchema();
		
		//See if the ontologies help identify my new friends? Nope!
		System.out.println("\nSee if the ontologies help to say hello to all my friends - Nope!");
		hello.myFriends(hello._friends);
		
		//Align the ontologies to bind my friends together
		System.out.println("\nOk, lets add alignment statements for the two ontologies.");
		hello.addAlignment();
		
		//Now say hello to my friends - nope still no new friends!
		System.out.println("\nTry again - Hello to all my friends - nope still not all!");
		hello.myFriends(hello._friends);
		
		//Run reasoner to  align the instances
		System.out.println("\nRun a Reasoner");
		hello.bindReasoner();
		
		hello.myFriends(hello.inferredFriends);
		
		
	}
}
