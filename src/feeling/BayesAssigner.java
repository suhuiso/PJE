package feeling;

import java.util.ArrayList;
import java.util.List;
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

	/**
	 * List of degrees of the n-grammes considered.
	 */
	protected List< Integer > degrees;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of BayesAssigner.
	 * 
	 * @param tweetPool
	 *            tweet pool used for the Bayesian classification
	 * @param simplified
	 *            determines if the Bayesian classification is simplified or not
	 * @param nGrammes
	 *            degrees of the n-grammes used for the assignement
	 */
	public BayesAssigner ( TweetPool tweetPool, Boolean simplified, List< Integer > degrees ) {
		this.tweetPool = tweetPool;
		this.simplified = simplified;
		this.degrees = degrees;
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

	protected List< NGramme > getNGrammesListFrom ( String msg ) {
		List< NGramme > res = new ArrayList< NGramme >();

		for ( int degree : this.degrees ) {
			res.addAll( NGramme.buildNGrammesFrom( msg, degree ) );
		}
		
		return res;
	}

	// Gives the number of n-gramme with this degree in the tweet pool
	private int nbOfNGrammeOfDegree ( int degree ) {
		Set< NGramme > set = new TreeSet< NGramme >();

		for ( Tweet tweet : this.tweetPool.values() ) {
			for ( NGramme nGramme : NGramme.buildNGrammesFrom( tweet.getMsg(), degree ) ) {
				set.add( nGramme );
			}
		}

		return set.size();
	}

	// Gives the number of n-grammes with this degree in tweets that have the feeling in the tweet pool
	private int nbOfNGrammesForFeeling ( Feeling feeling, int degree ) {
		Set< NGramme > set = new TreeSet< NGramme >();

		for ( Tweet tweet : this.tweetPool.values() ) {
			if ( tweet.getFeeling() == feeling ) {
				for ( NGramme nGramme : NGramme.buildNGrammesFrom( tweet.getMsg(), degree ) ) {
					set.add( nGramme );
				}
			}
		}

		return set.size();
	}

	// Gives the number of occurence of the n-gramme in tweets that have the feeling
	private int nbOccurenceOfNGrammeForTheFeeling ( NGramme ng, Feeling feeling ) {
		int res = 0;

		for ( Tweet tweet : this.tweetPool.values() ) {
			if ( tweet.getFeeling() == feeling ) {
				for ( NGramme nGramme : NGramme.buildNGrammesFrom( tweet.getMsg(), ng.getDegree() ) ) {
					if ( nGramme.equals( ng ) ) {
						res++;
					}
				}
			}
		}

		return res;
	}

	// Gives the probability of a n-gramme to occure in a tweet with the feeling
	protected double probaNGrammeForFeeling ( NGramme nGramme, Feeling feeling ) {
		int degree = nGramme.getDegree();

		return ( this.nbOccurenceOfNGrammeForTheFeeling( nGramme, feeling ) + 1 )
		        / ( this.nbOfNGrammesForFeeling( feeling, degree ) + this
		                .nbOfNGrammeOfDegree( degree ) );
	}

	// Gives the accepted words of the msg to do the classification
	protected String getAcceptedWords ( String msg ) {
		if ( this.simplified ) {
			StringBuffer bs = new StringBuffer();
			for ( String word : msg.split( " " ) ) {
				if ( word.length() > this.WORD_LENGTH_MIN ) {
					bs.append( word + " " );
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
