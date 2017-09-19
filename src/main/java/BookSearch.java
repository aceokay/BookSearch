import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Book;

public class BookSearch {
    private List<Book> books;

    public BookSearch() {
        this.books = new ArrayList<Book>();
    }

    public BookSearch loadJson(String location)
            throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = BookSearch.parseJSONFile(location);

        for (Object bookKey : jsonObject.keySet()) {
            Book newBook = mapper.readValue(jsonObject.get(bookKey).toString(), Book.class);
            this.books.add(newBook);
        }
        return this;
    }

    /**
     * 
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    private static JSONObject parseJSONFile(String filename)
            throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filename));

        return (JSONObject) obj;
    }

    public int getBookCount() {
        return this.books.size();
    }
}
