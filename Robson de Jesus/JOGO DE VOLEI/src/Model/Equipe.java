/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Robson de Jesus
 */
public class Equipe {

    private int Score;

    private int SetsWons;

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public void addScore() {
        this.Score++;
    }

    public void subScore() {
        if (this.Score > 0) {
            this.Score--;
        }
    }

    public int getSetsWons() {
        return SetsWons;
    }

    public void wonSet() {
        SetsWons++;
    }

}
