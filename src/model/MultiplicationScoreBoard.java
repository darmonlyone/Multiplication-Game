package model;

import java.io.IOException;
import java.util.ArrayList;

public class MultiplicationScoreBoard{
    private ArrayList<Integer> highScoreList;
    private String highScorePath;
    private int maxMulti;
    private ScoreReader scoreReader;

    public MultiplicationScoreBoard(String highScorePath, int maxMulti){
        this.highScorePath = highScorePath;
        this.maxMulti = maxMulti;
        this.scoreReader = new ScoreReader(highScorePath);
        this.highScoreList = new ArrayList<>();
    }

    public void setUpHighScoreList(){
        highScoreList.clear();
        try {
            scoreReader.readScore();
            String[] scoreStrings = scoreReader.getHighScoreList();
            for (int i = 0; i < scoreStrings.length; i++) {
                highScoreList.add(Integer.parseInt(scoreStrings[i]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveScoreBoard(){
        try {
            scoreReader.writeScore(highScoreList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getHighScoreList() {
        return highScoreList;
    }

    public int getHighScore(int index) {
        return highScoreList.get(index);
    }

    public void updateHighScore(int index,int score){
        highScoreList.set(index,score);
    }

}

