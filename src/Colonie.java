import Exceptions.*;

import java.util.*;

public class Colonie
{
	private List<Colon> colons;
    private Map<Colon, Set<Colon>> relationsDetestables; //Colon & ListOfColonsHated
	
    public Colonie()
    {
    	colons=new ArrayList<Colon>();
    	relationsDetestables= new HashMap<Colon, Set<Colon>>();
    }
    public Colonie(List<Colon> colons)
    {
        this.colons=colons;
        relationsDetestables= new HashMap<Colon, Set<Colon>>();
    }
    
    public List<Colon> getColons(){
    	return this.colons;
    }

    public void ajouterColon(Colon currentColon) throws ColonDejaExistantException
    //Symetrie
    {
        for (Colon c : colons)
        {
            if (currentColon.getNom().equals(c.getNom())) {
                throw new ColonDejaExistantException("Erreur : un colon avec le nom " + c.getNom() + " existe déjà.");
            }
        }

        //Add Colon and add everyone who hates him in his list
        colons.add(currentColon);
        relationsDetestables.put(currentColon,new HashSet<Colon>());
        for(Map.Entry<Colon, Set<Colon>> everyColon: relationsDetestables.entrySet())
        {
            Colon otherColon = everyColon.getKey();
            Set<Colon> hatelist = everyColon.getValue();
            if(hatelist.contains(currentColon))
            {
                relationsDetestables.get(currentColon).add(otherColon);
            }
        }

        //Update other Colon
        Set<Colon> currentColonHateList = relationsDetestables.get(currentColon);
        for(Colon hatedColon : currentColonHateList)
        {
            relationsDetestables.get(hatedColon).add(currentColon);
        }

    }

    public void retireColon(Colon colon) throws ColonNonExistantException
    {
        relationsDetestables.remove(colon);
        for (Set<Colon> hateList : relationsDetestables.values())
        {
            hateList.remove(colon);
        }
        colons.remove(colon);
    }


    public Map<Colon , Set<Colon>> getRelations(){return this.relationsDetestables;}

    public void ajouterRelation(Colon colon1, ArrayList<Colon> relationsList) throws ColonInexistantException, RelationDejaExistanteException, RelationAvecSoiMemeException
    {

        if(!relationsList.contains(colon1))
        {
            throw new ColonInexistantException("Erreur: Le colon n'existe pas.");
        }
        for(Colon c:relationsList)
        {
            //Add Colon
            if (colon1==c)
            {
                throw new RelationAvecSoiMemeException("Erreur : un colon ne peut pas avoir une relation avec lui-même (" + colon1.getNom() + ").");
            }
            if (relationsDetestables.get(colon1).contains(c))
            {
                throw new RelationDejaExistanteException("Erreur : la relation entre " + colon1.getNom() + " et " + colon1.getNom() + " existe déjà.");
            }
            //add ton main
            relationsDetestables.get(colon1).add(c);
            //add to hated
            relationsDetestables.get(c).add(colon1);
        }
    }

    public Colon getColonObjet(String nom) throws ColonInexistantException
    {
        for (Colon colon : colons) {
            if (colon.getNom().equalsIgnoreCase(nom)) {
                return colon;
            }
       }
        throw new ColonInexistantException("Erreur : le colon " + nom + " n'existe pas");  
    }

    public String getColonName(Colon colonRecherche) throws ColonInexistantException
    {
        for (Colon colon : colons) {
            if (colon.equals(colonRecherche)) {
                return colon.getNom();
            }
        }
        throw new ColonInexistantException("Erreur : le colon " + colonRecherche.getNom() + " n'existe pas");
    }

    public boolean toutesLesPreferencesAttribuees()
    {
        for (Colon colon : colons) {
            if (colon.getPreferences() == null || colon.getPreferences().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public void verifierPreferencesCompletes(int nombreDeRessources) throws PreferencesIncompletesException
    {
        for (Colon colon : colons) {
            LinkedHashSet<Integer> preferences = colon.getPreferences();
            if (preferences == null || preferences.size() != nombreDeRessources) {
                throw new PreferencesIncompletesException(
                        "Erreur : le colon " + colon.getNom() + " n'a pas une liste de préférences complète."
                );
            }
        }
    }
    
    public Set<Colon> getEnnemis(Colon colon)
    {
        Set<Colon> ennemis = relationsDetestables.get(colon);
        return (ennemis == null || ennemis.isEmpty()) ? null : ennemis;
    }
    
    public int calculerColonsJaloux()
    //TODO
    {
        int jaloux = 0;
        for (Colon colon : getColons()) {
            Set<Colon> ennemis = getEnnemis(colon);
            if (ennemis == null) continue;

            for (Colon ennemi : ennemis) {
                if (ennemi != null
                        && colon.getPreferenceAT(ennemi.getRessource())
                        < colon.getPreferenceAT(colon.getRessource())) {
                    jaloux++;
                    break;
                }
            }
        }
        return jaloux;
    }

	public void echangerRessources(Colon colon1, Colon colon2) throws EchangeAvecSoiMemeException, ColonInexistantException
    {
		if(colon1 == colon2)
        {
			throw new EchangeAvecSoiMemeException("Erreur : un colon ne peut pas echanger d'objet avec lui-même (" + colon1.getNom() + ").");
		}

        if (!colons.contains(colon1) || !colons.contains(colon2))
        {
            throw new ColonInexistantException("Erreur : au moins un des colons n'existe pas (" + colon1.getNom() + ", " + colon2.getNom() + ").");
        }

		int ressource1 = colon1.getRessource();
		int ressource2 = colon2.getRessource();

		colon1.affectationRessource(ressource2);
		colon2.affectationRessource(ressource1);
	}

    public String afficherRessourcesDesColons()
    {
        StringBuffer Result = new StringBuffer();
        StringBuffer temp = new StringBuffer();
        for(Colon c:colons)
        {
            temp.append(c.getNom()+" Got "+c.getRessource()+" ressource.");
            System.out.println(temp);
            Result.append(temp+"\n");
            temp.setLength(0);
        }
        return Result.toString();
    }

    @Override
    public String toString()
    //TODO
    // To fix: Display of HateList
    {
        StringBuilder result = new StringBuilder();
        for (Colon colon : colons) {
            result.append("Colon ").append(colon.getNom()).append(" (Ressource allouée : ");
            if (colon.getRessource() == null) {
                result.append("pas encore allouée");
            } else {
                result.append(colon.getRessource());
            }
            result.append(")");
            result.append(", Préférences : ");
            LinkedHashSet<Integer> preferences = colon.getPreferences();
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
                    if (ennemi.getRessource() != null &&
                            colon.getPreferenceAT(ennemi.getRessource())
                                    < colon.getPreferenceAT(colon.getRessource())) {
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
