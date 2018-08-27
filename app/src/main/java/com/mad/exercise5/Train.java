package com.mad.exercise5;

public class Train {

    private String mPlatform;
    private Integer mArrivalTime;
    private String mStatus;
    private String mDestination;
    private String mDestinationTime;

    public Train(String platform, int arrivalTime, String status, String destination, String destinationTime) {
        mPlatform = platform;
        mArrivalTime = arrivalTime;
        mStatus = status;
        mDestination = destination;
        mDestinationTime = destinationTime;
    }

    public String getPlatform() {
        return mPlatform;
    }

    public void setPlatform(String platform) {
        mPlatform = platform;
    }

    public String getArrivalTime() {
        return mArrivalTime.toString();
    }

    public void setArrivalTime(int arrivalTime) {
        mArrivalTime = arrivalTime;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getDestination() {
        return mDestination;
    }

    public void setDestination(String destination) {
        mDestination = destination;
    }

    public String getDestinationTime() {
        return mDestinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        mDestinationTime = destinationTime;
    }
}
