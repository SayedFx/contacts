package contacts;

public class OrganizationalRecord extends Record {
    private String address;

    private OrganizationalRecord(String name, String number, String address) {
        super(name, number);
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format(
                "Organization name: %s\n" +
                        "Address: %s\n" +
                        "Number: %s\n" +
                        "Time created: %s\n" +
                        "Time last edit: %s\n"
                , name
                , address
                , number
                , created
                , modified
        );
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
            case "number":
                System.out.println("Enter number:");
                setNumber(getInputString());
                break;
            case "address":
                System.out.println("Enter birth date:");
                address=getInputString();
                break;

        }
    }

    @Override
    public String getSearchableData() {
        return name + number + address;
    }

    private void printEditPrompt() {
        System.out.println("Select a field (name, address,number):");
    }

    public static class Builder {
        private String name;
        private String number;
        private String address;

        public void setName(String name) {
            this.name = name;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public OrganizationalRecord create() {
            return new OrganizationalRecord(name, number, address);
        }
    }
}
