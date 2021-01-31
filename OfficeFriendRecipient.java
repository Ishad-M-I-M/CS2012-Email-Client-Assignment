package assignment1;

import java.util.Date;

public class OfficeFriendRecipient extends OfficialEmailRecipient implements Greetable{
    private Date birthday;
    OfficeFriendRecipient(String name, String email, String designation, Date birthday) {
        super(name, email, designation);
        this.birthday = birthday;
    }

    @Override
    public String greetForBirthday(String sender) {
        return "Wish you a Happy Birthday. "+sender; // temp
    }

    public Date getBirthday() {
        return birthday;
    }
}