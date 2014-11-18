package feeling;

import utils.TweetPool;

/**
 * Class representing objects that assign feeling to a message of a tweet with the Bayesian
 * classification using presence method.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class PresenceBayesAssigner extends BayesAssigner {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of PresenceBayesAssigner.
	 * 
	 * @param tweetPool
	 *            tweet pool used for the Bayesian classification
	 */
	public PresenceBayesAssigner ( TweetPool tweetPool ) {
		super( tweetPool );
	}

	@Override
	public Feeling assigns ( String msg ) {
		double pNegative = this.probaTweetHasFeeling( Feeling.NEGATIVE, msg );
		double pPositive = this.probaTweetHasFeeling( Feeling.POSITIVE, msg );
		double pNeutral = this.probaTweetHasFeeling( Feeling.NEUTRAL, msg );

		if ( ( pNeutral >= pPositive ) && ( pNeutral >= pNegative ) ) {
			return Feeling.NEUTRAL;
		} else if ( pPositive > pNegative ) {
			return Feeling.POSITIVE;
		} else {
			return Feeling.NEGATIVE;
		}
	}

}
