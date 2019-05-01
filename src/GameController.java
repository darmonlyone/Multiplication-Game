import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class GameController {

    @FXML
    private Label table2_score;

    @FXML
    private Button table2_btn;

    @FXML
    private Label table3_score;

    @FXML
    private Button table3_btn;

    @FXML
    private Label table4_score;

    @FXML
    private Button table4_btn;

    @FXML
    private Label table5_score;

    @FXML
    private Button table5_btn;

    @FXML
    private Label table6_score;

    @FXML
    private Button table6_btn;

    @FXML
    private Label table7_score;

    @FXML
    private Button table7_btn;

    @FXML
    private Label table8_score;

    @FXML
    private Button table8_btn;

    @FXML
    private Label table9_score;

    @FXML
    private Button table9_btn;

    @FXML
    private Label table10_score;

    @FXML
    private Button table10_btn;

    @FXML
    private Label table11_score;

    @FXML
    private Button table11_btn;

    @FXML
    private Label table12_score;

    @FXML
    private Button table12_btn;

    @FXML
    private FlowPane welcome_pane;

    @FXML
    private FlowPane gameplay_pane;

    @FXML
    private Pane question_box;

    @FXML
    private Label question_text;

    @FXML
    private Label current_time;

    @FXML
    private Label current_score;

    @FXML
    private Button answer1;

    @FXML
    private Button answer3;

    @FXML
    private Button answer2;

    @FXML
    private Button answer4;

    @FXML
    private Button giveup;

    @FXML
    private FlowPane result_pane;

    @FXML
    private Label result_score;

    @FXML
    private Label table_play;

    @FXML
    private Button newgame_btn;

    @FXML
    private Button tryagain_btn;


    @FXML
    private Label readyCount;

    @FXML
    private Label goLabel;

    @FXML
    private Pane ready_pane;

    @FXML
    private Label readyLabel;

    @FXML
    private Label fail_mark;

    @FXML
    private FlowPane result_table;

    private int playScore;
    private int playNumber;
    private Random random;
    private int answered;
    private ArrayList<Integer> ansList;
    private int MAXMULTI = 12;
    private Timeline timeline;
    private Timeline countDown;
    private int playTime;
    private int failCount = 3;
    private ArrayList<ResultFlowPane> resultFlowPaneArrayList;
    private TimeStop timeStop;
    private int multiplier;
    @FXML
    private void initialize(){
        changeScreen(1);
        playNumber = 1;
        random = new Random();
        ansList = new ArrayList<>();
        timeStop = new TimeStop();
        resultFlowPaneArrayList = new ArrayList<>();
    }

    private void changeScreen(int paneNo){
        if (paneNo == 1){
            // 1: welcome
            welcome_pane.setVisible(true);
            gameplay_pane.setVisible(false);
            result_pane.setVisible(false);
            ready_pane.setVisible(false);
        } else if (paneNo == 2) {
            // 2: game play
            welcome_pane.setVisible(false);
            gameplay_pane.setVisible(true);
            result_pane.setVisible(false);
            ready_pane.setVisible(false);
        } else if (paneNo == 3) {
            // 3: result
            welcome_pane.setVisible(false);
            gameplay_pane.setVisible(false);
            result_pane.setVisible(true);
            ready_pane.setVisible(false);
        } else if (paneNo == 4){
            // 4: ready
            welcome_pane.setVisible(false);
            gameplay_pane.setVisible(false);
            result_pane.setVisible(false);
            ready_pane.setVisible(true);
        }
    }

    private int makeQuestion(){
        int multiplier;
        if (ansList.size() >= (MAXMULTI * 2))
            multiplier = random.nextInt(MAXMULTI) + (MAXMULTI*2) + 1;
        else if (ansList.size() >= MAXMULTI)
            multiplier = random.nextInt(MAXMULTI) + MAXMULTI + 1;
        else
            multiplier = random.nextInt(MAXMULTI) + 1;
        question_text.setText(String.format("%d x %d = ??",playNumber,multiplier));
        this.multiplier =  multiplier;
        return playNumber * multiplier;
    }

    private void startTimer() {
        playTime = 60;
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), e ->updatePlayTime()),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void updatePlayTime(){
        current_time.setText("Time: "+ playTime-- + "sec");
        if (playTime == -1){
            changeScreen(4);
            goLabel.setText("Time Out!");
        }
        if (playTime == -4){
            endGame();
        }
    }

    private void stopTimer(){
        timeline.stop();
    }

    private void readyStep(int playNumber){
        failCount = 3;
        disablePlay(true);
        changeScreen(4);
        readyLabel.setVisible(true);
        readyCount.setVisible(true);
        goLabel.setVisible(false);
        readyCount.setText("4");
        goLabel.setText("Start");
        countDown = new Timeline(
                new KeyFrame(Duration.seconds(0), e ->readyCountDown(playNumber)),
                new KeyFrame(Duration.seconds(1)));
        countDown.setCycleCount(Animation.INDEFINITE);
        countDown.play();
    }

    private void readyCountDown(int playNumber){
        int countdown = Integer.parseInt(readyCount.getText());
        readyCount.setText(Integer.toString(--countdown));
        if (countdown == 0){
            goLabel.setVisible(true);
            readyCount.setVisible(false);
            readyLabel.setVisible(false);
        }
        if (countdown == -1){
            changeScreen(2);
            startPlay(playNumber);
            countDown.stop();
            startTimer();
        }
    }

    private void startPlay(int playNumber){
        this.playNumber = playNumber;
        answered = makeQuestion();
        while (ansList.contains(answered)){
            if (ansList.size() >= MAXMULTI*3)
                ansList.clear();
            answered = makeQuestion();
        }
        ansList.add(answered);
        int randAnsPoint = random.nextInt(4);
        ArrayList<Integer> randAnsList = new ArrayList<>();
        for (int i = 0; i < 4 ; i++) {
            int randAns = random.nextInt(playNumber*(multiplier >= MAXMULTI*2 ? MAXMULTI*3 : multiplier > MAXMULTI ? MAXMULTI*2 : MAXMULTI ))+1;
            if (playNumber % 2 == 0 && randAns % 2 != 0)
                randAns += 1;
            while (randAnsList.contains(randAns) || randAns == answered){
                randAns = random.nextInt(playNumber*(multiplier >= MAXMULTI*2 ? MAXMULTI*3 : multiplier > MAXMULTI ? MAXMULTI*2 : MAXMULTI ))+1;
                if (playNumber % 2 == 0 && randAns % 2 != 0)
                    randAns += 1;
            }
            randAnsList.add(randAns);
            if (i == randAnsPoint){
                setSelectAnswer(i,answered);
            }else {
                setSelectAnswer(i,randAns);
            }
        }
        timeStop.startTiming();
    }

    private void updateFail(){
        failCount--;
        if (failCount == 2){
            fail_mark.setText("X X");
        }else if(failCount == 1){
            fail_mark.setText("X");
        }else if (failCount <= 0){
            fail_mark.setText("X X X");
            endGame();
        }
    }

    private void setSelectAnswer(int i, int answer){
        if (i == 0)
            answer1.setText(Integer.toString(answer));
        if (i == 1)
            answer2.setText(Integer.toString(answer));
        if (i == 2)
            answer3 .setText(Integer.toString(answer));
        if (i == 3)
            answer4.setText(Integer.toString(answer));
    }

    private void updateScore(int score){
        current_score.setText("Score: " + score);
    }

    private void resetGamePlay(){
        current_score.setText("Score: 0");
        playScore = 0;
        ansList.clear();
        resultFlowPaneArrayList.clear();
    }

    private void disablePlay(boolean bool){
        table2_btn.setDisable(bool);
        table3_btn.setDisable(bool);
        table4_btn.setDisable(bool);
        table5_btn.setDisable(bool);
        table6_btn.setDisable(bool);
        table7_btn.setDisable(bool);
        table8_btn.setDisable(bool);
        table9_btn.setDisable(bool);
        table10_btn.setDisable(bool);
        table11_btn.setDisable(bool);
        table12_btn.setDisable(bool);
    }

    private void setResult(){
        table_play.setText("Table "+ playNumber);
        result_score.setText("Score: " + playScore);
    }

    private void endGame(){
        changeScreen(3);
        stopTimer();
        setResult();
        timeStop.stopTiming();
        result_table.getChildren().clear();
        result_table.getChildren().addAll(resultFlowPaneArrayList);
    }

    private void updateResultTable(int multiplier, int question){
        timeStop.stopTiming();
        resultFlowPaneArrayList.add(new ResultFlowPane(resultFlowPaneArrayList.size()+1,playNumber,multiplier,question,timeStop.getUsedTime()));
    }


    @FXML
    void answerFour(ActionEvent event) {
        updateResultTable(multiplier,Integer.parseInt(answer4.getText()));
        if (answer4.getText().equals(Integer.toString(answered))){
            updateScore(++playScore);
        }else {
            updateFail();
        }
        startPlay(playNumber);
    }

    @FXML
    void answerThree(ActionEvent event) {
        updateResultTable(multiplier,Integer.parseInt(answer3.getText()));
        if (answer3.getText().equals(Integer.toString(answered))){
            updateScore(++playScore);
        }else {
            updateFail();
        }
        startPlay(playNumber);
    }

    @FXML
    void answerTwo(ActionEvent event) {
        updateResultTable(multiplier,Integer.parseInt(answer2.getText()));
        if (answer2.getText().equals(Integer.toString(answered))){
            updateScore(++playScore);
        }else {
            updateFail();
        }
        startPlay(playNumber);
    }

    @FXML
    void asnwerOne(ActionEvent event) {
        updateResultTable(multiplier,Integer.parseInt(answer1.getText()));
        if (answer1.getText().equals(Integer.toString(answered))){
            updateScore(++playScore);
        }else {
            updateFail();
        }
        startPlay(playNumber);
    }

    @FXML
    void giveUp(ActionEvent event) {
        endGame();
    }

    @FXML
    void newGame(ActionEvent event) {
        changeScreen(1);
        disablePlay(false);
    }

    @FXML
    void playEigth(ActionEvent event) {
        resetGamePlay();
        readyStep(8);
    }

    @FXML
    void playEleven(ActionEvent event) {
        resetGamePlay();
        readyStep(11);
    }

    @FXML
    void playFive(ActionEvent event) {
        resetGamePlay();
        readyStep(5);
    }

    @FXML
    void playFour(ActionEvent event) {
        resetGamePlay();
        readyStep(4);
    }

    @FXML
    void playNine(ActionEvent event) {
        resetGamePlay();
        readyStep(9);
    }

    @FXML
    void playSeven(ActionEvent event) {
        resetGamePlay();
        readyStep(7);
    }

    @FXML
    void playSix(ActionEvent event) {
        resetGamePlay();
        readyStep(6);
    }

    @FXML
    void playTen(ActionEvent event) {
        resetGamePlay();
        readyStep(10);
    }

    @FXML
    void playThree(ActionEvent event) {
        resetGamePlay();
        readyStep(3);
    }

    @FXML
    void playTwelve(ActionEvent event) {
        resetGamePlay();
        readyStep(12);
    }

    @FXML
    void playTwo(ActionEvent event) {
        resetGamePlay();
        readyStep(2);
    }

    @FXML
    void tryAgain(ActionEvent event) {
        resetGamePlay();
        readyStep(playNumber);
    }

}
