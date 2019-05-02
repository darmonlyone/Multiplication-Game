package model;

import java.util.Random;

public class MultiplicationGame {
    private int failCount;
    private int failAmount;
    private int score;
    private int answers;
    private boolean gameOver;
    private Multiplication multiplication;

    public MultiplicationGame(int failCount, int score) {
        this.failAmount = failCount;
        this.failCount = this.failAmount;
        this.score = score;
        this.multiplication = new Multiplication();
        gameOver = false;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public int getAnswers() {
        return answers;
    }

    public boolean checkMultiplicationAnswer(int answers){
        return answers == multiplication.getAnswer();
    }

    public void resetFailCount(){
        this.failCount = failAmount;
    }
    public boolean checkMultiplicationAnswer(){
        return answers == multiplication.getAnswer();
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int upScore(){
        return ++this.score;
    }

    public void downFailCount(){
        failCount--;
    }

    public int getFailCount() {
        return failCount;
    }

    public Multiplication getMultiplication() {
        return multiplication;
    }

    public int getScore() {
        return score;
    }


    public boolean isGameOver(){
        return failCount <= 0 || gameOver;
    }
}
