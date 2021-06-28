package sg.edu.iss.events.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CommentGenerator {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final List<String> COMMENT_AUTHOR =
            Arrays.asList(
                    "Dilbert", "Alice", "Pointy", "Ashok", "DogBert",
                    "CatBert", "RatBert", "Wally", "Estonians", "DilMom");


    private static final List<String> COMMENT_MESSAGE =
            Arrays.asList(
                    "I an a geek!",
                    "Me too!",
                    "I am the Manager",
                    "Poor SA Intern!",
                    "Hello everyone, I am the boss here!",
                    "Good! I am the HR", "The admin", "The shameless", "From ALien Planet", "'m Dil's mom");

    public static String randomAuthor() {
        return COMMENT_AUTHOR.get(RANDOM.nextInt(COMMENT_AUTHOR.size()));
    }

    public static String randomMessage() {
        return COMMENT_MESSAGE.get(RANDOM.nextInt(COMMENT_MESSAGE.size()));
    }

    public static String getCurrentTimeStamp() {
        return dtf.format(LocalDateTime.now());
    }
}
