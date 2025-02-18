package com.gitlab.sokoban.domain.model;

import java.util.ArrayList;
import java.util.List;

public class MapBuilder {
    public static List<Tile> toTiles(String mapText) {
        var lines = mapText.lines().toList();

        List<Tile> tiles = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                State state = charToState(c);
                Position position = new Position(x, y);
                tiles.add(new Tile(position, state));
            }
        }

        return tiles;
    }

    public static Map build(String mapText) {
        int width = mapText.lines().findFirst().orElseThrow().length();
        int height = (int) mapText.lines().count();
        List<Tile> tiles = toTiles(mapText);
        return new Map(new Size(width, height), tiles);
    }

    private static State charToState(char c) {
        return switch (c) {
            case '#' -> State.Wall;
            case '.' | '!' -> State.Storage;
            default -> State.Empty;
        };
    }
}
