/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifes.jena.example.sparql;

import static br.edu.ifes.jena.example.sparql.AbstractQuery.inputFileName1;
import br.edu.ifes.jena.example.sparql.util.SPARQLUtil;

/**
 *
 * @author paulosantosjunior
 */
public class Optional {
    
        @SuppressWarnings("static-access")
	public static void main (String[] args)
	{
		String query = "";
		
		SPARQLUtil.INSTANCE.printQuery(query,inputFileName1);
	}
    
}
