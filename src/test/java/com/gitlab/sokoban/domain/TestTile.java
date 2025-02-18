package com.gitlab.sokoban.domain;
import com.gitlab.sokoban.domain.model.Position;
import com.gitlab.sokoban.domain.model.State;
import com.gitlab.sokoban.domain.model.Tile;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTile {
    private Tile tile1;
    private Tile tile2;
    @BeforeEach
    public void InitTiles() {
        Position pos1 = new Position(1, 2);
        State state1 = State.Storage;
        tile1 = new Tile(pos1, state1);
        tile2 = new Tile(pos1, state1);
    }

    @Test
    public void TestTilesEquals() {
        Assertions.assertEquals(tile1, tile2);
    }
}
