import Exceptions.*;

import java.util.*;

public class Colonie
{
	private final List<Colon> colons;

    public Colonie()
    {
    	colons=new ArrayList<Colon>();
    }
    public Colonie(List<Colon> colons)
    {
        this.colons=colons;
    }
    
    public List<Colon> getColons(){
    	return this.colons;
    }

    public void ajouterColon(Colon currentColon) throws Exception
    //Symetrie
    {
        //Execption
        for (Colon c : colons)
        {
            if (currentColon.getNom().equals(c.getNom()))
            {
                throw new ColonDejaExistantException("Erreur : un colon avec le nom " + c.getNom() + " existe déjà.");
            }
        }

        //Add Colon
        colons.add(currentColon);

        // Update CurrentColon Hatelist
        for (Colon everycolon : colons)
        {
            if (everycolon.getRelationsDetestables().contains(currentColon))
            {
                currentColon.addRelation(everycolon);
            }

            //Update other Colon
            if (currentColon.getRelationsDetestables().contains(everycolon))
            {
                everycolon.addRelation(currentColon);
            }
        }
    }

    public void retireColon(Colon colon) throws Exception
    {
        if (!colons.contains(colon))
        {
            throw new ColonNonExistantException("Erreur : le colon " + colon.getNom() + " n'existe pas.");
        }

        // Retirer le colon de la liste des colons
        colons.remove(colon);

        // Retirer le colon des listes de relations détestables de tous les autres colons
        for (Colon existingColon : colons)
        {
            existingColon.removeRelation(colon);
        }

    }

    public Colon getColonObjet(String nom) throws ColonInexistantException
    {
        for (Colon colon : colons)
        {
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

    public int verifierPreferencesCompletes(int nombreDeRessources) throws PreferencesIncompletesException
    {
        for (Colon colon : colons) {
            LinkedHashSet<Integer> preferences = colon.getPreferences();
            if (preferences == null || preferences.size() != nombreDeRessources) {
                throw new PreferencesIncompletesException(
                        "Erreur : le colon " + colon.getNom() + " n'a pas une liste de préférences complète."
                );
            }
        }
        return 0;
    }

    public int cout()
    //TODO NOT WORKING
    {
        int jaloux = 0;
        for (Colon colon : getColons())
        {
            Set<Colon> ennemis = colon.getRelationsDetestables();
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
        StringBuilder Result = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for(Colon c:colons)
        {
            temp.append(c.getNom())
                    .append(" Got ")
                    .append(c.getRessource())
                    .append(" ressource.");
            System.out.println(temp);
            Result.append(temp).append("\n");
            temp.setLength(0);
        }
        return Result.toString();
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (Colon colon : colons)
        {
            result.append(colon.toString());
            Colon colonJalouxDe = null;
            if (colon.getRelationsDetestables() != null) {
                for (Colon ennemi : colon.getRelationsDetestables()) {
                    if (ennemi.getRessource() != null &&
                            colon.getPreferenceAT(ennemi.getRessource())
                                    < colon.getPreferenceAT(colon.getRessource())) {
                        colonJalouxDe = ennemi;
                        break;
                    }
                }
            }
            if (colonJalouxDe != null)
            {
                result.append(" et est jaloux de ").append(colonJalouxDe.getNom());
            } else {
                result.append(" et n'est jaloux de personne");
            }
            result.append("\n");
        }
        return result.toString();
    }

}
