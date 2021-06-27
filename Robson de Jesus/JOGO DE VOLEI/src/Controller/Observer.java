/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.List;

/**
 *
 * @author Robson de Jesus
 */
public interface Observer {

    void resetGame();

    void refreshScore(int x, int y);

    void updateList();

    public void wonSet(int i, int set, String[] list);

    public void finishGame(int i);

}
