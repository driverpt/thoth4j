package pt.isel.adeetc.cc.thoth.api.client.entity;

import pt.isel.adeetc.cc.thoth.api.client.ThothEntity;
import pt.isel.adeetc.cc.thoth.api.client.ThothException;

public class Program extends ThothEntity<Integer> {
    private String shortName;
    private String fullName;

    public Program( String baseUrl, int id, String shortName, String fullName ) {
        super(baseUrl, id);
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    protected void getDetails() throws ThothException {
        // None Available
    }
}
