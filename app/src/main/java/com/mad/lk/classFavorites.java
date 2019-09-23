package com.mad.lk;

public class classFavorites {

    private String NAME;
    private String IMAGE;
    private String DESCRIPTION;
    private String RANKINGS;
    private String DATE;
    private String TIME;
    private String SEATS;
    private String NOTES;
    private String USERNMAE;



    public classFavorites(String NAME, String IMAGE, String DESCRIPTION, String RANKINGS, String DATE, String TIME, String SEATS, String NOTES, String USERNMAE) {
        this.NAME = NAME;
        this.IMAGE = IMAGE;
        this.DESCRIPTION = DESCRIPTION;
        this.RANKINGS = RANKINGS;
        this.DATE = DATE;
        this.TIME = TIME;
        this.SEATS = SEATS;
        this.NOTES = NOTES;
        this.USERNMAE = USERNMAE;
    }

    public classFavorites(){

    }
    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getRANKINGS() {
        return RANKINGS;
    }

    public void setRANKINGS(String RANKINGS) {
        this.RANKINGS = RANKINGS;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getSEATS() {
        return SEATS;
    }

    public void setSEATS(String SEATS) {
        this.SEATS = SEATS;
    }

    public String getUSERNMAE() {
        return USERNMAE;
    }

    public void setUSERNMAE(String USERNMAE) {
        this.USERNMAE = USERNMAE;
    }

    public String getNOTES() {
        return NOTES;
    }

    public void setNOTES(String NOTES) {
        this.NOTES = NOTES;
    }


}
