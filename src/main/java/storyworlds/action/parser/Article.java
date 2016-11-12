package storyworlds.action.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by nvaughan on 11/12/2016.
 */
public class Article {
    public static final String EMPTY = "";
    public static final String A = "a";
    public static final String AN = "an";
    private static final List<Character> vowels = Arrays.asList(new Character[] { 'a', 'e', 'i', 'o', 'u' } );

    public static String get(String input) {
        if (StringUtils.isEmpty(input)) {
            return EMPTY;
        }
        if (vowels.contains(input.charAt(0))) {
            return AN;
        }
        return A;

    }
}
