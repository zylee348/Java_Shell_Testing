package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RegexArgumentTest {
   private RegexArgument regexArgument;

    private static final String FOLDER_PATH = "test" + StringUtils.fileSeparator() + "resources"
            + StringUtils.fileSeparator() + "testSample" + StringUtils.fileSeparator() + "FolderWithFilesOnly"
            + StringUtils.fileSeparator();
    @Nested
    class RegexArgumentEmptyConstructor {
        @BeforeEach()
        void beforeEach() {
            regexArgument = new RegexArgument();
        }

        @AfterEach()
        void afterEach() {
            regexArgument = null;
        }

        @Test
        public void isRegex_CheckIfIsRegexAfterInitialise_ReturnFalse() {
            assertFalse(regexArgument.isRegex());
        }
        @Test
        public void isEmpty_CheckIfRegexIsEmptyAfterInitialise_IsEmpty() {
           assertTrue(regexArgument.isEmpty());
        }

        @Test
        public void toString_CheckIfRegexStringAfterInitialise_ReturnEmptyString() {
            assertEquals("", regexArgument.toString());
        }

        @Test
        public void globFiles_GetGlobFilesAfterInitialise_ReturnAEmptyStringInList() {
            List<String> expectation = new LinkedList<>(List.of(""));
            assertEquals(expectation, regexArgument.globFiles());
        }
    }

    @Nested
    class RegexArgumentConstructorWithOneParam {
        private static final String CHAR_ONLY_STRING = "Hello World";

        @BeforeEach()
        void beforeEach() {
            regexArgument = new RegexArgument(CHAR_ONLY_STRING);
        }
        @AfterEach()
        void afterEach() {
            regexArgument = null;
        }

        @Test
        public void isRegex_CheckIfIsRegexAfterInitialise_ReturnFalse() {
            assertFalse(regexArgument.isRegex());
        }

        @Test
        public void isEmpty_CheckIfRegexIsEmptyAfterInitialise_IsNotEmpty() {
            assertFalse(regexArgument.isEmpty());
        }

        @Test
        public void toString_CheckIfRegexStringAfterInitialise_ReturnNonEmptyString() {
            assertEquals(CHAR_ONLY_STRING, regexArgument.toString());
        }

        @Test
        public void globFiles_GetGlobFilesAfterInitialise_ReturnListWithTheParseArgument() {
            List<String> expectation = new LinkedList<>(List.of(CHAR_ONLY_STRING));
            assertEquals(expectation, regexArgument.globFiles());
        }
    }

    @Nested
    class RegexArgumentConstructorMultiParam {
        private static final String CHAR_ONLY_STRING = "ABC";
        private static final String CHAR_ASTERISK_STRING = "*";
        private final String FOLDER_PATH_WITH_ASTERISK = "test" + StringUtils.fileSeparator() + "resources"
                + StringUtils.fileSeparator() + "testSample" + StringUtils.fileSeparator() + "FolderWithFilesOnly"
                + StringUtils.fileSeparator() + "*";
        @AfterEach()
        void afterEach() {
            regexArgument = null;
        }
        @Test
        public void isRegex_CheckIfIsRegexAfterInitialiseWithFalse_ReturnFalse() {
            regexArgument = new RegexArgument(CHAR_ONLY_STRING, FOLDER_PATH, false);
            assertFalse(regexArgument.isRegex());
        }

        @Test
        public void isRegex_CheckIfIsRegexAfterInitialiseWithTrue_ReturnTrue() {
            regexArgument = new RegexArgument(CHAR_ONLY_STRING, FOLDER_PATH, true);
            assertTrue(regexArgument.isRegex());
        }

        @Test
        public void isEmpty_CheckIfRegexIsEmptyAfterInitialise_IsNotEmpty() {
            regexArgument = new RegexArgument(CHAR_ONLY_STRING, FOLDER_PATH, false);
            assertFalse(regexArgument.isEmpty());
        }

        @Test
        public void toString_CheckRegexStringAfterInitialise_ReturnFolderPath() {
            regexArgument = new RegexArgument(CHAR_ONLY_STRING, FOLDER_PATH, false);
            assertEquals(FOLDER_PATH, regexArgument.toString());
        }
        @Test
        public void globFiles_GlobFilesNormalCharAndNormalPathAndRegexIsFalse_ReturnListWithFolderPath() {
            regexArgument = new RegexArgument(CHAR_ONLY_STRING, FOLDER_PATH, true);
            List<String> expectation = new LinkedList<>(List.of(FOLDER_PATH));
            assertEquals(expectation, regexArgument.globFiles());
        }

        @Test
        public void globFiles_GlobFilesNormalCharAndAsteriskPathAndRegexIsTrue_ReturnListWithAsteriskFolderPath() {
            regexArgument = new RegexArgument(CHAR_ONLY_STRING, FOLDER_PATH_WITH_ASTERISK, false);
            List<String> expectation = new LinkedList<>(List.of(FOLDER_PATH_WITH_ASTERISK));
            assertEquals(expectation, regexArgument.globFiles());
        }

        @Test
        public void globFiles_GlobFilesAsteriskCharAndAsteriskPathAndRegexIsTrue_ReturnListOfFilesInFolderPath() {
            regexArgument = new RegexArgument(CHAR_ASTERISK_STRING, FOLDER_PATH_WITH_ASTERISK, true);
            List<String> expectation = new LinkedList<>(List.of(FOLDER_PATH+"A.txt", FOLDER_PATH+"B"));
            assertEquals(expectation, regexArgument.globFiles());
        }

        @Test
        public void globFiles_GlobFilesAsteriskCharAndNormalPathAndRegexIsFalse_ReturnListWithFolderPath() {
            regexArgument = new RegexArgument(CHAR_ASTERISK_STRING, FOLDER_PATH, false);
            List<String> expectation = new LinkedList<>(List.of(FOLDER_PATH));
            assertEquals(expectation, regexArgument.globFiles());
        }

    }

    @BeforeEach()
    void beforeEach() {
        regexArgument = new RegexArgument();
    }
    @AfterEach()
    void afterEach() {
        regexArgument = null;
    }

    @Test
    public void append_CallingFunction_ShouldReturnAppendedChar() {
        regexArgument.append('c');
        assertEquals("c",regexArgument.toString());
    }
    @Test
    public void appendAsterisk_CallingFunction_AsteriskIsAppendedANdRegexIsTrue() {
        regexArgument.appendAsterisk();
        assertEquals("*",regexArgument.toString());
        assertTrue(regexArgument.isRegex());
    }

    @Test
    public void merge_ArgumentIsRegexArgument_ValuesIsBasedOnOtherRegexArgument() {
        RegexArgument otherRegexArg = new RegexArgument("A.txt",FOLDER_PATH,true);
        regexArgument.merge(otherRegexArg);
        assertEquals(FOLDER_PATH,regexArgument.toString());
        assertTrue(regexArgument.isRegex());
    }

    @Test
    public void merge_ArgumentIsString_StringAddedCorrectly() {
        regexArgument.merge(FOLDER_PATH);
        assertEquals(FOLDER_PATH,regexArgument.toString());
    }
}
