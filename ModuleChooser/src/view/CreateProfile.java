package view;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Course;

//ModuleChooser
public class CreateProfile extends GridPane{

	private ComboBox<Course> cboCourses;
	private TextField txtPnumber, txtFirstname, txtSurname, txtEmail, txtCourse;
	private DatePicker dateInput = new DatePicker();
	private Button crtProfile;

	public CreateProfile() {
		//Styling
		this.setPadding(new Insets(10,10,10,10));
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		//Label Creation
		Label lblCourse 	= new Label("Select Course: ");
		Label lblPnumber 	= new Label("Input P Number: ");
		Label lblFirstname 	= new Label("Input Firstname: ");
		Label lblSurname 	= new Label("Input Surname: ");
		Label lblEmail 		= new Label("Input Email: ");
		Label lblDate 		= new Label("Input Date: ");

		cboCourses 	 = new ComboBox<Course>();
		txtPnumber 	 = new TextField();
		txtFirstname = new TextField();
		txtSurname 	 = new TextField();
		txtEmail     = new TextField();

		crtProfile = new Button("Create Profile");

		this.add(lblCourse, 0, 0);
		this.add(cboCourses, 1, 0);

		this.add(lblPnumber, 0, 1);
		this.add(txtPnumber, 1, 1);

		this.add(lblFirstname, 0, 2);
		this.add(txtFirstname, 1, 2);

		this.add(lblSurname, 0, 3);
		this.add(txtSurname, 1, 3);

		this.add(lblEmail, 0, 4);
		this.add(txtEmail, 1, 4);

		this.add(lblDate, 0, 5);
		this.add(dateInput, 1, 5);

		this.add(new HBox(), 0, 6);
		this.add(crtProfile, 1, 6);
	}

	public void populateComboBox(Course[] courses) {
		cboCourses.getItems().addAll(courses);
		cboCourses.getSelectionModel().select(0);
	}

	public Course getSelectedCourse() {
		return cboCourses.getSelectionModel().getSelectedItem();
	}

	public String getFName() {
		return txtFirstname.getText();
	}

	public String getSName() {
		return txtSurname.getText();
	}

	public String getEmail() { 
		return txtEmail.getText();
	}

	public LocalDate getDate(){
		return dateInput.getValue();
	}

	public String getPnumber(){
		return txtPnumber.getText();
	}
	//Method to attach Listener
	public void addCreateProfileListener(EventHandler<ActionEvent> handler) {
		crtProfile.setOnAction(handler);
	}
}
