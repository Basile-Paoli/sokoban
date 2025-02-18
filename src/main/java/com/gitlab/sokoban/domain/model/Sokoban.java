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

    public Size getSize() {
        return map.size();
    }

    public boolean move(Tile tile, Direction direction) throws IllegalArgumentException {
        Position currentPosition = tile.position();
        Position newPosition = switch (direction) {
            case UP -> new Position(currentPosition.x(), currentPosition.y() - 1);
            case DOWN -> new Position(currentPosition.x(), currentPosition.y() + 1);
            case LEFT -> new Position(currentPosition.x() - 1, currentPosition.y());
            case RIGHT -> new Position(currentPosition.x() + 1, currentPosition.y());
            default -> throw new IllegalArgumentException("Direction invalide");
        };

        if (this.map.isWall(tile.position())) {
            throw new IllegalArgumentException("On ne peut pas déplacer un mur");
        }

        if (this.map.isWall(newPosition)) {
            throw new IllegalArgumentException("On ne peut pas déplacer quelque chose dans un mur");
        }

        if (this.map.inside(newPosition)) {
            throw new IllegalArgumentException("On ne peut pas déplacer quelque en dehors de la map");
        }

        if (boxes.contains(newPosition)) {
            Tile boxTile = new Tile(newPosition, State.Box);
            if (!move(boxTile, direction)) {
                return false;
            }
        }

        // Joueur
        if (currentPosition.equals(this.player)) {
            this.player = newPosition;
            return true;
        }

        // Boites
        if (boxes.contains(currentPosition)) {
            boxes.remove(currentPosition);
            boxes.add(newPosition);
            return true;
        }
        return false;
    }
}
