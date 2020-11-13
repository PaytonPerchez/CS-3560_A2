package A2;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Driver class for running the mini twitter desktop application.
 * @author Payton Perchez
 */
public class MiniTwitterDemo extends Application {

	/**
	 * Calls the launch method.
	 * @param args String arguments through the console during runtime.
	 */
	public static void main(String[] args) {
		
		launch(args);
		
	}//end main
	
	/**
	 * Creates an AdminControlPanel, starting the mini twitter application.
	 * @param primaryStage The default stage.
	 */
	@Override
	public void start(Stage primaryStage) {
		
		AdminControlPanel admin = AdminControlPanel.getInstance();
		admin.show();
		
	}//end start
	
}//end MiniTwitterDemo
