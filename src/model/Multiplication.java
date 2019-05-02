package model;

public class Multiplication {
    private int number;
    private int multiplier;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getAnswer(){
        return number* multiplier;
    }
}
