package org.example.gameMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Game2 {
    static PlayerManager2 playerManager2 = new PlayerManager2();

    Random random = new Random();

    public void gameVersion2(League league) {
        System.out.println("\n" + league + " league play!");
        match(playerManager2.getDbPlayers().get(league));
        Collections.sort(playerManager2.getDbPlayers().get(league));
        updateTeam(playerManager2.getDbPlayers().get(league));

        playerManager2.zeroPointPlayers(playerManager2.getDbPlayers().get(League.FIRST));
        playerManager2.zeroPointPlayers(playerManager2.getDbPlayers().get(League.SECOND));
        playerManager2.zeroPointPlayers(playerManager2.getDbPlayers().get(League.PRIME));

        if (league.equals(League.PRIME)) {
            Collections.sort(playerManager2.getDbPlayers().get(league), new Comparator<PlayerVersion2>() {
                @Override
                public int compare(PlayerVersion2 o1, PlayerVersion2 o2) {
                    return o1.getAllGamePoint() - o2.getAllGamePoint();
                }
            });
            System.out.println("\nOur three winners:");
            for (int i = 0; i < 3; i++) {
                System.out.println(playerManager2.getDbPlayers().get(league).get((playerManager2.getDbPlayers().get(league).size() - 1) - i));
            }
        }
    }

    public void match(ArrayList<PlayerVersion2> players) {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                matchRound(players.get(i), players.get(j));
            }
        }
    }

    public void matchRound(PlayerVersion2 player1, PlayerVersion2 player2) {
        switch ((random.nextInt(1, 3))) {
            case 1 -> {
                player1.setMatchPoint(player1.getMatchPoint() + 1);
                player1.setAllGamePoint(player1.getAllGamePoint() + 1);
            }
            case 2 -> {
                player2.setMatchPoint(player2.getMatchPoint() + 1);
                player2.setAllGamePoint(player2.getAllGamePoint() + 1);
            }
        }
    }

    public void updateTeam(ArrayList<PlayerVersion2> players) {
        for (int i = 0; i < 3; i++) {
            playerManager2.playerUp(players.get(players.size() - 1));
            playerManager2.playerDown(players.get(0));
        }
    }

    public static void main(String[] args) {
        Game2 game2 = new Game2();
        Game2.playerManager2.makeTeam();
        game2.gameVersion2(League.FIRST);
        game2.gameVersion2(League.SECOND);
        game2.gameVersion2(League.PRIME);
    }


}
