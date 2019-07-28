package Server;

import java.util.Arrays;
import java.util.HashSet;

public class RestrictedWords {
    public static HashSet<String> restrictedList = new HashSet<>();
    public RestrictedWords(){
        // initializes
        String[] res = {"and", "or", "for", "but", "nor", "yet", "so", "is", "so", "was", "are"};
        String[] pron = {"he", "she", "they", "him", "her", "you", "them", "i", "me", "who"};
        this.restrictedList.addAll(Arrays.asList(res));
        this.restrictedList.addAll(Arrays.asList(pron));
    }

    public boolean restricted(String word){
        return restrictedList.contains(word.toLowerCase());
    }
}
