import com.company.substring.Substring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubstringTest {
    private String a = "abc";
    private String b = "bcabca";
    private String c = "ww";
    private String d = "w";
    private String e = "bcabcabc";
    @Test
    void testingSubstringMethod(){
        Assertions.assertEquals(1,Substring.substring(b,a));
        //Assertions.assertEquals(2,Substring.substring(a,b));
        Assertions.assertEquals(-1,Substring.substring(c,a));
        Assertions.assertEquals(1,Substring.substring(c,d));
        Assertions.assertEquals(3,Substring.substring(a,e));
    }
}
