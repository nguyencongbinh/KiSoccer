package com.hnib.kisoccer.model;

/**
 * Created by caucukien on 24/11/2015.
 */
public class Result {
    private String goalsHomeTeam;
    private String goalsAwayTeam;
    private HalfTime halfTime;

    public HalfTime getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(HalfTime halfTime) {
        this.halfTime = halfTime;
    }

    public String getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(String goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public String getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(String goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    public static class HalfTime {
        private String goalsHomeTeam;
        private String goalsAwayTeam;
        public String getGoalsHomeTeam() {
            return goalsHomeTeam;
        }

        public void setGoalsHomeTeam(String goalsHomeTeam) {
            this.goalsHomeTeam = goalsHomeTeam;
        }

        public String getGoalsAwayTeam() {
            return goalsAwayTeam;
        }

        public void setGoalsAwayTeam(String goalsAwayTeam) {
            this.goalsAwayTeam = goalsAwayTeam;
        }
    }
}
