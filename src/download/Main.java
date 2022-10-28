package download;

import save.GameProgress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    static final String HOME = "C://Users/capul/Desktop/Games/";

    public static void main(String[] args) {
        openZip("zip.zip", HOME + "savegames/");
        System.out.println(openProgress("save3.dat"));
    }

    public static void openZip(String filePath, String dirPath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(HOME + "savegames/" + filePath))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(dirPath + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String fileName) {
        GameProgress progress = null;

        try (FileInputStream fis = new FileInputStream(HOME + "savegames/" + fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            progress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return progress;
    }
}
