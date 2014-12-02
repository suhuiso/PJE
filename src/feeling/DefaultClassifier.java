package feeling;

/**
 * Singleton object classify the message of a tweet as UNPOLARIZED.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class DefaultClassifier extends Classifier {

	////////////
	// FIELDS //
	////////////

	/**
	 * Only instance of DefaultAssigner class.
	 */
	private static final DefaultClassifier INSTANCE = new DefaultClassifier();

	/////////////
	// METHODS //
	/////////////

	// Unaccessible constructor of DefaultAssigner
	private DefaultClassifier () {
	}

	/**
	 * Gives the only instance of DefaultAssigner class.
	 * 
	 * @return only instance of DefaultAssigner class
	 */
	public static DefaultClassifier getInstance () {
		return INSTANCE;
	}

	@Override
	public Feeling classifies ( String msg ) {
		return Feeling.UNPOLARIZED;
	}

}
