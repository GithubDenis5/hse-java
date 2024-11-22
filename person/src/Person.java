import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Person {
    private final String fullName;
    private final LocalDate birthDate;

    public Person(String fullName, String birthDateInput) {
        this.fullName = handleFullName(fullName);
        this.birthDate = handleBirthDate(birthDateInput);
    }

    // Проверка корректности имени
    private String handleFullName(String fullName) {
        try {
            if (fullName == null || fullName.split(" ").length != 3) {
                throw new IllegalArgumentException("ФИО должно содержать три слова (Фамилия Имя Отчество)");
            }
            return fullName.trim();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }


    // Проверка корректности даты рождения
    private LocalDate handleBirthDate(String birthDateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd.MM.yyyy][dd/MM/yyyy][dd-MM-yyyy]");
        try {
            return LocalDate.parse(birthDateInput.trim(), formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Дата рождения должна быть в формате дд.мм.гггг или дд/мм/гггг или дд-мм-гггг");
            System.exit(1);
            return null;
        }
    }

    // Получить инициалы
    public String getInitials() {
        String[] parts = fullName.split(" ");
        String lastName = parts[0];
        String firstName = parts[1];
        String middleName = parts[2];
        return String.format("%s %c.%c.", lastName, firstName.charAt(0), middleName.charAt(0));
    }

    // Получить пол
    public String getGender() {
        String middleName = fullName.split(" ")[2].toLowerCase();
        if (middleName.endsWith("ич")) {
            return "м";
        } else if (middleName.endsWith("на")) {
            return "ж";
        } else {
            return "не удалось определить";
        }
    }

    // Получить возраст
    public String getAge() {
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();

        if (age % 10 == 1 && age % 100 != 11) {
            return age + " год";
        } else if ( age % 10 <= 4 && age % 10 >= 2 && (age % 100 >= 20 || age % 100 < 10)) {
            return age + " года";
        } else {
            return age + " лет";
        }
    }

    // Вывод всей информации
    public void printFullInfo() {
        System.out.println("Фамилия и инициалы: " + getInitials());
        System.out.println("Пол: " + getGender());
        System.out.println("Возраст: " + getAge());
    }
}
