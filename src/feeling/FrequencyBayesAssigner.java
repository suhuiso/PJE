package feeling;

import java.util.List;

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
	public FrequencyBayesAssigner ( TweetPool tweetPool, Boolean simplified, List< Integer > degrees ) {
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
		List< NGramme > nGrammes = this.getNGrammesListFrom ( msg );

		for ( NGramme nGramme : nGrammes ) {
			int nb = this.nbOccurenceOfNGrammeInMsg( nGramme, msg );
			res *= Math.pow( this.probaNGrammeForFeeling( nGramme, feeling ), nb );
		}

		return res;
	}

}
