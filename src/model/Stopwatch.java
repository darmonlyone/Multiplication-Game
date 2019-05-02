package model;

public class Stopwatch {


    private long startTime;
    private long stopTime;
    private boolean isStart;

    public Stopwatch(){
        startTime = 0;
        stopTime = 0;
        isStart = false;
    }

    public void startTiming(){
        startTime = (long) System.currentTimeMillis();
        isStart = true;
    }


    public void stopTiming(){
        if (isStart)
            stopTime = (long) System.currentTimeMillis();
        isStart = false;
    }
    public long getStartTime() {
        return startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public double getUsedTime() {
        return (stopTime - startTime)*0.001;
    }
}
