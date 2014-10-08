package pt.isel.cc.thoth.api.client;

import java.text.MessageFormat;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import pt.isel.cc.thoth.api.client.Thoth;

public class ThothTests extends TestCase {

    private Thoth  thothApiObject;

    private int    thothVersionForTest;
    private String thothBaseUrlWithVersion;

    @Override
    @Before
    protected void setUp() throws Exception {
        super.setUp();
        thothApiObject = new Thoth(thothVersionForTest);
        thothVersionForTest = Thoth.Version.V1;
        thothBaseUrlWithVersion = MessageFormat.format("http://thoth.cc.e.ipl.pt/api/v{0}",
                String.valueOf(thothVersionForTest));
    }

    @Test
    public void testIfVersionIsCorrect() {
        assertEquals(thothVersionForTest, thothApiObject.getCurrentWorkingVersion());
        String thothObjUrl = thothApiObject.getBaseUrl();
        assertEquals(thothBaseUrlWithVersion, thothObjUrl);
    }
}
