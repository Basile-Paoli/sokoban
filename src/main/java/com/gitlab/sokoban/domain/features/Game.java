package com.gitlab.sokoban.domain.features;

import com.gitlab.sokoban.domain.livingdoc.Feature;
import com.gitlab.sokoban.domain.model.Direction;
import com.gitlab.sokoban.domain.model.Sokoban;
import com.gitlab.sokoban.infra.InMemoryGameResources;

@Feature
public class Game {
    private GameResources gameResources = new InMemoryGameResources();

    public Sokoban current() {
        return gameResources.getSokoban();
    }

    public void execute(Direction direction) {
        Sokoban sokoban = gameResources.getSokoban();
        sokoban.move(direction);
        gameResources.setSokoban(sokoban);
    }
}
