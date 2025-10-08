import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Student {
    public String name;
    public String rollNo;
    public String hostelRoom;
    public String phoneNumber;

    public Student(String name, String rollNo, String hostelRoom, String phoneNumber){
        this.name = name;
        this.rollNo = rollNo;
        this.hostelRoom = hostelRoom;
        this.phoneNumber = phoneNumber;
    }

    public static Student loginGUI() {
        String roll = JOptionPane.showInputDialog("Enter your Roll No:");
        if(roll == null) return null;
        Student current = null;
        for(Student s : DataStore.students){
            if(s.rollNo.equals(roll)){
                current = s;
                break;
            }
        }
        if(current == null){
            String name = JOptionPane.showInputDialog("Not found. Enter name to register:");
            String room = JOptionPane.showInputDialog("Enter hostel room:");
            String phone = JOptionPane.showInputDialog("Enter phone number:");
            current = new Student(name, roll, room, phone);
            DataStore.students.add(current);
            DataStore.saveData();
            JOptionPane.showMessageDialog(null, "Registered successfully!");
        }
        return current;
    }

    public void studentDashboard() {
        while(true){
            String[] options = {"Apply for Outpass", "View My Requests", "Cancel Pending Request", "Logout"};
            int choice = JOptionPane.showOptionDialog(null, "Choose an action", "Student Dashboard",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if(choice == 0){ // Apply
                String reason = JOptionPane.showInputDialog("Enter reason for outpass:");
                if(reason != null && !reason.isEmpty()){
                    PassRequest p = new PassRequest(this.rollNo, reason, "Pending", LocalDateTime.now());
                    DataStore.passRequests.add(p);
                    DataStore.saveData();
                    JOptionPane.showMessageDialog(null, "Request submitted!");
                }
            } else if(choice == 1){ // View
                ArrayList<String> reqs = new ArrayList<>();
                for(PassRequest p : DataStore.passRequests){
                    if(p.rollNo.equals(this.rollNo)) reqs.add(p.toDisplayString());
                }
                if(reqs.isEmpty()) reqs.add("No requests found.");
                JOptionPane.showMessageDialog(null, String.join("\n", reqs));
            } else if(choice == 2){ // Cancel
                ArrayList<PassRequest> pendings = new ArrayList<>();
                for(PassRequest p : DataStore.passRequests){
                    if(p.rollNo.equals(this.rollNo) && p.status.equals("Pending")) pendings.add(p);
                }
                if(pendings.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No pending requests to cancel.");
                    continue;
                }
                String[] pendingList = new String[pendings.size()];
                for(int i=0;i<pendings.size();i++) pendingList[i] = pendings.get(i).toDisplayString();
                String sel = (String) JOptionPane.showInputDialog(null, "Select request to cancel", "Cancel Request",
                        JOptionPane.QUESTION_MESSAGE, null, pendingList, pendingList[0]);
                if(sel != null){
                    for(PassRequest p : pendings){
                        if(p.toDisplayString().equals(sel)){
                            DataStore.passRequests.remove(p);
                            DataStore.saveData();
                            JOptionPane.showMessageDialog(null, "Request canceled successfully.");
                            break;
                        }
                    }
                }
            } else break; // Logout
        }
    }
}
