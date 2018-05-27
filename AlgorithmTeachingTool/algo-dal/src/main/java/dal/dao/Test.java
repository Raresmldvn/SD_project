package dal.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dal.model.*;

public class Test {

	public static void main(String args[]) {
		
		UserDAO userDAO = new UserDAO();
		AlgorithmDAO algDAO = new AlgorithmDAO();
		DisciplineDAO discDAO = new DisciplineDAO();
		RequestDAO reqDAO = new RequestDAO();
		RatingDAO ratDAO = new RatingDAO();
		LanguageDAO langDAO = new LanguageDAO();
		InformationDAO infoDAO = new InformationDAO();
		
		Algorithm alg = algDAO.findById(1);
		Discipline d = discDAO.findByAnything("name", "Software Design");
		Set<Algorithm> disciplines = d.getAlgorithms();
		for(Algorithm a: disciplines) {
			
			System.out.println(a.getName());
		}
		
	}
}
