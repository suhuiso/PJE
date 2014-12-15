package statistics;

/**
 * Class representing objects that make statistics about a classifier.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public abstract class ClassifierStatistics {

	/**
	 * Evaluates the classifier using the tweet pool.
	 */
	public abstract double evaluates ();

}
