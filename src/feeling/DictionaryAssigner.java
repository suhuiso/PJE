package feeling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class reprsenting objects that assign a feeling to a message of a tweet using the dictionary
 * method.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public class DictionaryAssigner extends FeelingAssigner {

	////////////
	// FIELDS //
	////////////

	/**
	 * Path to the positive dictionary of the DictionaryAssigner.
	 */
	private String positivePath;

	/**
	 * Path to the negative dictionary of the DictionaryAssigner.
	 */
	private String negativePath;

	/////////////
	// METHODS //
	/////////////

	/**
	 * Contructor of DictionaryAssigner.
	 * 
	 * @param positivePath
	 *            path to the positive dictionary of the DictionaryAssigner
	 * @param negativePath
	 *            path to the negative dictionary of the DictionaryAssigner
	 */
	public DictionaryAssigner ( String positivePath, String negativePath ) {
		this.positivePath = positivePath;
		this.negativePath = negativePath;
	}

	// Load a file into a string
	private String fileToString ( String path ) {
		StringBuffer res = new StringBuffer();
		File file = new File( path );

		if ( file.exists() && !file.isDirectory() ) {
			try {
				BufferedReader br = new BufferedReader( new FileReader( path ) );
				String line = "";

				while ( ( line = br.readLine() ) != null ) {
					res.append( line );
				}

				br.close();
			} catch ( FileNotFoundException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch ( IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return res.toString();
	}

	// Add back slash before metachars of regex
	private String addBackSlash ( String s ) {
		String metachars = "()[]";
		StringBuffer buf = new StringBuffer();

		for ( int i = 0; i < s.length(); i++ ) {
			String charRead = ( ( Character ) s.charAt( i ) ).toString();

			if ( metachars.contains( charRead ) ) {
				buf.append( "\\" + charRead );
			} else {
				buf.append( charRead );
			}
		}

		return buf.toString();
	}

	// Gives a feeling to a tweet using dictonaries
	private Feeling msgDictionaryPolarize ( String msg, List< String > positiveWords,
	        List< String > negativeWords ) {
		int cpt = 0;

		for ( String positiveWord : positiveWords ) {
			if ( msg.matches( ".*\\b" + this.addBackSlash( positiveWord ) + "\\b.*" ) ) {
				cpt++;
			}
		}

		for ( String negativeWord : negativeWords ) {
			if ( msg.matches( ".*\\b" + this.addBackSlash( negativeWord ) + "\\b.*" ) ) {
				cpt--;
			}
		}

		if ( cpt < 0 ) {
			return Feeling.NEGATIVE;
		} else if ( cpt > 0 ) {
			return Feeling.POSITIVE;
		} else {
			return Feeling.NEUTRAL;
		}
	}

	// Removes empty strings from a list of string
	private List< String > removeEmptyString ( List< String > ls ) {
		List< String > res = new ArrayList< String >();

		for ( String s : ls ) {
			String st = s.trim();

			if ( !st.isEmpty() ) {
				res.add( st );
			}
		}

		return res;
	}

	@Override
	public Feeling assigns ( String msg ) {
		// Lists with clean string
		List< String > positiveWords =
		        this.removeEmptyString( Arrays.asList( this.fileToString( this.positivePath )
		                .split( "," ) ) );
		List< String > negativeWords =
		        this.removeEmptyString( Arrays.asList( this.fileToString( this.negativePath )
		                .split( "," ) ) );

		return msgDictionaryPolarize( msg, positiveWords, negativeWords );
	}
}
