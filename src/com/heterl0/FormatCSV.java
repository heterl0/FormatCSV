package com.heterl0;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * FormatCSV is class use to format two field name and address of file csv with five attributes, 
 * Id, Name, Email, Phone, Address. 
 * @author Le Van Hieu CE160866 
 *         Class SE1607
 */
public class FormatCSV {

    private ArrayList<Person> dataCSV;        // dataCSV used to stored data of file
    private String importPath;                  // importPath stored the path of import file 
    private String exportPath;                  // exportPath stored the path of export file

    /**
     * Constructor used to crate a new object FormatCSV and initialize dataCSV,
     * importPath, exportPath for instant.
     */
    public FormatCSV() {
        dataCSV = new ArrayList<>();        
        importPath = "";
        exportPath = "";
    }

    /**
     * Method importCSV used to import only CSV file, inform exception if user's 
     * path is entered doesn't '.csv' file. At the same update importPath.
     * @param path the path to '.csv' file
     */
    public void importCSV(String path) {
        File f = new File(path);
        // check if file has been existed!
        if (f.exists()) {
            // check if file is a '.csv' file
            if (f.getName().endsWith(".csv")) {
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String line = br.readLine();
                    // check if line is null
                    if (line != null) {
                        // loop and read file until the end of file (eof)
                        while (line != null) {
                            // each record is splited to an array of string
                            String[] arr = line.split(",");
                            this.dataCSV.add(new Person(arr[0], arr[1], arr[2], arr[3], arr[4]));
                            line = br.readLine();
                        }
                        System.out.println("Import: Done");
                        this.importPath = path;
                    } else {
                        throw new NoSuchFieldError("Can't read csv file!");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Path is not '.csv' file");
            }
        } else {
            System.out.println("File doesn't exist!");
        }
    }

    /**
     * This method format attribute `Address` in file csv. Address is stored at 
     * index fourth of "Array Record". Correct format of address.
     */
    public void formatAddress() {
        // loop through all records in dataCSV
        for (int i = 1; i < this.dataCSV.size(); i++) {
            // get a record at index specify
            Person person = this.dataCSV.get(i);
            // get address.
            String address = person.getAddress();
            // address is splited into array of words
            String[] words = address.split("\\s+");
            address = "";
            // loop through all words
            for (int j = 0; j < words.length; j++) {
                if (Character.isLetter(words[j].charAt(0))) {
                    words[j] = words[j].toLowerCase();
                    words[j] = Character.toUpperCase(words[j].charAt(0)) + words[j].substring(1);
                }
                address = address + words[j] + " ";
            }
            this.dataCSV.get(i).setAddress(address.trim());
        }
        System.out.println("---------- Format Address");
        System.out.println("------ Format: Done");

    }

    /**
     * This method format attribute `Name` in file csv. Name is stored at 
     * index first of "Array Record". Correct format of name.
     */
    public void formatName() {
        // loop through all records in dataCSV
        for (int i = 1; i < this.dataCSV.size(); i++) {
            // get a record at index specify
            Person person = this.dataCSV.get(i);
            // get name.
            String name = person.getName();
            // name is splited into array of words
            String[] words = name.split("\\s+");
            name = "";
            for (int j = 0; j < words.length; j++) {
                if (Character.isLetter(words[j].charAt(0))) {
                    words[j] = words[j].toLowerCase();
                    words[j] = Character.toUpperCase(words[j].charAt(0)) + words[j].substring(1);
                }
                name = name + words[j] + " ";
            }
            this.dataCSV.get(i).setName(name.trim());
        }
        System.out.println("---------- Format Name");
        System.out.println("------ Format: Done");
    }

    /**
     * Export a new '.csv' file, from data from dataCSV (formated or not format)
     * export a new file.  But data is following csv structure, is separated by 
     * a comma.
     * @param path the path is new file, user can export csv, docs, txt,... or 
     * everything or not with a extension specify.
     */
    public void exportCSV(String path) {
        this.exportPath = path;
        File exportFile = new File(path);
        // check if the path has been exist or not, if not create a new file
        if (!exportFile.exists()) {
            try {
                exportFile.createNewFile();
            } catch (IOException ex) {
                System.out.println("Can't create file " + path);
                return;
            }
        }
        if (exportFile.isDirectory()) {
            System.out.println("The path to directory!");
            return;
        }
        try (FileWriter fw = new FileWriter(exportFile, false)) {
            // loop through dataCSV get each record and write it into file
            for (int i = 0; i < this.dataCSV.size(); i++) {
                Person person = this.dataCSV.get(i);
                fw.append(person.getId() + "," + person.getName() + "," + person.getEmail()+ 
                        "," + person.getPhoneNumber() + "," + person.getAddress() + "\n");
            }
            System.out.println("Export: Done");
        } catch (Exception e) {
            // if it has any exception, write fail
            System.out.println("Export: Fail");
        }
    }

    /**
     * call main menu with 5 function
     */
    public void callMenu() {
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        String path = "";
        boolean isValid = false, isLoop = true;
        do {
            System.out.println("====== Format CSV Program ======");
            System.out.println("1. Import CSV");
            System.out.println("2. Format Address");
            System.out.println("3. Format Nam");
            System.out.println("4. Export CSV");
            System.out.println("5. Exit");
            do {
                try {
                    System.out.print("Please choice one option: ");
                    choice = sc.nextInt();
                    if (choice < 1 || choice > 5) {
                        System.out.println("Choice must be 1 - 5!");
                    } else if (this.importPath.equals("") && choice >= 2 && choice <= 4) {
                        System.out.println("Please import file '.csv' first!");
                    } else {
                        isValid = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Choice must be 1 - 5!");
                }
                sc.nextLine();
            } while (isValid == false);
            switch (choice) {
                case 1:
                    System.out.println("- - - - Import CSV - - - -");
                    System.out.print("Enter Path: ");
                    path = sc.nextLine();
                    importCSV(path);
                    break;
                case 2:
                    formatAddress();
                    break;
                case 3:
                    formatName();
                    break;
                case 4:
                    System.out.println("- - - - Export CSV - - - -");
                    System.out.print("Enter Path: ");
                    path = sc.nextLine();
                    exportCSV(path);
                    break;
                case 5:
                    System.out.println("Thanks for using software! Goodbye.");
                    isLoop = false;
            }
        } while (isLoop);
    }
    
}
