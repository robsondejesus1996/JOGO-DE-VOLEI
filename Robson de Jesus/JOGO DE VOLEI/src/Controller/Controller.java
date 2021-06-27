package Controller;

import Model.Equipe;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Robson de Jesus
 */
public class Controller {

    private static Controller instance = null;

    public static Controller getIntance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
        team1 = new Equipe();
        team2 = new Equipe();
    }

    private int set = 1;
    private String[] list = new String[5];
    private Equipe team1;
    private Equipe team2;

    private int getSet() {
        return set;
    }

    private void addSet() {
        this.set++;
    }

    //Aumenta a pontuação
    public void addScore(int i) {
        switch (i) {
            case 1:
                team1.addScore();
                break;
            case 2:
                team2.addScore();
                break;
        }
        if (this.set < 5) {
            verifyScore(25, 30);
        } else {
            verifyScore(15, 15);
        }
        notifyRefreshScore();

        verifyWonGame();
    }

    //Reduz a pontuação
    public void subScore(int i) {
        switch (i) {
            case 1:
                team1.subScore();
                break;
            case 2:
                team2.subScore();
                break;
        }

        notifyRefreshScore();

    }

    /*
    *   Verifica se a pontuação é suficiente para ganhar um set
    *
     */
    private void verifyScore(int min, int max) {

        if (team1.getScore() >= min && team1.getScore() > team2.getScore() + 1) {
            team1.wonSet();
            saveData(team1.getScore(), team2.getScore());
            notifyWonSet(1);
            return;
        }

        if (team2.getScore() >= min && team2.getScore() > team1.getScore() + 1) {
            team2.wonSet();
            saveData(team1.getScore(), team2.getScore());
            notifyWonSet(2);
            return;
        }

        if (team1.getScore() == max) {
            team1.wonSet();
            saveData(team1.getScore(), team2.getScore());
            notifyWonSet(1);
            return;
        }

        if (team2.getScore() == max) {
            team2.wonSet();
            saveData(team1.getScore(), team2.getScore());
            notifyWonSet(2);
            return;
        }

    }

    //Verifica se se o time venceu
    private void verifyWonGame() {
        if (team1.getSetsWons() == 3) {
            notifyWhoWon(1);
            return;
        }
        if (team2.getSetsWons() == 3) {
            notifyWhoWon(2);
            return;
        }

    }

    //Salva o placar do set
    private void saveData(int score1, int score2) {
        list[getSet() - 1] = "Set " + getSet() + " -> Time 1 | " + score1 + " X " + score2 + " | Time 2";
    }

    //Reseta todo o placar
    public void resetGame() {
        team1 = new Equipe();
        team2 = new Equipe();
        list = new String[5];

        notifyRefreshScoreboard();

    }

    private List<Observer> addGameScoreObserver = new ArrayList<>();

    public void attach(Observer obs) {
        this.addGameScoreObserver.add(obs);
    }

    public void detach(Observer obs) {
        this.addGameScoreObserver.remove(obs);
    }

    private void notifyRefreshScore() {
        for (Observer GameScore : addGameScoreObserver) {
            GameScore.refreshScore(team1.getScore(), team2.getScore());
        }
    }

    private void notifyWonSet(int i) {
        addSet();
        team1.setScore(0);
        team2.setScore(0);
        for (Observer GameScore : addGameScoreObserver) {
            GameScore.wonSet(i, getSet(), list);
        }
    }

    private void notifyRefreshScoreboard() {
        for (Observer GameScore : addGameScoreObserver) {
            GameScore.resetGame();
        }

    }

    private void notifyWhoWon(int i) {
        for (Observer GameScore : addGameScoreObserver) {
            GameScore.finishGame(i);
        }

    }

}
