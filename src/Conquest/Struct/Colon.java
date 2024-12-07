package Conquest.Struct;

import Conquest.Exception.ColonInexistantException;
import Conquest.Exception.RelationAvecSoiMemeException;
import Conquest.Exception.RelationDejaExistanteException;
import Conquest.Menus.Menu;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Classe Colon
 *
 * Elle permet d'avoir un Colon.
 * 
 * 
 * @see Colonie
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
 * @see Conquest.Expedition#algoFavoriteFirst(int, Ressource)
 * @see Conquest.Expedition#Importation(String)
 * @see Conquest.Expedition#save(String, int)
 * @see Menu#afficherMenuCreationColonie()
 * @see Conquest.Menus.Menu#afficherMenuConfiguration(int)
 * @see Conquest.Menus.Menu#afficherMenuAffectation(int, int)
 *
 *
 * @author Devasenaradjounayagar Damien
 *
 * @version 1.0
 *
 */
public class Colon
{
	/**
	 * Nom du Colon.
	 *
	 * @see Colon#Colon(String)
	 * @see Colon#Colon(String, LinkedHashSet)
	 * @see Colon#getNom()
	 * @see Colon#toString()
	 *
	 */
	private final String nom;
	/**
	 * Liste ordonnée des preferences du colon
	 *
	 * @see Colon#Colon(String)
	 * @see Colon#Colon(String, LinkedHashSet)
	 * @see Colon#getPreferences()
	 * @see Colon#getPreferenceIndex(String)
	 * @see	Colon#getPreferenceAT(int)
	 * @see	Colon#addPreferences(LinkedHashSet)
	 * @see	Colon#setPreferences(LinkedHashSet)
	 * @see	Colon#toString()
	 *
	 */
	private LinkedHashSet<String> preferences;
	/**
	 * La Ressource que le colon a.
	 * Initialisé comme liste vide.
	 *
	 * @see Colon#Colon(String)
	 * @see Colon#Colon(String, LinkedHashSet)
	 * @see Colon#getRessource()
	 * @see Colon#affectationRessource(String)
	 * @see Colon#toString()
	 *
	 */
	private String ressourceAttribuee;
	/**
	 * Liste des mauvaises relations que le colon a avec les autres.
	 *
	 * @see Colon#Colon(String)
	 * @see Colon#Colon(String, LinkedHashSet)
	 * @see Colon#addRelation(Colon)
	 * @see Colon#removeRelation(Colon)
	 * @see Colon#getRelationsDetestables()
	 * @see Colon#ajouterRelations(List)
	 * @see Colon#toString()
	 *
	 */
	private final LinkedHashSet<Colon> relationsDetestables;

	/**
	 * Constructeur de Colon via le nom.
	 *
	 * @param nom
	 * Nom du Colon.
	 *
	 * @see Conquest.Expedition#Importation(String)
	 * @see Conquest.Expedition#Importation(String)
	 * @see Menu#afficherMenuCreationColonie()
	 *
	 */
	public Colon(String nom)
	{
		this.nom = nom;
		preferences = new LinkedHashSet<>();
		ressourceAttribuee = "";
		relationsDetestables = new LinkedHashSet<>();
	}

	/**
	 *
	 * Constructeur de colon via son nom et sa liste de preference.
	 *
	 * @param nom
	 * Nom du colon
	 *
	 * @param preferences
	 * Preferences du colon.
	 *
	 *
	 */
	public Colon(String nom, LinkedHashSet<String> preferences)
	{
		this.nom = nom;
		this.preferences = preferences;
		ressourceAttribuee = "";
		relationsDetestables = new LinkedHashSet<>();
	}

	/**
	 * Retourne le nom du Colon.
	 *
	 * @return
	 * Retourne le nom du colon
	 *
	 * @see Colon#ajouterRelations(List)
	 * @see Colon#toString() 
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
	 * @see Conquest.Expedition#save(String, int)
	 * @see Menu#afficherMenuConfiguration(int)
	 *
	 */
	public String getNom()
	{
		return this.nom;
	}

	/**
	 * Retourne les preferences du colon.
	 *
	 * @return
	 * Retourne les preferences du colon.
	 *
	 * @see Colonie#toutesLesPreferencesAttribuees()
	 * @see Colonie#verifierPreferencesCompletes(int)
	 * @see Colonie#cout()
	 * @see Menu#afficherMenuConfiguration(int)
	 *
	 */
	public LinkedHashSet<String> getPreferences()
	{
		return preferences;
	}

	/**
	 * Trouve l'index d'une preference.
	 *
	 * @param preference
	 * Le nom de la preference a chercher.
	 *
	 * @return
	 * L'index de la reference.
	 *
	 * @see Colonie#toString()
	 *
	 */
	public int getPreferenceIndex(String preference)
	{
		int index = 0;
		for (String pref : preferences)
		{
			if (pref.equals(preference))
			{
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Trouve la preference à un index donné.
	 *
	 * @param n
	 * Index de la preference.
	 *
	 * @return
	 * Le nom de la preference.
	 *
	 * @see Colonie#cout()
	 * @see Conquest.Expedition#algoFavoriteFirst(int, Ressource)
	 *
	 *
	 */
	public String getPreferenceAT(int n)
	{
		if (n < 0 || n >= preferences.size())
		{
			throw new IndexOutOfBoundsException("Invalid index: " + n);
		}

		var iterator = preferences.iterator();
		int currentIndex = 0;

		while (iterator.hasNext())
		{
			String currentElement = iterator.next();
			if (currentIndex == n)
			{
				return currentElement;
			}
			currentIndex++;
		}

		// This line should never be reached due to the index check above.
		throw new IllegalStateException("Element not found");
	}

	/**
	 * Ajoute une preference au colon.
	 *
	 * @param pref
	 * Le nom de la preference a ajouter.
	 *
	 * @see Conquest.Expedition#Importation(String)
	 * @see Menu#afficherMenuConfiguration(int)
	 *
	 */
	public void addPreference(String pref)
	{
		preferences.add(pref);
	}

	/**
	 * Ajoute une liste de preference.
	 *
	 * @param prefs
	 * La liste des preferences.
	 *
	 */
	public void addPreferences(LinkedHashSet<String> prefs)
	{
		preferences.addAll(prefs);
	}

	/**
	 * Affecte une liste de preference.
	 *
	 * @param prefs
	 * La liste des preferences.
	 *
	 */
	public void setPreferences(LinkedHashSet<String> prefs)
	{
		preferences = prefs;
	}

	/**
	 * Retourne les ressources du colon.
	 *
	 * @return
	 * Les ressources du colon.
	 */
	public String getRessource()
	{
		return ressourceAttribuee;
	}

	/**
	 *
	 * Affecte une ressource a un colon
	 *
	 * @param ressourceAttribuee
	 * Resspurce a attribuer.
	 *
	 * @see Colonie#echangerRessources(Colon, Colon)
	 * @see Conquest.Expedition#algoFavoriteFirst(int, Ressource)
	 *
	 */
	public void affectationRessource(String ressourceAttribuee)
	{
		this.ressourceAttribuee = ressourceAttribuee;
	}

	/**
	 * Ajoute une relation au colon.
	 *
	 * @param jelaimepas
	 * Le colon a ajouter.
	 *
	 * @see Colonie#ajouterColon(Colon)
	 * @see Conquest.Expedition#Importation(String)
	 */
	public void addRelation(Colon jelaimepas)
	{
		relationsDetestables.add(jelaimepas);
		jelaimepas.relationsDetestables.add(this);
	}

	/**
	 * Retire une relation au colon.
	 *
	 * @param jelaimepas
	 * Le colon a retirer.
	 * @throws Exception
	 * Relation non existante.
	 *
	 * @see Colonie#retireColon(Colon)
	 *
	 */
	public void removeRelation(Colon jelaimepas) throws Exception
	{
		if (!relationsDetestables.contains(jelaimepas))
		{
			throw new Exception();
		}
		relationsDetestables.remove(jelaimepas);
		jelaimepas.relationsDetestables.remove(this);
	}

	/**
	 * Retourne les relations de colon.
	 *
	 * @return
	 * Retourne les relations de colon,
	 *
	 * @see Colon#ajouterRelations(List)
	 * @see Colonie#ajouterColon(Colon)
	 * @see Colonie#cout()
	 * @see Colonie#toString()
	 *
	 */
	public LinkedHashSet<Colon> getRelationsDetestables()
	{
		return relationsDetestables;
	}

	/**
	 * Ajoute une liste de relations au colon. (Et met a jour les autres relations.)
	 *
	 * @param relationsList
	 * Liste de relations.
	 * @throws Exception
	 * Action Impossible.
	 *
	 * @see Menu#afficherMenuConfiguration(int)
	 *
	 */
	public void ajouterRelations(List<Colon> relationsList) throws Exception
	{

		if (relationsList.contains(this))
		{
			throw new ColonInexistantException("Erreur: Le colon n'existe pas.");
		}
		for (Colon colon2 : relationsList)
		{
			//Add Colon
			if (this == colon2)
			{
				throw new RelationAvecSoiMemeException("Erreur : un colon ne peut pas avoir une relation avec lui-même (" + this.getNom() + ").");
			}
			if (this.getRelationsDetestables().contains(colon2))
			{
				throw new RelationDejaExistanteException("Erreur : la relation entre " + this.getNom() + " et " + this.getNom() + " existe déjà.");
			}
			relationsDetestables.add(colon2);
			colon2.relationsDetestables.add((this));

		}
	}

	/**
	 * Retourne les informations du colon.
	 *
	 * @return
	 * La chaine descriptive du colon.
	 *
	 */
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();

		result.append("Colon ").append(nom).append(" (Ressource allouée : ");
		if (preferences == null)
		{
			result.append("pas encore allouée");
		} else
		{
			if(Objects.equals(ressourceAttribuee, ""))
				result.append("aucune");
			else
				result.append(ressourceAttribuee);
		}
		result.append(")");
		result.append(", Préférences : ");
		if (preferences == null || preferences.isEmpty())
		{
			result.append("aucune préférence");
		} else
		{
			result.append(preferences);
		}
		Set<Colon> ennemis = relationsDetestables;
		result.append(", Ennemis : ");
		if (ennemis.isEmpty())
		{
			result.append("aucun");
		} else
		{
			result.append("{");
			int i = 0;
			for (Colon ennemi : ennemis)
			{
				result.append(ennemi.getNom());
				if (i < ennemis.size() - 1)
				{
					result.append(", ");
				}
				i++;
			}
			result.append("}");
		}

		return result.toString();
	}
}
