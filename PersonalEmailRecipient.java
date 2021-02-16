package assignment1;

public class PersonalEmailRecipient extends EmailRecipient implements Greetable{
    private String nickname;
    private NewDate birthday;
    PersonalEmailRecipient(String name,String nickname, String email, NewDate birthday) {
        super(name, email);
        this.nickname = nickname;
        this.birthday = birthday;
    }

    @Override
    public String greetForBirthday(String sender) {
        return "hugs and love on your birthday. "+sender;
    }

    @Override
    public NewDate getBirthday() {
        return birthday;
    }
}
