package feeling;

/**
 * Singleton object assigning UNPOLARIZED feeling to a message of a tweet.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class DefaultAssigner extends FeelingAssigner {

	////////////
	// FIELDS //
	////////////
	
	/**
	 * Only instance of DefaultAssigner class.
	 */
	private static final DefaultAssigner INSTANCE = new DefaultAssigner();
	
	/////////////
	// METHODS //
	/////////////
	
	// Unaccessible constructor of DefaultAssigner
	private DefaultAssigner () {
	}
	
	/**
	 * Gives the only instance of DefaultAssigner class.
	 * 
	 * @return only instance of DefaultAssigner class
	 */
	public static DefaultAssigner getInstance () {
		return INSTANCE;
	}

	@Override
	public Feeling assigns ( String msg ) {
		return Feeling.UNPOLARIZED;
	}

}
