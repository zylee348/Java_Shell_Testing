package resources.util.teststubs;

import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218.impl.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CallCommandStub extends CallCommand {

    List<String> argsList;
    private static final String FIRST_COMMAND_ERROR_MSG = "First Command Error Msg";
    private static final String SECOND_COMMAND_ERROR_MSG = "Second Command Error Msg";
    private static final String THIRD_COMMAND_ERROR_MSG = "Third Command Error Msg";
    private static final String FIRST_COMMAND_SUCCESS_MSG = "First command succeed";
    private static final String SECOND_COMMAND_SUCCESS_MSG = "Two command succeed";
    private static final String THIRD_COMMAND_SUCCESS_MSG = "Three command succeed";

    public enum CallCommandType {
        FIRST_VALID_COMMAND("FIRST_VALID_COMMAND"),
        FIRST_INVALID_COMMAND("FIRST_INVALID_COMMAND"),
        SECOND_VALID_COMMAND("SECOND_VALID_COMMAND"),
        SECOND_INVALID_COMMAND("SECOND_INVALID_COMMAND"),
        THIRD_VALID_COMMAND("THIRD_VALID_COMMAND"),
        THIRD_INVALID_COMMAND("THIRD_INVALID_COMMAND");

        private final String text;

        CallCommandType(String text) {
            this.text = text;
        }
        @Override
        public String toString() {
            return text;
        }
    }

    public CallCommandStub(List<String> argsList, ApplicationRunner appRunner, ArgumentResolver argumentResolver) {
        super(argsList, appRunner, argumentResolver);
        this.argsList = getArgsList();
    }

    @Override
    public void evaluate(InputStream stdin, OutputStream stdout) throws ShellException {
        // For testing purpose will only have 1 valid/invalid argument
        CallCommandType commandType = CallCommandType.valueOf(argsList.get(0));
        switch (commandType) {
            case FIRST_VALID_COMMAND:
                writeSuccessfulMsg(stdout, FIRST_COMMAND_SUCCESS_MSG);
                break;
            case FIRST_INVALID_COMMAND:
                throw new ShellException(FIRST_COMMAND_ERROR_MSG);
            case SECOND_VALID_COMMAND:
                if(isCorrectSuccessMsg(stdin, FIRST_COMMAND_SUCCESS_MSG)) {
                    writeSuccessfulMsg(stdout, SECOND_COMMAND_SUCCESS_MSG);
                }
                break;
            case SECOND_INVALID_COMMAND:
                throw new ShellException(SECOND_COMMAND_ERROR_MSG);
            case THIRD_VALID_COMMAND:
                if(isCorrectSuccessMsg(stdin, SECOND_COMMAND_SUCCESS_MSG)) {
                    writeSuccessfulMsg(stdout, THIRD_COMMAND_SUCCESS_MSG);
                }
                break;
            case THIRD_INVALID_COMMAND:
                throw new ShellException(THIRD_COMMAND_ERROR_MSG);
            default:
                throw new ShellException("Wrong Message Give");

        }
    }

    private void writeSuccessfulMsg(OutputStream stdout, String msg) {
        try {
            stdout.write(msg.getBytes());
        } catch(Exception ignored) {}

    }

    private boolean isCorrectSuccessMsg(InputStream stdin, String expectedMsg) {
        try {
            List<String> readMsg = IOUtils.getLinesFromInputStream(stdin);
            return readMsg.size() == 1 && readMsg.contains(expectedMsg);
        } catch (IOException e){
            return false;
        }
    }

    @Override
    public void terminate() {
        // Not use for now
    }
}
