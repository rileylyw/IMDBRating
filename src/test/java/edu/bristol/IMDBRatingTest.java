package edu.bristol;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
        ArrayList<String> seriesIMDB = new ArrayList<String>();
        while (nextLine != null) {
            nextLine = reader.readLine();
            if(nextLine!=null && nextLine.contains("> <img alt=")) {
//                System.out.println("line " + nextLine);
                seriesIMDB.add(nextLine.substring(nextLine.indexOf('"')+1, nextLine.lastIndexOf('"')));
//                System.out.println(seriesIMDB);
            }
        }
        URL url2 = new URL("https://en.wikipedia.org/wiki/Shoestring_(TV_series)#Episodes");
        HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
        connection2.setDoOutput(true);
        connection2.setRequestMethod("GET");
        InputStream stream2 = connection2.getInputStream();
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(stream2));
        String nextLine2 = reader2.readLine();
        ArrayList<String> seriesWiki = new ArrayList<String>();
        while (nextLine2 != null) {
            nextLine2 = reader2.readLine();
            if(nextLine2!=null && nextLine2.contains("<td class=\"summary\" style=\"text-align:left\">\"")) {
                seriesWiki.add(nextLine2.substring(nextLine2.indexOf("text-align:left\">\"")+18, nextLine2.indexOf("\"</td><td")));
//                System.out.println(seriesWiki);
            }
        }
        seriesWiki.removeAll(seriesIMDB);
        System.out.println(seriesWiki);
    }
}
