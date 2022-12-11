package org.example.gameMap;

import lombok.ToString;
import org.jetbrains.annotations.NotNull;
@ToString
public class PlayerVersion2 implements Comparable<PlayerVersion2> {
    private String name;
    private int age;
    private League league;
    private int matchPoint;
    private int allGamePoint;

    public PlayerVersion2(String name, int age, League league) {
        this.name = name;
        this.age = age;
        this.league = league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public void setMatchPoint(int matchPoint) {
        this.matchPoint = matchPoint;
    }

    public void setAllGamePoint(int allGamePoint) {
        this.allGamePoint = allGamePoint;
    }

    public int getAge() {
        return age;
    }

    public League getLeague() {
        return league;
    }

    public int getMatchPoint() {
        return matchPoint;
    }

    public int getAllGamePoint() {
        return allGamePoint;
    }

    @Override
    public int compareTo(@NotNull PlayerVersion2 o) {
        return this.getMatchPoint() - o.getMatchPoint();
    }
}
