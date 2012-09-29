package pt.isel.cc.thoth.api.client.entity;

public class CourseUnit {
    private String shortName;
    private String fullName;
    private int    id;

    public CourseUnit( String shortName, String fullName, int id ) {
        super();
        this.shortName = shortName;
        this.fullName = fullName;
        this.id = id;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName( String shortName ) {
        this.shortName = shortName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName( String fullName ) {
        this.fullName = fullName;
    }

    /**
     * @param id the id to set
     */
    public void setId( int id ) {
        this.id = id;
    }
}
