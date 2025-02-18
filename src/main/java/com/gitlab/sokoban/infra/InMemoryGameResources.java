package com.gitlab.sokoban.infra;


import com.gitlab.sokoban.domain.features.GameResources;
import com.gitlab.sokoban.domain.livingdoc.Stub;
import com.gitlab.sokoban.domain.model.Map;
import com.gitlab.sokoban.domain.model.MapBuilder;
import com.gitlab.sokoban.domain.model.Position;
import com.gitlab.sokoban.domain.model.Sokoban;

import java.util.ArrayList;
import java.util.List;

@Stub
public class InMemoryGameResources implements GameResources {
    private String mapText = """
            #####
            #   #
            #   #
            # @ #
            #####
            """;

    @Override
    public Sokoban getSokoban() {
        return new Sokoban(getMap(), getBoxes(), getPlayerPosition());
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
                if (line.charAt(x) == '@' | line.charAt(x) == '+') {
                    return new Position(x, y);
                }
            }
        }

        throw new IllegalStateException("Player not found");
    }
}


