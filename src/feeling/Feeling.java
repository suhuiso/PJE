package feeling;

/**
 * Enumeration representing feeling attached to a tweet.
 * 
 * @author Quentin Baert & Thomas Bernard
 */
public enum Feeling {

	UNPOLARIZED {

		@Override
		public int getValue () {
			return -1;
		}

	},

	NEGATIVE {

		@Override
		public int getValue () {
			return 0;
		}

	},

	NEUTRAL {

		@Override
		public int getValue () {
			return 2;
		}

	},

	POSITIVE {

		@Override
		public int getValue () {
			return 4;
		}

	};

	/**
	 * Gives the integer value of the feeling.
	 * 
	 * @return integer value of the feeling
	 */
	public abstract int getValue ();

	/**
	 * Gives the feeling represented by an integer.
	 * 
	 * @param value
	 *            integer representing a feeling
	 * @return feeling represented by value
	 * @throws IllegalArgumentException
	 *             if value is not matching with a feeling
	 */
	public static Feeling createByValue ( int value ) throws IllegalArgumentException {
		switch ( value ) {
			case -1:
				return UNPOLARIZED;

			case 0:
				return NEGATIVE;

			case 2:
				return NEUTRAL;

			case 4:
				return POSITIVE;

			default:
				throw new IllegalArgumentException( "Value has to be -1, 0, 2 or 4" );
		}
	}

}
