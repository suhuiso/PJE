package statistics;

import java.util.ArrayList;
import java.util.Collection;

import utils.Tweet;
import utils.TweetPool;
import feeling.CrossValidable;
import feeling.Feeling;

/**
 * Class representing objects that make statistics on classifiers using the cross validation method.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class CrossValidation extends ClassifierStatistics {

	////////////
	// FIELDS //
	////////////

	/**
	 * Tweet pool used for the cross validation.
	 */
	private TweetPool tweetPool;

	/**
	 * Classifier to cross validate.
	 */
	private CrossValidable classifier;

	/**
	 * Number of folds used to do the cross validation.
	 */
	private int nbFolds;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor.
	 * 
	 * @param tweetPool
	 *            tweet pool to use for the classifier cross validation
	 * @param classifier
	 *            classifier to cross validate
	 * @param nbFolds
	 *            number of folds used to do the cross validation
	 */
	public CrossValidation ( TweetPool tweetPool, CrossValidable classifier, int nbFolds ) {
		this.tweetPool = tweetPool;
		this.classifier = classifier;
		this.nbFolds = nbFolds;
	}

	// Generates the folds
	private TweetPool[] generatesFolds () throws IllegalArgumentException {
		Collection< Tweet > tweets = this.tweetPool.tweets();

		if ( tweets.size() <= this.nbFolds ) {
			throw new IllegalArgumentException(
			        "Not enough tweet in the learning base to fill all the folds." );
		} else {
			TweetPool[] res = new TweetPool[ this.nbFolds ];
			ArrayList< Tweet > positives = new ArrayList< Tweet >();
			ArrayList< Tweet > negatives = new ArrayList< Tweet >();
			ArrayList< Tweet > neutrals = new ArrayList< Tweet >();

			// Initializes res
			for ( int i = 0; i < res.length; i++ ) {
				res[ i ] = new TweetPool();
			}

			// Fills the feelings lists
			for ( Tweet tweet : tweets ) {
				Feeling feeling = tweet.getFeeling();

				if ( feeling == Feeling.POSITIVE ) {
					positives.add( tweet );
				} else if ( feeling == Feeling.NEUTRAL ) {
					negatives.add( tweet );
				} else if ( feeling == Feeling.NEGATIVE ) {
					neutrals.add( tweet );
				}
			}

			// Builds a List< List< Tweet> > with positives, negatives and neutrals tweets
			ArrayList< ArrayList< Tweet >> lists = new ArrayList< ArrayList< Tweet > >();
			lists.add( positives );
			lists.add( negatives );
			lists.add( neutrals );

			// Fills the folds equitably
			for ( ArrayList< Tweet > list : lists ) {
				int i = 0;
				for ( Tweet tweet : list ) {
					res[ i ].add( tweet );
					i = ( i + 1 ) % nbFolds;
				}
			}

			return res;
		}
	}

	// Evaluates the classifier with the fold number nbFolds as learning base
	private double evaluatesWithFoldNb ( int nbFold, TweetPool[] folds )
	        throws IllegalArgumentException {
		if ( nbFold >= folds.length ) {
			throw new IllegalArgumentException( "Unknown fold number." );
		} else {
			int res = 0;
			TweetPool learningBase = new TweetPool();
			TweetPool toClassify = folds[ nbFold ];

			// Fills the learning base
			for ( int i = 0; i < folds.length; i++ ) {
				if ( i != nbFold ) {
					for ( Tweet tweet : folds[ i ].tweets() ) {
						learningBase.add( tweet );
					}
				}
			}

			// Set the classifier learning base
			this.classifier.setTweetPool( learningBase );

			// Calculates the error rate
			for ( Tweet tweet : toClassify.tweets() ) {
				Feeling feeling = this.classifier.classifies( tweet.getMsg() );
				if ( feeling != tweet.getFeeling() ) {
					res++;
				}
			}

			// TODO : Retourner res ou ( res / toClassify.tweets().size() ) ?
			return ( ( double ) res / ( double ) toClassify.tweets().size() ) * 100;
		}
	}

	@Override
	public double evaluates () {
		double res = 0;
		TweetPool[] folds = this.generatesFolds();

		for ( int fold = 0; fold < this.nbFolds; fold++ ) {
			res += this.evaluatesWithFoldNb( fold, folds );
		}

		// Put the intiale tweet pool in the classifier
		this.classifier.setTweetPool( this.tweetPool );

		return res / this.nbFolds;
	}

}
