package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

//ModuleChooser
public class OverviewResults extends StackPane {

	private TextArea overview;
	private Button btnSaveOverview;


	public OverviewResults() {


		overview = new TextArea("Overview will appear here...");
		overview.setEditable(false);
		overview.setWrapText(true);

		btnSaveOverview = new Button ("Save Overview");

		this.setPadding(new Insets(40,40,40,40));
		this.getChildren().add(overview);


	}

	public void setOverview(String overview) {
		this.overview.setText(overview);
	}

	public void setColor(String color) {
		this.setStyle("-fx-background-color: " + color + ";");
	}

}
