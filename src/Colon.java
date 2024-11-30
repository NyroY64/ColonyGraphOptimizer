import Exceptions.ColonInexistantException;
import Exceptions.RelationAvecSoiMemeException;
import Exceptions.RelationDejaExistanteException;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class Colon
{
	private String nom;
	private LinkedHashSet<Integer> preferences;
	private Integer ressourceAttribuee;
	private LinkedHashSet<Colon> relationsDetestables;

	public Colon(String nom)
	{
		this.nom = nom;
		preferences = new LinkedHashSet<>();
		ressourceAttribuee = 0;
		relationsDetestables = new LinkedHashSet<>();
	}

	public Colon(String nom, LinkedHashSet<Integer> preferences)
	{
		this.nom = nom;
		this.preferences = preferences;
		ressourceAttribuee = 0;
		relationsDetestables = new LinkedHashSet<>();
	}

	public String getNom()
	{
		return this.nom;
	}

	public LinkedHashSet<Integer> getPreferences()
	{
		return preferences;
	}

	public int getPreferenceAT(int n)
	{
		if (n < 0 || n >= preferences.size())
		{
			throw new IndexOutOfBoundsException("Invalid index: " + n);
		}

		var iterator = preferences.iterator();
		int currentIndex = 0;

		while (iterator.hasNext())
		{
			int currentElement = iterator.next();
			if (currentIndex == n)
			{
				return currentElement;
			}
			currentIndex++;
		}

		// This line should never be reached due to the index check above.
		throw new IllegalStateException("Element not found");
	}

	public void addPreference(int pref)
	{
		preferences.add(pref);
	}

	public void addPreferences(LinkedHashSet<Integer> prefs)
	{
		preferences.addAll(prefs);
	}

	public void setPreferences(LinkedHashSet<Integer> prefs)
	{
		preferences = prefs;
	}

	public Integer getRessource()
	{
		return ressourceAttribuee;
	}

	public void affectationRessource(Integer ressourceAttribuee)
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
		if (ennemis == null || ennemis.isEmpty())
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
