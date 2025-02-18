package com.gitlab.sokoban.domain.model;

public record Position(int x, int y) {
    public Position posAt(Direction direction) {
        return switch (direction) {
            case Up -> new Position(x, y - 1);
            case Down -> new Position(x, y + 1);
            case Left -> new Position(x - 1, y);
            case Right -> new Position(x + 1, y);
        };
    }
}
