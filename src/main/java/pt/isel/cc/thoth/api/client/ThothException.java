package pt.isel.cc.thoth.api.client;

public class ThothException extends Exception {
    public ThothException( String reason ) {
        super( reason );
    }

    public ThothException( Throwable innerException ) {
        super( innerException );
    }
}
