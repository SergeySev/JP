package org.example.game;

import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.UUID;

/*
У нас есть класс игроки✅
У игроков есть имена и возраст✅
Есть Энам который хранит лиги✅
Есть класс который хранит в листе всех игроков✅
Есть класс который проводит игру среди игроков одной лиги✅
Каждый игрок в каждой лиге должен сыграть 25 раз со своей же лигой
Есть метод который считает очки у каждого игрок✅
Вам необходимо написать все это
+ метод который будет брать из нашего листа игроков✅
и проводить с ними игру в одной лиге так что бы все сыграли одинаковое количество раз.✅
После того как отыграют все игроки в каждой лиге,
надо найти трех дидеров в каждой лиге и✅
если ЕСТЬ ВОЗМОЖНОСТЬ ПЕРЕМЕСТИТЬ ЛИДЕР В ЛИГУ ВЫШЕ СДЕЛАТЬ ЭТО ЕСЛИ НЕТ ТО НЕ НАДО.✅
Найти трех лузеров и если есть возможносмть переместить в лигу ниже то сделать это.✅
Прогнать такую игру 5 раз и вывести на экран✅

Тех кто заработал больше всего очков✅

Тех кто перешел в лигу выше✅

Тех кто перешел в лигу ниже✅

В каждой лиге минимум 25 человек✅
Сделать класс который генерирует Участников - использовать faker библиотеку✅
Лиг минимум 3✅
 */
@ToString
public class Player implements Comparable<Player> {
    private String name;
    private int age;
    private UUID uuid;
    private Lig lig;
    private int matchPoint;
    private int allGamePoint;

    public Player(String name, int age, UUID uuid, Lig lig) {
        this.name = name;
        this.age = age;
        this.uuid = uuid;
        this.lig = lig;
    }

    public void setLig(Lig lig) {
        this.lig = lig;
    }

    public Lig getLig() {
        return lig;
    }

    public int getMatchPoint() {
        return matchPoint;
    }

    public void setMatchPoint(int matchPoint) {
        this.matchPoint = matchPoint;
    }

    public int getAllGamePoint() {
        return allGamePoint;
    }

    public void setAllGamePoint(int allGamePoint) {
        this.allGamePoint = allGamePoint;
    }

    @Override
    public int compareTo(Player o) {
        return this.getMatchPoint() - o.getMatchPoint();
    }

}
