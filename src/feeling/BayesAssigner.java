package feeling;

import java.util.Set;
import java.util.TreeSet;

import utils.Tweet;
import utils.TweetPool;

/**
 * Class representing objects that assign feeling to a message of a tweet with the Bayesian
 * classification
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class BayesAssigner extends FeelingAssigner {

	////////////
	// FIELDS //
	////////////

	private TweetPool tweetPool;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of BayesAssigner.
	 * 
	 * @param tweetPool
	 *            tweet pool used for the Bayesian classification
	 */
	public BayesAssigner ( TweetPool tweetPool ) {
		this.tweetPool = tweetPool;
	}

	/**
	 * Gives the probability that a feeling appears in the tweet pool.
	 * 
	 * @param feeling
	 *            feeling which to know what is the probability that he appears in the tweet pool
	 * @return the probability that feeling appears in the tweet pool
	 */
	public double probaFeeling ( Feeling feeling ) {
		int res = 0;

		for ( Tweet tweet : this.tweetPool.values() ) {
			if ( tweet.getFeeling() == feeling ) {
				res++;
			}
		}

		return res / this.tweetPool.values().size();
	}

	// Gives the number of words in the tweet pool
	private int nbOfWords () {
		Set< String > set = new TreeSet< String >();

		for ( Tweet tweet : this.tweetPool.values() ) {
			for ( String word : tweet.getMsg().split( " " ) ) {
				set.add( word );
			}
		}

		return set.size();
	}

	// Gives the number of words in tweets that have the feeling in the tweet pool
	private int nbOfWordsForFeeling ( Feeling feeling ) {
		Set< String > set = new TreeSet< String >();

		for ( Tweet tweet : this.tweetPool.values() ) {
			if ( tweet.getFeeling() == feeling ) {
				for ( String word : tweet.getMsg().split( " " ) ) {
					set.add( word );
				}
			}
		}

		return set.size();
	}

	// Gives the number of occurence of the word in tweets that have the feeling
	private int nbOccurenceOfWordForTheFeeling ( String w, Feeling feeling ) {
		int res = 0;

		for ( Tweet tweet : this.tweetPool.values() ) {
			if ( tweet.getFeeling() == feeling ) {
				for ( String word : tweet.getMsg().split( " " ) ) {
					if ( word.equals( w ) ) {
						res++;
					}
				}
			}
		}

		return res;
	}
	
	// Gives the probability of a word to occure in a tweet with the feeling
	private double probaWordForFeeling ( String w, Feeling feeling ) {
		return ( this.nbOccurenceOfWordForTheFeeling( w, feeling ) + 1 )
		        / ( this.nbOfWordsForFeeling( feeling ) + this.nbOfWords() );
	}

	// Gives the probability of a message of the tweet to have the feeling
	private double probaTweetHasFeeling ( Feeling feeling, String msg ) {
		double res = 1;

		for ( String word : msg.split( " " ) ) {
			res *= this.probaWordForFeeling( word, feeling );
		}

		return res * this.probaFeeling( feeling );
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
