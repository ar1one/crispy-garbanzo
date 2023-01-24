package hw2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        GameProgress gm1 = new GameProgress(10, 12, 14, 16);
        GameProgress gm2 = new GameProgress(14, 19, 11, 21);
        GameProgress gm3 = new GameProgress(17, 14, 10, 14);
        saveGame("C:\\Games\\savegames\\data1.dat", gm1);
        list.add("C:\\Games\\savegames\\data1.dat");
        saveGame("C:\\Games\\savegames\\data2.dat", gm2);
        list.add("C:\\Games\\savegames\\data2.dat");
        saveGame("C:\\Games\\savegames\\data3.dat", gm3);
        list.add("C:\\Games\\savegames\\data3.dat");
        zipFiles("C:\\Games\\savegames\\data.zip", list);

    }

    public static void saveGame(String path, GameProgress gm) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gm);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void zipFiles(String path, List<String> list) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (int i = 0; i < list.size(); i++) {
                try (FileInputStream fis = new FileInputStream(list.get(i))) {
                    ZipEntry entry = new ZipEntry("data" + (i + 1) + ".dat");
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                }
                File file = new File(list.get(i));
                file.delete();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
