package controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Course;
import model.Delivery;
import model.Module;
import model.Name;
import model.StudentProfile;
import view.CreateProfile;
import view.ModuleChooserRootPane;
import view.MyMenuBar;
import view.OverviewResults;
import view.SelectModules;



public class ModuleChooserController {

	//fields to be used throughout the class
	private ModuleChooserRootPane view;
	private StudentProfile model;
	private CreateProfile cpp;
	private SelectModules sm;
	private OverviewResults or;
	private MyMenuBar mmb;
	private Module m;


	public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
		//initialise model and view fields
		this.model = model;
		this.view = view;


		cpp = view.getCreateProfile();
		sm = view.getSelectModules();
		or = view.getOverviewResults();
		mmb = view.getMyMenuBar();
		//populate combobox in create profile pane, e.g. if cpp represented your create profile pane you could invoke the line below
		cpp.populateComboBox(setupAndGetCourses());

		//attach event handlers to view using private helper method
		this.attachEventHandlers();	

	}

	private void attachEventHandlers() {
		cpp.addCreateProfileListener(new CreateProfileHandler());

		sm.add1Listener(new add1Handler());
		sm.add2Listener(new add2Handler());
		sm.remove1Listener(new remove1Handler());
		sm.remove2Listener(new remove2Handler());

		sm.submitListener(new submitHandler());
		sm.resetListener(new resetHandler());

		mmb.addAboutHandler(new aboutHandler());
		mmb.addLoadHandler(new loadHandler());
		mmb.addSaveHandler(new saveHandler());
		mmb.addExitHandler(e -> System.exit(0));
		//sm.addAdd1Listener(new add1Handler()); 
	}


	private Course[] setupAndGetCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Delivery.TERM_1);
		Module imat3451 = new Module("IMAT3451", "Computing Project", 30, true, Delivery.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigerous Systems", 15, true, Delivery.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigerous Systems", 15, false, Delivery.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Delivery.TERM_1);
		Module ctec3426 = new Module("CTEC3426", "Telematics", 15, false, Delivery.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Delivery.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Delivery.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Delivery.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Delivery.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Delivery.TERM_1);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Delivery.TERM_2);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Delivery.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Delivery.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computing Ethics and Privacy", 15, false, Delivery.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Delivery.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data", 15, false, Delivery.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Delivery.TERM_2);

		Course compSci = new Course("Computer Science");
		compSci.addModule(imat3423);
		compSci.addModule(imat3451);
		compSci.addModule(ctec3902_CompSci);
		compSci.addModule(ctec3110);
		compSci.addModule(ctec3426);
		compSci.addModule(ctec3605);
		compSci.addModule(ctec3606);
		compSci.addModule(ctec3410);
		compSci.addModule(ctec3904);
		compSci.addModule(ctec3905);
		compSci.addModule(ctec3906);
		compSci.addModule(imat3410);
		compSci.addModule(imat3406);
		compSci.addModule(imat3611);
		compSci.addModule(imat3613);
		compSci.addModule(imat3614);
		compSci.addModule(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModule(imat3423);
		softEng.addModule(imat3451);
		softEng.addModule(ctec3902_SoftEng);
		softEng.addModule(ctec3110);
		softEng.addModule(ctec3426);
		softEng.addModule(ctec3605);
		softEng.addModule(ctec3606);
		softEng.addModule(ctec3410);
		softEng.addModule(ctec3904);
		softEng.addModule(ctec3905);
		softEng.addModule(ctec3906);
		softEng.addModule(imat3410);
		softEng.addModule(imat3406);
		softEng.addModule(imat3611);
		softEng.addModule(imat3613);
		softEng.addModule(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}

	private class CreateProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

			if(cpp.getFName().isEmpty()){
				Alert fNameEmpty = new Alert(AlertType.WARNING);
				fNameEmpty.setTitle("Error");
				fNameEmpty.setHeaderText("");
				fNameEmpty.setContentText("First Name Field Empty");

				fNameEmpty.showAndWait();
				return;
			}

			if(cpp.getSName().isEmpty()){
				Alert sNameEmpty = new Alert(AlertType.WARNING);
				sNameEmpty.setTitle("Error");
				sNameEmpty.setHeaderText("");
				sNameEmpty.setContentText("Surname Name Field Empty");

				sNameEmpty.showAndWait();
				return;

			}

			if(cpp.getEmail().isEmpty()){
				Alert EmailEmpty = new Alert(AlertType.WARNING);
				EmailEmpty.setTitle("Error");
				EmailEmpty.setHeaderText("");
				EmailEmpty.setContentText("Email Field Empty");

				EmailEmpty.showAndWait();
				return;
			}

			if(cpp.getPnumber().isEmpty()){
				Alert pNumberEmpty = new Alert(AlertType.WARNING);
				pNumberEmpty.setTitle("Error");
				pNumberEmpty.setHeaderText("");
				pNumberEmpty.setContentText("P Number Field Empty");

				pNumberEmpty.showAndWait();
				return;
			}


			else{
				model.setStudentName(new Name(cpp.getFName(), cpp.getSName()));
				model.setpNumber(cpp.getPnumber());
				model.setCourse(cpp.getSelectedCourse());
				model.setEmail(cpp.getEmail());
				model.setDate(cpp.getDate());



				sm.clearOptions();
				for (Module m : model.getCourse().getModulesOnCourse()){
					if(m.getRunPlan().equals(Delivery.TERM_1) && !m.isMandatory())
						sm.addT1UM(m);
					if(m.getRunPlan().equals(Delivery.TERM_2) && !m.isMandatory())
						sm.addT2UM(m);
					if(m.getRunPlan().equals(Delivery.TERM_1) && m.isMandatory())
						sm.addT1SM(m);
					if(m.getRunPlan().equals(Delivery.TERM_2) && m.isMandatory())
						sm.addT2SM(m);
					if(m.getRunPlan().equals(Delivery.YEAR_LONG) && m.isMandatory())
						sm.addTLM(m);
				}

				if (model.getCourse().toString() == "Software Engineering"){
					sm.setCounter1(40);
					sm.setCounter2(40);
				}
				else{
					sm.setCounter1(30);
					sm.setCounter2(45);
				}

				//Get Course Method
				view.changeTab(1);
			}
		}

	}

	private class submitHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Alert emptySelect = new Alert(AlertType.WARNING);
			emptySelect.setTitle("Error!");
			emptySelect.setHeaderText("");
			emptySelect.setContentText("You have not selected all modules and have credits left!");


			if(sm.getTerm1().getItems().size() <= 2){
				emptySelect.showAndWait();
				return;
			}
			else{
				for(Module m : sm.returnSelectedModules1()){
					model.addSelectedModule(m);
				}

				for(Module b : sm.returnSelectedModules2()){
					model.addSelectedModule(b);
				}
				or.setOverview("" + model.getStudentName().toString() + "\n" + "Student P Number: " 
						+ model.getpNumber() + "\n" + "Student Email: " + model.getEmail() + "\n" 
						+ "Student Course: " + model.getCourse() + "\n" + 
						"Student Selected Modules: " + model.getSelectedModules() + "\n" + "Mandatory Year Long Unit: IMAT3451 Computing Project ");
				view.changeTab(2);
			}
		}
	}

	private class resetHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			sm.clearOptions();
			for (Module m : model.getCourse().getModulesOnCourse()){
				if(m.getRunPlan().equals(Delivery.TERM_1) && !m.isMandatory())
					sm.addT1UM(m);
				if(m.getRunPlan().equals(Delivery.TERM_2) && !m.isMandatory())
					sm.addT2UM(m);
				if(m.getRunPlan().equals(Delivery.TERM_1) && m.isMandatory())
					sm.addT1SM(m);
				if(m.getRunPlan().equals(Delivery.TERM_2) && m.isMandatory())
					sm.addT2SM(m);
				if(m.getRunPlan().equals(Delivery.YEAR_LONG) && m.isMandatory())
					sm.addTLM(m);
			}
		}
	}

	private class add1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Alert fullModule = new Alert(AlertType.INFORMATION);
			fullModule.setTitle("Sorry!");
			fullModule.setHeaderText("");
			fullModule.setContentText("You have already fulfilled the units for this Term. Please remove one before adding");

			if(sm.checkFullT1()) {
				fullModule.showAndWait();
				return;
			}
			else{
				sm.add1M();
				//sm.setCounter1(sm.getCounter1()-15);
			}
		}

	}


	private class add2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Alert fullModule = new Alert(AlertType.INFORMATION);
			fullModule.setTitle("Sorry!");
			fullModule.setHeaderText("");
			fullModule.setContentText("You have already fulfilled the units for this Term. Please remove one before adding");

			if(sm.checkFullT2()) {
				fullModule.showAndWait();
				return;
			}
			else{
				sm.add2M();
				//sm.setCounter2(sm.getCounter2()-15);
			}
		}
	}

	private class remove1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			sm.rem1M();
		}
	}

	private class remove2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			sm.rem2M();
		}
	}

	private class aboutHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Select Modules App v1.0");
			alert.setHeaderText(null);
			alert.setContentText("This is a software which will allow Technology Students to select their final year modules. ");

			alert.showAndWait();
		}
	}

	private class loadHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
		}

	}

	private class saveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {

		}
	}


}
