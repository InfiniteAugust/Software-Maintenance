package application.view;


import application.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class WorkbenchController {
	@FXML
	private AnchorPane canvasArea;
	@FXML
	private ColorPicker theColorPicker;
	@FXML
	private ColorPicker theColorPickerBG;
	@FXML
	public Canvas canvas;
	@FXML
	public Canvas background;
	@FXML
	private Button newCanvas, newCanva64, newCanva128;
	
	private Color pickedcolor, pickedcolorBG;
	
	// Reference to the main application.
    private Main mainApp;

    public WorkbenchController() {
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    
    @FXML
    private void handleCanvas() {
    		// draw as the user drags the mouse
    		GraphicsContext graCon = canvas.getGraphicsContext2D();
    		canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            		graCon.setFill(pickedcolor);
            		graCon.fillRect(e.getX() - 2, e.getY() - 2, 5, 5);
            }
        });
    		
    		mainApp.setSaveCanvas(canvas);
    		GraphicsContext graConBG = background.getGraphicsContext2D();
    		graConBG.drawImage(mainApp.getImage(), 0, 0);
    }
    
    
    @FXML
    private void handleBackground() {
    		GraphicsContext graConBG = background.getGraphicsContext2D();
    		graConBG.setFill(pickedcolorBG);
    		graConBG.fillRect(0, 0, background.getWidth(), background.getHeight());
    }
    
    
    @FXML
    private void handleColorPicker() {
    		pickedcolor = (Color)theColorPicker.getValue();
    		canvas.toFront();
    }
    
    @FXML
    private void handleColorPickerBG() {
    		pickedcolorBG = (Color)theColorPickerBG.getValue();

    		GraphicsContext graConBG = background.getGraphicsContext2D();
    		graConBG.setFill(pickedcolorBG);
    		graConBG.fillRect(0, 0, background.getWidth(), background.getHeight());
    }
    
    /**
     * 3 choices for creating a new canvas: 32 * 32, 64 * 64, 128 * 128
     */
    @FXML
    private void handleNewCanvas() {
    		
    		canvas.setWidth(32);
    		canvas.setHeight(32);
    		background.setWidth(32);
    		background.setHeight(32);
    		clear(canvas);
        reset(background, Color.WHITE);
    }
    
    @FXML
    private void handleNewCanvas64() {
    		
    		canvas.setWidth(64);
		canvas.setHeight(64);
		background.setWidth(64);
		background.setHeight(64);
		clear(canvas);
        reset(background, Color.WHITE);
    }
    
    @FXML
    private void handleNewCanvas128() {
	
    		canvas.setWidth(128);
		canvas.setHeight(128);
		background.setWidth(128);
		background.setHeight(128);
        clear(canvas);
        reset(background, Color.WHITE);
    }
    
    /**
     * set canvas to a specified color
     * @param canvas
     * @param color
     */
    public void reset(Canvas canvas, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    /**
     * clear canvas
     * @param canvas
     */
    public void clear(Canvas canvas) {
    		GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    
}
