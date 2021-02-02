package assignment1;

import java.util.Date;

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
        this.day = today.getDay();
    }

    public boolean checkForBirthday(NewDate date){
        return (this.month == date.month) && (this.day == date.day);
    }
    public boolean checkForBirthday(){
        NewDate today = new NewDate();
        return this.checkForBirthday(today);
    }

    public String toString(){
        return this.year+"/"+this.month+"/"+this.day;
    }

    public boolean equals(NewDate other){
        return (this.year == other.year) && (this.month == other.month) && (this.day == other.day);
    }

}
