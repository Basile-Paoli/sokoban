package com.gitlab.sokoban.domain.model;

import java.util.List;

public record Map(int size, List<Tile> tiles) {
    public boolean isWall(Position position) {
        return tiles.stream()
                .filter(tile -> tile.position().equals(position))
                .anyMatch(tile -> tile.state() == State.Wall);
    }

    public boolean inside(Position position) {
        return 0 <= position.x() && position.x() < size
                && 0 <= position.y() && position.y() < size;
    }
}
