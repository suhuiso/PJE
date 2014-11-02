package feeling;

import java.util.Arrays;
import java.util.List;

import utils.Tweet;
import utils.TweetPool;

/**
 * Class reprsenting objects that assign feeling to a message of a tweet using the KNN method.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class KNNAssigner extends FeelingAssigner {

	////////////
	// FIELDS //
	////////////

	/**
	 * Tweet pool used for basic learning.
	 */
	private TweetPool tweetPool;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of KNNAssigner.
	 * 
	 * @param tweetPool
	 *            tweet pool used for basic learning
	 */
	public KNNAssigner ( TweetPool tweetPool ) {
		this.tweetPool = tweetPool;
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
	public int distance ( Tweet tweet1, Tweet tweet2 ) {
		List< String > msg1 = Arrays.asList( tweet1.getMsg().split( " " ) );
		List< String > msg2 = Arrays.asList( tweet2.getMsg().split( " " ) );
		int totalNbOfWords = msg1.size() + msg2.size();
		int commonNbOfWords = 0;

		for ( String word : msg1 ) {
			if ( msg2.contains( word ) ) {
				commonNbOfWords++;
			}
		}

		return ( totalNbOfWords - commonNbOfWords ) / totalNbOfWords;
	}

	@Override
	public Feeling assigns ( String msg ) {
		// TODO Auto-generated method stub
		return null;
	}

}
