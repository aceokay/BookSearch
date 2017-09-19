import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.logging.Logger;

import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class TestBookSearch {
    static private Logger LOGGER = 
            Logger.getLogger(TestBookSearch.class.getName());
    private static BookSearch bookSearch;

    @BeforeClass
    public static void setUp() {
        // Here we'll load all the JSON into one instance of BookSearch.
        TestBookSearch.bookSearch = new BookSearch();
        try {
            bookSearch.loadJson("json/bookdata.json");
        } catch (IOException | ParseException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Test
    public void testLoadJson() {
        assertThat(TestBookSearch.bookSearch.getBookCount(), is(9));
    }
}