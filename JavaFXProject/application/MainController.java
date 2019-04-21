package application;


import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController implements Initializable {

	//Buttons
	
	@FXML
	private Button returnToHomePage, colladaFilesBTN, 
				   siteTransmitterBTN, rfPropagationBTN,
				   createBTN, resetBTN;
	// Text Fields
	
	@FXML
	private TextField designNameTF;
	
	// Radio Buttons
	
	ToggleGroup tg = new ToggleGroup(); 
	
	@FXML
	private RadioButton uploadNewFilesRB, availableFilesRB;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		uploadNewFilesRB.setToggleGroup(tg);
		availableFilesRB.setToggleGroup(tg);
	}
	
	// List Views for listing file paths
	
	@FXML
	private ListView<String> colladaFilesLV, siteTransmitterLV, 
	                         rfPropagationLV;
	

	public void returnToHomePageAction(ActionEvent ae) {
		
		// Link to Home Page from here
		
	}
	
	// To allow the user to select the collada file
	public void colladaFilesBTNAction(ActionEvent ae) {
		
		List<File> selectedFilesList = getMultipleFiles(new ExtensionFilter("Collada Files", "*.dae"));
	
		if ((selectedFilesList != null) && (!selectedFilesList.isEmpty())) {
			
			for (Iterator<File> iterator = selectedFilesList.iterator(); iterator.hasNext();) {
				File file = iterator.next();
				colladaFilesLV.getItems().add(file.getAbsolutePath());
			}
			
		} else {
			displayAlertMessage(AlertType.ERROR, "Input Not Valid", "Please select a file");
		}
		
		
	}
	
	public void siteTransmitterBTNAction(ActionEvent ae) {
		File selectedFile = getSingleFile(new ExtensionFilter("Excel files", "*.xlsx"));
		if (selectedFile != null) {
			siteTransmitterLV.getItems().add(selectedFile.getAbsolutePath());
		} else {
			displayAlertMessage(AlertType.ERROR, "Input Not Valid", "Please select a file");
		}
		
	}
	
	public void rfPropagationBTNAction(ActionEvent ae) {
		
		File selectedFile = getSingleFile(null);
		if (selectedFile != null) {
			rfPropagationLV.getItems().add(selectedFile.getAbsolutePath());
		} else {
			displayAlertMessage(AlertType.ERROR, "Input Not Valid", "Please select a file");
		}
		
	}
	
	public void createBTNAction(ActionEvent ae) {
		
		
	}
	
	public void resetBTNAction(ActionEvent ae) {
		
		
	}

	private File getSingleFile(ExtensionFilter filter) {
		FileChooser fc = new FileChooser();
		
		if (filter != null) {
			fc.getExtensionFilters().addAll(filter);
		}
		
		File selectedFile = fc.showOpenDialog(null);
		return selectedFile;
	}
	
	private List<File> getMultipleFiles(ExtensionFilter filter) {
		FileChooser fc = new FileChooser();
		if (filter != null) {
			fc.getExtensionFilters().addAll(filter);
		}
		List<File> selectedFilesList = fc.showOpenMultipleDialog(null);
	
		return selectedFilesList;
	}
	
	private void displayAlertMessage (AlertType type,String alertHeader,String message) {
		Alert errorAlert = new Alert(type);
		errorAlert.setHeaderText(alertHeader);
		errorAlert.setContentText(message);
		errorAlert.showAndWait();
		
		
	}
	
}
