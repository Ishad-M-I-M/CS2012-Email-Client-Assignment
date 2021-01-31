package assignment1;

import java.util.Date;

public class PersonalEmailRecipient extends EmailRecipient implements Greetable{
    private String nickname;
    private Date birthday;
    PersonalEmailRecipient(String name,String nickname, String email, Date birthday) {
        super(name, email);
        this.nickname = nickname;
        this.birthday = birthday;
    }

    @Override
    public String greetForBirthday(String sender) {
        return "hugs and love on your birthday. "+sender; // temp
    }

    public Date getBirthday() {
        return birthday;
    }
}
