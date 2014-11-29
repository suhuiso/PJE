package app;

import model.AppModel;
import view.AppView;
import controller.AppController;

public class Main {
	
	public static void main(String[] args) {
		AppModel search = new AppModel();
		AppController controller = new AppController( search );
		/* First view */
		// AppViewTmp searchView = new AppViewTmp( controller );
		/* New view : Work in progress */
		AppView searchView = new AppView( controller );
		
		search.addObserver( searchView );
	}	
}
