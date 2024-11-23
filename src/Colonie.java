

import Exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Colonie {
	private List<Colon> colons;
    private Map<Colon, Set<Colon>> relations;
	
    public Colonie() {
    	colons=new ArrayList<Colon>();
    	relations= new HashMap<Colon, Set<Colon>>();
    }
    
    public List<Colon> getColons(){
    	return this.colons;
    }
    public Map<Colon , Set<Colon>> getRelation(){
    	return this.relations;
    }
  

    
	
    public void ajouterRelation(String nom1, String nom2) throws ColonInexistantException, RelationDejaExistanteException, RelationAvecSoiMemeException {
        if (nom1.equals(nom2)) {
            throw new RelationAvecSoiMemeException("Erreur : un colon ne peut pas avoir une relation avec lui-même (" + nom1 + ").");
        }

        Colon colon1 = getColon(nom1);
        Colon colon2 = getColon(nom2);

        if (colon1 == null || colon2 == null) {
            throw new ColonInexistantException("Erreur : au moins un des colons n'existe pas (" + nom1 + ", " + nom2 + ").");
        }
        if (relations.get(colon1).contains(colon2)) {
            throw new RelationDejaExistanteException("Erreur : la relation entre " + nom1 + " et " + nom2 + " existe déjà.");
        }
        relations.get(colon1).add(colon2);
        relations.get(colon2).add(colon1);
    }

	

	public void ajouterColon(String nom) throws ColonDejaExistantException {
        for (Colon colon : colons) {
            if (colon.getNom().equals(nom)) {
                throw new ColonDejaExistantException("Erreur : un colon avec le nom " + nom + " existe déjà.");
            }
        }
        Colon colon = new Colon(nom);
        colons.add(colon);
        relations.put(colon, new HashSet<>());
    }

	
	
	
	
    public Colon getColon(String nom) throws ColonInexistantException {
        for (Colon colon : colons) {
            if (colon.getNom().equalsIgnoreCase(nom)) {
                return colon;
            }
       }
        throw new ColonInexistantException("Erreur : le colon " + nom + " n'existe pas");  
    }
    

 
    public boolean toutesLesPreferencesAttribuees() {
        for (Colon colon : colons) {
            if (colon.getPreferences() == null || colon.getPreferences().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public void verifierPreferencesCompletes(int nombreDeRessources) throws PreferencesIncompletesException {
        for (Colon colon : colons) {
            List<Integer> preferences = colon.getPreferences();
            if (preferences == null || preferences.size() != nombreDeRessources) {
                throw new PreferencesIncompletesException(
                        "Erreur : le colon " + colon.getNom() + " n'a pas une liste de préférences complète."
                );
            }
        }
    }
    
    public Set<Colon> getEnnemis(Colon colon) {
        Set<Colon> ennemis = relations.get(colon);
        return (ennemis == null || ennemis.isEmpty()) ? null : ennemis;
    }
    
    public int calculerColonsJaloux() {
        int jaloux = 0;
        for (Colon colon : getColons()) {
            Set<Colon> ennemis = getEnnemis(colon);
            if (ennemis == null) continue;

            for (Colon ennemi : ennemis) {
                if (ennemi != null
                        && colon.getPreferences().indexOf(ennemi.getRessourceAttribuee())
                        < colon.getPreferences().indexOf(colon.getRessourceAttribuee())) {
                    jaloux++;
                    break;
                }
            }
        }
        return jaloux;
    }
    
	public void echangerRessources(String nom1, String nom2) throws EchangeAvecSoiMemeException, ColonInexistantException {
		if(nom1.equals(nom2)) {
			throw new EchangeAvecSoiMemeException("Erreur : un colon ne peut pas echanger d'objet avec lui-même (" + nom1 + ").");
		}
		
		Colon colon1=new Colon(nom1);
		Colon colon2=new Colon(nom2);
		
		if (colon1 == null || colon2 == null) {
            throw new ColonInexistantException("Erreur : au moins un des colons n'existe pas (" + nom1 + ", " + nom2 + ").");
        }
		
		int ressource1 = colon1.getRessourceAttribuee();
		int ressource2=colon2.getRessourceAttribuee();
		
		colon1.setRessourceAttribuee(ressource2);
		colon2.setRessourceAttribuee(ressource1);
		
		
	}
    
    
    
    
    
    
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Colon colon : colons) {
            result.append("Colon ").append(colon.getNom()).append(" (Ressource allouée : ");
            if (colon.getRessourceAttribuee() == null) {
                result.append("pas encore allouée");
            } else {
                result.append(colon.getRessourceAttribuee());
            }
            result.append(")");
            result.append(", Préférences : ");
            List<Integer> preferences = colon.getPreferences();
            if (preferences == null || preferences.isEmpty()) {
                result.append("aucune préférence");
            } else {
                result.append(preferences);
            }
            Set<Colon> ennemis = getEnnemis(colon);
            result.append(", Ennemis : ");
            if (ennemis == null || ennemis.isEmpty()) {
                result.append("aucun");
            } else {
                result.append("{");
                int i = 0;
                for (Colon ennemi : ennemis) {
                    result.append(ennemi.getNom());
                    if (i < ennemis.size() - 1) {
                        result.append(", ");
                    }
                    i++;
                }
                result.append("}");
            }
            Colon colonJalouxDe = null;
            if (ennemis != null) {
                for (Colon ennemi : ennemis) {
                    if (ennemi.getRessourceAttribuee() != null &&
                            colon.getPreferences().indexOf(ennemi.getRessourceAttribuee())
                                    < colon.getPreferences().indexOf(colon.getRessourceAttribuee())) {
                        colonJalouxDe = ennemi;
                        break;
                    }
                }
            }
            if (colonJalouxDe != null) {
                result.append(" et est jaloux de ").append(colonJalouxDe.getNom());
            } else {
                result.append(" et n'est jaloux de personne");
            }
            result.append("\n");
        }
        return result.toString();
    }


    
    
    
    
    
    
    
    
    

}
