package pt.isel.adeetc.cc.thoth;

import java.util.List;

import pt.isel.adeetc.cc.thoth.api.client.Thoth;
import pt.isel.adeetc.cc.thoth.api.client.entity.CourseClassSimple;

public class Main {
    public static void main( String[] args ) {
        Thoth thoth = new Thoth( Thoth.Version.V1 );
        List<CourseClassSimple> classes = thoth.getAllClasses();
        System.out.println(classes);
    }
}
