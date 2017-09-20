import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;

import models.Book;

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

    @Test
    public void testHashedBooksSingleResult() {
        List<Book> foundBooks = TestBookSearch.bookSearch.getbooksFor("collapse");
        assertThat(foundBooks.size(), is(1));
    }

    @Test
    public void testHashedBooksMultipleResults() {
        List<Book> foundBooks = TestBookSearch.bookSearch.getbooksFor("sandwich");
        assertThat(foundBooks.size(), is(9));
    }

    @Test
    public void testHashedBooksOriginalWordHadAnApostrophe() {
        List<Book> foundBooks = TestBookSearch.bookSearch.getbooksFor("oberons");
        assertThat(foundBooks.size(), is(2));
    }
}