package assignment1;

import java.util.Date;

// customized date class for handle date related functions in the assignment
public class NewDate {
    private int year;
    private int month;
    private int day;

    public NewDate(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public NewDate(String dateString){
        String[] info = dateString.trim().split("/");
        this.year = Integer.parseInt(info[0]);
        this.month = Integer.parseInt(info[1]);
        this.day = Integer.parseInt(info[2]);

    }
    public NewDate(){
        Date today = new Date();
        this.year = today.getYear()+1900;
        this.month = today.getMonth()+1;
        this.day = today.getDate();
    }

    public NewDate(Date date){
        this.year = date.getYear()+1900;
        this.month = date.getMonth()+1;
        this.day = date.getDate();
    }

    public boolean checkForBirthday(NewDate date){
        return (this.month == date.month) && (this.day == date.day);
    }
    public boolean checkForBirthday(){
        NewDate today = new NewDate();
        return this.checkForBirthday(today);
    }

    public String toString(){
        if (this.month <10 && this.day<10)
            return this.year+"/0"+this.month+"/0"+this.day;
        else if (this.month<10)
            return this.year+"/0"+this.month+"/"+this.day;
        else if (this.day <10)
            return this.year+"/"+this.month+"/0"+this.day;
        else
            return this.year+"/"+this.month+"/"+this.day;
    }

    public boolean equals(NewDate other){
        return (this.year == other.year) && (this.month == other.month) && (this.day == other.day);
    }

    public static String getCurrentTime(){
        Date date = new Date();
        return ((new NewDate(date)).toString() +" "+ date.getHours())+":"+date.getMinutes()+":"+date.getSeconds();
    }

    public static String getTime(Date date){
        return ((new NewDate(date)).toString() +" "+ date.getHours())+":"+date.getMinutes()+":"+date.getSeconds();
    }

}
