package contacts;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Record {
    static final String NO_NUMBER = "[no number]";
    String name;
    String number;
    LocalDateTime created;
    LocalDateTime modified;

    Record(String name, String number) {
        this.name = name;
        setNumber(number);
        created = LocalDateTime.now();
        modified = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setNumber(String number) {
        if (isNumberValid(number)) {
            this.number = number;
        } else {
            this.number = NO_NUMBER;
            System.out.println();
        }
    }

    public abstract String getSearchableData();

    public boolean matches(String query) {
        Matcher matcher = Pattern.compile(query, Pattern.CASE_INSENSITIVE).matcher(getSearchableData());
        return matcher.find();
    }

    private static boolean isNumberValid(String number) {
        return validateNumber(number);
    }

    private static boolean validateNumber(String number) {
        if (hasMoreThanOneParenthesesGroup(number)) {
            return false;
        }
        String[] groups = number.split("[\\s-]");
        for (int i = 0; i < groups.length; i++) {
            String group = groups[i];
            if (i == 0) {
                if (group.length() < 1 || !group.matches("\\+?\\([\\da-zA-Z]+\\)|\\+?[\\da-zA-Z]+")) return false;
            } else if (group.length() < 2 || !group.matches("\\([\\da-zA-Z]+\\)|[\\da-zA-Z]+")) return false;
        }
        return true;
    }

    private static boolean hasMoreThanOneParenthesesGroup(String number) {
        return number.replaceAll("[^(]", "").length() > 1;
    }

    void edit() {
        modified = LocalDateTime.now();
    }

    String getInputString() {
        return new Scanner(System.in).nextLine().strip();
    }


}
