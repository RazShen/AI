import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        int i = 0;
        int size = 0;
        Enums.SolvingAlgorithms algorithm;
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
                if (i==0)
                    algorithm = Enums.SolvingAlgorithms.values()[Integer.parseInt(line) - 1];
                if (i==1)
                    size = Integer.parseInt(line);
                if (i==2) {
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
        ISolvingAlgorithm bfs = new BFS(initBoard, size);
        bfs.search();
        System.out.println(bfs.getPath());



    }
}

