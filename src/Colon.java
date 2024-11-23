

import java.util.List;

public class Colon {
	private String nom;
	private List<Integer> preferences;
	private Integer ressourceAttribuee;

	public Colon(String nom) {
		this.nom=nom;
	}

	
	public List<Integer> getPreferences() {
		return preferences;
	}
	  
	public void ajouterPreference(int nextInt) {
		// TODO Auto-generated method stub
	}


	public String getNom() {
		
		return this.nom;
	}


	
	 public Integer getRessourceAttribuee() {
	        return ressourceAttribuee;
	    }

	 public void setRessourceAttribuee(Integer ressourceAttribuee) {
		 this.ressourceAttribuee = ressourceAttribuee;
	 }

}
