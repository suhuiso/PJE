package feeling;

import java.util.Set;
import java.util.TreeSet;

import utils.Tweet;
import utils.TweetPool;

/**
 * Abstract class representing objects that assign feeling to a message of a tweet with the Bayesian
 * classification.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public abstract class BayesAssigner extends FeelingAssigner {

	////////////
	// FIELDS //
	////////////

	/**
	 * Tweet pool on which apply the Bayesian classification.
	 */
	protected TweetPool tweetPool;

	/**
	 * Tells if the Bayesian classification is only applied on words which have
	 * more than 3 letters.
	 */
	protected Boolean simplified;

	/**
	 * Length of the words to accept when the classification with simplified method.
	 */
	private final int WORD_LENGTH_MIN = 3;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of BayesAssigner.
	 * 
	 * @param tweetPool
	 *            tweet pool used for the Bayesian classification
	 */
	public BayesAssigner ( TweetPool tweetPool, Boolean simplified ) {
		this.tweetPool = tweetPool;
		this.simplified = simplified;
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
	protected double probaWordForFeeling ( String w, Feeling feeling ) {
		return ( this.nbOccurenceOfWordForTheFeeling( w, feeling ) + 1 )
		        / ( this.nbOfWordsForFeeling( feeling ) + this.nbOfWords() );
	}

	// Gives the accepted words of the msg to do the classification
	protected String getAcceptedWords ( String msg ) {
		if ( this.simplified ) {
			StringBuffer bs = new StringBuffer();
			for ( String word : msg.split( " " ) ) {
				if ( word.length() > this.WORD_LENGTH_MIN ) {
					bs.append( word );
				}
			}
			return bs.toString();
		} else {
			return msg;
		}
	}

	// Gives the probability of a message of the tweet to have the feeling
	protected abstract double probaTweetHasFeeling ( Feeling feeling, String msg );

	@Override
	public Feeling assigns ( String msg ) {
		double pNegative =
		        this.probaTweetHasFeeling( Feeling.NEGATIVE, this.getAcceptedWords( msg ) );
		double pPositive =
		        this.probaTweetHasFeeling( Feeling.POSITIVE, this.getAcceptedWords( msg ) );
		double pNeutral = this.probaTweetHasFeeling( Feeling.NEUTRAL, this.getAcceptedWords( msg ) );

		if ( ( pNeutral >= pPositive ) && ( pNeutral >= pNegative ) ) {
			return Feeling.NEUTRAL;
		} else if ( pPositive > pNegative ) {
			return Feeling.POSITIVE;
		} else {
			return Feeling.NEGATIVE;
		}
	}

}
