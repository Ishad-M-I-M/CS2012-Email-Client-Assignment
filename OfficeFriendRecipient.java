package assignment1;

public class OfficeFriendRecipient extends OfficialEmailRecipient implements Greetable{
    private NewDate birthday;
    OfficeFriendRecipient(String name, String email, String designation, NewDate birthday) {
        super(name, email, designation);
        this.birthday = birthday;
    }

    @Override
    public String greetForBirthday(String sender) {
        return "Wish you a Happy Birthday. "+sender; // temp
    }

    @Override
    public NewDate getBirthday() {
        return birthday;
    }
}