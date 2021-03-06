package feeling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.Tweet;
import utils.TweetPool;

/**
 * Class reprsenting objects that classify the message of a tweet using the KNN method.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class KNNClassifier extends CrossValidable {

	////////////
	// FIELDS //
	////////////

	/**
	 * Number of neighbors considered.
	 */
	private int nbNeighbors;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of KNNAssigner.
	 * 
	 * @param tweetPool
	 *            tweet pool used for basic learning
	 * @param nbNeighbors
	 *            number of neighbors considered
	 */
	public KNNClassifier ( TweetPool tweetPool, int nbNeighbors ) {
		super( tweetPool );
		this.nbNeighbors = nbNeighbors;
	}

	/**
	 * Gives the distance between two tweets.
	 * 
	 * @param tweet1
	 *            firs tweet
	 * @param tweet2
	 *            second tweet
	 * @return distance between tweet1 and tweet2
	 */
	public int distance ( String tweet1, String tweet2 ) {
		List< String > msg1 = Arrays.asList( tweet1.split( " " ) );
		List< String > msg2 = Arrays.asList( tweet2.split( " " ) );
		int totalNbOfWords = msg1.size() + msg2.size();
		int commonNbOfWords = 0;

		for ( String word : msg1 ) {
			if ( msg2.contains( word ) ) {
				commonNbOfWords++;
			}
		}

		return totalNbOfWords - ( 2 * commonNbOfWords );
	}

	@Override
	public Feeling classifies ( String msg ) {
		int nb = this.nbNeighbors;
		List< Tweet > tweets = new ArrayList< Tweet >( this.tweetPool.tweets() );
		DTCouple[] neighbors = new DTCouple[ nb ];
		int maxIndex = 0;

		for ( int i = 0; i < nb; i++ ) {
			Tweet tweet = tweets.get( i );
			neighbors[ i ] = new DTCouple( this.distance( msg, tweet.getMsg() ), tweet );
			if ( neighbors[ i ].getDistance() > neighbors[ maxIndex ].getDistance() ) {
				maxIndex = i;
			}
		}

		for ( int i = nb; i < tweets.size(); i++ ) {
			Tweet tweet = tweets.get( i );
			int distance = this.distance( msg, tweet.getMsg() );

			if ( distance < neighbors[ maxIndex ].getDistance() ) {
				neighbors[ maxIndex ] = new DTCouple( distance, tweet );

				// Search the new maxIndex
				for ( int k = 0; k < neighbors.length; k++ ) {
					if ( neighbors[ k ].getDistance() > neighbors[ maxIndex ].getDistance() ) {
						maxIndex = k;
					}
				}
			}
		}

		int cptPositive = 0;
		int cptNegative = 0;
		int cptNeutral = 0;

		for ( int i = 0; i < nb; i++ ) {
			Feeling feeling = neighbors[ i ].getTweet().getFeeling();

			if ( feeling == Feeling.POSITIVE ) {
				cptPositive++;
			} else if ( feeling == Feeling.NEGATIVE ) {
				cptNegative++;
			} else {
				cptNeutral++;
			}
		}

		if ( ( cptNeutral >= cptPositive ) && ( cptNeutral >= cptNegative ) ) {
			return Feeling.NEUTRAL;
		} else if ( cptPositive > cptNegative ) {
			return Feeling.POSITIVE;
		} else {
			return Feeling.NEGATIVE;
		}
	}
	
	@Override
	public String toString () {
		return "KNN";
	}

	/////////////////////
	// PRIVATE CLASSES //
	/////////////////////

	// Class representing a couple : ( Distance, Tweet )
	private class DTCouple {

		private int distance;

		private Tweet tweet;

		public DTCouple ( int distance, Tweet tweet ) {
			this.distance = distance;
			this.tweet = tweet;
		}

		public double getDistance () {
			return this.distance;
		}

		public Tweet getTweet () {
			return this.tweet;
		}

		public String toString () {
			return "(" + this.distance + ", " + this.tweet + ")";
		}

	}

}
