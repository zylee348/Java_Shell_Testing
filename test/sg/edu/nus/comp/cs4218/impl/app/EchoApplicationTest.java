package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class EchoApplicationTest {
    private static final EchoApplication ECHO_APPLICATION = new EchoApplication();
    private static final String ECHO_EX_PREFIX = "echo: ";

    @Test
    public void constructResult_SingleStrNoSpaces_ConstructsCorrectString()
            throws Exception {
        assertEquals("Hello" + STRING_NEWLINE, ECHO_APPLICATION.constructResult(new String[]{"Hello"}));
    }

    @Test
    public void constructResult_StrWithSingleSpace_ConstructsCorrectString()
            throws Exception {
        assertEquals("Hello World!" + STRING_NEWLINE, ECHO_APPLICATION.constructResult(new String[]{"Hello World!"}));
    }

    @Test
    public void constructResult_SpecialCharactersStrWithSpacing_ConstructsCorrectString()
            throws Exception {
        assertEquals("@*#123 $456%^&" + STRING_NEWLINE, ECHO_APPLICATION.constructResult(new String[]{"@*#123 $456%^&"}));
    }

    @Test
    public void constructResult_EmptyString_ConstructsEmptyString()
            throws Exception {
        assertEquals(STRING_NEWLINE, ECHO_APPLICATION.constructResult(new String[]{}));
    }

    @Test
    public void constructResult_NullArgs_ThrowsEchoException() {
        Exception exception = assertThrows(Exception.class,
                () -> ECHO_APPLICATION.constructResult(null));
        assertEquals(ECHO_EX_PREFIX + ERR_NULL_ARGS, exception.getMessage());
    }

    @Test
    public void run_NullOutputStreamCorrectInputStream_ThrowsEchoException()
            throws Exception {
        Exception exception = assertThrows(Exception.class,
                () -> ECHO_APPLICATION.run(new String[]{"Hello World!"}, System.in, null));
        assertEquals(ECHO_EX_PREFIX + ERR_NO_OSTREAM, exception.getMessage());
    }

    @Test
    public void run_CorrectOutputAndInputStreamStr_CorrectEcho()
            throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ECHO_APPLICATION.run(new String[]{"Hello World!"}, System.in, outputStream);
        assertEquals("Hello World!" + STRING_NEWLINE, outputStream.toString());
    }
}
