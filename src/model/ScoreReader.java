package model;

import java.io.*;

public class ScoreReader {
    private String[] highScore;

    public ScoreReader(){
        highScore = new String[11];
    }

    public void readScore(String url) throws IOException {
        File file = new File(url);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String score;
        int index = 0;
        while ((score = reader.readLine()) != null){
            String[] score_split = score.split(": ");
            highScore[index] = score_split[1];
            index++;
        }
    }

    public void writeScore(String url) throws IOException {
        File file = new File(url);
        if(file.exists()) {
            file.delete();
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (int i = 1;i <= highScore.length;i++){
            writer.write(String.format("Table_%d: %s\n",i+1,highScore[i-1]));
        }
        writer.close();
    }



    public String getHighScore(int index) {
        return highScore[index];
    }

    public String[] getHighScoreList(){
        return highScore;
    }

    public void setHighScore(int index,int score){
        highScore[index] = score+"";
    }
}

