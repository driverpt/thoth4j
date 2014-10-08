package pt.isel.cc.thoth.api.client.entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import pt.isel.cc.thoth.api.client.ThothEntity;
import pt.isel.cc.thoth.api.client.ThothException;

public class Program extends ThothEntity< Integer > {
    private String shortName;
    private String fullName;

    public Program( String baseUrl, int id, String shortName, String fullName ) {
        super( baseUrl, id );
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public static List<Program> getProgramsFromJSON( String json, String baseUrl ) throws ThothException {
        // TODO: Who wants some Spaghetti??? No, really, this code must be optimized...
        if( json.trim().isEmpty() ) {
            return Collections.emptyList();
        }
        final List<Program> programs = new LinkedList<Program>();
        try {
            JSONObject jsonbase = new JSONObject(json);
            JSONArray classes = jsonbase.getJSONArray( "programs" );
            for( int programsIdx = 0; programsIdx < classes.length(); ++programsIdx ) {
                JSONObject courseClassJson = classes.getJSONObject( programsIdx );
                int id = courseClassJson.getInt( "id" );
                String shortName = courseClassJson.getString( "shortName" );
                String fullName = courseClassJson.getString( "name" );
                Program program = new Program(baseUrl, id, shortName, fullName);
                programs.add(program);
            }
        } catch ( JSONException e ) {
            throw new ThothException( e );
        }
        return programs;
    }
}
