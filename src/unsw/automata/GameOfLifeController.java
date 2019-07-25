package unsw.automata;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class GameOfLifeController {
	
	GameOfLife gol;
	Timeline timeline;
	
	public GameOfLifeController() {
		
		timeline = new Timeline();
		
		// adding a keyframe to the timeline that calls the
		// tick() method every 0.5 seconds
		KeyFrame keyframe = new KeyFrame(Duration.seconds(0.5), 
				new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				gol.tick();
			}
		});		
		timeline.getKeyFrames().add(keyframe);
		
		// setting the timeline to repeat indefinitely
		timeline.setCycleCount(Animation.INDEFINITE);
		
		gol = new GameOfLife();
		
		gol.ensureAlive(9, 8);
        gol.ensureAlive(8, 8);
        gol.ensureAlive(7, 8);
        gol.ensureAlive(9, 7);
        gol.ensureAlive(8, 6); 
	}

    @FXML
    private GridPane boardGrid;

    @FXML
    private Button playButton;

    @FXML
    void actionPlay(ActionEvent event) {
    	
    	// if button says stop, then the animation is running,
    	// so we want to stop it
    	if (playButton.getText() == "Stop") {
    		timeline.stop();
    		playButton.setText("Start");
    	
    	// if it isn't playing, then we want to start it
    	} else {
    		timeline.play();
    		playButton.setText("Stop");
    	}
    }

    @FXML
    void actionTick(ActionEvent event) {
    	gol.tick();
    }
    
    @FXML
    public void initialize() {
    	
    	for (int i = 0; i < 10; i++) {
    		for (int j = 0; j < 10; j++) {
    			
    			CheckBox cb = new CheckBox();
    			boardGrid.add(cb, i, j);
    			if (gol.isAlive(i, j)) {
    				cb.setSelected(true);
    			}
    			
    			gol.cellProperty(i, j).addListener(new ChangeListener<Boolean>() {

    	            @Override
    	            public void changed(ObservableValue<? extends Boolean> observable,
    	                    Boolean oldValue, Boolean newValue) {
    	                cb.setSelected(newValue);
    	            }

    	        });
    		}
    	}
    }
}

