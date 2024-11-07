import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class FreqDict {
    private final Map<Character, Integer> freqDict  = new HashMap<>();
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    FreqDict(String path) {
        // Заполнение ключей для словаря
        for (char ch : alphabet.toCharArray()) {
            freqDict.put(ch, 0);
        }

        createDictFromFile(path);
    }

    // Чтение файла и создание словаря
    private void createDictFromFile(String path) {
        try (FileReader reader = new FileReader(path)){
            char c;
            while ((c = (char) reader.read()) != '\uFFFF') {
                if (freqDict.containsKey(c)) {
                    freqDict.put(c, freqDict.get(c) + 1);
                }
            }
            System.out.println("Dictionary created");
        }
        catch (FileNotFoundException ex) {
            System.out.println("File not found!!!");
        } catch (IOException e) {
            System.out.println("Runtime!!!");
            throw new RuntimeException(e);

        }
    }

    // Вывод в консоль с нулевыми значениями
    public void printFullDict() {
        for (char c: alphabet.toCharArray()) {
            System.out.println(c+": "+freqDict.get(c));
        }
    }

    // Вывод в консоль без нулевых значений
    public void printDict() {
        for (char c: alphabet.toCharArray()) {
            int cn = freqDict.get(c);
            if (cn > 0) {
                System.out.println(c + ": " + cn);
            }
        }
    }

    // Запись в файл буз нулевых значений
    public void writeToFile(String path, String fileName) {
        try (FileWriter writer = new FileWriter(path + "/" + fileName)) {
            for (char c : alphabet.toCharArray()) {
                int cn = freqDict.get(c);
                if (cn > 0) {
                    writer.write(c + ": " + cn + "\n");
                }
            }
            System.out.println("Dictionary written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }

    // Запись в файл с нулевыми значениями
    public void writeFullToFile(String path, String fileName) {
        try (FileWriter writer = new FileWriter(path + "/" + fileName)) {
            for (char c : alphabet.toCharArray()) {
                writer.write(c + ": " + freqDict.get(c) + "\n");
            }
            System.out.println("Dictionary written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }


}
