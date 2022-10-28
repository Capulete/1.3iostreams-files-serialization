package install;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static StringBuilder log = new StringBuilder();

    static final String HOME = "C://Users/capul/Desktop/Games/";

    public static void main(String[] args) {
        createDir("src", HOME);
        createDir("res", HOME);
        createDir("savegames", HOME);
        createDir("temp", HOME);
        createDir("main", HOME + "src/");
        createDir("test", HOME + "src/");

        createFile("Main.java", HOME + "src/main");
        createFile("Utils.java", HOME + "src/main");

        createDir("drawables", HOME + "res/");
        createDir("vectors", HOME + "res/");
        createDir("icons", HOME + "res/");

        createFile("temp.txt", HOME + "temp/");

        writeLog(HOME + "temp/temp.txt");
        readLog(HOME + "temp/temp.txt");
    }

    public static void createDir(String dirName, String dirPath) {
        File dir = new File(dirPath + dirName);
        if (dir.mkdir()) {
            log.append("Каталог ").append(dirName).append(" создан").append("\n");
        } else {
            log.append("Что-то пошло не так при создании каталога ").append(dirName).append("\n");
        }
    }

    public static void createFile(String fileName, String dirPath) {
        File file = new File(dirPath + fileName);
        try {
            if (file.createNewFile()) {
                log.append("Файл ").append(fileName).append(" создан").append("\n");
            }
        } catch (IOException ex) {
            log.append("Что-то пошло не так при создании файла ").append(fileName).append("\n");
            System.out.println(ex.getMessage());
        }
    }

    public static void writeLog(String logPath) {
        try (FileWriter writer = new FileWriter(logPath, false)) {
            writer.write(log.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void readLog(String logPath) {
        try (FileReader reader = new FileReader(logPath)) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.println((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}