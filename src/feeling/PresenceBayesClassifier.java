package feeling;

import java.util.List;

import utils.TweetPool;

/**
 * Class representing objects that classify the message of a tweet with the Bayesian
 * classification using presence method.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class PresenceBayesClassifier extends BayesClassifier {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of PresenceBayesAssigner.
	 * 
	 * @param tweetPool
	 *            tweet pool used for the Bayesian classification
	 */
	public PresenceBayesClassifier ( TweetPool tweetPool, Boolean simplified,
	        List< Integer > degrees ) {
		super( tweetPool, simplified, degrees );
	}

	// Gives the probability of a message of the tweet to have the feeling
	@Override
	protected double probaTweetHasFeeling ( Feeling feeling, String msg ) {
		double res = 1;
		List< NGramme > nGrammes = this.getNGrammesListFrom( msg );

		for ( NGramme nGramme : nGrammes ) {
			res *= this.probaNGrammeForFeeling( nGramme, feeling );
		}

		return res * this.probaFeeling( feeling );
	}

	public String toString () {
		return "Bayes par présence";
	}
}
