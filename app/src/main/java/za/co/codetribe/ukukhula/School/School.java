package za.co.codetribe.ukukhula.School;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import static za.co.codetribe.ukukhula.R.id.contact_details;
import static za.co.codetribe.ukukhula.R.id.school_address;
import static za.co.codetribe.ukukhula.R.id.school_manager;
import static za.co.codetribe.ukukhula.R.id.school_name;

/**
 * Created by User8 on 11/6/2017.
 */

public class School {

    private String year;
    private String schoolname;
    private String schooladdress;
    private String schoolmanager;
    private String contactdetails;
    private String emailaddress;

    // Children button will add it later once we have the right structure


    public School() {

    }

    public School(String year, String schoolname,String schooladdress, String schoolmanager, String contactdetails, String emailaddress) {
        this.schoolname = schoolname;
        this.schooladdress = schooladdress;
        this.schoolmanager = schoolmanager;
        this.contactdetails = contactdetails;
        this.emailaddress = emailaddress;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getSchooladdress() {
        return schooladdress;
    }

    public void setSchooladdress(String schooladdress) {
        this.schooladdress = schooladdress;
    }

    public String getSchoolmanager() {
        return schoolmanager;
    }

    public void setSchoolmanager(String schoolmanager) {
        this.schoolmanager = schoolmanager;
    }

    public String getContactdetails() {
        return contactdetails;
    }

    public void setContactdetails(String contactdetails) {
        this.contactdetails = contactdetails;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("year", year);
        result.put("School_Name", schoolname);
        result.put("School_Address", schooladdress);
        result.put("School_Manager", schoolmanager);
        result.put("Contact-Details", contactdetails);
        result.put("Email_address", emailaddress);
        return result;
    }


}

