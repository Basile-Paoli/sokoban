package com.gitlab.sokoban.domain.model;

public enum State {
    Player,
    PlayerOnStorage,
    Wall,
    Empty,
    Box,
    Storage,
    BoxStored;

    @Override
    public String toString() {
        return switch (this) {
            case Player, PlayerOnStorage -> "player";
            case Wall -> "wall";
            case Empty -> "empty";
            case Box -> "box";
            case Storage -> "storage";
            case BoxStored -> "boxstored";
        };
    }
}
