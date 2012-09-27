package pt.isel.adeetc.cc.thoth.api.client;

public class ThothUtils {
    private ThothUtils() {
    }

    private static final String URL_PATH_SEPARATOR = "/";

    public static String appendUrl( String url, String ... paths ) {
        StringBuilder builder = new StringBuilder();
        String normalizedUrl = normalizePathSeparator(url);
        builder.append( normalizedUrl );
        for( String path : paths ) {
            builder.append(URL_PATH_SEPARATOR);
            String normalizedPath = normalizePathSeparator(path);
            builder.append(normalizedPath);
        }
        
        return builder.toString();
    }
    
    private static String normalizePathSeparator(String path ) {
        if( path.endsWith(URL_PATH_SEPARATOR) ) {
            return path.substring(0, path.length() - 1 );
        }
        return path;
    }
}
