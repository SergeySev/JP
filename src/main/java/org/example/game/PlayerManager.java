package org.example.game;

import com.github.javafaker.Faker;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PlayerManager {
    private List<Player> playersFirst = new ArrayList<>();
    private List<Player> playersSecond = new ArrayList<>();
    private List<Player> playersGold = new ArrayList<>();

    protected List<Player> makeTeam(Lig lig) {
        Random random = new Random();
        Faker faker = new Faker();
        for (int i = 0; i < random.nextInt(25, 37); i++) {
            switch (lig) {
                case GOLD -> this.playersGold.add(new Player(faker.name().lastName(), random.nextInt(35,40), UUID.randomUUID(), Lig.GOLD));
                case FIRST -> this.playersFirst.add(new Player(faker.name().lastName(), random.nextInt(15,18), UUID.randomUUID(), Lig.FIRST));
                case SECOND -> this.playersSecond.add(new Player(faker.name().lastName(), random.nextInt(18,35), UUID.randomUUID(), Lig.SECOND));
            }
        }
        return switch (lig) {
            case FIRST -> getPlayersFirst();
            case GOLD -> getPlayersGold();
            case SECOND -> getPlayersSecond();
        };
    }

    protected void playerUp (Player player) {
        switch (player.getLig()) {
            case FIRST -> {
                player.setLig(Lig.SECOND);
                System.out.println("lig up: " + player);
                playersSecond.add(player);
                playersFirst.remove(player);
            }
            case SECOND -> {
                player.setLig(Lig.GOLD);
                System.out.println("lig up: " + player);
                playersGold.add(player);
                playersSecond.remove(player);
            }
        }
    }

    protected void playersZeroPoint(List<Player> list){
        for (Player player :list) {
            player.setMatchPoint(0);
        }
    }

    protected void playerDown (Player player) {
        switch (player.getLig()) {
            case GOLD -> {
                player.setLig(Lig.SECOND);
                System.out.println("lig down: " + player);
                playersGold.remove(player);
                playersSecond.add(player);
            }
            case SECOND -> {
                player.setLig(Lig.FIRST);
                System.out.println("lig down: " + player);
                playersSecond.remove(player);
                playersFirst.add(player);
            }
        }
    }

    public List<Player> getPlayersFirst() {
        return playersFirst;
    }

    public List<Player> getPlayersSecond() {
        return playersSecond;
    }

    public List<Player> getPlayersGold() {
        return playersGold;
    }

}
