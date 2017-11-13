package za.co.codetribe.ukukhula.Groups;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Codetribe on 2017/11/10.
 */

public class Group {

    private String GroupNane;
    String GroupTeacher;


    public Group() {

    }

    public Group(String groupNane, String groupTeacher) {
        GroupNane = groupNane;
        GroupTeacher = groupTeacher;
    }

    public String getGroupNane() {
        return GroupNane;
    }

    public void setGroupNane(String groupNane) {
        GroupNane = groupNane;
    }

    public String getGroupTeacher() {
        return GroupTeacher;
    }

    public void setGroupTeacher(String groupTeacher) {
        GroupTeacher = groupTeacher;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Group names", GroupNane);
        result.put("Group teacher", GroupTeacher);

        return result;
    }
}
