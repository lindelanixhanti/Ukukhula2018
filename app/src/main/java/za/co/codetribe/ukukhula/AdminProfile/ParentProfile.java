package za.co.codetribe.ukukhula.AdminProfile;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Codetribe on 2017/10/25.
 */

public class ParentProfile {

    private String names;
    private String surname;
    private String contact_number;
    private String date_of_birth;
    private String address;
    String type;

    // Children but will add it later once we have the right structure


    public ParentProfile() {
    }


    public ParentProfile(String names, String surname, String contact_number, String date_of_birth, String address,String type) {
        this.names = names;
        this.surname = surname;
        this.contact_number = contact_number;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("names", names);
        result.put("surname", surname);
        result.put("contact_number", contact_number);
        result.put("date_of_birth", date_of_birth);
        result.put("address", address);
        result.put("type",type);
        return result;
    }
}
