package sg.edu.nus.comp.cs4218.impl.cmd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobbingTest {
    private static final String GLOB_FOLDER_PATH = "test" + StringUtils.fileSeparator() + "resources"
            + StringUtils.fileSeparator() + "testSample" + StringUtils.fileSeparator() + "GlobTestFolder";
    private ArgumentResolver argumentResolver;

    @BeforeEach
    void setUp() {
        argumentResolver = new ArgumentResolver();
    }

    @Test
    public void resolveOneArgument_SingleAsterisk_DisplayAllFileInFolder() throws
            FileNotFoundException, AbstractApplicationException, ShellException {
        List<String> actual = argumentResolver.resolveOneArgument(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "*");
        List<String> expected = new ArrayList<>(List.of(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A.t",
                GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A.txt", GLOB_FOLDER_PATH+ StringUtils.fileSeparator()
                        + "Ap.txt", GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "B.tnt", GLOB_FOLDER_PATH
                        + StringUtils.fileSeparator() + "Bp.tat", GLOB_FOLDER_PATH+ StringUtils.fileSeparator() + "C.axt"));
        assertEquals(expected, actual);
    }

    @Test
    public void resolveOneArgument_SingleAsteriskOnStartOfFilePath_DisplayCorrectFileInFolder() throws
            FileNotFoundException, AbstractApplicationException, ShellException {
        List<String> actual = argumentResolver.resolveOneArgument(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "*.txt");
        List<String> expected = new ArrayList<>(List.of(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A.txt",
                GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "Ap.txt"));
        assertEquals(expected, actual);
    }

    @Test
    public void resolveOneArgument_SingleAsteriskOnEndOfFilePath_DisplayCorrectFileInFolder() throws
            FileNotFoundException, AbstractApplicationException, ShellException {
        List<String> actual = argumentResolver.resolveOneArgument(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A*");
        List<String> expected = new ArrayList<>(List.of(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A.t",
                GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A.txt", GLOB_FOLDER_PATH
                        + StringUtils.fileSeparator() + "Ap.txt"));
        assertEquals(expected, actual);
    }

    @Test
    public void resolveOneArgument_SingleAsteriskWithInvalidFile_ReturnExactPath() throws
            FileNotFoundException, AbstractApplicationException, ShellException {
        List<String> actual = argumentResolver.resolveOneArgument(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "E*");
        List<String> expected = new ArrayList<>(List.of(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "E*"));
        assertEquals(expected, actual);
    }

    @Test
    public void resolveOneArgument_DoubleAsteriskOnRandomFilePath_DisplayCorrectFileInFolder() throws
            FileNotFoundException, AbstractApplicationException, ShellException {
        List<String> actual = argumentResolver.resolveOneArgument(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "*.t*t");
        List<String> expected = new ArrayList<>(List.of(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A.txt",
                GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "Ap.txt", GLOB_FOLDER_PATH
                        + StringUtils.fileSeparator() + "B.tnt", GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "Bp.tat"));
        assertEquals(expected, actual);
    }

    @Test
    public void resolveOneArgument_DoubleAsteriskStartOfFilePath_DisplayCorrectFileInFolder() throws
            FileNotFoundException, AbstractApplicationException, ShellException {
        List<String> actual = argumentResolver.resolveOneArgument(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "**.txt");
        List<String> expected = new ArrayList<>(List.of(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A.txt",
                GLOB_FOLDER_PATH+ StringUtils.fileSeparator() + "Ap.txt"));
        assertEquals(expected, actual);
    }

    @Test
    public void resolveOneArgument_DoubleAsteriskBehindOfFilePath_DisplayCorrectFileInFolder() throws
            FileNotFoundException, AbstractApplicationException, ShellException {
        List<String> actual = argumentResolver.resolveOneArgument(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A.t**");
        List<String> expected = new ArrayList<>(List.of(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A.t",
                GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "A.txt"));
        assertEquals(expected, actual);
    }

    @Test
    public void resolveOneArgument_DoubleAsteriskWithInvalidFile_ReturnExactPath() throws
            FileNotFoundException, AbstractApplicationException, ShellException {
        List<String> actual = argumentResolver.resolveOneArgument(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "*.s*s");
        List<String> expected = new ArrayList<>(List.of(GLOB_FOLDER_PATH + StringUtils.fileSeparator() + "*.s*s"));
        assertEquals(expected, actual);
    }



}
