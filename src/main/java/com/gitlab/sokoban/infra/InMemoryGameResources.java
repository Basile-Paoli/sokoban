package com.gitlab.sokoban.infra;


import com.gitlab.sokoban.domain.features.GameResources;
import com.gitlab.sokoban.domain.livingdoc.Stub;
import com.gitlab.sokoban.domain.model.*;

import java.util.ArrayList;
import java.util.List;

@Stub
public class InMemoryGameResources implements GameResources {
    private String mapText = """
            ######\s
            #@   ##
            # $$  #
            # #. .#
            #     #
            #######
            """;

    @Override
    public Sokoban getSokoban() {
        return new Sokoban(getMap(), getBoxes(), getPlayerPosition());
    }

    @Override
    public void setSokoban(Sokoban sokoban) {
        int height = sokoban.getSize().height();
        int width = sokoban.getSize().width();

        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newText.append(charAtPos(new Position(j, i), sokoban));
            }

            if (i < height - 1) {
                newText.append("\n");
            }
        }

        mapText = newText.toString();
    }

    private char charAtPos(Position position, Sokoban sokoban) {
        Tile tile = sokoban.getTiles().stream().filter(t -> t.position().equals(position)).findFirst().orElseThrow();
        return switch (tile.state()) {
            case Wall -> '#';
            case Storage -> '.';
            case Empty -> ' ';
            case Box -> '$';
            case BoxStored -> '!';
            case Player -> '@';
            case PlayerOnStorage -> '?';
        };
    }


    private Map getMap() {
        return MapBuilder.build(mapText);
    }

    private List<Position> getBoxes() {
        var lines = mapText.lines().toList();

        List<Position> boxes = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '!' | line.charAt(x) == '$') {
                    boxes.add(new Position(x, y));
                }
            }
        }

        return boxes;
    }

    private Position getPlayerPosition() {
        var lines = mapText.lines().toList();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '@' | line.charAt(x) == '?') {
                    return new Position(x, y);
                }
            }
        }

        throw new IllegalStateException("Player not found");
    }
}


