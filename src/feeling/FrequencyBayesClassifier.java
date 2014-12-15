package feeling;

import java.util.List;

import utils.TweetPool;

/**
 * Class representing objects that classify the message of a tweet with the Bayesian
 * classification using frequency method.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class FrequencyBayesClassifier extends BayesClassifier {

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of PresenceBayesAssigner.
	 * 
	 * @param tweetPool
	 *            tweet pool used for the Bayesian classification
	 */
	public FrequencyBayesClassifier ( TweetPool tweetPool, Boolean simplified,
	        List< Integer > degrees ) {
		super( tweetPool, simplified, degrees );
	}

	// Gives the number of occurence of the n-gramme in the message
	private int nbOccurenceOfNGrammeInMsg ( NGramme ng, String msg ) {
		int res = 0;

		for ( NGramme nGramme : NGramme.buildNGrammesFrom( msg, ng.getDegree() ) ) {
			if ( nGramme.equals( ng ) ) {
				res++;
			}
		}

		return res;
	}

	// Gives the probability of a message of the tweet to have the feeling
	@Override
	protected double probaTweetHasFeeling ( Feeling feeling, String msg ) {
		double res = 1;
		List< NGramme > nGrammes = this.getNGrammesListFrom( msg );

		for ( NGramme nGramme : nGrammes ) {
			int nb = this.nbOccurenceOfNGrammeInMsg( nGramme, msg );
			res *= Math.pow( this.probaNGrammeForFeeling( nGramme, feeling ), nb );
		}

		return res;
	}

	public String toString () {
		return "Bayes par fréquence";
	}
}
