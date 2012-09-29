package pt.isel.cc.thoth.api.client.entity;

import java.net.HttpURLConnection;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pt.isel.cc.thoth.api.client.ThothConstants;
import pt.isel.cc.thoth.api.client.ThothEntity;
import pt.isel.cc.thoth.api.client.ThothException;
import pt.isel.cc.thoth.api.client.ThothUtils;

import com.github.kevinsawicki.http.HttpRequest;

public class CourseClass extends ThothEntity<Integer> {
    private String              fullName;
    private String              courseUnitShortName;
    private String              lectiveSemesterShortName;
    private String              className;
    private String              mainTeacherShortName;

    private CourseClassDetailed details;

    private static class CourseClassDetailed {
        private int courseUnitId;
        private int lectiveSemesterId;
        private int maxGroupSize;
    }

    @Override
    protected void getDetails() throws ThothException {
        String classUrl = ThothUtils.appendUrl(getBaseUrl(), ThothConstants.THOTH_API_CLASSES_ROOT, String.valueOf(getId()));
        HttpRequest request = HttpRequest.get(classUrl);
        if (request.code() != HttpURLConnection.HTTP_OK) {
            throw new ThothException(MessageFormat.format("Invalid Response Status Code: {0}\nURL: {1}",
                    request.code(), classUrl));
        }
        String jsonResponse = request.body();
        details = detailsFromJSON(jsonResponse);
    }

    public static CourseClassDetailed detailsFromJSON(String json) throws ThothException {
        try {
            CourseClassDetailed courseDetails = new CourseClassDetailed();

            JSONObject object = new JSONObject(json);
            courseDetails.courseUnitId = object.getInt("courseUnitId");
            courseDetails.lectiveSemesterId = object.getInt("lectiveSemesterId");
            courseDetails.maxGroupSize = object.getInt("maxGroupSize");
            return courseDetails;
        } catch (Exception e) {
            throw new ThothException(e);
        }
    }
    
    public static List<CourseClass> getCoursesFromJSON(String json, String baseUrl) throws ThothException {
        // TODO: Who wants some Spaghetti??? No, really, this code must be optimized...
        if( json.trim().isEmpty() ) {
            return Collections.emptyList();
        }
        final List<CourseClass> courseClasses = new LinkedList< CourseClass >();
        try {
            JSONObject jsonbase = new JSONObject(json);
            JSONArray classes = jsonbase.getJSONArray( "classes" );
            for( int classesIdx = 0; classesIdx < classes.length(); ++classesIdx ) {
                JSONObject courseClassJson = classes.getJSONObject( classesIdx );
                int id = courseClassJson.getInt( "id" );
                String fullName = courseClassJson.getString( "fullName" );
                String courseUnitShortName = courseClassJson.getString( "courseUnitShortName" );
                String lectiveSemesterShortName = courseClassJson.getString( "lectiveSemesterShortName" );
                String className = courseClassJson.getString( "className" );
                String mainTeacherShortName = courseClassJson.getString( "mainTeacherShortName" );
                CourseClass courseClass = new CourseClass(baseUrl, id, fullName, courseUnitShortName, lectiveSemesterShortName, className, mainTeacherShortName );
                courseClasses.add(courseClass);
            }
        } catch ( JSONException e ) {
            throw new ThothException( e );
        }
        return courseClasses;
    }        
    
    public CourseClass(String baseUrl, int id, String fullName, String courseUnitShortName, String lectiveSemesterShortName,
            String className, String mainTeacherShortName) {
        // TODO: CHANGE THIS ASAP
        super(baseUrl, id);
        this.fullName = fullName;
        this.courseUnitShortName = courseUnitShortName;
        this.lectiveSemesterShortName = lectiveSemesterShortName;
        this.className = className;
        this.mainTeacherShortName = mainTeacherShortName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCourseUnitShortName() {
        return courseUnitShortName;
    }

    public String getLectiveSemesterShortName() {
        return lectiveSemesterShortName;
    }

    public String getClassName() {
        return className;
    }

    public String getMainTeacherShortName() {
        return mainTeacherShortName;
    }

    /*
     * Future Methods
     */

    public int getCourseUnitId() throws ThothException {
        ensureDetails();
        return details.courseUnitId;
    }

    public int getLectiveSemesterId() throws ThothException {
        ensureDetails();
        return details.lectiveSemesterId;
    }

    public int getMaxGroupSize() throws ThothException {
        ensureDetails();
        return details.maxGroupSize;
    }

    @Override
    public String toString() {
        try {
            return "CourseClassSimple [id=" + getId() + ", fullName=" + fullName + ", courseUnitShortName="
                    + courseUnitShortName + ", lectiveSemesterShortName=" + lectiveSemesterShortName + ", className="
                    + className + ", mainTeacherShortName=" + mainTeacherShortName + ", details="
                    + details + ", key=" + key + ", baseUrl=" + getBaseUrl() + ", getCourseUnitId()=" + getCourseUnitId()
                    + ", getLectiveSemesterId()=" + getLectiveSemesterId() + ", getMaxGroupSize()=" + getMaxGroupSize()
                    + "]";
        } catch (ThothException e) {
            e.printStackTrace();
            return null;
        }
    }
}
