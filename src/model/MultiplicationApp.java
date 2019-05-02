package model;

import java.util.ArrayList;
import java.util.Random;

public class MultiplicationApp {

    private Random random = new Random();
    private MultiplicationGame multiplicationGame;
    private Multiplication multiplication;
    private ArrayList<Integer> ansList;
    private MultiplicationScoreBoard scoreBoard;
    private int maxMultiplier;
    private int playHighScore;
    private int ans1;
    private int ans2;
    private int ans3;
    private int ans4;

    public MultiplicationApp(int maxMultiplier, String highScorePath){
        this.maxMultiplier = maxMultiplier;
        this.ansList = new ArrayList<>();
        this.scoreBoard = new MultiplicationScoreBoard(highScorePath, maxMultiplier);
    }

    public MultiplicationScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public boolean checkHighScore(){
        playHighScore = (scoreBoard.getHighScore(multiplication.getNumber()-2));
        if (multiplicationGame.getScore() > playHighScore){
            scoreBoard.updateHighScore(multiplication.getNumber()-2,multiplicationGame.getScore());
            return true;
        }
        return false;
    }

    public int getMaxMultiplier() {
        return maxMultiplier;
    }

    public void randomMultiplier(){
        multiplication.setMultiplier(random.nextInt(maxMultiplier) + 1);
    }

    public void setMultiplicationGame(MultiplicationGame multiplicationGame) {
        this.multiplicationGame = multiplicationGame;
    }

    public int makeMultiplication(){
        randomMultiplier();
        if (ansList.size() >= (getMaxMultiplier() * 2))
            multiplication.setMultiplier(multiplication.getMultiplier() + (getMaxMultiplier()*2));
        else if (ansList.size() >= getMaxMultiplier())
            multiplication.setMultiplier(multiplication.getMultiplier() + (getMaxMultiplier()));

        return multiplication.getAnswer();
    }

    public int getPlayHighScore() {
        return playHighScore;
    }

    public void startPlay(int playNumber){
        multiplication = multiplicationGame.getMultiplication();
        multiplication.setNumber(playNumber);

        int maxMulti = getMaxMultiplier();
        int multiplier = multiplication.getMultiplier();
        int number = multiplication.getNumber();
        int answered = makeMultiplication();

        while (ansList.contains(answered)){
            if (ansList.size() >= getMaxMultiplier()*3)
                ansList.clear();
            answered = makeMultiplication();
        }
        ansList.add(answered);
        int randAnsPoint = random.nextInt(4);
        ArrayList<Integer> randAnsList = new ArrayList<>();
        for (int i = 0; i < 4 ; i++) {
            int bound = number * (multiplier >= maxMulti * 2 ? maxMulti * 3 : multiplier > maxMulti ? maxMulti * 2 : maxMulti);
            int randAns = random.nextInt(bound)+1;
            if (number % 2 == 0 && randAns % 2 != 0)
                randAns += 1;
            while (randAnsList.contains(randAns) || randAns == answered){
                randAns = random.nextInt(bound)+1;
                if (number % 2 == 0 && randAns % 2 != 0)
                    randAns += 1;
            }
            randAnsList.add(randAns);
            if (i == randAnsPoint){
                setSelectAnswer(i,answered);
            }else {
                setSelectAnswer(i,randAns);
            }
        }
    }

    public void setSelectAnswer(int i, int answer){
        if (i == 0)
            ans1 = answer;
        if (i == 1)
            ans2 = answer;
        if (i == 2)
            ans3 = answer;
        if (i == 3)
            ans4 = answer;
    }

    public int[] getAns() {
        return new int[]{ans1, ans2, ans3, ans4};
    }

    public ArrayList<Integer> getAnsList() {
        return ansList;
    }
    public void resetGame(){
        multiplicationGame.resetFailCount();
        multiplicationGame.setGameOver(false);
    }
    public void clearAnsList(){
        ansList.clear();
    }
    public void saveHighScore(){
        scoreBoard.saveScoreBoard();
    }
    public ArrayList<Integer> getHighScoreList(){
        scoreBoard.setUpHighScoreList();
        return scoreBoard.getHighScoreList();
    }
}
