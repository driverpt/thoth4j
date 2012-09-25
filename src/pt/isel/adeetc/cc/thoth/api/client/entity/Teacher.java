package pt.isel.adeetc.cc.thoth.api.client.entity;

import java.util.LinkedList;
import java.util.List;

public class Teacher {
    private int                     id;
    private int                     number;
    private String                  name;
    private String                  email;
    private List< GravatarPicture > gravatarUrls;

    public Teacher( int id, String name, String email, int number ) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.gravatarUrls = new LinkedList< Teacher.GravatarPicture >();
        this.number = number;
    }

    public Teacher( int id, String name, String email, int number, GravatarPicture ... gravatarUrls ) {
        this( id, name, email, number );
        for ( GravatarPicture picture : gravatarUrls ) {
            this.gravatarUrls.add( picture );
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber( int number ) {
        this.number = number;
    }

    public List< GravatarPicture > getGravatarPictureUrls() {
        return gravatarUrls;
    }

    public void addGravatarPictureUrl( GravatarPicture url ) {
        gravatarUrls.add( url );
    }

    public static class GravatarPicture {
        private int    size;
        private String url;

        public GravatarPicture( int size, String url ) {
            this.size = size;
            this.url = url;
        }

        public int getSize() {
            return size;
        }

        public String getUrl() {
            return url;
        }

        public void setSize( int size ) {
            this.size = size;
        }

        public void setUrl( String url ) {
            this.url = url;
        }
    }
}
