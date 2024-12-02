package Conquest.Struct;

import Conquest.Exception.ColonInexistantException;
import Conquest.Exception.RelationAvecSoiMemeException;
import Conquest.Exception.RelationDejaExistanteException;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class Colon
{
	private String nom;
	private LinkedHashSet<String> preferences;
	private String ressourceAttribuee;
	private final LinkedHashSet<Colon> relationsDetestables;

	public Colon(String nom)
	{
		this.nom = nom;
		preferences = new LinkedHashSet<>();
		ressourceAttribuee = "";
		relationsDetestables = new LinkedHashSet<>();
	}

	public Colon(String nom, LinkedHashSet<String> preferences)
	{
		this.nom = nom;
		this.preferences = preferences;
		ressourceAttribuee = "";
		relationsDetestables = new LinkedHashSet<>();
	}

	public String getNom()
	{
		return this.nom;
	}

	public LinkedHashSet<String> getPreferences()
	{
		return preferences;
	}

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

	public void addPreference(String pref)
	{
		preferences.add(pref);
	}

	public void addPreferences(LinkedHashSet<String> prefs)
	{
		preferences.addAll(prefs);
	}

	public void setPreferences(LinkedHashSet<String> prefs)
	{
		preferences = prefs;
	}

	public String getRessource()
	{
		return ressourceAttribuee;
	}

	public void affectationRessource(String ressourceAttribuee)
	{
		this.ressourceAttribuee = ressourceAttribuee;
	}

	public void addRelation(Colon jelaimepas)
	{
		relationsDetestables.add(jelaimepas);
		jelaimepas.relationsDetestables.add(this);
	}

	public void removeRelation(Colon jelaimepas) throws Exception
	{
		if (!relationsDetestables.contains(jelaimepas))
		{
			throw new Exception();
		}
		relationsDetestables.remove(jelaimepas);
		jelaimepas.relationsDetestables.remove(this);
	}

	public LinkedHashSet<Colon> getRelationsDetestables()
	{
		return relationsDetestables;
	}

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
