package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class MarvelTests {
    private static File file = new File ("./datasets/marvel.txt");
    private static Graph marvelGraph = new AdjacencyListGraph();
    private static Map<String, List<String>> comicToCharacter = new HashMap<>();

    @BeforeClass
    public static void setup() throws IOException {
        Scanner sc = null;

        try {
            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split("\t");
                if (input.length >= 2) {
                    String character = input[0].replaceAll("^\"|\"$", "").trim();

                    String comic = input[1].replaceAll("^\"|\"$", "").trim();

//                    System.out.println(character + " ===" + comic);
                    if (character.isEmpty() || comic.isEmpty()) {
                        continue;
                    }
                    Vertex v = new Vertex(character);
                    if (!marvelGraph.getVertices().contains(v)) {
                        marvelGraph.addVertex(v);
                    }

                    List<String>  characterList = comicToCharacter.get(comic);
                    if (characterList == null ) {
                        characterList = new ArrayList<>();
                        characterList.add(character);
                        comicToCharacter.put(comic, characterList);

                    }else{
                        characterList.add(character);
                    }


                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (sc != null) {
                sc.close();
            }
        }

        System.out.println(comicToCharacter.keySet());
        System.out.println(comicToCharacter.keySet().size());
        for (String key : comicToCharacter.keySet()) {
            List<String> characterSameComic = comicToCharacter.get(key);


            for (int i = 0; i < characterSameComic.size() - 1; i++) {

                Vertex a = new Vertex(characterSameComic.get(i));
                for (int j = i + 1; j < characterSameComic.size(); j++) {
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

    @Test
    public void testMarvelDiameter() {
        assertEquals(1, Algorithms.diameter(marvelGraph));
    }

    @Test
    public void testMarvelShortestDistance() {
        Vertex a = new Vertex("DOC SAMSON/DR. LEONA");
        Vertex b = new Vertex("ROSS, GEN. THADDEUS");
        System.out.println("TOTAL HEROS == " + marvelGraph.getVertices().size());
//        System.out.println(marvelGraph.getNeighbors(a));
//        System.out.println(marvelGraph.getNeighbors(b));
        assertEquals(1, Algorithms.shortestDistance(marvelGraph,a, b));
    }
}