import javax.print.attribute.standard.MediaSize;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        int i = 0;
        int size = 0;
        Enums.SolvingAlgorithms algorithm = Enums.SolvingAlgorithms.BFS;
        Integer[][] initBoard = new Integer[size][size];
        BufferedReader reader;
        List<Integer> ints = new ArrayList<>();
        if (args.length != 1) {
            System.out.println("Usage is <Input File>, quitting...");
            return;
        }
        try {
            reader = new BufferedReader(new FileReader(args[0]));
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
        if (algorithm == Enums.SolvingAlgorithms.BFS) {
            ISolvingAlgorithm bfs = new BFS(initBoard, size);
            bfs.search();
            writeToOutput(bfs, "fuckoutput");
        } else if(algorithm == Enums.SolvingAlgorithms.IDS) {
            ISolvingAlgorithm ids = new IDS(initBoard, size);
            ids.search();
            writeToOutput(ids, "fuckoutput");
        } else if(algorithm == Enums.SolvingAlgorithms.A_STAR) {
            ISolvingAlgorithm astar = new AStar(initBoard, size);
            astar.search();
            writeToOutput(astar, "fuckoutput");
        }
    }




        public static void writeToOutput(ISolvingAlgorithm algorithm, String fileName) {
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(new FileWriter(fileName));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(algorithm.getPath() + " ");
            sb.append(algorithm.getCloseListSize() + " ");
            sb.append(algorithm.getSpecificCost());
            pw.write(sb.toString());
            pw.close();
        }




}

