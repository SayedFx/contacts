package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PhoneBook {
    private final List<Record> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();

    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            printActionPrompt();
            String action = scanner.next();
            switch (action) {
                case "add":
                    add(buildRecord());
                    printFeedback();
                    break;
                case "count":
                    System.out.printf("The Phone Book has %d records.", contacts.size());
                    break;
                case "search":
                    search();
                    break;
                case "list":
                    printRecordInfo();
                    break;
                case "exit":
                    exit = true;
                    break;
            }

        }
    }

    public void add(Record record) {
        contacts.add(record);
    }

    private static void printNamePrompt() {
        System.out.println("Enter the name:");
    }

    private static void printActionPrompt() {
        System.out.println("\n[menu] Enter action (add, list, search, count, exit):");
    }

    private static void printSurnamePrompt() {
        System.out.println("Enter the surname:");
    }

    private static void printNumberPrompt() {
        System.out.println("Enter the number:");
    }

    private static void printFeedback() {
        System.out.println("The record added!\n");
    }

    private void search() {
        System.out.println("Enter search query: ");
        String query = getInputString();
        List<Record> searchResult = contacts.stream().filter(record -> record.matches(query)).collect(Collectors.toList());
        System.out.println(String.format("Found %d results:", searchResult.size()));
        printNames(searchResult);
        System.out.println("\n[search] Enter action ([number], back, again):");
        String choice = getInputString();
        if (choice.equals("again")) search();
        else if (choice.matches("\\d+")) {
            processRecordAction(searchResult.get(Integer.parseInt(choice) - 1));
        }
    }

    private void editAction() {
        printEditSelectionPrompt();
        if (!contacts.isEmpty()) {
            contacts.get(getSelectedIndex()).edit();
            printEditFeedback();
        }
    }

    private void printEditFeedback() {
        System.out.println("Saved");
    }


    private String getInputString() {
        return new Scanner(System.in).nextLine();
    }

    private void removeRecord() {
        printNames();
        printRemoveSelectionPrompt();
        if (!contacts.isEmpty()) {
            removeRecord(getSelectedIndex());
            printRemoveFeedback();
        }
    }

    private int getSelectedIndex() {
        return Integer.parseInt(getInputString().strip()) - 1;
    }

    private void printRemoveFeedback() {
        System.out.println("The record removed!");
    }

    private void removeRecord(int index) {
        contacts.remove(index);
    }

    private void printEditSelectionPrompt() {
        if (contacts.isEmpty()) System.out.print("No records to edit!");
        else System.out.println("Select a record:");
    }

    private void printRemoveSelectionPrompt() {
        if (contacts.isEmpty()) System.out.print("No records to remove!");
        else System.out.println("Select a record:");
    }

    private Record buildRecord() {
        printRecordTypePrompt();
        switch (getInputString()) {
            case "person":
                return buildPersonalRecord();
            case "organization":
                return buildOrganizationalRecord();
        }
        return null;
    }

    private Record buildPersonalRecord() {
        PersonalRecord.Builder builder = new PersonalRecord.Builder();
        printNamePrompt();
        builder.setName(getInputString());
        printSurnamePrompt();
        builder.setSurname(getInputString());
        printDOBPrompt();
        builder.setDob(getInputString());
        printGenderPrompt();
        builder.setGender(getInputString());
        printNumberPrompt();
        builder.setNumber(getInputString());
        return builder.create();
    }

    private Record buildOrganizationalRecord() {
        OrganizationalRecord.Builder builder = new OrganizationalRecord.Builder();
        printOrganizationNamePrompt();
        builder.setName(getInputString());
        printAddressPrompt();
        builder.setAddress(getInputString());
        printNumberPrompt();
        builder.setNumber(getInputString());
        return builder.create();
    }

    private void printAddressPrompt() {
        System.out.println("Enter the address:");
    }

    private void printOrganizationNamePrompt() {
        System.out.println("Enter the organization name:");
    }

    private void printGenderPrompt() {
        System.out.println("Enter the gender (M, F):");
    }

    private void printDOBPrompt() {
        System.out.println("Enter the birth date:");
    }

    private void printRecordTypePrompt() {
        System.out.println("Enter the type (person, organization): ");
    }

    private void printRecordInfo() {
        printNames();
        printInfoPrompt();
        processRecordAction(this.contacts.get(getSelectedIndex()));
    }

    private void processRecordAction(Record record) {
        System.out.println(record.toString());
        printRecordActionPrompt();
        switch (getInputString()) {
            case "edit":
                record.edit();
                printEditFeedback();
                System.out.println(record.toString());
                break;
            case "menu":
                return;
            case "delete":
                contacts.remove(record);
                printRemoveFeedback();
                break;
        }
    }

    private void printRecordActionPrompt() {
        System.out.println("[record] Enter action (edit, delete, menu):");
    }

    private void printNames() {
        printNames(this.contacts);
    }

    private void printNames(List<Record> contacts) {
        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, contacts.get(i).getName());
        }
    }

    private void printInfoPrompt() {
        System.out.println("Enter index to show info:");
    }

}
