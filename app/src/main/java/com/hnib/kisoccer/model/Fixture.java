package com.hnib.kisoccer.model;

/**
 * Created by caucukien on 24/11/2015.
 */
public class Fixture {

    private String date;
    private String status;
    private int matchDay;
    private String homeTeamName;
    private String awayTeamName;
    private Result result;
    private Links _links;

    public Links getLinks() {
        return _links;
    }

    public void setLinks(Links links) {
        this._links = links;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public int getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(int matchDay) {
        this.matchDay = matchDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


