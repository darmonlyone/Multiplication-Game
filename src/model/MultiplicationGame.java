package model;

import java.util.Random;

public class MultiplicationGame {
    private int failCount;
    private int failAmount;
    private int score;
    private int maxMultiplier;
    private int answers;
    private Multiplication multiplication;
    private Random random = new Random();

    public MultiplicationGame(int failCount, int score, int maxMultiplier) {
        this.failAmount = failCount;
        this.failCount = this.failAmount;
        this.score = score;
        this.maxMultiplier =  maxMultiplier;
        this.multiplication = new Multiplication();
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

    public int getMaxMultiplier() {
        return maxMultiplier;
    }

    public void randomMultiplier(){
        multiplication.setMultiplier(random.nextInt(maxMultiplier) + 1);
    }
}
