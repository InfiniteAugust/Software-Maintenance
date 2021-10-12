package application.view;


import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.File;


import application.Main;


/**
 * the controller for the root layout.
 * It provides a menu bar and functions such as save and load
 * @author Jingyu LUO
 */

public class RootLayoutController {
	private Main mainApp;

	@FXML
	private MenuItem saveAs;
	@FXML
	private MenuItem open;
	@FXML
	private MenuItem exit;
	
	/**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Opens a FileChooser to let the user select a PNG file to load.
     */
    @FXML
    private void handleOpen() {
    		FileChooser fileChooser = new FileChooser();
    		
    		// Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        
        // Show open file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
		    mainApp.loadPicture(file);
		}    
    }
    
    
    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
    		FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".png")) {
                file = new File(file.getPath() + ".png");
            }
            mainApp.savePicture(file);
        }
    }
    
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    

}
