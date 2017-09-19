import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.Book;

public class BookSearch {
    private List<Book> books;
    private Map<String, List<Book>> hashedBooksByWord;

    public BookSearch() {
        this.books = new ArrayList<>();
        this.hashedBooksByWord = new HashMap<>();
    }

    /**
     * Loads JSON data from a given path location.
     * 
     * Parses JSON in two steps, first as generic objects, and finally casting
     * to Book models. All Books are saved to the books list.
     * 
     * @param path
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public BookSearch loadJson(String path)
            throws IOException, ParseException {
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObject = BookSearch.parseJSONFile(path);

        for (Object bookKey : jsonObject.keySet()) {
            Book newBook = mapper.readValue(jsonObject.get(bookKey).toString(), Book.class);
            this.books.add(newBook);
        }

        // Hash all the books!
        this.hashBooks();

        return this;
    }

    private void hashBooks() {
        for (Book book : this.books) {
            // Get a complete list of all words in the book, including the title.
            List<String> bookWords = new ArrayList<>();
            bookWords.addAll(this.splitWords(book.getTitle()));
            bookWords.addAll(this.splitWords(book.getDescription()));

            // Cycle through all the words in the book and has the book to each word.
            for (String word : bookWords) {
                List<Book> hashedBooks = this.hashedBooksByWord.get(word);

                // Check if this is the first entry for this word, save a new list if it is.
                if (hashedBooks == null) {
                    hashedBooks = new ArrayList<Book>();
                }

                // Save the book to the list and save it back to the HashMap.
                hashedBooks.add(book);
                this.hashedBooksByWord.put(word, hashedBooks);
            }
        }
    }

    public List<Book> getbooksFor(String...queriedWords) {
        List<Book> foundBooks = new ArrayList<>();

        for (String query : queriedWords) {
            foundBooks.addAll(this.hashedBooksByWord.get(query.toLowerCase()));
        }
        return foundBooks;
    }

    private List<String> splitWords(String sentence) {
        List<String> words = new ArrayList<>();

        // Replace all the non alphanumeric characters.
        sentence = sentence.replaceAll("[^a-zA-Z ]", "");

        // Split by spaces and save to the word list.
        for (String word : sentence.split("\\s+")) {
            words.add(word.toLowerCase());
        }
        return words;
    }

    /**
     * Loads JSON data as a generic object and returns a JSONObject.
     * 
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    private static JSONObject parseJSONFile(String path)
            throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));

        return (JSONObject) obj;
    }

    /*
     * The number of books loaded from JSON.
     * 
     */
    public int getBookCount() {
        return this.books.size();
    }

}
