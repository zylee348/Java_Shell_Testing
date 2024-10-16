package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ErrorConstantsTest {

    @Test
    public void errorConstants_CheckNull_ShouldNotBeNull() {
        assertNotNull(ErrorConstants.ERR_WRITE_STREAM);
        assertNotNull(ErrorConstants.ERR_NULL_STREAMS);
        assertNotNull(ErrorConstants.ERR_CLOSING_STREAMS);
        assertNotNull(ErrorConstants.ERR_MULTIPLE_STREAMS);
        assertNotNull(ErrorConstants.ERR_STREAM_CLOSED);
        assertNotNull(ErrorConstants.ERR_NO_OSTREAM);
        assertNotNull(ErrorConstants.ERR_NO_ISTREAM);
        assertNotNull(ErrorConstants.ERR_NO_INPUT);
        assertNotNull(ErrorConstants.ERR_NO_FILE_ARGS);
        assertNotNull(ErrorConstants.ERR_MISSING_ARG);
        assertNotNull(ErrorConstants.ERR_NO_ARGS);
        assertNotNull(ErrorConstants.ERR_NULL_ARGS);
        assertNotNull(ErrorConstants.ERR_TOO_MANY_ARGS);
        assertNotNull(ErrorConstants.ERR_INVALID_FLAG);
        assertNotNull(ErrorConstants.ERR_BAD_REGEX);
        assertNotNull(ErrorConstants.ERR_FILE_NOT_FOUND);
        assertNotNull(ErrorConstants.ERR_READING_FILE);
        assertNotNull(ErrorConstants.ERR_IS_DIR);
        assertNotNull(ErrorConstants.ERR_IS_NOT_DIR);
        assertNotNull(ErrorConstants.ERR_NO_PERM);
        assertNotNull(ErrorConstants.ERR_INVALID_FORMAT_PREFIX);
        assertNotNull(ErrorConstants.ERR_INVALID_FORMAT_FIELD);
        assertNotNull(ErrorConstants.ERR_MISSING_FIELD);
        assertNotNull(ErrorConstants.ERR_INVALID_FILE);
        assertNotNull(ErrorConstants.ERR_NAME_FLAG);
        assertNotNull(ErrorConstants.ERR_NO_REP_RULE);
        assertNotNull(ErrorConstants.ERR_INVALID_REP_RULE);
        assertNotNull(ErrorConstants.ERR_INVALID_REP_X);
        assertNotNull(ErrorConstants.ERR_INVALID_REGEX);
        assertNotNull(ErrorConstants.ERR_EMPTY_REGEX);
        assertNotNull(ErrorConstants.ERR_NO_REGEX);
        assertNotNull(ErrorConstants.ERR_NO_FOLDERS);
        assertNotNull(ErrorConstants.ERR_FILE_EXISTS);
        assertNotNull(ErrorConstants.ERR_TOP_LEVEL_MISSING);
        assertNotNull(ErrorConstants.ERR_NO_EXP);
        assertNotNull(ErrorConstants.ERR_DEC_RANGE);
        assertNotNull(ErrorConstants.ERR_INVALID_INDEX);
        assertNotNull(ErrorConstants.ERR_NON_EMPTY_DIR);
        assertNotNull(ErrorConstants.ERR_INVALID_APP);
        assertNotNull(ErrorConstants.ERR_NOT_SUPPORTED);
        assertNotNull(ErrorConstants.ERR_SYNTAX);
        assertNotNull(ErrorConstants.ERR_GENERAL);
        assertNotNull(ErrorConstants.ERR_IO_EXCEPTION);
    }
}
