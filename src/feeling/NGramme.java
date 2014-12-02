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

	/**
	 * Gives the number of words of the n-gramme.
	 * 
	 * @return number of words of the n-gramme
	 */
	public int getDegree () {
		return this.n;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + n;
		result = prime * result + Arrays.hashCode( words );
		return result;
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		NGramme other = ( NGramme ) obj;
		if ( n != other.n ) return false;
		if ( !Arrays.equals( words, other.words ) ) return false;
		return true;
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
