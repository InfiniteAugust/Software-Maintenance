package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

import application.view.RootLayoutController;
import application.view.WorkbenchController;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Stage primaryStage;
    private BorderPane rootLayout;
    private Canvas saveCanvas;
    private Image image;
    
    public Main() {
    	
    }
	
	@Override
	public void start(Stage primaryStage) {
			
			this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("Sprite Editor");
			
			initRootLayout();
			showWorkbench();		
	}
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.setScene(scene);
			primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the workbench inside the root layout.
     */
    public void showWorkbench() {
        try {
            //load workbench
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Workbench.fxml"));
            AnchorPane workbench = (AnchorPane) loader.load();
            
            // Set workbench into the center of root layout.
            rootLayout.setCenter(workbench);

            // Give the controller access to the main app.
            WorkbenchController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * convert the loaded picture to an image and return it
     * @param file
     */
    public void loadPicture(File file) {
		try {
			image = new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
    }
    
    public Image getImage() {
    		return image;
    }
    
    
    public void savePicture(File file) {
		WritableImage image = getSaveCanvas().snapshot(new SnapshotParameters(), null);
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (IOException e) {
    			e.printStackTrace();
		}
    }	
  
    /**
     * getter and setter for the canvas to save
     * @param canvas
     */
    public void setSaveCanvas(Canvas canvas) {
    		this.saveCanvas = canvas;
    }
    
    public Canvas getSaveCanvas() {
    		return this.saveCanvas;
    }
     
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
