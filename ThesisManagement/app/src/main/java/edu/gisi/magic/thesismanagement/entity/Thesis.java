package edu.gisi.magic.thesismanagement.entity;

import java.util.List;

/**
 * Created by AlexXu on 2016/12/11.
 */

public class Thesis {

    private List<ThesisResult> thesisResult;

    public List<ThesisResult> getThesisResult() {
        return thesisResult;
    }

    public void setThesisResult(List<ThesisResult> thesisResult) {
        this.thesisResult = thesisResult;
    }
}
