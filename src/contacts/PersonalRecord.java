package contacts;

public class PersonalRecord extends Record {
    private static final String NO_DATA = "[no data]";
    private String surname;
    private String gender;
    private String dob;

    private PersonalRecord(String name, String surname, String number, String gender, String dob) {
        super(name, number);
        this.surname = surname;
        setGender(gender);
        setDob(dob);
    }

    public void setGender(String gender) {
        if (gender != null && (gender.equals("M") || gender.equals("F"))) {
            this.gender = gender;
        } else {
            this.gender = NO_DATA;
            System.out.println("Bad gender!");
        }
    }

    public void setDob(String dob) {
        if (dob == null || dob.isBlank()) {
            this.dob = NO_DATA;
            System.out.println("Bad birth date!");
        } else this.dob = dob;
    }

    @Override
    public void edit() {
        super.edit();
        printEditPrompt();
        switch (getInputString()) {
            case "name":
                System.out.println("Enter name:");
                name = getInputString();
                break;
            case "surname":
                System.out.println("Enter surname:");
                surname = getInputString();
                break;
            case "number":
                System.out.println("Enter number:");
                setNumber(getInputString());
                break;
            case "gender":
                System.out.println("Enter gender:");
                setGender(getInputString());
                break;
            case "birth":
                System.out.println("Enter birth date:");
                setDob(getInputString());
                break;

        }
    }

    @Override
    public String getName() {
        return String.format("%s %s", name, surname);
    }

    @Override
    public String getSearchableData() {
        return name + surname + number + gender + dob;
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s\n" +
                        "Surname: %s\n" +
                        "Birth date: %s\n" +
                        "Gender: %s\n" +
                        "Number: %s\n" +
                        "Time created: %s\n" +
                        "Time last edit: %s\n"
                , name
                , surname
                , dob
                , gender
                , number
                , created
                , modified
        );
    }


    private void printEditPrompt() {
        System.out.println("Select a field (name, surname, birth, gender, number):");
    }

    public static class Builder {
        private String name;
        private String surname;
        private String number;
        private String gender;
        private String dob;

        public void setName(String name) {
            this.name = name;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public PersonalRecord create() {
            return new PersonalRecord(name, surname, number, gender, dob);
        }
    }
}
