package feeling;

import utils.TweetPool;

public class FrequencyBayesAssigner extends BayesAssigner {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of PresenceBayesAssigner.
	 * 
	 * @param tweetPool
	 *            tweet pool used for the Bayesian classification
	 */
	public FrequencyBayesAssigner ( TweetPool tweetPool, Boolean simplified ) {
		super( tweetPool, simplified );
	}
	
	// Gives the number of occurence of the word in the message
	private int nbOccurenceOfWordInMsg ( String w, String msg ) {
		int res = 0;

		for ( String word : msg.split( " " ) ) {
			if ( word.equals( w ) ) {
				res++;
			}
		}

		return res;
	}

	// Gives the probability of a message of the tweet to have the feeling
	@Override
	protected double probaTweetHasFeeling ( Feeling feeling, String msg ) {
		double res = 1;

		for ( String word : msg.split( " " ) ) {
			int nb = this.nbOccurenceOfWordInMsg( word, msg );
			res *= Math.pow( this.probaWordForFeeling( word, feeling ), nb );
		}

		return res;
	}

}
