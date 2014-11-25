package feeling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NGramme {

	////////////
	// FIELDS //
	////////////

	/**
	 * Number of words composing the n-gramme.
	 */
	private int n;

	/**
	 * Words composing the n-gramme.
	 */
	private String[] words;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Constructor of the NGramme class.
	 * 
	 * @param n
	 *            number of words composing the n-gramme
	 */
	public NGramme ( int n, String[] words ) throws IllegalArgumentException {
		if ( words.length != n ) {
			throw new IllegalArgumentException( "This n-gramme has to contain " + n + " words" );
		} else {
			this.n = n;
			this.words = new String[ n ];
		}
	}

	/**
	 * Gives the words of the n-gramme.
	 * 
	 * @return words of the n-gramme
	 */
	public String[] getWords () {
		return this.words;
	}

	////////////////////
	// STATIC METHODS //
	////////////////////

	public static List< NGramme > buildNGrammesFrom ( String msg, int n )
	        throws IllegalArgumentException {
		String[] words = msg.split( " " );

		if ( words.length < n ) {
			throw new IllegalArgumentException(
			        "There is not enough words in the message to build a " + n + "-gramme" );
		} else {
			List< NGramme > res = new ArrayList< NGramme >();

			for ( int i = 0; i <= words.length - n; i++ ) {
				NGramme nGramme = new NGramme( n, Arrays.copyOfRange( words, i, i + n ) );
				res.add( nGramme );
			}

			return res;
		}
	}

}
