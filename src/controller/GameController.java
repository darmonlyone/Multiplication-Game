package controller;

import controller.sub.ResultFlowPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import model.*;

import java.util.ArrayList;

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
    private FlowPane result_pane;

    @FXML
    private Label result_score;

    @FXML
    private Label result_highscore;

    @FXML
    private Label table_play;

    @FXML
    private Label readyCountLabel;

    @FXML
    private Label readyStatusLabel;

    @FXML
    private Pane ready_pane;

    @FXML
    private Label readyLabel;

    @FXML
    private Label fail_mark;

    @FXML
    private FlowPane result_table;

    private MultiplicationApp multiplicationApp;
    private MultiplicationGame multiplicationGame;
    private Multiplication multiplication;
    private Timer playTimer;
    private Timer readyTimer;
    private ArrayList<ResultFlowPane> resultFlowPaneArrayList;
    private Stopwatch stopwatch;


    @FXML
    private void initialize(){
        playTimer = new Timer(60);
        multiplicationGame = new MultiplicationGame(3,0);
        multiplication = multiplicationGame.getMultiplication();
        multiplicationApp = new MultiplicationApp(12, "src/highscore.txt");
        multiplicationApp.setMultiplicationGame(multiplicationGame);
        stopwatch = new Stopwatch();
        resultFlowPaneArrayList = new ArrayList<>();

        changeScreen(1);
        showHighScore();
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

    private void setQuestionLabel(){
        question_text.setText(String.format("%d x %d = ??",multiplication.getNumber(),multiplication.getMultiplier()));
    }


    private void readyStep(int playNumber){
        readyTimer = new Timer(3);
        disablePlay(true);
        changeScreen(4);
        readyLabel.setVisible(true);
        readyCountLabel.setVisible(true);
        readyStatusLabel.setVisible(false);
        readyCountLabel.setText("4");
        readyStatusLabel.setText("Start");
        fail_mark.setText("X X X");

        multiplicationApp.resetGame();

        readyTimer.startTimer(e->{
            int countdown = readyTimer.countDown() + 1;
            readyCountLabel.setText(Integer.toString(--countdown));
            if (countdown == 0){
                readyStatusLabel.setVisible(true);
                readyCountLabel.setVisible(false);
                readyLabel.setVisible(false);
            }
            if (countdown == -1){
                changeScreen(2);
                setupPlay(playNumber);
                readyTimer.stopTimer();
                playTimer.startTimer(event -> {
                    current_time.setText("Time: "+ (playTimer.countDown()) + " sec");
                    if (playTimer.getTimeLeft() == -1){
                        changeScreen(4);
                        readyStatusLabel.setText("Time Out!");
                    }
                    if (playTimer.getTimeLeft() == -4){
                        endGame();
                    }
                });
            }
        });
    }

    private void setupPlay(int playNumber){
        multiplicationApp.startPlay(playNumber);

        int[] ansBox = multiplicationApp.getAns();
        answer1.setText(Integer.toString(ansBox[0]));
        answer2.setText(Integer.toString(ansBox[1]));
        answer3 .setText(Integer.toString(ansBox[2]));
        answer4.setText(Integer.toString(ansBox[3]));

        setQuestionLabel();
        stopwatch.startTiming();
    }

    private void updateFail(){
        multiplicationGame.downFailCount();
        if (multiplicationGame.getFailCount() == 2){
            fail_mark.setText("X X");
        }else if(multiplicationGame.getFailCount() == 1){
            fail_mark.setText("X");
        }else if (multiplicationGame.getFailCount() <= 0){
            fail_mark.setText("X X X");
            endGame();
        }
    }

    private void updateScore(int score){
        current_score.setText("Score: " + score);
    }

    private void resetGamePlay(){
        current_score.setText("Score: 0");
        multiplicationGame.setScore(0);
        multiplicationApp.clearAnsList();
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
        table_play.setText("Table "+ multiplication.getNumber());
        result_score.setText("Score: " + multiplicationGame.getScore());
        if(multiplicationApp.checkHighScore())
            result_highscore.setText("High Score: " + multiplicationGame.getScore());
        else
            result_highscore.setText("High Score: " + multiplicationApp.getPlayHighScore());
    }

    private void showHighScore(){

        ArrayList<Integer> highscore = multiplicationApp.getHighScoreList();
        table2_score.setText("High Score: "+ highscore.get(0));
        table3_score.setText("High Score: "+ highscore.get(1));
        table4_score.setText("High Score: "+ highscore.get(2));
        table5_score.setText("High Score: "+ highscore.get(3));
        table6_score.setText("High Score: "+ highscore.get(4));
        table7_score.setText("High Score: "+ highscore.get(5));
        table8_score.setText("High Score: "+ highscore.get(6));
        table9_score.setText("High Score: "+ highscore.get(7));
        table10_score.setText("High Score: "+ highscore.get(8));
        table11_score.setText("High Score: "+ highscore.get(9));
        table12_score.setText("High Score: "+ highscore.get(10));
    }

    private void endGame(){
        changeScreen(3);
        playTimer.stopTimer();
        setResult();
        multiplicationApp.saveHighScore();
        showHighScore();
        stopwatch.stopTiming();
        result_table.getChildren().clear();
        result_table.getChildren().addAll(resultFlowPaneArrayList);
    }

    private void updateResultTable(int multiplier, int question){
        stopwatch.stopTiming();
        resultFlowPaneArrayList.add(new ResultFlowPane(resultFlowPaneArrayList.size()+1,multiplication.getNumber(),multiplier,question, stopwatch.getUsedTime()));
    }


    @FXML
    void answerFour(ActionEvent event) {
        updateResultTable(multiplicationGame.getMultiplication().getMultiplier(),Integer.parseInt(answer4.getText()));
        if (multiplicationGame.checkMultiplicationAnswer(Integer.parseInt(answer4.getText())))
            updateScore(multiplicationGame.upScore());
        else
            updateFail();
        setupPlay(multiplication.getNumber());
    }

    @FXML
    void answerThree(ActionEvent event) {
        updateResultTable(multiplicationGame.getMultiplication().getMultiplier(),Integer.parseInt(answer3.getText()));
        if (multiplicationGame.checkMultiplicationAnswer(Integer.parseInt(answer3.getText())))
            updateScore(multiplicationGame.upScore());
        else
            updateFail();
        setupPlay(multiplication.getNumber());
    }

    @FXML
    void answerTwo(ActionEvent event) {
        updateResultTable(multiplicationGame.getMultiplication().getMultiplier(),Integer.parseInt(answer2.getText()));
        if (multiplicationGame.checkMultiplicationAnswer(Integer.parseInt(answer2.getText())))
            updateScore(multiplicationGame.upScore());
        else
            updateFail();
        setupPlay(multiplication.getNumber());
    }

    @FXML
    void asnwerOne(ActionEvent event) {
        updateResultTable(multiplicationGame.getMultiplication().getMultiplier(),Integer.parseInt(answer1.getText()));
        if (multiplicationGame.checkMultiplicationAnswer(Integer.parseInt(answer1.getText())))
            updateScore(multiplicationGame.upScore());
        else
            updateFail();
        setupPlay(multiplication.getNumber());
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
        readyStep(multiplication.getNumber());
    }

}
