import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DataStore.loadData();

        while(true){
            String[] options = {"Student Login", "Admin Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Hostel Pass Management", "Main Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if(choice == 0){
                Student s = Student.loginGUI();
                if(s!=null) s.studentDashboard();
            } else if(choice == 1){
                Admin.loginGUI();
            } else break;
        }

        DataStore.saveData();
        JOptionPane.showMessageDialog(null,"Exiting...");
        System.exit(0);
    }
}
