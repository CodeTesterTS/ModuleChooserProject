package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import model.Course;
import model.Delivery;
import model.Module;
import model.StudentProfile;

//ModuleChooser
public class SelectModules extends GridPane {

	private ListView<Module> mlTerm1, mlTerm2, mlTerm1s, mlTerm2s, mlYearLong;
	private Button add1, remove1, add2, remove2, submit, reset;
	private Delivery term1;
	private Label term1counter, term2counter; 


	//private StudentProfile sp;

	public SelectModules() {
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(10);
		this.setVgap(15);

		ColumnConstraints column1 = new ColumnConstraints(100,100, Double.MAX_VALUE);
		column1.setHgrow(Priority.ALWAYS);
		ColumnConstraints column2 = new ColumnConstraints(100,100,Double.MAX_VALUE);
		column2.setHgrow(Priority.ALWAYS);
		this.getColumnConstraints().addAll(column1, column2);


		//Variables
		//--Buttons--
		add1 = new Button("Add Term 1 Module");
		remove1 = new Button("Remove Term 1 Module");
		add2 = new Button("Add Term 2 Module");
		remove2 = new Button("Remove Term 2 Module");
		submit = new Button("Submit");
		reset = new Button("Reset");

		//--Listviews-- 
		mlTerm1 = new ListView<Module>();
		mlTerm2 = new ListView<Module>();
		mlTerm1s = new ListView<Module>();
		mlTerm2s = new ListView<Module>();
		mlYearLong = new ListView<Module>();


		mlTerm1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		mlTerm1s.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		mlTerm2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		mlTerm2s.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		//--Labels--
		Label lblTerm1 	= new Label("Term 1 Modules ");
		Label lblTerm1s = new Label("Selected Term 1 Modules");
		Label lblTerm1Counter = new Label ("Term 1 Credits : ");

		Label lblTerm2 	= new Label("Term 2 Modules");
		Label lblTerm2s = new Label ("Selected Term 2 Modules");
		Label lblTerm2Counter = new Label ("Term 2 Credits : ");

		Label lblYearLong	= new Label("Selected Year Long Modules");

		//--Counter--
		term1counter = new Label("0");
		term2counter = new Label("0");

		//--HBOX--
		HBox hbTerm1 = new HBox(24); // spacing = 8
		hbTerm1.getChildren().addAll(add1, remove1);
		hbTerm1.setAlignment(Pos.CENTER);

		HBox hbTerm2 = new HBox(24); // spacing = 8
		hbTerm2.getChildren().addAll(add2, remove2);
		hbTerm2.setAlignment(Pos.CENTER);

		HBox hbCounter = new HBox(24);
		hbCounter.getChildren().addAll(lblTerm1Counter, term1counter,  lblTerm2Counter, term2counter);
		hbCounter.setAlignment(Pos.CENTER);

		HBox hbSubmit = new HBox(24);
		hbSubmit.getChildren().addAll(submit,reset);
		hbSubmit.setAlignment(Pos.CENTER);


		//--Term 1--
		this.add(lblTerm1, 0, 0);
		this.add(mlTerm1, 0, 1);
		this.add(hbTerm1, 0, 3);

		//--Term 1 Selected --
		this.add(lblTerm1s, 1, 0);
		this.add(mlTerm1s, 1, 1);

		//--Term 2--
		this.add(lblTerm2, 0, 5);
		this.add(mlTerm2, 0, 6);
		this.add(hbTerm2, 0, 8);

		//--Term 2 Selected --
		this.add(lblTerm2s, 1, 5);
		this.add(mlTerm2s, 1, 6);

		//--Year Long Module -- 
		this.add(lblYearLong, 1, 9);
		this.add(mlYearLong, 1, 10);

		//--Counter--
		this.add(hbCounter, 0, 9);

		//---Submit--
		this.add(hbSubmit, 0, 10);
	}

	//Methods
	public ListView<Module> getTerm1(){
		return mlTerm1s;
	}

	public ListView<Module> getTerm2(){
		return mlTerm2s;
	}

	public int getCounter1(){
		return Integer.valueOf(term1counter.toString()); 
	}

	public int getCounter2(){
		return Integer.valueOf(term2counter.toString()); 
	}

	public void setCounter1(int i){
		term1counter.setText(String.valueOf(i));
	}

	public void setCounter2(int i){
		term2counter.setText(String.valueOf(i));
	}

	public boolean checkFullT1(){
		if(mlTerm1s.getItems().size() == 3){
			return true;	
		}

		else {
			return false;
		}
	}

	public boolean checkFullT2(){
		if(mlTerm2s.getItems().size() == 3){
			return true;	
		}

		else {
			return false;
		}
	}

	public void addT1UM(Module m){
		mlTerm1.getItems().add(m);
	}

	public void addT2UM(Module m){
		mlTerm2.getItems().add(m);
	}

	public void addTLM(Module m){
		mlYearLong.getItems().add(m);
	}

	public void addT1SM(Module m){
		mlTerm1s.getItems().add(m);
	}

	public void addT2SM(Module m){
		mlTerm2s.getItems().add(m);
	}

	public void add1M(){
		Alert selectedUnit = new Alert(AlertType.INFORMATION);
		selectedUnit.setTitle("Sorry!");
		selectedUnit.setHeaderText("");
		selectedUnit.setContentText("This unit is already added to your selected module");

		if(mlTerm1s.getItems().contains(mlTerm1.getSelectionModel().getSelectedItem())){
			selectedUnit.showAndWait();
			return;
		}

		else{
			mlTerm1s.getItems().add(mlTerm1.getSelectionModel().getSelectedItem());
		}
	}

	public void add2M(){
		Alert selectedUnit = new Alert(AlertType.INFORMATION);
		selectedUnit.setTitle("Sorry!");
		selectedUnit.setHeaderText("");
		selectedUnit.setContentText("This unit is already added to your selected module");


		if(mlTerm2s.getItems().contains(mlTerm2.getSelectionModel().getSelectedItem())){
			selectedUnit.showAndWait();
			return;
		}

		else{
			mlTerm2s.getItems().add(mlTerm2.getSelectionModel().getSelectedItem());
		}
	}

	public void rem1M(){
		Alert manModule = new Alert(AlertType.WARNING);
		manModule.setTitle("Mandatory Unit");
		manModule.setHeaderText("");
		manModule.setContentText("You have selected a mandatory unit. You cannot remove this.");

		if(mlTerm1s.getSelectionModel().getSelectedItem().toString().contains( "IMAT3423" )){
			manModule.showAndWait();
			return;
		}
		if(mlTerm1s.getSelectionModel().getSelectedItem().toString().contains( "IMAT3451" )){
			manModule.showAndWait();
			return;
		}

		if(mlTerm1s.getSelectionModel().getSelectedItem().toString().contains( "CTEC3902" )){
			manModule.showAndWait();
			return;
		}

		else{
			mlTerm1s.getItems().remove(mlTerm1s.getSelectionModel().getSelectedItem());
		}
	}

	public void rem2M(){
		Alert manModule = new Alert(AlertType.WARNING);
		manModule.setTitle("Mandatory Unit");
		manModule.setHeaderText("");
		manModule.setContentText("You have selected a mandatory unit. You cannot remove this.");

		if(mlTerm2s.getSelectionModel().getSelectedItem().toString().contains( "IMAT3423" )){
			manModule.showAndWait();
			return;
		}
		if(mlTerm2s.getSelectionModel().getSelectedItem().toString().contains( "IMAT3451" )){
			manModule.showAndWait();
			return;
		}

		if(mlTerm2s.getSelectionModel().getSelectedItem().toString().contains( "CTEC3902")){
			manModule.showAndWait();
			return;
		}

		else{
			mlTerm2s.getItems().remove(mlTerm2s.getSelectionModel().getSelectedItem());
		}
	}

	public ObservableList<Module> returnSelectedModules1(){
		return mlTerm1s.getItems();
	}

	public ObservableList<Module> returnSelectedModules2(){
		return mlTerm2s.getItems();
	}

	public void clearOptions(){
		mlTerm1.getItems().clear();
		mlTerm2.getItems().clear();
		mlYearLong.getItems().clear();
		mlTerm1s.getItems().clear();
		mlTerm2s.getItems().clear();
	}

	public void add1Listener(EventHandler<ActionEvent> handler) {
		add1.setOnAction(handler);
	}

	public void add2Listener(EventHandler<ActionEvent> handler) {
		add2.setOnAction(handler);
	}

	public void remove1Listener(EventHandler<ActionEvent> handler){
		remove1.setOnAction(handler);
	}

	public void remove2Listener(EventHandler<ActionEvent> handler){
		remove2.setOnAction(handler);
	}

	public void submitListener(EventHandler<ActionEvent> handler){
		submit.setOnAction(handler);
	}

	public void resetListener(EventHandler<ActionEvent> handler) {
		reset.setOnAction(handler);
	}



}
