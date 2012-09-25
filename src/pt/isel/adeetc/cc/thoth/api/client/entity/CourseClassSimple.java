package pt.isel.adeetc.cc.thoth.api.client.entity;

public class CourseClassSimple {
    private int    id;
    private String fullName;
    private String courseUnitShortName;
    private String lectiveSemesterShortName;
    private String className;
    private String mainTeacherShortName;

    public CourseClassSimple( int id, String fullName, String courseUnitShortName, String lectiveSemesterShortName,
            String className, String mainTeacherShortName ) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.courseUnitShortName = courseUnitShortName;
        this.lectiveSemesterShortName = lectiveSemesterShortName;
        this.className = className;
        this.mainTeacherShortName = mainTeacherShortName;
    }

    public int getId() {
        return id;
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

    public void setId( int id ) {
        this.id = id;
    }

    public void setFullName( String fullName ) {
        this.fullName = fullName;
    }

    public void setCourseUnitShortName( String courseUnitShortName ) {
        this.courseUnitShortName = courseUnitShortName;
    }

    public void setLectiveSemesterShortName( String lectiveSemesterShortName ) {
        this.lectiveSemesterShortName = lectiveSemesterShortName;
    }

    public void setClassName( String className ) {
        this.className = className;
    }

    public void setMainTeacherShortName( String mainTeacherShortName ) {
        this.mainTeacherShortName = mainTeacherShortName;
    }
}
