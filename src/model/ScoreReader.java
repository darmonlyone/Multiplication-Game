package model;

import java.io.*;
import java.util.ArrayList;

public class ScoreReader {
    private String[] highScore;
    private String url;

    public ScoreReader(String url){
        highScore = new String[11];
        this.url = url;
    }

    public void readScore() throws IOException {
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

    public void writeScore(ArrayList<Integer> scoreList) throws IOException {
        File file = new File(url);
        if(file.exists()) {
            file.delete();
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (int i = 1;i <= scoreList.size();i++){
            writer.write(String.format("Table_%d: %s\n",i+1,scoreList.get(i-1)));
        }
        writer.close();
    }

    public String[] getHighScoreList(){
        return highScore;
    }
}

