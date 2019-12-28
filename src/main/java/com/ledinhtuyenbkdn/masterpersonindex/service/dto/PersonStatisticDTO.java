package com.ledinhtuyenbkdn.masterpersonindex.service.dto;

public class PersonStatisticDTO {

    private Long newMasterPerson;

    private Long needReview;

    private Long manualMatch;

    private Long autoMatch;

    private Long fastMatch;

    public Long getNewMasterPerson() {
        return newMasterPerson;
    }

    public void setNewMasterPerson(Long newMasterPerson) {
        this.newMasterPerson = newMasterPerson;
    }

    public Long getNeedReview() {
        return needReview;
    }

    public void setNeedReview(Long needReview) {
        this.needReview = needReview;
    }

    public Long getManualMatch() {
        return manualMatch;
    }

    public void setManualMatch(Long manualMatch) {
        this.manualMatch = manualMatch;
    }

    public Long getAutoMatch() {
        return autoMatch;
    }

    public void setAutoMatch(Long autoMatch) {
        this.autoMatch = autoMatch;
    }

    public Long getFastMatch() {
        return fastMatch;
    }

    public void setFastMatch(Long fastMatch) {
        this.fastMatch = fastMatch;
    }

    @Override
    public String toString() {
        return "PersonStatisticDTO{" +
                "newMasterPerson=" + newMasterPerson +
                ", needReview=" + needReview +
                ", manualMatch=" + manualMatch +
                ", autoMatch=" + autoMatch +
                ", fastMatch=" + fastMatch +
                '}';
    }
}