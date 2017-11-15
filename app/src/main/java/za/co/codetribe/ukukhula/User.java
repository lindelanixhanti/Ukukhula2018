package za.co.codetribe.ukukhula;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.type;
import static android.R.attr.value;

/**
 * Created by User8 on 11/13/2017.
 */

public class User {


    private String address,contacts,email,gender,name,dateofbirth,qualifications,surname,user_role;

    public User() {
    }

    public User(String address) {
        this.address = address;
    }

    public User(String address, String contacts, String email, String gender, String name, String dateofbirth, String qualifications, String surname, String user_role) {
        this.address = address;

        this.contacts = contacts;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.dateofbirth = dateofbirth;
        this.qualifications = qualifications;
        this.surname = surname;
        this.user_role = user_role;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("surname", surname);
        result.put("contacts", contacts);
        result.put("dateofbirth", dateofbirth);
        result.put("address", address);
        result.put("user_role",user_role);
        result.put("qualifications",qualifications);
        result.put("email",email);
        result.put("gender",gender);
        return result;
    }
}
