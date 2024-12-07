package Conquest.Struct;

import Conquest.Exception.*;
import Conquest.Expedition;
import Conquest.Menus.Menu;
import Conquest.Menus.MenuLoadTXT;

import java.util.*;

/**
 * Classe Colonie.
 *
 * Elle stocke une liste de Colons.
 *
 *
 *
 * @see Expedition
 * @see Expedition#getColonie(int)
 * @see Expedition#createRessource()
 * @see Expedition#algoFavoriteFirst(int, Ressource)
 * @see Expedition#algoBestPerfSUR(int, int, Ressource)
 * @see Menu#afficherMenuCreationColonie()
 * @see Menu#afficherMenuConfiguration(int)
 * @see Menu#afficherMenuAffectation(int, int)
 * @see MenuLoadTXT#afficherMenuConfigurationLoadTXT()
 *
 *
 * @author Devasenaradjounayagar Damien
 *
 * @version 1.0
 *
 */
public class Colonie
{
    /**
     * La liste des colons de la Colonie.
     *
     * @see Colonie#Colonie()
     * @see Colonie#Colonie(List)
     * @see Colonie#getColons()
     * @see Colonie#ajouterColon(Colon)
     * @see Colonie#retireColon(Colon)
     * @see Colonie#getColonObjet(String)
     * @see Colonie#getColonName(Colon)
     * @see Colonie#toutesLesPreferencesAttribuees()
     * @see Colonie#verifierPreferencesCompletes(int)
     * @see Colonie#cout()
     * @see Colonie#echangerRessources(Colon, Colon)
     * @see Colonie#afficherRessourcesDesColons()
     * @see Colonie#afficherAffectations()
     * @see Colonie#toString()
     *
     */
	private final List<Colon> colons;

    /**
     * Constructeur de Colonie.
     *
     * @see Expedition#createColonie()
     * @see Expedition#Importation(String)
     *
     */
    public Colonie()
    {
    	colons=new ArrayList<Colon>();
    }

    /**
     * Constructeur de Colonie via une liste de Colon.
     *
     * @param colons
     * Liste de Colon.
     *
     */
    public Colonie(List<Colon> colons)
    {
        this.colons=colons;
    }

    /**
     *
     * @return
     *
     * @see Conquest.Expedition#algoFavoriteFirst(int, Ressource)
     * @see Conquest.Expedition#algoBestPerfSUR(int, int, Ressource)
     * @see Conquest.Expedition#save(String, int)
     *
     */
    public List<Colon> getColons(){
    	return this.colons;
    }

    /**
     * Ajouter un colon a la colonie.
     *
     * @param currentColon
     * Le colon à ajouter à la colonie.
     *
     * @throws Exception
     * Le colon n'existe pas.
     *
     * @see Conquest.Expedition#Importation(String)
     * @see Menu#afficherMenuCreationColonie()
     *
     */
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

    /**
     * Retire un Colon de la Colonie.
     *
     * @param colon
     * Le Colon a retirer.
     *
     * @throws Exception
     * Le Colon n'existe pas.
     */
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

    /**
     * Trouve un Colon de la colonie via son nom.
     *
     * @param nom
     * Nom du Colon.
     *
     * @return
     * Retourne l'Objet Colon qui correspond.
     *
     * @throws ColonInexistantException
     * Le colon n'existe pas.
     *
     * @see Conquest.Expedition#Importation(String)
     * @see Conquest.Menus.Menu#afficherMenuConfiguration(int)
     * @see Conquest.Menus.Menu#afficherMenuAffectation(int, int)
     *
     */
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

    /**
     * Retourne le nom d'un colon via son Objet.
     *
     * @param colonRecherche
     * Objet Colon.
     *
     * @return
     * Le nom du Colon.
     *
     * @throws ColonInexistantException
     * Le Colon n'existe pas.
     *
     */
    public String getColonName(Colon colonRecherche) throws ColonInexistantException
    {
        for (Colon colon : colons) {
            if (colon.equals(colonRecherche)) {
                return colon.getNom();
            }
        }
        throw new ColonInexistantException("Erreur : le colon " + colonRecherche.getNom() + " n'existe pas");
    }

    /**
     * Verifie si tout les colons ont une Ressource.
     *
     * @return
     * true si ils ont tous une Ressource. Si non, false.
     */
    public boolean toutesLesPreferencesAttribuees()
    {
        for (Colon colon : colons) {
            if (colon.getPreferences() == null || colon.getPreferences().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifie si un colon a une liste de preference complete.
     *
     * @param nombreDeRessources
     * le nombre de ressource que la colonie doit partager.
     *
     * @return
     * 0 si sucess.
     *
     * @throws PreferencesIncompletesException
     * Un colon n'as pas de liste de preference complete.
     */
    public int verifierPreferencesCompletes(int nombreDeRessources) throws PreferencesIncompletesException
    {
        for (Colon colon : colons) {
            LinkedHashSet<String> preferences = colon.getPreferences();
            if (preferences == null || preferences.size() != nombreDeRessources) {
                throw new PreferencesIncompletesException(
                        "Erreur : le colon " + colon.getNom() + " n'a pas une liste de préférences complète."
                );
            }
        }
        return 0;
    }

    /**
     * Retourne le coup d'une colonie.
     * 
     * @return
     * Le cout d'une colonie.
     * 
     * @throws Exception
     * Ressource non attribuée.
     * 
     * @see Conquest.Expedition#algoFavoriteFirst(int, Ressource) 
     * @see Conquest.Expedition#algoBestPerfSUR(int, int, Ressource) 
     * @see Conquest.Expedition#save(String, int)
     * 
     */
    public int cout() throws Exception
    {
        int cout = 0;
        Set<Set<Colon>> alreadyHated = new HashSet<>();
        int positionitemcolon1 = -1;
        int positionitemcolon2 = -1;

        for (Colon colon1 : colons)
        {
            if (Objects.equals(colon1.getRessource(), ""))
            {
                throw new CaseVideException("le colon n'a pas de ressources");
            }

            if(!colon1.getRelationsDetestables().isEmpty())
            {
                for(Colon colon2 : colon1.getRelationsDetestables())
                {
                    Set<Colon> pair = new TreeSet<>(Comparator.comparing(Colon::getNom));
                    pair.add(colon1);
                    pair.add(colon2);

                    for(int i = 0; i<colon1.getPreferences().size(); i++)
                    {
                        if(Objects.equals(colon1.getPreferenceAT(i), colon1.getRessource()))
                        {
                            positionitemcolon1 = i;
                        }
                    }
                    for(int i = 0; i<colon2.getPreferences().size(); i++)
                    {
                        if (Objects.equals(colon2.getPreferenceAT(i), colon2.getRessource()))
                        {
                            positionitemcolon2 = i;
                        }
                    }
                    if(positionitemcolon2>positionitemcolon1 && !alreadyHated.contains(pair))
                    {
                        alreadyHated.add(pair);
                        cout++;
                    }
                }

            }
        }

        return cout;
    }

    /**
     * Echange les ressources entre 2 colons.
     *
     * @param colon1
     * Le premier colon.
     * @param colon2
     * Le deuxieme colon
     * @throws EchangeAvecSoiMemeException
     * Les 2 colons sont les memes.
     * @throws ColonInexistantException
     * Un des deux colons n'est pas dans la colonie.
     *
     * @see Conquest.Expedition#algoBestPerfSUR(int, int, Ressource)
     * @see Conquest.Menus.Menu#afficherMenuAffectation(int, int)
     *
     */
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

		String ressource1 = colon1.getRessource();
		String ressource2 = colon2.getRessource();

		colon1.affectationRessource(ressource2);
		colon2.affectationRessource(ressource1);
	}

    /**
     * Retourne la chaine des affectations des la colonie.
     *
     * @return
     * La chaine des affectations des colons.
     *
     */
    public String afficherRessourcesDesColons()
    {
        StringBuilder Result = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for(Colon c:colons)
        {
            String actual = c.getRessource().isEmpty() ? "aucune" : c.getRessource();
            temp.append(c.getNom())
                    .append(" a ")
                    .append(actual)
                    .append(" ressource.");
            System.out.println(temp);
            Result.append(temp).append("\n");
            temp.setLength(0);
        }
        return Result.toString();
    }

    /**
     * Affiche les affectations de la colonie.
     *
     * @see Conquest.Menus.Menu#afficherMenuAffectation(int, int)
     */
    public void afficherAffectations()
    {
        System.out.println();
        for(Colon c:colons)
        {
            System.out.println(c.getNom()+":"+c.getRessource());
        }
        System.out.println();
    }


    /**
     * Affiche les membres de la colonie.
     *
     * @return
     * Retourne la liste des colons.
     */
    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (Colon colon : colons)
        {
            result.append(colon.toString());
            Colon colonJalouxDe = null;
            if (colon.getRelationsDetestables() != null) {
                for (Colon ennemi : colon.getRelationsDetestables())
                {

                    if (ennemi.getRessource() != null && colon.getPreferenceIndex(ennemi.getRessource()) < colon.getPreferenceIndex(colon.getRessource()))
                    {
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
