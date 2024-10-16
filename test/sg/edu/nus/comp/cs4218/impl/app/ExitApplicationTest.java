package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ExitApplicationTest {
    private OutputStream stdout;
    private InputStream stdin;
    private ExitApplication exit;
    private String[] argsList;

    @BeforeEach
    public void setUp() {
        stdout = new ByteArrayOutputStream();
        stdin = new ByteArrayInputStream("".getBytes());
        argsList = new String[1];
        exit = new ExitApplication();
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    public void run_WhenInvoke_ShouldExit() throws AbstractApplicationException{
        exit.run(argsList, stdin,stdout);
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    public void terminateExecution_WhenInvoke_ShouldExit() throws AbstractApplicationException{
        exit.terminateExecution();
    }
}


