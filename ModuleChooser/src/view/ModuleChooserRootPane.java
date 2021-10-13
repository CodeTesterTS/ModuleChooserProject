package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;

//ModuleChooser
//You may change this class to extend another type if you wish
public class ModuleChooserRootPane extends BorderPane {

	private CreateProfile cp;
	private SelectModules sm;
	private OverviewResults or;
	private MyMenuBar mmb;
	private TabPane tp;


	public ModuleChooserRootPane() {
		cp 	= new CreateProfile();
		sm 	= new SelectModules();
		or 	= new OverviewResults();

		mmb = new MyMenuBar();
		tp 	= new TabPane();

		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		//Creation of Tabs
		Tab t1 = new Tab("Create Profile", cp);
		Tab t2 = new Tab("Select Modules", sm);
		Tab t3 = new Tab("Overview Results", or);


		//Adding Tabs to Tab Pane
		tp.getTabs().addAll(t1,t2,t3);

		this.setTop(mmb);
		this.setCenter(tp);
	}

	public CreateProfile getCreateProfile(){
		return cp;
	}

	public SelectModules getSelectModules(){
		return sm;
	}

	public OverviewResults getOverviewResults(){
		return or;
	}

	public MyMenuBar getMyMenuBar(){
		return mmb;
	}
	//Allows controller to change tabs.
	public void changeTab(int index){
		tp.getSelectionModel().select(index);
	}
}
