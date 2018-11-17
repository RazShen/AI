import javax.print.attribute.standard.MediaSize;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class of the program.
 * parses the input.txt and
 */
public class java_ex1 {
    public static void main(String[] args) {
        String output = "output.txt";
        String input = "input.txt";
        int i = 0;
        int size = 0;
        Enums.SolvingAlgorithms algorithm = null;
        Integer[][] initBoard = new Integer[size][size];
        BufferedReader reader;
        List<Integer> ints = new ArrayList<>();
        // Parse the input.txt file by its format.
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = reader.readLine();
            while (line != null) {
                if (i == 0)
                    algorithm = Enums.SolvingAlgorithms.values()[Integer.parseInt(line) - 1];
                if (i == 1)
                    size = Integer.parseInt(line);
                if (i == 2) {
                    ints = Arrays.stream(line.split("-"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    initBoard = new Integer[size][size];
                    for (int j = 0; j < size; j++) {
                        for (int k = 0; k < size; k++) {
                            initBoard[j][k] = ints.get(size * j + k);
                        }
                    }
                }
                i++;
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Operate specific algorithm (the one specified in input.txt file)
        if (algorithm == Enums.SolvingAlgorithms.BFS) {
            ISolvingAlgorithm bfs = new BFS(initBoard, size);
            bfs.search();
            writeToOutput(bfs, output);
        } else if (algorithm == Enums.SolvingAlgorithms.IDS) {
            ISolvingAlgorithm ids = new IDS(initBoard, size);
            ids.search();
            writeToOutput(ids, output);
        } else if (algorithm == Enums.SolvingAlgorithms.A_STAR) {
            ISolvingAlgorithm astar = new AStar(initBoard, size);
            astar.search();
            writeToOutput(astar, output);
        }
    }

    /**
     * Write the algorithm results into the output.txt by the required format.
     *
     * @param algorithm operated algorithm.
     * @param fileName  to write the results into.
     */
    public static void writeToOutput(ISolvingAlgorithm algorithm, String fileName) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        StringBuilder sb = new StringBuilder();
        // add path
        sb.append(algorithm.getPath() + " ");
        // add size of close list.
        sb.append(algorithm.getCloseListSize() + " ");
        // add cost of the algorithm (as details in ex1.pdf
        sb.append(algorithm.getSpecificCost());
        pw.write(sb.toString());
        pw.close();
    }
}

