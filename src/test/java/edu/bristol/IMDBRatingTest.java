package edu.bristol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IMDBRatingTest
{
    @Test
    public void testAverageRating()
    {
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
}
