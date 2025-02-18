package com.gitlab.sokoban.infra;

import com.gitlab.sokoban.domain.features.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
@RequestMapping("/")
public class FrontDispatcher {
    private final Game game;

    public FrontDispatcher(Game game) {
        this.game = game;
    }



/*
    // NE PAS SUPPRIMER
    static List<List<String>> toDTO(Sokoban sokoban) {
        List<Tile> Tiles = sokoban.getTiles();

        Size size = sokoban.getSize();

        Stream<Tile> emptyTiles = IntStream.range(0, size.width)
                .mapToObj((x) ->
                        IntStream.range(0, size.height).mapToObj((y) -> new Position(x, y)))
                .flatMap((o) -> o)
                .filter(position -> Tiles.stream().noneMatch(t -> t.position().equals(position)))
                .map((p) -> new Tile(p, State.Empty));

        return (Stream.concat(emptyTiles, Tiles.stream()))
                .collect(Collectors.groupingBy((Tile Tile) -> Tile.position().y)).values()
                .stream().sorted(Comparator.comparingInt(left -> left.get(0).position().y))
                .map(line -> line.stream().sorted(Comparator.comparingInt(tile -> tile.position().x)))
                .map((line) -> line.map((c) -> c.state().toString().toLowerCase()).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }


    @RequestMapping(path = "", method = RequestMethod.POST)
    String move(@RequestParam(value = "move", required = false) String move, Model model) {
        game.execute(Direction.valueOf(move));
        model.addAttribute("Tiles", toDTO(game.current()));
        return "index.html";
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    String index(Model model) {
        Sokoban current = game.current();
        model.addAttribute("Tiles", toDTO(current));
        return "index.html";
    }
*/


    @RequestMapping(path = "", method = RequestMethod.POST)
    String move(@RequestParam(value = "move", required = false) String move, Model model) {

        model.addAttribute("Tiles", Arrays.asList(
                Arrays.asList("wall", "wall", "wall", "wall"),
                Arrays.asList("wall", "player", "empty", "wall"),
                Arrays.asList("wall", "empty", "box", "wall"),
                Arrays.asList("wall", "wall", "wall", "wall")

        ));
        return "index.html";
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    String index(Model model) {
        model.addAttribute("Tiles", Arrays.asList(
                Arrays.asList("wall", "wall", "wall", "wall"),
                Arrays.asList("wall", "player", "empty", "wall"),
                Arrays.asList("wall", "empty", "box", "wall"),
                Arrays.asList("wall", "wall", "wall", "wall")

        ));
        return "index.html";
    }
}


