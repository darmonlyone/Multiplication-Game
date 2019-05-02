package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Timer {

    private int timeLeft;
    private int startTime;
    private Timeline timeline;

    public Timer(int startTime){
        this.startTime = startTime;
    }
    public void startTimer(EventHandler<ActionEvent> eventHandler) {
        timeLeft = startTime;
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),eventHandler),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int countDown(){
        return timeLeft--;
    }
    public void stopTimer(){
        timeline.stop();
    }

    public int getStartTime() {
        return startTime;
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}
