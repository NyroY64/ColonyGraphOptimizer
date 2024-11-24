import java.util.LinkedHashSet;


public class Colon
{
	private String nom;
	private LinkedHashSet<Integer> preferences;
	private Integer ressourceAttribuee;

	public Colon(String nom)
	{
		this.nom=nom;
		preferences = new LinkedHashSet<>();
		ressourceAttribuee=0;
	}

	public Colon(String nom, LinkedHashSet<Integer> preferences)
	{
		this.nom=nom;
		this.preferences = preferences;
		ressourceAttribuee=0;
	}

	public String getNom() {return this.nom;}
	public LinkedHashSet<Integer> getPreferences() {
		return preferences;
	}
	public int getPreferenceAT(int n)
	{
		if (n < 0 || n >= preferences.size()) {
			throw new IndexOutOfBoundsException("Invalid index: " + n);
		}

		var iterator = preferences.iterator();
		int currentIndex = 0;

		while (iterator.hasNext()) {
			int currentElement = iterator.next();
			if (currentIndex == n) {
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
	public Integer getRessource() {
		return ressourceAttribuee;
	}
	public void affectationRessource(Integer ressourceAttribuee) {this.ressourceAttribuee = ressourceAttribuee;}







	



}
