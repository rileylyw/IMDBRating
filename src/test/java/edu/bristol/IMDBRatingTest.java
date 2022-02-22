package edu.bristol;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IMDBRatingTest {
    @Test
    public void testAverageRating() {
        float averageRating;
        IMDBRating rater = new IMDBRating();

        averageRating = rater.addNewRating(2);
        assertTrue(averageRating == 2.0, "Adding 1st rating: average should be 2.0");

        averageRating = rater.addNewRating(4);
        assertTrue(averageRating == 3.0, "Adding 2nd rating: average should be 3.0");

        averageRating = rater.addNewRating(6);
        assertTrue(averageRating == 4.0);

        averageRating = rater.addNewRating(8);
        assertTrue(averageRating == 5.0);
        //testing branch
    }

    @Test
    public void releaseTesting() throws IOException {
        URL url = new URL("https://www.imdb.com/search/title/?locations=bristol&role=nm0263368");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String nextLine = reader.readLine();
        while (nextLine != null) {
            nextLine = reader.readLine();
            if(nextLine.contains("Bristol")) {
                System.out.println("line " + nextLine);
            }
        }
    }
}
