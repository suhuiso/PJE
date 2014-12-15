package feeling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utils.Tweet;
import utils.TweetPool;

/**
 * Abstract class representing objects that classify the message of a tweet with the Bayesian
 * classification.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public abstract class BayesClassifier extends CrossValidable {

	////////////
	// FIELDS //
	////////////

	/**
	 * Length of the words to accept when the classification with simplified method.
	 */
	private final int WORD_LENGTH_MIN = 3;

	/**
	 * Tells if the Bayesian classification is only applied on words which have
	 * more than WORD_LENGTH_MIN letters.
	 */
	protected Boolean simplified;

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
	 *            degrees of the n-grammes used for the classification
	 */
	public BayesClassifier ( TweetPool tweetPool, Boolean simplified, List< Integer > degrees ) {
		super( tweetPool );
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
		double res = 0;
		double cpt = 0;

		for ( Tweet tweet : this.tweetPool.tweets() ) {
			if ( tweet.getFeeling() == feeling ) {
				res++;
			}
			cpt++;
		}

		return res / cpt;
	}

	/**
	 * Gives a list of all the n-grammes to consider for the classification.
	 * 
	 * @param msg
	 *            message in which extract the n-grammes
	 * @return a list of all the n-grammes to consider for the classification
	 */
	protected List< NGramme > getNGrammesListFrom ( String msg ) {
		List< NGramme > res = new ArrayList< NGramme >();

		for ( int degree : this.degrees ) {
			res.addAll( NGramme.buildNGrammesFrom( msg, degree ) );
		}

		return res;
	}

	// Gives the number of n-gramme with this degree in the tweet pool
	private int nbOfNGrammeOfDegree ( int degree ) {
		Set< NGramme > set = new HashSet< NGramme >();

		for ( Tweet tweet : this.tweetPool.tweets() ) {
			for ( NGramme nGramme : NGramme.buildNGrammesFrom( tweet.getMsg(), degree ) ) {
				set.add( nGramme );
			}
		}

		return set.size();
	}

	// Gives the number of n-grammes with this degree in tweets that have the feeling in the tweet pool
	private int nbOfNGrammesForFeeling ( Feeling feeling, int degree ) {
		Set< NGramme > set = new HashSet< NGramme >();

		for ( Tweet tweet : this.tweetPool.tweets() ) {
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

		for ( Tweet tweet : this.tweetPool.tweets() ) {
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

	/**
	 * Gives the probability of a n-gramme to occure in a tweet with a feeling.
	 * 
	 * @param nGramme
	 *            n-gramme to know the probability that it occures with feeling
	 * @param feeling
	 *            feeling with which the n-gramme has probability to occure
	 * @return probability of a n-gramme to occure in a tweet with feeling
	 */
	protected double probaNGrammeForFeeling ( NGramme nGramme, Feeling feeling ) {
		int degree = nGramme.getDegree();

		return ( ( double ) ( this.nbOccurenceOfNGrammeForTheFeeling( nGramme, feeling ) + 1 ) )
		        / ( ( double ) ( this.nbOfNGrammesForFeeling( feeling, degree ) + this
		                .nbOfNGrammeOfDegree( degree ) ) );
	}

	/**
	 * Give the accepted words of the message to classify.
	 * 
	 * @param msg
	 *            message to classify
	 * @return message without the words that are not accepted
	 */
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

	/**
	 * Gives the probability of a message of the tweet to have the feeling.
	 * 
	 * @param feeling
	 *            feeling to know the probability
	 * @param msg
	 *            message to classify
	 * @return probability of msg to have feeling
	 */
	protected abstract double probaTweetHasFeeling ( Feeling feeling, String msg );

	@Override
	public Feeling classifies ( String msg ) {
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

	public String toString () {
		return "Bayes";
	}
}
