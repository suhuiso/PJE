package app;

import view.AppView;
import model.*;
import controller.*;

public class Main {
	
	public static void main(String[] args) {
		AbstractModel search = new AppModel();
		AppController controller = new AppController( search );
		AppView searchView = new AppView( controller );
		
		search.addObserver( searchView );
	}	
}
