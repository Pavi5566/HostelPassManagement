import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PassRequest {
    public String rollNo;
    public String reason;
    public String status;
    public LocalDateTime dateTime;

    public PassRequest(String rollNo, String reason, String status, LocalDateTime dateTime){
        this.rollNo = rollNo;
        this.reason = reason;
        this.status = status;
        this.dateTime = dateTime;
    }

    public String toFileString() {
        return rollNo + "," + reason + "," + status + "," + dateTime.toString();
    }

    public String toDisplayString() {
        return reason + " -> " + status + " (" + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}
