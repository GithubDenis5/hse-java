import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Введите ФИО");
        String fullName = s.nextLine().trim();
        System.out.println("Введите дату рождения");
        String birthDate = s.nextLine().trim();

        Person pers = new Person(fullName, birthDate);

        // отдельный вывод
        System.out.println(pers.getInitials());
        System.out.println(pers.getGender());
        System.out.println(pers.getAge());


        System.out.println("Введите ФИО");
        String fullName2 = s.nextLine().trim();
        System.out.println("Введите дату рождения");
        String birthDate2 = s.nextLine().trim();

        Person pers2 = new Person(fullName2, birthDate2);

        // вывод информации целиком
        pers2.printFullInfo();

        s.close();
    }
}
