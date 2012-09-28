package pt.isel.adeetc.cc.thoth.api.client.entity;

import java.net.HttpURLConnection;
import java.text.MessageFormat;

import org.json.JSONObject;

import com.github.kevinsawicki.http.HttpRequest;

import pt.isel.adeetc.cc.thoth.api.client.ThothConstants;
import pt.isel.adeetc.cc.thoth.api.client.ThothEntity;
import pt.isel.adeetc.cc.thoth.api.client.ThothException;
import pt.isel.adeetc.cc.thoth.api.client.ThothUtils;

public class Teacher extends ThothEntity< Integer > {

    private int    number;
    private String shortName;
    private String academicEmail;
    private String fullName;
    private String gravatarUrl;

    protected Teacher( String baseUrl, int key ) {
        super( baseUrl, key );
    }

    public Teacher( String baseUrl, int key, int number, String shortName, String academicEmail, String gravatarUrl ) {
        this( baseUrl, key );
        this.number = number;
        this.shortName = shortName;
        this.academicEmail = academicEmail;
        this.gravatarUrl = gravatarUrl;
    }

    public Teacher( String baseUrl, int key, int number, String shortName, String academicEmail, String gravatarUrl,
            String fullName ) {
        this( baseUrl, key, number, shortName, academicEmail, gravatarUrl );
        detailRetrieved = true;
        this.fullName = fullName;
    }

    public int getNumber() {
        return number;
    }

    public String getShortName() {
        return shortName;
    }

    public String getAcademicEmail() {
        return academicEmail;
    }

    public String getFullName() throws ThothException {
        ensureDetails();
        return fullName;
    }

    public String getGravatarUrl() {
        return gravatarUrl;
    }
    
    @Override
    protected void getDetails() throws ThothException {
        super.getDetails();
        String teacherUrl = ThothUtils.appendUrl(baseUrl, ThothConstants.THOTH_API_TEACHERS_ROOT, String.valueOf(getId()));
        HttpRequest request = HttpRequest.get(teacherUrl);
        if (request.code() != HttpURLConnection.HTTP_OK) {
            throw new ThothException(MessageFormat.format("Invalid Response Status Code: {0}\nURL: {1}",
                    request.code(), teacherUrl));
        }
        String jsonResponse = request.body();
        fullName = detailsFromJSON(jsonResponse);
    }
    
    static String detailsFromJSON(String json) throws ThothException {
        try {
            JSONObject object = new JSONObject(json);
            return object.getString( "fullName" );
        } catch (Exception e) {
            throw new ThothException(e);
        }
    }
}
