package com.gitlab.sokoban.domain;

import com.gitlab.sokoban.domain.model.Map;
import com.gitlab.sokoban.domain.model.Position;
import com.gitlab.sokoban.domain.model.State;
import com.gitlab.sokoban.domain.model.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestMap {

    private Map map;

    @BeforeEach
    public void InitTiles() {
        State wall = State.Wall;
        State air = State.Empty;
        State user = State.Player;
        Tile tile1 = new Tile(new Position(0, 0), wall);
        Tile tile2 = new Tile(new Position(0, 1), wall);
        Tile tile3 = new Tile(new Position(0, 2), wall);

        Tile tile4 = new Tile(new Position(1, 0), wall);
        Tile tile5 = new Tile(new Position(1, 1), air);
        Tile tile6 = new Tile(new Position(1, 2), wall);

        Tile tile7 = new Tile(new Position(2, 0), wall);
        Tile tile8 = new Tile(new Position(2, 1), wall);
        Tile tile9 = new Tile(new Position(2, 2), wall);

        List<Tile> listOfTile = new ArrayList<>(Arrays.asList(
                tile1, tile2, tile3, tile4, tile5, tile6,
                tile7, tile8, tile9
        ));

        map = new Map(3, listOfTile);
    }

    @Test
    public void TestIsWall() {
        Assertions.assertTrue(map.isWall(new Position(0, 0)), "La position doit être un mur");
    }

    @Test
    public void TestIsNotWall() {
        Assertions.assertFalse(map.isWall(new Position(1, 1)), "La position ne doit pas être un mur");
    }

    @Test
    public void TestIsInside() {
        Assertions.assertTrue(map.inside(new Position(0, 0)), "La position doit être a l'intérieur de la map");
    }

    @Test
    public void TestIsNotInside() {
        Assertions.assertFalse(map.inside(new Position(12, 42)), "La position ne doit pas être en dehors de la map");
    }
}
