package pt.isel.adeetc.cc.thoth.api.client.entity;

public class Program {
    private int    id;
    private String shortName;
    private String fullName;

    public Program( int id, String shortName, String fullName ) {
        super();
        this.id = id;
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public void setShortName( String shortName ) {
        this.shortName = shortName;
    }

    public void setFullName( String fullName ) {
        this.fullName = fullName;
    }
}
