package za.co.codetribe.ukukhula.learner;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.Transaction;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.type;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Codetribe on 2017/10/25.
 */

public class LearnerProfile {

    private String names;
    private String surname;
    private String allegies;
    private String date_of_birth;
    private String parentName;
    String contacts;
    String Gender;
    String className;
    String favouriteMeal;

    // Children but will add it later once we have the right structure


    public LearnerProfile() {
    }

    public LearnerProfile(String names, String surname, String allegies, String date_of_birth, String parentName, String contacts, String gender, String className, String favouriteMeal) {
        this.names = names;
        this.surname = surname;
        this.allegies = allegies;
        this.date_of_birth = date_of_birth;
        this.parentName = parentName;
        this.contacts = contacts;
        Gender = gender;
        this.className = className;
        this.favouriteMeal = favouriteMeal;
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

    public String getAllegies() {
        return allegies;
    }

    public void setAllegies(String allegies) {
        this.allegies = allegies;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFavouriteMeal() {
        return favouriteMeal;
    }

    public void setFavouriteMeal(String favouriteMeal) {
        this.favouriteMeal = favouriteMeal;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("names", names);
        result.put("surname", surname);
        result.put("contact_number", contacts);
        result.put("date_of_birth", date_of_birth);
        result.put("favouriteMeal", favouriteMeal);
        result.put("gender",Gender);
        result.put("allegies",allegies);
        result.put("parentName",parentName);
        result.put("className",className);



        return result;
    }
}
