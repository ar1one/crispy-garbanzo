package hw1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        File[] dir = {
                new File("C:\\Games\\src"),
                new File("C:\\Games\\res"),
                new File("C:\\Games\\savegames"),
                new File("C:\\Games\\temp"),
                new File("C:\\Games\\src\\main"),
                new File("C:\\Games\\src\\test"),
                new File("C:\\Games\\res\\drawables"),
                new File("C:\\Games\\res\\vectors"),
                new File("C:\\Games\\res\\icons"),
                new File("C:\\Games\\src\\main", "Main.java"),
                new File("C:\\Games\\src\\main", "Utils.java"),
                new File("C:\\Games\\temp", "temp.txt")
        };
        for (File file : dir) createFile(file, sb);
        try (FileWriter fw = new FileWriter("C:\\Games\\temp\\temp.txt")) {
            fw.write(sb.toString());
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public static void createFile(File file, StringBuilder sb) {
        if (file.getName().contains(".")) {
            try {
                if (file.createNewFile()) sb.append("Файл " + file.getName() + " создан! \n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (file.mkdir()) sb.append("Папка " + file.getName() + " создана! \n");
    }
}
