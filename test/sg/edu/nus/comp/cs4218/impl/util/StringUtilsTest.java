package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

public class StringUtilsTest {

    static final String OS_PROPERTY_NAME = "os.name";
    static final String CURRENT_OS = System.getProperty(OS_PROPERTY_NAME);
    @AfterEach
    void reset() {
        System.setProperty(OS_PROPERTY_NAME, CURRENT_OS);
    }

    @Test
    void fileSeparator_WindowsOS_ReturnsCorrectFileSeparator() {
        System.setProperty(OS_PROPERTY_NAME, "Windows");
        assertEquals("\\" + File.separator, fileSeparator());
    }

    @Test
    void fileSeparator_MacOS_ReturnsCorrectFileSeparator() {
        System.setProperty(OS_PROPERTY_NAME, "Mac");
        assertEquals(File.separator, fileSeparator());
    }

    @Test
    void fileSeparator_LinuxOS_ReturnsCorrectFileSeparator() {
        System.setProperty(OS_PROPERTY_NAME, "Linux");
        assertEquals(File.separator, fileSeparator());
    }


    @Test
    void isBlank_isNull_returnTrue() {
        assertTrue(isBlank(null));
    }

    @Test
    void isBlank_whiteSpace_returnTrue() {
        assertTrue(isBlank("\n"));
    }

    @Test
    void isBlank_emptyString_returnTrue() {
        assertTrue(isBlank(""));
    }

    @Test
    void isBlank_nonEmptyString_returnFalse() {
        boolean test = isBlank("testing");
        assertFalse(test);
    }

    @Test
    void isBlank_nonEmptyStringWithWhitespaces_returnFalse() {
        assertFalse(isBlank("string with random  amount    of white spaces  ."));
    }

    @Test
    void multiplyChar_AlphabZero_ReturnsCorrectString() {
        assertEquals(multiplyChar('x', 0), "");
    }

    @Test
    void multiplyChar_SingleAlphab_ReturnsCorrectString() {
        assertEquals(multiplyChar('x', 1), "x");
    }

    @Test
    void multiplyChar_FiveAlphab_ReturnsCorrectString() {
        assertEquals(multiplyChar('x', 5), "xxxxx");
    }

    @Test
    void multiplyChar_OneSymbol_ReturnsCorrectString() {
        assertEquals(multiplyChar('*', 1), "*");
    }

    @Test
    void multiplyChar_TenSymbols_ReturnsCorrectString() {
        assertEquals(multiplyChar('*', 10), "**********");
    }

    @Test
    void multiplyChar_FiveSpecialChar_ReturnsCorrectString() {
        assertEquals(multiplyChar('\\', 5), "\\\\\\\\\\");
    }

    @Test
    void tokenize_StringWithAllWhiteSpaces_TokensMatchesEmptyArray() {
        String toBeTokenize = "  ";
        String[] expectedTokens = new String[0];

        String[] actualTokens = tokenize(toBeTokenize);
        assertTrue(Arrays.equals(actualTokens, expectedTokens));
    }

    @Test
    void tokenize_StringWithNoWhiteSpaces_TokensMatchesExpectedTokens() {
        String toBeTokenize = "string";
        String[] expectedTokens = {"string"};

        String[] actualTokens = tokenize(toBeTokenize);
        assertTrue(Arrays.equals(actualTokens, expectedTokens));
    }

    @Test
    void tokenize_StringWithOneWhiteSpaces_TokensMatchesExpectedTokens() {
        String toBeTokenize = "string1 string2";
        String[] expectedTokens = {"string1", "string2"};

        String[] actualTokens = tokenize(toBeTokenize);
        assertTrue(Arrays.equals(actualTokens, expectedTokens));
    }

    @Test
    void tokenize_ComplexString_TokensMatchesExpectedTokens() {
        String toBeTokenize = " string1   string2 string3 string4  ";
        String[] expectedTokens = {"string1", "string2", "string3", "string4"};

        String[] actualTokens = tokenize(toBeTokenize);
        assertTrue(Arrays.equals(actualTokens, expectedTokens));
    }

    @Test
    void isNumber_NumsStr_ReturnsTrue() {
        assertTrue(isNumber("012345"));
    }

    @Test
    void isNumber_EmptyStr_ReturnsFalse() {
        assertFalse(isNumber(""));
    }

    @Test
    void isNumber_AlphabeticalStr_ReturnsFalse() {
        assertFalse(isNumber("abc*"));
    }

    @Test
    void isNumber_AlphanumericStr_ReturnsFalse() {
        assertFalse(isNumber("a123"));
    }

    @Test
    void isNumber_DecimalNumStr_ReturnsFalse() {
        assertFalse(isNumber("1.1"));
    }
}
