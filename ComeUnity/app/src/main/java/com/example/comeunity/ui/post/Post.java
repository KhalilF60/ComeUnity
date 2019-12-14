package com.example.comeunity.ui.post;

public class Post {
   private  String userID;
   String title;
    private String Date;
    private String startTime;
    private String endTime;
    private String eventType;
    private String lat;
    private String lon;
    private String locationName;
    private String information;

    public Post () {

    }

    /**
     *
     * @param userID
     * @param Date
     * @param startTime
     * @param endTime
     * @param eventType
     * @param lon
     * @param lat
     * @param information
     */
    public Post (String userID, String Date, String startTime, String endTime,String title, String eventType, String lon, String lat,String locationName, String information){
        this.userID = userID;
        this.Date = Date;
        this.locationName = locationName;
        this.eventType = eventType;
        this.information = information;
        this.lat = lat;
        this.lon = lon;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
