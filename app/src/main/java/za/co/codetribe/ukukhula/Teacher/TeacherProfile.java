package za.co.codetribe.ukukhula.Teacher;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.data;
import static android.R.attr.type;

/**
 * Created by Codetribe on 2017/10/25.
 */

public class TeacherProfile  implements Parcelable{

    private String name,surname,email,password,Address,Contacts,Qualifications, Gender,IdNumber,Type;
    private String className;

    // Children but will add it later once we have the right structure
    public TeacherProfile() {
    }

    public TeacherProfile(String name, String surname, String email, String password, String address, String contacts, String qualifications, String gender, String class_name, String idNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        Address = address;
        Contacts = contacts;
        Qualifications = qualifications;
        Gender = gender;
        className = class_name;
        IdNumber = idNumber;

    }

    public static final Creator<TeacherProfile> CREATOR = new Creator<TeacherProfile>() {
        @Override
        public TeacherProfile createFromParcel(Parcel in) {
            return new TeacherProfile(in);
        }

        @Override
        public TeacherProfile[] newArray(int size) {
            return new TeacherProfile[size];
        }
    };

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContacts() {
        return Contacts;
    }

    public void setContacts(String contacts) {
        Contacts = contacts;
    }

    public String getQualifications() {
        return Qualifications;
    }

    public void setQualifications(String qualifications) {
        Qualifications = qualifications;
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

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("names", name);
        result.put("surname", surname);
        result.put("contact_number", Contacts);
        result.put("id_number", IdNumber);
        result.put("qualification", Qualifications);
        result.put("gender",Gender);
        result.put("address",Address);
        result.put("class_name",className);
        result.put("email",email);
        result.put("password",password);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    //private String Name,Surname,Email,password,Address,Contacts,Qualifications, Gender,ClassName,IdNumber;
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Address);
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(Contacts);
        parcel.writeString(Gender);
        parcel.writeString(className);
        parcel.writeString(IdNumber);
        parcel.writeString(Qualifications);
    }
    public TeacherProfile(Parcel in){
        this.Address = in.readString();
        this.name = in.readString();
        this.surname = in.readString();
        this.className = in.readString();
        this.Qualifications = in.readString();
        this.Gender = in.readString();
        this.email = in.readString();
        this.Contacts = in.readString();
        this.IdNumber = in.readString();
        this.password = in.readString();
    }
}
