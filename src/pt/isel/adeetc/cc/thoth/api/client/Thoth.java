package pt.isel.adeetc.cc.thoth.api.client;

import java.net.HttpURLConnection;
import java.util.LinkedList;
import java.util.List;

import pt.isel.adeetc.cc.thoth.api.client.entity.CourseClass;

import com.github.kevinsawicki.http.HttpRequest;

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
        HttpRequest request = HttpRequest.get(classesUrl);
        String jsonResponse = request.body();
        if (request.code() != HttpURLConnection.HTTP_OK) {
            throw new ThothException("Invalid Http Response");
        }

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

    public int getCurrentWorkingVersion() {
        return currentWorkingVersion;
    }
    
    String getBaseUrl() {
        return baseUrl;
    }
}
