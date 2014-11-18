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
	public PresenceBayesAssigner ( TweetPool tweetPool, Boolean simplified ) {
		super( tweetPool, simplified );
	}

	// Gives the probability of a message of the tweet to have the feeling
	@Override
	protected double probaTweetHasFeeling ( Feeling feeling, String msg ) {
		double res = 1;

		for ( String word : msg.split( " " ) ) {
			res *= this.probaWordForFeeling( word, feeling );
		}

		return res * this.probaFeeling( feeling );
	}

}
