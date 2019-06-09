package com.votechandnichowk;

public class ResultJava {

    private String candidateImage;
    private String name;
    private String partyName;
    private String votes;

    public ResultJava(String candidateImage, String name, String partyName, String votes) {
        this.candidateImage = candidateImage;
        this.name = name;
        this.partyName = partyName;
        this.votes = votes;
    }

    public String getCandidateImage() {
        return candidateImage;
    }

    public String getName() {
        return name;
    }

    public String getPartyName() {
        return partyName;
    }

    public String getVotes() {
        return votes;
    }
}
