package assignment1;

import java.io.Serializable;

public abstract class EmailRecipient implements Serializable {
    private String name;
    private String email;

    EmailRecipient(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}