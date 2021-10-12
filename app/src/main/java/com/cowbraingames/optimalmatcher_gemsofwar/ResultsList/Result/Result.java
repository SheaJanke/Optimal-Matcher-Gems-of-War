package com.cowbraingames.optimalmatcher_gemsofwar.ResultsList.Result;

import java.util.ArrayList;
import java.util.Collections;

public class Result {
    static final int ORB_TYPES = 17;
    private final int[] matchedOrbs;
    private final int[][] finalBoard;
    private boolean extraTurn;
    private boolean invalidFinalBoard;
    public final int r1, c1, r2, c2;


    public Result(int r1, int c1, int r2, int c2, int[][] finalBoard){
        matchedOrbs = new int[ORB_TYPES];
        this.finalBoard = finalBoard;
        extraTurn = false;
        invalidFinalBoard = false;
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
    }

    public void addMatched(int orbIndex){
        matchedOrbs[orbIndex]++;
    }

    public int totalMatched(){
        int total = 0;
        for(int i = 0; i < ORB_TYPES; i++){
            total += matchedOrbs[i];
        }
        return total;
    }


    public int[][] getFinalBoard(){
        return finalBoard;
    }

    public void setExtraTurn(boolean extraTurn){
        this.extraTurn = extraTurn;
    }

    public boolean getExtraTurn(){
        return extraTurn;
    }

    public void setInvalidFinalBoard(boolean invalidFinalBoard) {
        this.invalidFinalBoard = invalidFinalBoard;
    }

    public boolean getInvalidFinalBoard() {
        return invalidFinalBoard;
    }

    public ArrayList<ResultPair> getDisplayResults(){
        ArrayList<ResultPair> displayResults = new ArrayList<>();
        for(int i = 0; i < ORB_TYPES; i++){
            if(matchedOrbs[i] > 0){
                displayResults.add(new ResultPair(matchedOrbs[i], i));
            }
        }
        displayResults.sort((r1, r2) -> {
            if (r1.numOrbs != r2.numOrbs) {
                return r2.numOrbs - r1.numOrbs;
            } else {
                return r1.orbType - r2.orbType;
            }
        });
        return displayResults;
    }

}
