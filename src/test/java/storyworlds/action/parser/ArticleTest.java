package storyworlds.action.parser;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by nvaughan on 11/12/2016.
 */
public class ArticleTest {

    @Test
    public void vowels() {
        assertTrue(Article.A.equals(Article.get("foo")));
        assertTrue(Article.A.equals(Article.get("foo bar baz")));
        assertTrue(Article.AN.equals(Article.get("oo bar baz")));
        assertTrue(StringUtils.isEmpty(Article.get(null)));
        assertTrue(StringUtils.isEmpty(Article.get("")));
    }

}
