package save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    static final String HOME = "C://Users/capul/Desktop/Games/";

    public static void main(String[] args) {
        GameProgress progress = new GameProgress(100, 2, 3, 220.12);
        GameProgress progress2 = new GameProgress(98, 5,4,110.02);
        GameProgress progress3 = new GameProgress(69, 7,5, 87);

        saveGame("save.dat", progress);
        saveGame("save2.dat", progress2);
        saveGame("save3.dat", progress3);

        zipFiles("zip.zip", "save.dat");
        zipFiles("zip.zip", "save2.dat");
        zipFiles("zip.zip", "save3.dat");

        deleteSaves(HOME + "savegames");
    }

    public static void saveGame(String fileName, GameProgress progress) {
        try (FileOutputStream fos = new FileOutputStream(HOME + "savegames/" + fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String fileName, String save) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(HOME + "savegames/" + fileName));
             FileInputStream fis = new FileInputStream(HOME + "savegames/" + save)) {
            ZipEntry entry = new ZipEntry(save);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteSaves(String dirPath) {
        File dir = new File(dirPath);
        if (dir.isDirectory()) {
            for (File item : Objects.requireNonNull(dir.listFiles())) {
                if (!item.isDirectory() && !item.getName().equals("zip.zip")) {
                    item.delete();
                }
            }
        }
    }
}