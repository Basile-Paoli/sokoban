package com.gitlab.sokoban.domain.model;

import java.util.List;

public class Sokoban {
    private Map map;
    private List<Position> boxes;
    private Position player;

    public Sokoban(Map map, List<Position> boxes, Position player) {
        this.map = map;
        this.boxes = boxes;
        this.player = player;
    }

    public List<Tile> getTiles() {
        var initialTiles = map.tiles();
        var tilesWithBoxes = initialTiles.stream().map(tile -> {
            if (boxes.contains(tile.position())) {
                return addBoxToTile(tile);
            } else {
                return tile;
            }
        });

        var tilesWithPlayer = tilesWithBoxes.map(tile -> {
            if (tile.position().equals(player)) {
                return addPlayerToTile(tile);
            } else {
                return tile;
            }
        });

        return tilesWithPlayer.toList();
    }

    private Tile addBoxToTile(Tile tile) {
        return switch (tile.state()) {
            case Empty -> new Tile(tile.position(), State.Box);
            case Storage -> new Tile(tile.position(), State.BoxStored);
            default -> throw new IllegalStateException("Box cannot be on wall or another box");
        };
    }

    private Tile addPlayerToTile(Tile tile) {
        return switch (tile.state()) {
            case Empty, Storage -> new Tile(tile.position(), State.Player);
            default -> throw new IllegalStateException("Player cannot be on wall or box");
        };
    }
}
