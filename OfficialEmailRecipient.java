package assignment1;

public class OfficialEmailRecipient extends EmailRecipient{
    private String designation;
    OfficialEmailRecipient(String name, String email, String designation) {
        super(name, email);
        this.designation = designation;
    }
}
