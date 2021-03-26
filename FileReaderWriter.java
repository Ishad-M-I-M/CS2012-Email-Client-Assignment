package assignment1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// class to handle all functionalities of reading and writing to files
public class FileReaderWriter {
    public static List<Object> read(String fileName){
        List<Object> list = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String s;
                while ((s = br.readLine()) != null){
                    list.add(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void write(String fileName, String inString){
        try {
            File file = new File(fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.append(inString).append("\n");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
