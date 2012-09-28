package pt.isel.adeetc.cc.thoth.api.client;

import java.net.HttpURLConnection;
import java.util.LinkedList;
import java.util.List;

import com.github.kevinsawicki.http.HttpRequest;

import pt.isel.adeetc.cc.thoth.api.client.entity.CourseClass;
import pt.isel.adeetc.cc.thoth.api.client.entity.Program;

// TODO: Make this code more OO
public class Thoth {
    private String baseUrl;
    private int    currentWorkingVersion;

    public Thoth(int version) {
        // TODO: Version Validation
        baseUrl = ThothUtils
                .appendUrl(ThothConstants.THOTH_API_ROOT_URL, ThothConstants.THOTH_VERSION_PREFIX + version);
        currentWorkingVersion = version;
    }

    public static class Version {
        public static final int V1 = 1;
    }

    public List<CourseClass> getAllClasses() throws ThothException {
        String classesUrl = ThothUtils.appendUrl(baseUrl, ThothConstants.THOTH_API_CLASSES_ROOT);
        String jsonResponse = getBodyAsStringFromUrl( classesUrl );

        return CourseClass.getCoursesFromJSON(jsonResponse, baseUrl);
    }

    public List<CourseClass> getCourseClassByName(String name) throws ThothException {
        List<CourseClass> allClasses = getAllClasses();
        List<CourseClass> filteredClasses = new LinkedList<CourseClass>();
        for (CourseClass courseClass : allClasses) {
            if (courseClass.getClassName().equals(name)) {
                filteredClasses.add(courseClass);
            }
        }

        return filteredClasses;
    }

    public List<Program> getAllPrograms() throws ThothException {
        String programsUrl = ThothUtils.appendUrl( baseUrl, ThothConstants.THOTH_API_PROGRAMS_ROOT );
        String jsonResponse = getBodyAsStringFromUrl( programsUrl );
        
        return Program.getProgramsFromJSON(jsonResponse, baseUrl);
    }
    
    public int getCurrentWorkingVersion() {
        return currentWorkingVersion;
    }
    
    String getBaseUrl() {
        return baseUrl;
    }
    
    private static String getBodyAsStringFromUrl(String url) throws ThothException{
        HttpRequest request = HttpRequest.get(url);
        if (request.code() != HttpURLConnection.HTTP_OK) {
            throw new ThothException("Invalid Http Response");
        }
        String response = request.body();
        return response;
    }
}
