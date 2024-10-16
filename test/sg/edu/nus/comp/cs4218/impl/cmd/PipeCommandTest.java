package sg.edu.nus.comp.cs4218.impl.cmd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import resources.util.teststubs.CallCommandStub;

import java.io.*;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


public class PipeCommandTest {
    private OutputStream stdout;
    private InputStream stdin;
    private static final String TWO_COMMAND_SUCCEED_MSG = "Two command succeed";
    private static final String THREE_COMMAND_SUCCEED_MSG = "Three command succeed";
    private static final String FIRST_COMMAND_ERROR_MSG = "shell: First Command Error Msg";
    private static final String SECOND_COMMAND_ERROR_MSG = "shell: Second Command Error Msg";
    private static final String THIRD_COMMAND_ERROR_MSG = "shell: Third Command Error Msg";
    private PipeCommand pipeCommand;

    private ApplicationRunner appRunner;
    private ArgumentResolver argumentResolver;

    private LinkedList<String> argList1;
    private LinkedList<String> argList2;
    private LinkedList<String> argList3;
    private LinkedList<CallCommand> callCommandsList;


    @BeforeEach
    void setUp() {
        stdout = new ByteArrayOutputStream();
        stdin = new ByteArrayInputStream("".getBytes());
        appRunner = new ApplicationRunner();
        argumentResolver = new ArgumentResolver();
        argList1 = new LinkedList<>();
        argList2 = new LinkedList<>();
        argList3 = new LinkedList<>();
        callCommandsList = new LinkedList<>();

    }

    @AfterEach
    void tearDown() throws IOException {
        stdout.close();
        stdin.close();
    }

    private void setUpTwoCommands(String firstCommand, String secondCommand) {
        argList1.add(firstCommand);
        argList2.add(secondCommand);
        callCommandsList.add(new CallCommandStub(argList1, appRunner, argumentResolver));
        callCommandsList.add(new CallCommandStub(argList2, appRunner, argumentResolver));
    }

    private void setUpThreeCommands(String firstCommand, String secondCommand, String thirdCommand) {
        setUpTwoCommands(firstCommand, secondCommand);
        argList3.add(thirdCommand);
        callCommandsList.add(new CallCommandStub(argList3, appRunner, argumentResolver));
    }

    @Test
    public void evaluate_ValidTwoCommand_DisplayCorrectResult() throws FileNotFoundException,
            AbstractApplicationException, ShellException {
        setUpTwoCommands(CallCommandStub.CallCommandType.FIRST_VALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.SECOND_VALID_COMMAND.toString());
        pipeCommand = new PipeCommand(callCommandsList);

        pipeCommand.evaluate(stdin, stdout);

        assertEquals(TWO_COMMAND_SUCCEED_MSG, stdout.toString());
    }

    @Test
    public void evaluate_ValidThreeCommand_DisplayCorrectResult() throws FileNotFoundException,
            AbstractApplicationException, ShellException {
        setUpThreeCommands(CallCommandStub.CallCommandType.FIRST_VALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.SECOND_VALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.THIRD_VALID_COMMAND.toString());
        pipeCommand = new PipeCommand(callCommandsList);

        pipeCommand.evaluate(stdin, stdout);

        assertEquals(THREE_COMMAND_SUCCEED_MSG, stdout.toString());
    }

    @Test
    public void evaluate_FirstCommandInvalidSecondValid_ThrowsExceptionMsgForFirstCommand() {
        setUpTwoCommands(CallCommandStub.CallCommandType.FIRST_INVALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.SECOND_VALID_COMMAND.toString());
        pipeCommand = new PipeCommand(callCommandsList);

        ShellException exception = assertThrows( ShellException.class, () -> pipeCommand.evaluate(stdin, stdout));

        assertEquals(FIRST_COMMAND_ERROR_MSG, exception.getMessage());

    }

    @Test
    public void evaluate_FirstCommandValidSecondInvalidThirdCommandValid_ThrowsExceptionMsgForSecondCommand() {
        setUpThreeCommands(CallCommandStub.CallCommandType.FIRST_VALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.SECOND_INVALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.THIRD_VALID_COMMAND.toString());
        pipeCommand = new PipeCommand(callCommandsList);

        ShellException exception = assertThrows( ShellException.class, () -> pipeCommand.evaluate(stdin, stdout));

        assertEquals(SECOND_COMMAND_ERROR_MSG, exception.getMessage());

    }

    @Test
    public void evaluate_FirstCommandValidSecondValidThirdCommandInvalid_ThrowsExceptionMsgForThirdCommand() {
        setUpThreeCommands(CallCommandStub.CallCommandType.FIRST_VALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.SECOND_VALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.THIRD_INVALID_COMMAND.toString());
        pipeCommand = new PipeCommand(callCommandsList);

        ShellException exception = assertThrows(ShellException.class, () -> pipeCommand.evaluate(stdin, stdout));

        assertEquals(THIRD_COMMAND_ERROR_MSG, exception.getMessage());
    }

    @Test
    public void evaluate_InvalidThreeCommand_ThrowsExceptionMsgForFirstCommand() {
        setUpThreeCommands(CallCommandStub.CallCommandType.FIRST_INVALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.SECOND_INVALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.THIRD_INVALID_COMMAND.toString());
        pipeCommand = new PipeCommand(callCommandsList);

        ShellException exception = assertThrows( ShellException.class, () -> pipeCommand.evaluate(stdin, stdout));

        assertEquals(FIRST_COMMAND_ERROR_MSG, exception.getMessage());
    }

    @Test
    public void getCallCommands_PassCommandListToPipeClass_ShouldReturnSameList() {
        setUpTwoCommands(CallCommandStub.CallCommandType.FIRST_VALID_COMMAND.toString(),
                CallCommandStub.CallCommandType.SECOND_VALID_COMMAND.toString());
        pipeCommand = new PipeCommand(callCommandsList);
        assertIterableEquals(callCommandsList, pipeCommand.getCallCommands());
    }

}
