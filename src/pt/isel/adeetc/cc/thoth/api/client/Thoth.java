package pt.isel.adeetc.cc.thoth.api.client;

import java.util.LinkedList;
import java.util.List;
import java.net.HttpURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.kevinsawicki.http.HttpRequest;

import pt.isel.adeetc.cc.thoth.api.client.entity.CourseClass;
import pt.isel.adeetc.cc.thoth.api.client.entity.CourseClassSimple;

// TODO: Make this code more OO
public class Thoth {
    private String baseUrl;
    private int currentWorkingVersion;
    
    public Thoth(int version) {
        // TODO: Version Validation
        baseUrl = ThothUtils.appendUrl( ThothConstants.THOTH_API_ROOT_URL, ThothConstants.THOTH_VERSION_PREFIX + version );
        currentWorkingVersion = version;
    }
    
    public static class Version {
        public static final int V1 = 1;
    }
    
    public List<CourseClassSimple> getAllClasses() throws ThothException {
        // TODO: Who wants some Spaghetti??? No, really, this code must be optimized...
        String classesUrl = ThothUtils.appendUrl( baseUrl, ThothConstants.THOTH_API_CLASSES_ROOT );
        HttpRequest request = HttpRequest.get( classesUrl );
        String jsonResponse = request.body();
        if ( request.code() != HttpURLConnection.HTTP_OK ) {
            throw new ThothException("Invalid Http Response");
        }
        List<CourseClassSimple> courseClasses = new LinkedList< CourseClassSimple >();
        try {
            JSONObject jsonbase = new JSONObject(jsonResponse);
            JSONArray classes = jsonbase.getJSONArray( "classes" );
            for( int i = 0; i < classes.length(); ++i ) {
                JSONObject courseClassJson = classes.getJSONObject( i );
                int id = courseClassJson.getInt( "id" );
                String fullName = courseClassJson.getString( "fullName" );
                String courseUnitShortName = courseClassJson.getString( "courseUnitShortName" );
                String lectiveSemesterShortName = courseClassJson.getString( "lectiveSemesterShortName" );
                String className = courseClassJson.getString( "className" );
                String mainTeacherShortName = courseClassJson.getString( "mainTeacherShortName" );
                CourseClassSimple courseClass = new CourseClassSimple(id, fullName, courseUnitShortName, lectiveSemesterShortName, className, mainTeacherShortName );
                courseClasses.add(courseClass);
            }
        } catch ( JSONException e ) {
            throw new ThothException( e );
        }
        return courseClasses;
    }
    
    public CourseClass getClassById(int id) {
        throw new UnsupportedOperationException( "Not yet implemented" );
    }
}
