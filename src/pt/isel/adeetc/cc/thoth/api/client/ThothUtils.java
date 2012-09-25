package pt.isel.adeetc.cc.thoth.api.client;

class ThothUtils {
    private ThothUtils() {
    }

    private static final String URL_PATH_SEPARATOR = "/";

    public static String appendUrl( String url, String path ) {
        StringBuilder builder = new StringBuilder();
        builder.append( url );
        if ( !url.endsWith( URL_PATH_SEPARATOR ) ) {
            builder.append( URL_PATH_SEPARATOR );
        }
        if ( path.startsWith( URL_PATH_SEPARATOR ) ) {
            builder.append( path.substring( 1 ) );
        } else {
            builder.append( path );
        }
        return builder.toString();
    }
}
