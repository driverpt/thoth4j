package pt.isel.cc.thoth.api.client;

public abstract class ThothEntity<K> {
    
    protected K key;
    
    // TODO: This will be final, it is modifiable for debugging purposes
    private String baseUrl;
    
    protected volatile boolean detailRetrieved = false;
    
    protected ThothEntity(String baseUrl, K key) {
        this.baseUrl = baseUrl;
        this.key = key;
    }
    
    protected void getDetails() throws ThothException {}
    
    protected final void ensureDetails() throws ThothException {
        if( !detailRetrieved ) {
            getDetails();
            detailRetrieved = true;
        }
    }
    
    public K getId() {
        return key;
    }

    public void setId(K key) {
        this.key = key;
    }
    
    protected String getBaseUrl() {
        return baseUrl;
    }
}
