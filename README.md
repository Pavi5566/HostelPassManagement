# 🏫 Hostel Pass Management System

A **Java-based Hostel Pass Management System** with a **Swing GUI** for managing student outpass requests.  
This project allows students to register, apply for outpasses, view or cancel requests, and allows admins to approve or reject requests.  
All data is persisted in simple `.txt` files, making it lightweight and easy to run.

---

## ✨ Features

### 👩‍🎓 For Students
- 📝 Register and login using Roll Number.
- 🏃 Apply for outpass with a reason.
- 👀 View all requests with their status (Pending, Approved, Rejected).
- ❌ Cancel pending requests.

### 👨‍💼 For Admin
- 🔐 Admin login with password (`admin123` by default).
- 📋 View all student pass requests in a readable format.
- ✅ Approve or ❌ Reject requests.
- 🔎 Search requests by Roll Number.
- 📌 Filter requests by status (Pending, Approved, Rejected).
- 📊 Generate a summary report:
  - Total Requests
  - Approved
  - Rejected
  - Pending

### 💾 Persistence
- Student and request data is stored in `.txt` files:
  - `students.txt` → Stores student information.
  - `pass_requests.txt` → Stores outpass requests with status and timestamp.
- Data is automatically loaded on startup and saved on updates.

---

## 🛠 Tech Stack
- Java 17+  
- Swing GUI  
- File-based storage (`.txt` files)  

---

## 📂 Project Structure

HostelPassManagement/
├─ src/
│ ├─ Main.java -> Entry point of the application
│ ├─ Student.java -> Handles student operations
│ ├─ Admin.java -> Handles admin operations
│ ├─ PassRequest.java -> Pass request model
│ └─ DataStore.java -> Loads/saves data to text files
├─ data/
│ ├─ students.txt -> Stores registered students
│ └─ pass_requests.txt -> Stores outpass requests
├─ README.md
├─ .gitignore
└─ LICENSE

yaml
Copy code

---

## ▶️ How to Run

1. Clone the repository:

```bash
git clone https://github.com/YourUsername/HostelPassManagement.git
Navigate to the project folder:

bash
Copy code
cd HostelPassManagement/src
Ensure the data/ folder exists with empty files:

Copy code
students.txt
pass_requests.txt
Open the project in VS Code or any Java IDE.

Run Main.java to launch the GUI.

Use the Student/Admin GUI to perform actions.

🖼 Sample Workflow
Launch main menu → Choose Student Login 👩‍🎓

Enter Roll No → Register if new 📝

Apply for outpass → Status is "Pending" 🏃

Admin logs in → Approve/Reject requests 👨‍💼

Student views updated status → Persisted in .txt files 💾

🚀 Future Enhancements
💾 Integrate with SQL database instead of .txt for scalability.

📧 Add email/SMS notifications for pass approval.

🔐 Add login authentication with passwords for students.

📄 Add report export (PDF/CSV) for admin.

🎨 Improve GUI with JavaFX for better user experience.

