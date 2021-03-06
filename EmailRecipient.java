package assignment1;

public abstract class EmailRecipient {
    private String name;
    private String email;

    EmailRecipient(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
}