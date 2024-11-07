import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Вывод актуальной директории
        System.out.println("Current directory: " + new File(".").getAbsolutePath());

        Scanner scanner = new Scanner(System.in);

        // Вывод в консоль и файл без нулевых значений
        System.out.println("Enter path to file:");
        String path1 = scanner.nextLine();
        FreqDict dict1 = new FreqDict(path1);
        dict1.printDict();
        System.out.println("Enter path to result file:");
        String resPath1 = scanner.nextLine();
        System.out.println("Enter name to result:");
        String name1 = scanner.nextLine();
        dict1.writeToFile(resPath1, name1);


        // Вывод в консоль и файл с нулевыми значениями
        System.out.println("Enter path to file:");
        String path2 = scanner.nextLine();
        FreqDict dict2 = new FreqDict(path2);
        dict1.printFullDict();
        System.out.println("Enter path to result file:");
        String resPath2 = scanner.nextLine();
        System.out.println("Enter name to result:");
        String name2 = scanner.nextLine();
        dict1.writeFullToFile(resPath2, name2);
    }
}
