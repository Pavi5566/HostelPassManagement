import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class DataStore {
    public static List<Student> students = new ArrayList<>();
    public static List<PassRequest> passRequests = new ArrayList<>();

    public static void loadData() {
        try {
            File sFile = new File("data/students.txt");
            if(sFile.exists()){
                Scanner sc = new Scanner(sFile);
                while(sc.hasNextLine()){
                    String[] parts = sc.nextLine().split(",");
                    if(parts.length>=4)
                        students.add(new Student(parts[0], parts[1], parts[2], parts[3]));
                }
                sc.close();
            }

            File pFile = new File("data/pass_requests.txt");
            if(pFile.exists()){
                Scanner sc = new Scanner(pFile);
                while(sc.hasNextLine()){
                    String[] parts = sc.nextLine().split(",");
                    if(parts.length>=4){
                        LocalDateTime dt = LocalDateTime.parse(parts[3]);
                        passRequests.add(new PassRequest(parts[0], parts[1], parts[2], dt));
                    }
                }
                sc.close();
            }
        } catch(Exception e){ e.printStackTrace(); }
    }

    public static void saveData() {
        try {
            PrintWriter sWriter = new PrintWriter("data/students.txt");
            for(Student s : students){
                sWriter.println(s.name + "," + s.rollNo + "," + s.hostelRoom + "," + s.phoneNumber);
            }
            sWriter.close();

            PrintWriter pWriter = new PrintWriter("data/pass_requests.txt");
            for(PassRequest p : passRequests){
                pWriter.println(p.toFileString());
            }
            pWriter.close();
        } catch(Exception e){ e.printStackTrace(); }
    }
}
