package app;

import view.SearchView;
import model.*;
import controller.*;

public class Main {
	
	public static void main(String[] args) {
		AbstractModel search = new Search();
		AbstractController controller = new SearchController( search );
		SearchView searchView = new SearchView( controller );
		
		search.addObserver( searchView );
	}	
}
