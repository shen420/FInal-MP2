package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Vertex;
import org.junit.BeforeClass;
import org.junit.Test;
import ca.ubc.ece.cpen221.mp2.core.Graph;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class MarvelTests {
    private static File file = new File ("./datasets/marvel.txt");
    private static Graph marvelGraph = new AdjacencyListGraph();
    private static Map<String, List<String>> comicToCharacter = new HashMap<>();

    @BeforeClass
    public static void setup() throws IOException {

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split("\t");
                if (input[0] != "") {
                    String character = input[0].replaceAll("^\"|\"$", "");
                    String movie = input[1].replaceAll("^\"|\"$", "");
                    marvelGraph.addVertex(new Vertex (character));

                    if (!comicToCharacter.keySet().contains(movie)) {
                        List <String> chars = new ArrayList<>();
                        chars.add(character);
                        comicToCharacter.put(movie, chars);
                    }
                    else {
                        comicToCharacter.get(movie).add(character);
                    }

                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.print(comicToCharacter.keySet());
        for (String key : comicToCharacter.keySet()) {
            List<String> characterSameComic = comicToCharacter.get(key);

            for (int i = 0; i < characterSameComic.size() - 1; i++) {
                for (int j = i + 1; j < characterSameComic.size(); j++) {
                    Vertex a = new Vertex(characterSameComic.get(i));
                    Vertex b = new Vertex(characterSameComic.get(j));
                    marvelGraph.addEdge(a, b);
                }
            }
        }
    }

    @Test
    public void testMarvelCenter() {
        String center = "Doggie";
        assertEquals(center, Algorithms.center(marvelGraph));
    }
}

