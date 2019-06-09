package com.votechandnichowk;

import android.graphics.Bitmap;

public class Candidates {

    private String candidateImage;
    private String name;
    private String party;

    public Candidates(String candidateImage, String name, String party) {
        this.candidateImage = candidateImage;
        this.name = name;
        this.party = party;
    }

    public String getCandidateImage() { return candidateImage; }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }
}

