import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDataProcessor {

    public static void main(String[] args) {
    try {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Map<String, UserData> userDataMap = new HashMap<>();

    while (true) {
     System.out.println("Type: LastName FirstName MiddleName DateOfBirth PhoneNumber Gender (use space):");
      String input = reader.readLine();
     String[] inputArray = input.split(" ");

       if (inputArray.length != 6) {
         System.out.println("Error: not enough data");
            continue;
                }

     String lastName = inputArray[0];
     String firstName = inputArray[1];
     String middleName = inputArray[2];
     String birthDate = inputArray[3];
     String phoneNumber = inputArray[4];
     String gender = inputArray[5];

     try {
     UserData userData = new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
    userDataMap.put(lastName, userData);
    System.out.println("data was succesfully processed");
            } catch (UserDataFormatException e) {
             System.out.println("Error: " + e.getMessage());
            }
             System.out.println("Write data down? (yes/no):");

             String choice = reader.readLine();
                if (!choice.equalsIgnoreCase("yes")) {
                    break;
                }
            }
            for (Map.Entry<String, UserData> entry : userDataMap.entrySet()) {
                String fileName = entry.getKey() + ".txt";
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                    writer.write(entry.getValue().toString());
                    writer.newLine();
                }
            }

    System.out.println("Data were succesfully saved in files");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class UserData {
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private String phoneNumber;
    private String gender;

    public UserData(String lastName, String firstName, String middleName, String birthDate, String phoneNumber, String gender) throws UserDataFormatException {
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new UserDataFormatException("Error: use format dd.mm.yyyy");
        }

        if (!phoneNumber.matches("\\d+")) {
            throw new UserDataFormatException("Error: use only numbers");
        
        }

     if (!gender.matches("[fm]")) {
     throw new UserDataFormatException("Error: use'f' for female or 'm' for male");          
        }

        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return lastName + firstName + middleName + birthDate + phoneNumber + gender;
    }
}

class UserDataFormatException extends Exception {
    public UserDataFormatException(String message) {
        super(message);
    }
}
