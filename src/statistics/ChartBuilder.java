package statistics;

import java.util.List;

import utils.Tweet;

import com.googlecode.charts4j.AbstractGraphChart;

import feeling.Classifier;

/**
 * Class representing objects that generate charts from a classifier results on a list of tweets.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public abstract class ChartBuilder {

	////////////
	// FILEDS //
	////////////

	/**
	 * Classifier from which build the chart.
	 */
	protected Classifier classifier;

	/**
	 * Request with which the list of tweet was obtain.
	 */
	protected String request;

	/**
	 * List of tweet to classify.
	 */
	protected List< Tweet > tweets;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor.
	 * 
	 * @param classifier
	 *            classifier from which build the chart
	 * @param tweets
	 *            list of tweet to classify
	 */
	public ChartBuilder ( Classifier classifier, String request, List< Tweet > tweets ) {
		this.classifier = classifier;
		this.request = request;
		this.tweets = tweets;
	}

	/**
	 * Generates the chart representing the results of the classifier on the list of tweets.
	 * 
	 * @return chart representing the results of the classifier on the list of tweets
	 */
	public abstract AbstractGraphChart generatesChart ();

}
