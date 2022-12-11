package org.example.gameMap;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PlayerManager2 {
    private Map<League, ArrayList<PlayerVersion2>> dbPlayers = new HashMap<>();

    protected void addPlayers(League league) {
        if (!this.dbPlayers.containsKey(league)) {
            Random random = new Random();
            Faker faker = new Faker();
            int origin = 0;
            int bound = 0;

            switch (league) {
                case FIRST -> {
                    origin = 15;
                    bound = 25;
                }
                case SECOND -> {
                    origin = 25;
                    bound = 35;
                }
                case PRIME -> {
                    origin = 35;
                    bound = 45;
                }
            }

            ArrayList<PlayerVersion2> arrayList = new ArrayList<>();
            for (int i = 0; i < random.nextInt(25, 37); i++) {
                arrayList.add(new PlayerVersion2(faker.name().lastName(), random.nextInt(origin, bound), league));
            }
            this.dbPlayers.put(league, arrayList);
        }
    }
    protected void makeTeam() {
        addPlayers(League.FIRST);
        addPlayers(League.SECOND);
        addPlayers(League.PRIME);
    }

    protected void playerUp(PlayerVersion2 playerVersion2) {
        switch (playerVersion2.getLeague()) {
            case FIRST -> {
                playerVersion2.setLeague(League.SECOND);
                System.out.println("Lig up: " + playerVersion2);
                this.dbPlayers.get(League.FIRST).remove(playerVersion2);
                this.dbPlayers.get(League.SECOND).add(playerVersion2);
            }
            case SECOND -> {
                playerVersion2.setLeague(League.PRIME);
                System.out.println("Lig up: " + playerVersion2);
                this.dbPlayers.get(League.SECOND).remove(playerVersion2);
                this.dbPlayers.get(League.PRIME).add(playerVersion2);
            }
        }
    }
    protected void playerDown(PlayerVersion2 playerVersion2) {
        switch (playerVersion2.getLeague()) {
            case PRIME -> {
                playerVersion2.setLeague(League.SECOND);
                System.out.println("Lig down: " + playerVersion2);
                this.dbPlayers.get(League.PRIME).remove(playerVersion2);
                this.dbPlayers.get(League.SECOND).add(playerVersion2);
            }
            case SECOND -> {
                playerVersion2.setLeague(League.FIRST);
                System.out.println("Lig down: " + playerVersion2);
                this.dbPlayers.get(League.SECOND).remove(playerVersion2);
                this.dbPlayers.get(League.FIRST).add(playerVersion2);
            }
        }
    }
    protected void zeroPointPlayers(ArrayList<PlayerVersion2> list) {
        for (PlayerVersion2 player :
                list) {
            player.setMatchPoint(0);
        }
    }

    public Map<League, ArrayList<PlayerVersion2>> getDbPlayers() {
        return dbPlayers;
    }
}
