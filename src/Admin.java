import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class Admin {
    public static void loginGUI() {
        String pass = JOptionPane.showInputDialog("Enter admin password:");
        if(pass == null || !pass.equals("admin123")){
            JOptionPane.showMessageDialog(null, "Wrong password!");
            return;
        }

        while(true){
            String[] options = {"View All Requests", "Approve/Reject Request", "Search by Roll No", "Filter by Status", "View Report", "Logout"};
            int choice = JOptionPane.showOptionDialog(null, "Choose action", "Admin Dashboard",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if(choice == 0){ // View All
                ArrayList<String> list = new ArrayList<>();
                int idx = 1;
                for(PassRequest p : DataStore.passRequests){
                    list.add(idx + ". " + p.rollNo + " - " + p.toDisplayString());
                    idx++;
                }
                JOptionPane.showMessageDialog(null, list.isEmpty()?"No requests":String.join("\n", list));
            } else if(choice == 1){ // Approve/Reject
                List<PassRequest> allReqs = DataStore.passRequests;
                if(allReqs.isEmpty()){ JOptionPane.showMessageDialog(null,"No requests"); continue; }
                String[] reqList = new String[allReqs.size()];
                for(int i=0;i<allReqs.size();i++) reqList[i] = allReqs.get(i).rollNo + " - " + allReqs.get(i).toDisplayString();
                String sel = (String) JOptionPane.showInputDialog(null, "Select request", "Approve/Reject", JOptionPane.QUESTION_MESSAGE, null, reqList, reqList[0]);
                if(sel != null){
                    for(PassRequest p : allReqs){
                        if(sel.contains(p.rollNo)){
                            String ar = JOptionPane.showInputDialog("Approve or Reject (A/R):");
                            if(ar.equalsIgnoreCase("A")) p.status = "Approved";
                            else p.status = "Rejected";
                            DataStore.saveData();
                            JOptionPane.showMessageDialog(null,"Updated!");
                            break;
                        }
                    }
                }
            } else if(choice == 2){ // Search by Roll
                String roll = JOptionPane.showInputDialog("Enter Roll No to search:");
                ArrayList<String> res = new ArrayList<>();
                for(PassRequest p : DataStore.passRequests){
                    if(p.rollNo.equals(roll)) res.add(p.toDisplayString());
                }
                JOptionPane.showMessageDialog(null, res.isEmpty()?"No requests":String.join("\n", res));
            } else if(choice == 3){ // Filter by status
                String status = JOptionPane.showInputDialog("Enter status (Pending/Approved/Rejected):");
                ArrayList<String> res = new ArrayList<>();
                for(PassRequest p : DataStore.passRequests){
                    if(p.status.equalsIgnoreCase(status)) res.add(p.rollNo + " - " + p.toDisplayString());
                }
                JOptionPane.showMessageDialog(null, res.isEmpty()?"No requests":String.join("\n", res));
            } else if(choice == 4){ // Report
                int total = DataStore.passRequests.size();
                int approved=0, rejected=0, pending=0;
                for(PassRequest p : DataStore.passRequests){
                    switch(p.status){
                        case "Approved": approved++; break;
                        case "Rejected": rejected++; break;
                        case "Pending": pending++; break;
                    }
                }
                JOptionPane.showMessageDialog(null,
                        "Total Requests: " + total + "\nApproved: " + approved + "\nRejected: " + rejected + "\nPending: " + pending);
            } else break; // Logout
        }
    }
}
