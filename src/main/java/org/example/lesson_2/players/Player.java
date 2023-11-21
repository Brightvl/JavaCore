package org.example.lesson_2.players;

import org.example.lesson_2.field.Turn;

public abstract class Player {
    private String name;
    private Turn turn;

    public Player(String name) {
        this.name = name;
        this.turn = new Turn();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
