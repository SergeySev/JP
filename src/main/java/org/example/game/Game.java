package org.example.game;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Game games = new Game();
        for (int i = 0; i < 5; i++) {
            games.game();
        }
    }

    PlayerManager playerManager = new PlayerManager();

    public void game() {
        System.out.println("\nFirst lig - first play");

        List<Player> firstLig = playerManager.makeTeam(Lig.FIRST);
        List<Player> secondLig = playerManager.makeTeam(Lig.SECOND);
        List<Player> goldLig = playerManager.makeTeam(Lig.GOLD);

        match(firstLig);

        Collections.sort(firstLig);

        updateTeam(firstLig);

        playerManager.playersZeroPoint(firstLig);
        playerManager.playersZeroPoint(secondLig);
        System.out.println("\nSecond lig - second play");
        match(secondLig);

        Collections.sort(secondLig);

        updateTeam(secondLig);

        playerManager.playersZeroPoint(firstLig);
        playerManager.playersZeroPoint(secondLig);
        playerManager.playersZeroPoint(goldLig);
        System.out.println("\nGold lig - third play");
        match(goldLig);

        Collections.sort(goldLig);

        updateTeam(goldLig);
        Collections.sort(goldLig, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getAllGamePoint() - o2.getAllGamePoint();
            }
        });
        System.out.println("\nOur third winners!");
        for (int i = 0; i < 3; i++) {
            System.out.println(goldLig.get((goldLig.size()-1) - i));
        }

    }

    public void match(List<Player> players) {
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                matchRound(players.get(i), players.get(j));
            }
        }
    }

    public void updateTeam(List<Player> players) {
        for (int i = 0; i < 3; i++) {
            playerManager.playerUp(players.get((players.size() - 1)));
            playerManager.playerDown(players.get(0));
        }
    }

    public void matchRound(Player player1, Player player2) {
        Random random = new Random();
        switch (random.nextInt(1, 3)) {
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
}
