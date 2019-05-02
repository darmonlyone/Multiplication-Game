package controller.sub;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ResultFlowPane extends Pane {

    private int questionNo;
    private int playNumber;
    private int question;
    private int answered;
    private double resultTime;

    public ResultFlowPane(int questionNo, int playNumber, int question, int answered, double resultTime) {
        this.questionNo = questionNo;
        this.question = question;
        this.answered = answered;
        this.resultTime = resultTime;
        this.playNumber = playNumber;

        this.setPrefSize(485,56);
        setUpChildren();
    }

    private void setUpChildren() {
        Label questionNoLabel = new Label("Question: "+ questionNo);
        Label questionLabel = new Label(playNumber +" x "+ question + " = "+ playNumber * question);
        Label answerLabel = new Label("Answer:");
        Label answeredLabel = new Label(Integer.toString(answered));
        Label timeLabel = new Label("Time:");
        Label resultTimeLabel = new Label(String.format("%.3f sec",resultTime));

        Font font = new Font(14);

        setLabelFont(questionNoLabel, font);
        setLabelFont(questionLabel,font);
        if (question * playNumber != answered)
            answeredLabel.setTextFill(Color.RED);
        else
            answeredLabel.setTextFill(Color.GREEN);
        setLabelFont(answeredLabel,font);
        setLabelFont(answerLabel,font);
        setLabelFont(timeLabel,font);
        setLabelFont(resultTimeLabel,font);
        questionNoLabel.setPrefSize(80,17);
        questionLabel.setPrefSize(96,17);
        answerLabel.setPrefWidth(55);
        answeredLabel.setPrefSize(30,17);
        timeLabel.setPrefWidth(34);
        resultTimeLabel.setPrefSize(73,17);

        setNodePosition(questionNoLabel,35,20);
        setNodePosition(questionLabel,115,20);
        setNodePosition(answerLabel,225,20);
        setNodePosition(answeredLabel,292,20);
        setNodePosition(timeLabel,337,20);
        setNodePosition(resultTimeLabel,387,20);

        this.getChildren().addAll(questionNoLabel,questionLabel,answerLabel,answeredLabel,timeLabel,resultTimeLabel);
    }


    private void setLabelFont(Label label, Font font) {
        label.setFont(font);
    }

    private void setNodePosition(Node label, int x, int y){
        label.setLayoutX(x);
        label.setLayoutY(y);
    }


}
