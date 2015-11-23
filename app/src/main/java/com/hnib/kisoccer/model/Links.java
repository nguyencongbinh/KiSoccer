package com.hnib.kisoccer.model;

/**
 * Created by caucukien on 24/11/2015.
 */
public class Links {
    private Self self;
    private SoccerSeason soccerseason;
    private HomeTeam homeTeam;
    private AwayTeam awayTeam;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public SoccerSeason getSoccerSeason() {
        return soccerseason;
    }

    public void setSoccerSeason(SoccerSeason soccerSeason) {
        this.soccerseason = soccerSeason;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }
}

class Self {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

class SoccerSeason {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

class HomeTeam {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

class AwayTeam {
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
