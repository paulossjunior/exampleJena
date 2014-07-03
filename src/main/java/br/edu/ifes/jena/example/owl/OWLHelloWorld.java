package br.edu.ifes.jena.example.owl;



public class OWLHelloWorld {

	public static void main(String[] args) {
		
		HelloSemanticWeb hello = new HelloSemanticWeb();
		
		//Load my FOAF friends
		System.out.println("Load my FOAF Friends");
		hello.populateFOAFFriends();
		
		// Say Hello to myself
		System.out.println("\nSay Hello to Myself");
		hello.mySelf(hello._friends);  
		
		
	}

}
