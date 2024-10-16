import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {  //NOPMD - Suppressed for automatically generated test cases

    public static boolean debug = false;

    @Test
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test001");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.GrepApplication.EMPTY_PATTERN;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Pattern should not be empty." + "'", str0, "Pattern should not be empty.");
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test002");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.util.List<int[]> intArrayList2 = cutApplication0.getCutRanges("Pattern should not be empty.");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: Invalid regular expression supplied");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test003");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.MkdirApplication.ERR_CANNOT_CREATE;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Could not create directory" + "'", str0, "Could not create directory");
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test004");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.MkdirApplication.ERR_SYNTAX;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Invalid syntax" + "'", str0, "Invalid syntax");
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test005");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.MkdirApplication.ERR_ALREADY_EXISTS;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Directory already exists" + "'", str0, "Directory already exists");
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test006");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.parser.ArgsParser.ILLEGAL_FLAG_MSG;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "illegal option -- " + "'", str0, "illegal option -- ");
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test007");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray2 = mvArgsParser1.getSourceFiles();
        java.io.InputStream inputStream3 = null;
        java.io.OutputStream outputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            cutApplication0.run(strArray2, inputStream3, outputStream4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test008");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.util.List<int[]> intArrayList2 = cutApplication0.getCutRanges("Could not create directory");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: Invalid regular expression supplied");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test009");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromStdin((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream4, "illegal option -- ");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test010");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = sortApplication0.sortFromStdin((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, inputStream4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test011");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.CatApplication.ERR_READING_FILE;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Could not read file" + "'", str0, "Could not read file");
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test012");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "illegal option -- ", "Could not create directory", "Invalid syntax", "Directory already exists", "Could not read file" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            mvApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test013");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser.FLAG_IS_RECURSIVE;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'r' + "'", char0 == 'r');
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test014");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.CatApplication.ERR_WRITE_STREAM;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Could not write to output stream" + "'", str0, "Could not write to output stream");
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test015");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        java.lang.Class<?> wildcardClass1 = echoApplication0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test016");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.CatApplication.ERR_GENERAL;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Exception Caught" + "'", str0, "Exception Caught");
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test017");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Directory already exists", "Could not write to output stream", "Exception Caught", "Directory already exists" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            mvApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: Missing Argument");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test018");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser.FLAG_IS_BYTE_COUNT;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'c' + "'", char0 == 'c');
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test019");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser.FLAG_RM_EMPTY;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'd' + "'", char0 == 'd');
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test020");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str1 = mvArgsParser0.getDestFile();
        java.lang.String[] strArray5 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser0.parse(strArray5);
        java.lang.String[] strArray7 = mvArgsParser0.getSourceFiles();
        java.lang.String str8 = mvArgsParser0.getDestFile();
        org.junit.Assert.assertNull(str1);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "Pattern should not be empty." + "'", str8, "Pattern should not be empty.");
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test021");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = sortApplication0.sortFromStdin((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test022");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.CatApplication.ERR_NO_FILE_ARGS;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "No files provided" + "'", str0, "No files provided");
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test023");
        sg.edu.nus.comp.cs4218.impl.app.TeeApplication teeApplication0 = new sg.edu.nus.comp.cs4218.impl.app.TeeApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "Could not write to output stream", "Exception Caught" };
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            teeApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.TeeException; message: tee: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.TeeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test024");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        java.lang.String[] strArray2 = new java.lang.String[] { "Could not write to output stream" };
        java.io.InputStream inputStream3 = null;
        java.io.OutputStream outputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            echoApplication0.run(strArray2, inputStream3, outputStream4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test025");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, "", "Could not read file");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: : This is a directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test026");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.MkdirApplication.ERR_NO_FOLDERS;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "No folder names are supplied" + "'", str0, "No folder names are supplied");
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test027");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.GrepApplication.IS_DIRECTORY;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Is a directory" + "'", str0, "Is a directory");
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test028");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.CatApplication.ERR_NULL_STREAMS;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Null Pointer Exception" + "'", str0, "Null Pointer Exception");
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test029");
        sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser teeArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser();
        java.lang.Boolean boolean1 = teeArgsParser0.isAppend();
        java.lang.String[] strArray2 = null;
        // The following exception was thrown during execution in test generation
        try {
            teeArgsParser0.parse(strArray2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read the array length because \"args\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test030");
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray0 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList1 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1, callCommandArray0);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand3 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        pipeCommand3.evaluate(inputStream4, outputStream5);
        org.junit.Assert.assertNotNull(callCommandArray0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test031");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray3 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList4 = new java.util.ArrayList<int[]>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList4, intArray3);
        java.io.InputStream inputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = cutApplication0.cutFromStdin((java.lang.Boolean) true, (java.lang.Boolean) true, (java.util.List<int[]>) intArrayList4, inputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test032");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.GrepApplication.NULL_POINTER;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Null Pointer Exception" + "'", str0, "Null Pointer Exception");
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test033");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.io.InputStream inputStream1 = null;
        // The following exception was thrown during execution in test generation
        try {
            long[] longArray2 = wcApplication0.getCountReport(inputStream1);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test034");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str1 = mvArgsParser0.getDestFile();
        java.lang.String[] strArray2 = null;
        // The following exception was thrown during execution in test generation
        try {
            mvArgsParser0.parse(strArray2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read the array length because \"args\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str1);
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test035");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser.FLAG_IS_CASE_IGNORE;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'f' + "'", char0 == 'f');
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test036");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str1 = grepArgsParser0.getPattern();
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test037");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser.FLAG_IS_LINE_COUNT;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'l' + "'", char0 == 'l');
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test038");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream2 = null;
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication3 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray5 = mvArgsParser4.getSourceFiles();
        java.lang.String str6 = echoApplication3.constructResult(strArray5);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = catApplication0.catFileAndStdin((java.lang.Boolean) true, inputStream2, strArray5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: No files provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\n" + "'", str6, "\n");
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test039");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean2 = cutArgsParser1.hasByteFlag();
        java.lang.Boolean boolean3 = cutArgsParser1.hasByteFlag();
        java.lang.String[] strArray5 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser1.parse(strArray5);
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            uniqApplication0.run(strArray5, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test040");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser.FLAG_IS_REV_ORDER;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'r' + "'", char0 == 'r');
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test041");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromStdin((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream4, "Could not create directory");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test042");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray3 = mvArgsParser2.getSourceFiles();
        java.lang.String str4 = echoApplication1.constructResult(strArray3);
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            catApplication0.run(strArray3, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\n" + "'", str4, "\n");
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test043");
        sg.edu.nus.comp.cs4218.impl.app.TeeApplication teeApplication0 = new sg.edu.nus.comp.cs4218.impl.app.TeeApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str2 = mvArgsParser1.getDestFile();
        java.lang.String[] strArray3 = mvArgsParser1.getSourceFiles();
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            teeApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.TeeException; message: tee: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.TeeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test044");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean3 = cutArgsParser2.hasByteFlag();
        java.lang.Boolean boolean4 = cutArgsParser2.hasByteFlag();
        java.lang.String[] strArray6 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser2.parse(strArray6);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = catApplication0.catFiles((java.lang.Boolean) true, strArray6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: Exception Caught: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test045");
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean1 = cutArgsParser0.hasByteFlag();
        java.lang.Boolean boolean2 = cutArgsParser0.hasByteFlag();
        java.lang.Class<?> wildcardClass3 = cutArgsParser0.getClass();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(wildcardClass3);
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test046");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.changeToDirectory("Could not create directory");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
            // Expected exception.
        }
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test047");
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Class<?> wildcardClass1 = cutArgsParser0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test048");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream2 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = catApplication0.catStdin((java.lang.Boolean) false, inputStream2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test049");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) false, "No folder names are supplied", "");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: No folder names are supplied is already in ");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test050");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean5 = cutArgsParser4.hasByteFlag();
        java.lang.Boolean boolean6 = cutArgsParser4.hasByteFlag();
        java.lang.String[] strArray8 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser4.parse(strArray8);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str10 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertNotNull(strArray8);
    }

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test051");
        sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser rmArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser();
        java.util.List<java.lang.String> strList1 = rmArgsParser0.getDirectories();
        sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner applicationRunner2 = null;
        sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver argumentResolver3 = null;
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand callCommand4 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand(strList1, applicationRunner2, argumentResolver3);
        java.util.List<java.lang.String> strList5 = callCommand4.getArgsList();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList5);
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test052");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str2 = mvArgsParser1.getDestFile();
        java.lang.String[] strArray3 = mvArgsParser1.getSourceFiles();
        java.lang.Boolean boolean4 = mvArgsParser1.isOverwrite();
        java.lang.String[] strArray5 = mvArgsParser1.getSourceFiles();
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            lsApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.LsException; message: ls: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.LsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test053");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.CatApplication.ERR_IS_DIR;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "This is a directory" + "'", str0, "This is a directory");
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test054");
        java.lang.String[] strArray9 = new java.lang.String[] { "No files provided", "hi!", "No files provided", "Invalid syntax", "", "Exception Caught", "Invalid syntax", "Could not write to output stream", "hi!" };
        java.util.ArrayList<java.lang.String> strList10 = new java.util.ArrayList<java.lang.String>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList10, strArray9);
        sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner applicationRunner12 = null;
        sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver argumentResolver13 = null;
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand callCommand14 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand((java.util.List<java.lang.String>) strList10, applicationRunner12, argumentResolver13);
        callCommand14.terminate();
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test055");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.CatApplication.ERR_NULL_ARGS;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Null arguments" + "'", str0, "Null arguments");
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test056");
        sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser teeArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser();
        java.lang.Class<?> wildcardClass1 = teeArgsParser0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test057");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean2 = cutArgsParser1.hasByteFlag();
        java.lang.Boolean boolean3 = cutArgsParser1.hasByteFlag();
        java.lang.String[] strArray5 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser1.parse(strArray5);
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            lsApplication0.run(strArray5, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.LsException; message: ls: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.LsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test058");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication1 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean6 = cutArgsParser5.hasByteFlag();
        java.lang.Boolean boolean7 = cutArgsParser5.hasByteFlag();
        java.lang.String[] strArray9 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser5.parse(strArray9);
        java.lang.String str11 = wcApplication1.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray9);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray9, inputStream12, outputStream13);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "wc: No such file or directory\n" + "'", str11, "wc: No such file or directory\n");
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test059");
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean1 = cutArgsParser0.hasCharFlag();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test060");
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str2 = mvArgsParser1.getDestFile();
        java.lang.String[] strArray3 = mvArgsParser1.getSourceFiles();
        java.lang.Boolean boolean4 = mvArgsParser1.isOverwrite();
        java.lang.String[] strArray5 = mvArgsParser1.getSourceFiles();
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            pasteApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PasteException; message: paste: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.PasteException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test061");
        sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser teeArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser();
        java.lang.Boolean boolean1 = teeArgsParser0.isAppend();
        java.lang.Boolean boolean2 = teeArgsParser0.isAppend();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test062");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray2 = mvArgsParser1.getSourceFiles();
        java.lang.String str3 = echoApplication0.constructResult(strArray2);
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str5 = mvArgsParser4.getDestFile();
        java.lang.String[] strArray6 = mvArgsParser4.getSourceFiles();
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            echoApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "\n" + "'", str3, "\n");
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test063");
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean1 = cutArgsParser0.hasByteFlag();
        java.lang.Boolean boolean2 = cutArgsParser0.hasByteFlag();
        java.lang.String[] strArray4 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser0.parse(strArray4);
        java.lang.String str6 = cutArgsParser0.getExp();
        java.util.List<java.lang.String> strList7 = cutArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "Exception Caught" + "'", str6, "Exception Caught");
        org.junit.Assert.assertNull(strList7);
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test064");
        sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser rmArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser();
        java.util.List<java.lang.String> strList1 = rmArgsParser0.getDirectories();
        java.lang.Boolean boolean2 = rmArgsParser0.isRecursive();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test065");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean5 = cutArgsParser4.hasByteFlag();
        java.lang.Boolean boolean6 = cutArgsParser4.hasByteFlag();
        java.lang.String[] strArray8 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser4.parse(strArray8);
        java.lang.String str10 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray8);
        java.io.InputStream inputStream14 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = wcApplication0.countFromStdin((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream14);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "wc: No such file or directory\n" + "'", str10, "wc: No such file or directory\n");
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test066");
        sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser teeArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser();
        java.util.List<java.lang.String> strList1 = teeArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test067");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, "Could not read file", "Is a directory");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: Could not read file: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test068");
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.io.InputStream inputStream2 = null;
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication3 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str8 = mvArgsParser7.getDestFile();
        java.lang.String[] strArray12 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser7.parse(strArray12);
        java.lang.String[] strArray14 = mvArgsParser7.getSourceFiles();
        java.lang.String str15 = sortApplication3.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray14);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str16 = pasteApplication0.mergeFileAndStdin((java.lang.Boolean) true, inputStream2, strArray14);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PasteException; message: paste: paste: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.PasteException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str8);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertNotNull(strArray14);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\n" + "'", str15, "\n");
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test069");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, "No folder names are supplied", "");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: No folder names are supplied: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test070");
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.Boolean boolean1 = pasteArgsParser0.isSerial();
        java.lang.Boolean boolean2 = pasteArgsParser0.isSerial();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test071");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication1 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str6 = mvArgsParser5.getDestFile();
        java.lang.String[] strArray10 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser5.parse(strArray10);
        java.lang.String[] strArray12 = mvArgsParser5.getSourceFiles();
        java.lang.String str13 = sortApplication1.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray12);
        java.io.InputStream inputStream14 = null;
        java.io.OutputStream outputStream15 = null;
        // The following exception was thrown during execution in test generation
        try {
            cutApplication0.run(strArray12, inputStream14, outputStream15);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\n" + "'", str13, "\n");
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test072");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.Boolean boolean1 = sortArgsParser0.isReverseOrder();
        boolean boolean2 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean3 = sortArgsParser0.isReverseOrder();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test073");
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray0 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList1 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1, callCommandArray0);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand3 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        pipeCommand3.evaluate(inputStream4, outputStream5);
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray7 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList8 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList8, callCommandArray7);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand10 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList8);
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray11 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList12 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList12, callCommandArray11);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand14 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList12);
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        pipeCommand14.evaluate(inputStream15, outputStream16);
        sg.edu.nus.comp.cs4218.Command[] commandArray18 = new sg.edu.nus.comp.cs4218.Command[] { pipeCommand3, pipeCommand10, pipeCommand14 };
        java.util.ArrayList<sg.edu.nus.comp.cs4218.Command> commandList19 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.Command>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.Command>) commandList19, commandArray18);
        sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand sequenceCommand21 = new sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand((java.util.List<sg.edu.nus.comp.cs4218.Command>) commandList19);
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        sequenceCommand21.evaluate(inputStream22, outputStream23);
        org.junit.Assert.assertNotNull(callCommandArray0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(callCommandArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(callCommandArray11);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(commandArray18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test074");
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.io.InputStream inputStream2 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = pasteApplication0.mergeStdin((java.lang.Boolean) true, inputStream2);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PasteException; message: paste: paste: InputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.PasteException e) {
            // Expected exception.
        }
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test075");
        sg.edu.nus.comp.cs4218.impl.app.TeeApplication teeApplication0 = new sg.edu.nus.comp.cs4218.impl.app.TeeApplication();
        java.io.InputStream inputStream2 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean4 = cutArgsParser3.hasByteFlag();
        java.lang.Boolean boolean5 = cutArgsParser3.hasByteFlag();
        java.lang.String[] strArray7 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser3.parse(strArray7);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = teeApplication0.teeFromStdin((java.lang.Boolean) true, inputStream2, strArray7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.TeeException; message: tee: InputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.TeeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test076");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray3 = mvArgsParser2.getSourceFiles();
        java.lang.String str4 = echoApplication1.constructResult(strArray3);
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray3, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\n" + "'", str4, "\n");
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test077");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean2 = cutArgsParser1.hasByteFlag();
        java.lang.Boolean boolean3 = cutArgsParser1.hasByteFlag();
        java.lang.String[] strArray5 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser1.parse(strArray5);
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            catApplication0.run(strArray5, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test078");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.CatApplication.ERR_NO_ARGS;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Insufficient arguments" + "'", str0, "Insufficient arguments");
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test079");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray3 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList4 = new java.util.ArrayList<int[]>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList4, intArray3);
        java.io.InputStream inputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = cutApplication0.cutFromStdin((java.lang.Boolean) true, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList4, inputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test080");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray2 = mvArgsParser1.getSourceFiles();
        java.lang.String str3 = echoApplication0.constructResult(strArray2);
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean5 = cutArgsParser4.hasByteFlag();
        java.lang.Boolean boolean6 = cutArgsParser4.hasByteFlag();
        java.lang.String[] strArray8 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser4.parse(strArray8);
        java.io.InputStream inputStream10 = null;
        java.io.OutputStream outputStream11 = null;
        // The following exception was thrown during execution in test generation
        try {
            echoApplication0.run(strArray8, inputStream10, outputStream11);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "\n" + "'", str3, "\n");
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertNotNull(strArray8);
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test081");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication4 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean9 = cutArgsParser8.hasByteFlag();
        java.lang.Boolean boolean10 = cutArgsParser8.hasByteFlag();
        java.lang.String[] strArray12 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser8.parse(strArray12);
        java.lang.String str14 = wcApplication4.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray12);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray12);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean9 + "' != '" + false + "'", boolean9, false);
        org.junit.Assert.assertEquals("'" + boolean10 + "' != '" + false + "'", boolean10, false);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "wc: No such file or directory\n" + "'", str14, "wc: No such file or directory\n");
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test082");
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication0 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str2 = mvArgsParser1.getDestFile();
        java.lang.String[] strArray3 = mvArgsParser1.getSourceFiles();
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            rmApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.RmException; message: rm: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.RmException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test083");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) true, "Insufficient arguments", "wc: No such file or directory\n");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: Permission denied");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test084");
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray0 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList1 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1, callCommandArray0);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand3 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        pipeCommand3.terminate();
        java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList5 = pipeCommand3.getCallCommands();
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand6 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand(callCommandList5);
        org.junit.Assert.assertNotNull(callCommandArray0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(callCommandList5);
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test085");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        java.lang.String[] strArray3 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str4 = lsApplication0.listFolderContent((java.lang.Boolean) true, (java.lang.Boolean) true, strArray3);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot read the array length because \"folderName\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test086");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromStdin((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, inputStream4, "Null arguments");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test087");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str5 = mvArgsParser4.getDestFile();
        java.lang.String[] strArray9 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser4.parse(strArray9);
        java.lang.String[] strArray11 = mvArgsParser4.getSourceFiles();
        java.lang.String str12 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray11);
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser16 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str17 = mvArgsParser16.getDestFile();
        java.lang.String[] strArray18 = mvArgsParser16.getSourceFiles();
        java.lang.String str19 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray18);
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication20 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser24 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str25 = mvArgsParser24.getDestFile();
        java.lang.String[] strArray29 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser24.parse(strArray29);
        java.lang.String[] strArray31 = mvArgsParser24.getSourceFiles();
        java.lang.String str32 = sortApplication20.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray31);
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser36 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str37 = mvArgsParser36.getDestFile();
        java.lang.String[] strArray38 = mvArgsParser36.getSourceFiles();
        java.lang.String str39 = sortApplication20.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray38);
        java.io.InputStream inputStream40 = null;
        java.io.OutputStream outputStream41 = null;
        // The following exception was thrown during execution in test generation
        try {
            sortApplication0.run(strArray38, inputStream40, outputStream41);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "\n" + "'", str12, "\n");
        org.junit.Assert.assertNull(str17);
        org.junit.Assert.assertNotNull(strArray18);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "\n" + "'", str19, "\n");
        org.junit.Assert.assertNull(str25);
        org.junit.Assert.assertNotNull(strArray29);
        org.junit.Assert.assertNotNull(strArray31);
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "\n" + "'", str32, "\n");
        org.junit.Assert.assertNull(str37);
        org.junit.Assert.assertNotNull(strArray38);
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + "\n" + "'", str39, "\n");
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test088");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.io.InputStream inputStream4 = null;
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean6 = cutArgsParser5.hasByteFlag();
        java.lang.Boolean boolean7 = cutArgsParser5.hasByteFlag();
        java.lang.String[] strArray9 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser5.parse(strArray9);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str11 = wcApplication0.countFromFileAndStdin((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, inputStream4, strArray9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
        org.junit.Assert.assertNotNull(strArray9);
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test089");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean2 = cutArgsParser1.hasByteFlag();
        java.lang.Boolean boolean3 = cutArgsParser1.hasByteFlag();
        java.lang.String[] strArray5 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser1.parse(strArray5);
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            echoApplication0.run(strArray5, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test090");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray2 = mvArgsParser1.getSourceFiles();
        java.lang.String str3 = echoApplication0.constructResult(strArray2);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication4 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean9 = cutArgsParser8.hasByteFlag();
        java.lang.Boolean boolean10 = cutArgsParser8.hasByteFlag();
        java.lang.String[] strArray12 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser8.parse(strArray12);
        java.lang.String str14 = wcApplication4.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray12);
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        // The following exception was thrown during execution in test generation
        try {
            echoApplication0.run(strArray12, inputStream15, outputStream16);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "\n" + "'", str3, "\n");
        org.junit.Assert.assertEquals("'" + boolean9 + "' != '" + false + "'", boolean9, false);
        org.junit.Assert.assertEquals("'" + boolean10 + "' != '" + false + "'", boolean10, false);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "wc: No such file or directory\n" + "'", str14, "wc: No such file or directory\n");
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test091");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        boolean boolean1 = uniqArgsParser0.isAllRepeated();
        java.lang.String[] strArray2 = uniqArgsParser0.getFileNames();
        boolean boolean3 = uniqArgsParser0.isRepeated();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test092");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser.FLAG_IS_WORD_COUNT;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'w' + "'", char0 == 'w');
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test093");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray1 = grepArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = grepArgsParser0.isInvert();
        org.junit.Assert.assertNull(strArray1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test094");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray2 = mvArgsParser1.getSourceFiles();
        java.lang.String str3 = echoApplication0.constructResult(strArray2);
        sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser teeArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser();
        java.lang.Boolean boolean5 = teeArgsParser4.isAppend();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str7 = mvArgsParser6.getDestFile();
        java.lang.String[] strArray8 = mvArgsParser6.getSourceFiles();
        java.lang.Boolean boolean9 = mvArgsParser6.isOverwrite();
        java.lang.String[] strArray10 = mvArgsParser6.getSourceFiles();
        teeArgsParser4.parse(strArray10);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        // The following exception was thrown during execution in test generation
        try {
            echoApplication0.run(strArray10, inputStream12, outputStream13);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "\n" + "'", str3, "\n");
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + boolean9 + "' != '" + true + "'", boolean9, true);
        org.junit.Assert.assertNotNull(strArray10);
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test095");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str5 = wcApplication0.countFromStdin((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test096");
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.util.List<java.lang.String> strList1 = pasteArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test097");
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean1 = cutArgsParser0.hasByteFlag();
        java.lang.String str2 = cutArgsParser0.getExp();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str2);
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test098");
        sg.edu.nus.comp.cs4218.impl.app.TeeApplication teeApplication0 = new sg.edu.nus.comp.cs4218.impl.app.TeeApplication();
        java.lang.String[] strArray2 = new java.lang.String[] { "Directory already exists" };
        java.io.InputStream inputStream3 = null;
        java.io.OutputStream outputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            teeApplication0.run(strArray2, inputStream3, outputStream4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.TeeException; message: tee: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.TeeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test099");
        char char0 = sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser.FLAG_IS_FIRST_NUM;
        org.junit.Assert.assertTrue("'" + char0 + "' != '" + 'n' + "'", char0 == 'n');
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test100");
        sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser rmArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser();
        java.util.List<java.lang.String> strList1 = rmArgsParser0.getDirectories();
        sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner applicationRunner2 = null;
        sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver argumentResolver3 = null;
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand callCommand4 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand(strList1, applicationRunner2, argumentResolver3);
        callCommand4.terminate();
        java.lang.Class<?> wildcardClass6 = callCommand4.getClass();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test101");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, "Is a directory", "No files provided");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: Is a directory: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test102");
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray0 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList1 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1, callCommandArray0);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand3 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        pipeCommand3.evaluate(inputStream4, outputStream5);
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray7 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList8 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList8, callCommandArray7);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand10 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList8);
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray11 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList12 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList12, callCommandArray11);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand14 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList12);
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        pipeCommand14.evaluate(inputStream15, outputStream16);
        sg.edu.nus.comp.cs4218.Command[] commandArray18 = new sg.edu.nus.comp.cs4218.Command[] { pipeCommand3, pipeCommand10, pipeCommand14 };
        java.util.ArrayList<sg.edu.nus.comp.cs4218.Command> commandList19 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.Command>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.Command>) commandList19, commandArray18);
        sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand sequenceCommand21 = new sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand((java.util.List<sg.edu.nus.comp.cs4218.Command>) commandList19);
        sequenceCommand21.terminate();
        org.junit.Assert.assertNotNull(callCommandArray0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(callCommandArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(callCommandArray11);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(commandArray18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
    }

    @Test
    public void test103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test103");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean5 = cutArgsParser4.hasByteFlag();
        java.lang.Boolean boolean6 = cutArgsParser4.hasByteFlag();
        java.lang.String[] strArray8 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser4.parse(strArray8);
        java.lang.String str10 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray8);
        java.io.InputStream inputStream14 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str15 = wcApplication0.countFromStdin((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream14);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "wc: No such file or directory\n" + "'", str10, "wc: No such file or directory\n");
    }

    @Test
    public void test104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test104");
        sg.edu.nus.comp.cs4218.impl.app.TeeApplication teeApplication0 = new sg.edu.nus.comp.cs4218.impl.app.TeeApplication();
        java.io.InputStream inputStream2 = null;
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str4 = mvArgsParser3.getDestFile();
        java.lang.String[] strArray5 = mvArgsParser3.getSourceFiles();
        java.lang.Boolean boolean6 = mvArgsParser3.isOverwrite();
        java.lang.String[] strArray7 = mvArgsParser3.getSourceFiles();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str8 = teeApplication0.teeFromStdin((java.lang.Boolean) false, inputStream2, strArray7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.TeeException; message: tee: InputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.TeeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + true + "'", boolean6, true);
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test105");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isRecursive();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test106");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
// flaky:         cdApplication0.changeToDirectory("Null Pointer Exception");
    }

    @Test
    public void test107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test107");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str2 = mvArgsParser1.getDestFile();
        java.lang.String[] strArray6 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser1.parse(strArray6);
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            uniqApplication0.run(strArray6, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test108");
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray3 = mvArgsParser2.getSourceFiles();
        java.lang.String str4 = echoApplication1.constructResult(strArray3);
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            pasteApplication0.run(strArray3, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PasteException; message: paste: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.PasteException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\n" + "'", str4, "\n");
    }

    @Test
    public void test109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test109");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication1 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean6 = cutArgsParser5.hasByteFlag();
        java.lang.Boolean boolean7 = cutArgsParser5.hasByteFlag();
        java.lang.String[] strArray9 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser5.parse(strArray9);
        java.lang.String str11 = wcApplication1.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray9);
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        // The following exception was thrown during execution in test generation
        try {
            cutApplication0.run(strArray9, inputStream12, outputStream13);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertEquals("'" + boolean7 + "' != '" + false + "'", boolean7, false);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "wc: No such file or directory\n" + "'", str11, "wc: No such file or directory\n");
    }

    @Test
    public void test110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test110");
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray0 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList1 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1, callCommandArray0);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand3 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        pipeCommand3.evaluate(inputStream4, outputStream5);
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray7 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList8 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList8, callCommandArray7);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand10 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList8);
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray11 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList12 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList12, callCommandArray11);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand14 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList12);
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        pipeCommand14.evaluate(inputStream15, outputStream16);
        sg.edu.nus.comp.cs4218.Command[] commandArray18 = new sg.edu.nus.comp.cs4218.Command[] { pipeCommand3, pipeCommand10, pipeCommand14 };
        java.util.ArrayList<sg.edu.nus.comp.cs4218.Command> commandList19 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.Command>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.Command>) commandList19, commandArray18);
        sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand sequenceCommand21 = new sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand((java.util.List<sg.edu.nus.comp.cs4218.Command>) commandList19);
        java.util.List<sg.edu.nus.comp.cs4218.Command> commandList22 = sequenceCommand21.getCommands();
        sequenceCommand21.terminate();
        org.junit.Assert.assertNotNull(callCommandArray0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(callCommandArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(callCommandArray11);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(commandArray18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        org.junit.Assert.assertNotNull(commandList22);
    }

    @Test
    public void test111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test111");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.Boolean boolean1 = sortArgsParser0.isFirstWordNumber();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test112");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.util.List<int[]> intArrayList2 = cutApplication0.getCutRanges("No folder names are supplied");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: Invalid regular expression supplied");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
    }

    @Test
    public void test113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test113");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        boolean boolean1 = uniqArgsParser0.isCount();
        boolean boolean2 = uniqArgsParser0.isAllRepeated();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test114");
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        boolean boolean2 = uniqArgsParser1.isAllRepeated();
        java.lang.String[] strArray3 = uniqArgsParser1.getFileNames();
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            pasteApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PasteException; message: paste: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.PasteException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test115");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isWordCount();
        java.util.List<java.lang.String> strList2 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
    }

    @Test
    public void test116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test116");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.String[] strArray1 = grepArgsParser0.getFileNames();
        java.lang.String[] strArray2 = grepArgsParser0.getFileNames();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = grepArgsParser0.getPattern();
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(strArray1);
        org.junit.Assert.assertNull(strArray2);
    }

    @Test
    public void test117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test117");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        java.lang.String[] strArray1 = null;
        java.io.InputStream inputStream2 = null;
        java.io.OutputStream outputStream3 = null;
        // The following exception was thrown during execution in test generation
        try {
            wcApplication0.run(strArray1, inputStream2, outputStream3);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
    }

    @Test
    public void test118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test118");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromStdin((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream4, "Directory already exists");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test119");
        sg.edu.nus.comp.cs4218.impl.app.ExitApplication exitApplication0 = new sg.edu.nus.comp.cs4218.impl.app.ExitApplication();
    }

    @Test
    public void test120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test120");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean5 = cutArgsParser4.hasByteFlag();
        java.lang.Boolean boolean6 = cutArgsParser4.hasByteFlag();
        java.lang.String[] strArray8 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser4.parse(strArray8);
        java.lang.String str10 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray8);
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication11 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray13 = mvArgsParser12.getSourceFiles();
        java.lang.String str14 = echoApplication11.constructResult(strArray13);
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        // The following exception was thrown during execution in test generation
        try {
            wcApplication0.run(strArray13, inputStream15, outputStream16);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "wc: No such file or directory\n" + "'", str10, "wc: No such file or directory\n");
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "\n" + "'", str14, "\n");
    }

    @Test
    public void test121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test121");
        sg.edu.nus.comp.cs4218.impl.app.TeeApplication teeApplication0 = new sg.edu.nus.comp.cs4218.impl.app.TeeApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication2 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray4 = mvArgsParser3.getSourceFiles();
        java.lang.String str5 = echoApplication2.constructResult(strArray4);
        mkdirArgsParser1.parse(strArray4);
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            teeApplication0.run(strArray4, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.TeeException; message: tee: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.TeeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "\n" + "'", str5, "\n");
    }

    @Test
    public void test122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test122");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray1 = mvArgsParser0.getSourceFiles();
        java.lang.String[] strArray2 = mvArgsParser0.getSourceFiles();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertNotNull(strArray2);
    }

    @Test
    public void test123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test123");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        boolean boolean1 = uniqArgsParser0.isAllRepeated();
        boolean boolean2 = uniqArgsParser0.isCount();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication3 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray5 = mvArgsParser4.getSourceFiles();
        java.lang.String str6 = echoApplication3.constructResult(strArray5);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication7 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean12 = cutArgsParser11.hasByteFlag();
        java.lang.Boolean boolean13 = cutArgsParser11.hasByteFlag();
        java.lang.String[] strArray15 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser11.parse(strArray15);
        java.lang.String str17 = wcApplication7.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray15);
        java.lang.String str18 = echoApplication3.constructResult(strArray15);
        uniqArgsParser0.parse(strArray15);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\n" + "'", str6, "\n");
        org.junit.Assert.assertEquals("'" + boolean12 + "' != '" + false + "'", boolean12, false);
        org.junit.Assert.assertEquals("'" + boolean13 + "' != '" + false + "'", boolean13, false);
        org.junit.Assert.assertNotNull(strArray15);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "wc: No such file or directory\n" + "'", str17, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "Exception Caught\n" + "'", str18, "Exception Caught\n");
    }

    @Test
    public void test124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test124");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.Boolean boolean1 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.util.List<java.lang.String> strList3 = sortArgsParser0.getFileNames();
        boolean boolean4 = sortArgsParser0.isCaseIndependent();
        java.lang.Boolean boolean5 = sortArgsParser0.isReverseOrder();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test125");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.util.List<java.lang.String> strList1 = sortArgsParser0.getFileNames();
        java.util.List<java.lang.String> strList2 = sortArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList2);
    }

    @Test
    public void test126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test126");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication2 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser3 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str4 = mvArgsParser3.getDestFile();
        java.lang.String[] strArray5 = mvArgsParser3.getSourceFiles();
        java.lang.String str6 = echoApplication2.constructResult(strArray5);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = catApplication0.catFiles((java.lang.Boolean) false, strArray5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CatException; message: cat: No files provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CatException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str4);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "\n" + "'", str6, "\n");
    }

    @Test
    public void test127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test127");
        sg.edu.nus.comp.cs4218.impl.app.PasteApplication pasteApplication0 = new sg.edu.nus.comp.cs4218.impl.app.PasteApplication();
        java.io.InputStream inputStream2 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = pasteApplication0.mergeStdin((java.lang.Boolean) false, inputStream2);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.PasteException; message: paste: paste: InputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.PasteException e) {
            // Expected exception.
        }
    }

    @Test
    public void test128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test128");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.GrepApplication.INVALID_PATTERN;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Invalid pattern syntax" + "'", str0, "Invalid pattern syntax");
    }

    @Test
    public void test129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test129");
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean1 = cutArgsParser0.hasByteFlag();
        java.lang.Boolean boolean2 = cutArgsParser0.hasByteFlag();
        java.lang.Boolean boolean3 = cutArgsParser0.hasByteFlag();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test130");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.Boolean boolean1 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.util.List<java.lang.String> strList3 = sortArgsParser0.getFileNames();
        boolean boolean4 = sortArgsParser0.isCaseIndependent();
        java.util.List<java.lang.String> strList5 = sortArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strList5);
    }

    @Test
    public void test131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test131");
        java.util.List<sg.edu.nus.comp.cs4218.Command> commandList0 = null;
        sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand sequenceCommand1 = new sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand(commandList0);
    }

    @Test
    public void test132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test132");
        sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser pasteArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.PasteArgsParser();
        java.lang.Boolean boolean1 = pasteArgsParser0.isSerial();
        java.util.List<java.lang.String> strList2 = pasteArgsParser0.getFileNames();
        java.lang.Boolean boolean3 = pasteArgsParser0.isSerial();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test133");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray2 = mvArgsParser1.getSourceFiles();
        java.lang.String str3 = echoApplication0.constructResult(strArray2);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication4 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean9 = cutArgsParser8.hasByteFlag();
        java.lang.Boolean boolean10 = cutArgsParser8.hasByteFlag();
        java.lang.String[] strArray12 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser8.parse(strArray12);
        java.lang.String str14 = wcApplication4.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray12);
        java.lang.String str15 = echoApplication0.constructResult(strArray12);
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser16 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str17 = mvArgsParser16.getDestFile();
        java.lang.String[] strArray21 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser16.parse(strArray21);
        java.lang.String[] strArray23 = mvArgsParser16.getSourceFiles();
        java.io.InputStream inputStream24 = null;
        java.io.OutputStream outputStream25 = null;
        // The following exception was thrown during execution in test generation
        try {
            echoApplication0.run(strArray23, inputStream24, outputStream25);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "\n" + "'", str3, "\n");
        org.junit.Assert.assertEquals("'" + boolean9 + "' != '" + false + "'", boolean9, false);
        org.junit.Assert.assertEquals("'" + boolean10 + "' != '" + false + "'", boolean10, false);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "wc: No such file or directory\n" + "'", str14, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "Exception Caught\n" + "'", str15, "Exception Caught\n");
        org.junit.Assert.assertNull(str17);
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertNotNull(strArray23);
    }

    @Test
    public void test134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test134");
        java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList0 = null;
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand1 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand(callCommandList0);
        java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList2 = pipeCommand1.getCallCommands();
        org.junit.Assert.assertNull(callCommandList2);
    }

    @Test
    public void test135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test135");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.util.List<int[]> intArrayList2 = cutApplication0.getCutRanges("");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: Invalid regular expression supplied");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
    }

    @Test
    public void test136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test136");
        sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser teeArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser();
        java.lang.Boolean boolean1 = teeArgsParser0.isAppend();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str3 = mvArgsParser2.getDestFile();
        java.lang.String[] strArray4 = mvArgsParser2.getSourceFiles();
        java.lang.Boolean boolean5 = mvArgsParser2.isOverwrite();
        java.lang.String[] strArray6 = mvArgsParser2.getSourceFiles();
        teeArgsParser0.parse(strArray6);
        java.lang.Boolean boolean8 = teeArgsParser0.isAppend();
        java.util.List<java.lang.String> strList9 = teeArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(str3);
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + true + "'", boolean5, true);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + false + "'", boolean8, false);
        org.junit.Assert.assertNotNull(strList9);
    }

    @Test
    public void test137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test137");
        java.lang.String[] strArray9 = new java.lang.String[] { "No files provided", "hi!", "No files provided", "Invalid syntax", "", "Exception Caught", "Invalid syntax", "Could not write to output stream", "hi!" };
        java.util.ArrayList<java.lang.String> strList10 = new java.util.ArrayList<java.lang.String>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList10, strArray9);
        sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner applicationRunner12 = null;
        sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver argumentResolver13 = null;
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand callCommand14 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand((java.util.List<java.lang.String>) strList10, applicationRunner12, argumentResolver13);
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        // The following exception was thrown during execution in test generation
        try {
            callCommand14.evaluate(inputStream15, outputStream16);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver.parseArguments(java.util.List)\" because \"this.argumentResolver\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test138");
        sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser rmArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser();
        java.lang.Boolean boolean1 = rmArgsParser0.isRecursive();
        java.lang.Boolean boolean2 = rmArgsParser0.isRemoveEmpty();
        java.util.List<java.lang.String> strList3 = rmArgsParser0.getDirectories();
        java.util.List<java.lang.String> strList4 = rmArgsParser0.getDirectories();
        java.util.List<java.lang.String> strList5 = rmArgsParser0.getDirectories();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertNotNull(strList5);
    }

    @Test
    public void test139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test139");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray1 = mvArgsParser0.getSourceFiles();
        java.lang.Boolean boolean2 = mvArgsParser0.isOverwrite();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + true + "'", boolean2, true);
    }

    @Test
    public void test140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test140");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.lang.Boolean boolean1 = wcArgsParser0.isWordCount();
        java.lang.Boolean boolean2 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean3 = wcArgsParser0.isLineCount();
        java.lang.Boolean boolean4 = wcArgsParser0.isWordCount();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test141");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str1 = mvArgsParser0.getDestFile();
        java.lang.String[] strArray2 = mvArgsParser0.getSourceFiles();
        java.lang.Boolean boolean3 = mvArgsParser0.isOverwrite();
        java.lang.Boolean boolean4 = mvArgsParser0.isOverwrite();
        org.junit.Assert.assertNull(str1);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + true + "'", boolean3, true);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + true + "'", boolean4, true);
    }

    @Test
    public void test142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test142");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray2 = mvArgsParser1.getSourceFiles();
        java.lang.String str3 = echoApplication0.constructResult(strArray2);
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str5 = mvArgsParser4.getDestFile();
        java.lang.String[] strArray9 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser4.parse(strArray9);
        java.lang.String[] strArray11 = mvArgsParser4.getSourceFiles();
        java.io.InputStream inputStream12 = null;
        java.io.OutputStream outputStream13 = null;
        // The following exception was thrown during execution in test generation
        try {
            echoApplication0.run(strArray11, inputStream12, outputStream13);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "\n" + "'", str3, "\n");
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertNotNull(strArray11);
    }

    @Test
    public void test143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test143");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray2 = mvArgsParser1.getSourceFiles();
        java.lang.String str3 = echoApplication0.constructResult(strArray2);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication4 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean9 = cutArgsParser8.hasByteFlag();
        java.lang.Boolean boolean10 = cutArgsParser8.hasByteFlag();
        java.lang.String[] strArray12 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser8.parse(strArray12);
        java.lang.String str14 = wcApplication4.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray12);
        java.lang.String str15 = echoApplication0.constructResult(strArray12);
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication16 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser19 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray20 = mvArgsParser19.getSourceFiles();
        rmApplication16.remove((java.lang.Boolean) true, (java.lang.Boolean) false, strArray20);
        java.io.InputStream inputStream22 = null;
        java.io.OutputStream outputStream23 = null;
        // The following exception was thrown during execution in test generation
        try {
            echoApplication0.run(strArray20, inputStream22, outputStream23);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.EchoException; message: echo: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.EchoException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "\n" + "'", str3, "\n");
        org.junit.Assert.assertEquals("'" + boolean9 + "' != '" + false + "'", boolean9, false);
        org.junit.Assert.assertEquals("'" + boolean10 + "' != '" + false + "'", boolean10, false);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "wc: No such file or directory\n" + "'", str14, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "Exception Caught\n" + "'", str15, "Exception Caught\n");
        org.junit.Assert.assertNotNull(strArray20);
    }

    @Test
    public void test144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test144");
        sg.edu.nus.comp.cs4218.impl.app.LsApplication lsApplication0 = new sg.edu.nus.comp.cs4218.impl.app.LsApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str2 = mvArgsParser1.getDestFile();
        java.lang.String[] strArray6 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser1.parse(strArray6);
        java.lang.String[] strArray8 = mvArgsParser1.getSourceFiles();
        java.io.InputStream inputStream9 = null;
        java.io.OutputStream outputStream10 = null;
        // The following exception was thrown during execution in test generation
        try {
            lsApplication0.run(strArray8, inputStream9, outputStream10);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.LsException; message: ls: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.LsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertNotNull(strArray8);
    }

    @Test
    public void test145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test145");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str1 = mvArgsParser0.getDestFile();
        java.lang.String[] strArray5 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser0.parse(strArray5);
        java.lang.String[] strArray7 = mvArgsParser0.getSourceFiles();
        java.lang.Boolean boolean8 = mvArgsParser0.isOverwrite();
        org.junit.Assert.assertNull(str1);
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + true + "'", boolean8, true);
    }

    @Test
    public void test146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test146");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray3 = mvArgsParser2.getSourceFiles();
        java.lang.String str4 = echoApplication1.constructResult(strArray3);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication5 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser9 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean10 = cutArgsParser9.hasByteFlag();
        java.lang.Boolean boolean11 = cutArgsParser9.hasByteFlag();
        java.lang.String[] strArray13 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser9.parse(strArray13);
        java.lang.String str15 = wcApplication5.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray13);
        java.lang.String str16 = echoApplication1.constructResult(strArray13);
        java.io.InputStream inputStream17 = null;
        java.io.OutputStream outputStream18 = null;
        // The following exception was thrown during execution in test generation
        try {
            uniqApplication0.run(strArray13, inputStream17, outputStream18);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\n" + "'", str4, "\n");
        org.junit.Assert.assertEquals("'" + boolean10 + "' != '" + false + "'", boolean10, false);
        org.junit.Assert.assertEquals("'" + boolean11 + "' != '" + false + "'", boolean11, false);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "wc: No such file or directory\n" + "'", str15, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "Exception Caught\n" + "'", str16, "Exception Caught\n");
    }

    @Test
    public void test147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test147");
        sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser sortArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.SortArgsParser();
        java.lang.Boolean boolean1 = sortArgsParser0.isReverseOrder();
        java.lang.Boolean boolean2 = sortArgsParser0.isFirstWordNumber();
        java.util.List<java.lang.String> strList3 = sortArgsParser0.getFileNames();
        java.lang.Boolean boolean4 = sortArgsParser0.isReverseOrder();
        java.util.List<java.lang.String> strList5 = sortArgsParser0.getFileNames();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
        org.junit.Assert.assertNotNull(strList5);
    }

    @Test
    public void test148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test148");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) true, "Pattern should not be empty.", "illegal option -- ");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: Pattern should not be empty.: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test149");
        sg.edu.nus.comp.cs4218.impl.app.TeeApplication teeApplication0 = new sg.edu.nus.comp.cs4218.impl.app.TeeApplication();
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        boolean boolean2 = uniqArgsParser1.isAllRepeated();
        java.lang.String[] strArray3 = uniqArgsParser1.getFileNames();
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            teeApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.TeeException; message: tee: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.TeeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test150");
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        java.util.List<java.lang.String> strList1 = mkdirArgsParser0.getDirectories();
        org.junit.Assert.assertNotNull(strList1);
    }

    @Test
    public void test151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test151");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication3 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser7 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean8 = cutArgsParser7.hasByteFlag();
        java.lang.Boolean boolean9 = cutArgsParser7.hasByteFlag();
        java.lang.String[] strArray11 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser7.parse(strArray11);
        java.lang.String str13 = wcApplication3.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray11);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "Invalid syntax", strArray11);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: /Users/rachelgina/Desktop/school_y4s2/cs4218/Project/cs4218-project-2024-team32/Null Pointer Exception/Exception Caught");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + false + "'", boolean8, false);
        org.junit.Assert.assertEquals("'" + boolean9 + "' != '" + false + "'", boolean9, false);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "wc: No such file or directory\n" + "'", str13, "wc: No such file or directory\n");
    }

    @Test
    public void test152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test152");
        sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser rmArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser();
        java.util.List<java.lang.String> strList1 = rmArgsParser0.getDirectories();
        sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner applicationRunner2 = null;
        sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver argumentResolver3 = null;
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand callCommand4 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand(strList1, applicationRunner2, argumentResolver3);
        callCommand4.terminate();
        java.util.List<java.lang.String> strList6 = callCommand4.getArgsList();
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            callCommand4.evaluate(inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.ShellException; message: shell: Invalid syntax");
        } catch (sg.edu.nus.comp.cs4218.exception.ShellException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertNotNull(strList6);
    }

    @Test
    public void test153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test153");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray1 = mvArgsParser0.getSourceFiles();
        java.lang.String str2 = mvArgsParser0.getDestFile();
        java.lang.String str3 = mvArgsParser0.getDestFile();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test154");
        sg.edu.nus.comp.cs4218.impl.app.CatApplication catApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CatApplication();
        java.io.InputStream inputStream2 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = catApplication0.catStdin((java.lang.Boolean) true, inputStream2);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test155");
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray0 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList1 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1, callCommandArray0);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand3 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand4 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        pipeCommand4.terminate();
        org.junit.Assert.assertNotNull(callCommandArray0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test156");
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray0 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList1 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1, callCommandArray0);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand3 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        pipeCommand3.terminate();
        java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList5 = pipeCommand3.getCallCommands();
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        pipeCommand3.evaluate(inputStream6, outputStream7);
        org.junit.Assert.assertNotNull(callCommandArray0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(callCommandList5);
    }

    @Test
    public void test157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test157");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        boolean boolean1 = uniqArgsParser0.isAllRepeated();
        boolean boolean2 = uniqArgsParser0.isRepeated();
        boolean boolean3 = uniqArgsParser0.isAllRepeated();
        boolean boolean4 = uniqArgsParser0.isCount();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test158");
        sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser rmArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser();
        java.lang.Boolean boolean1 = rmArgsParser0.isRecursive();
        java.lang.Boolean boolean2 = rmArgsParser0.isRemoveEmpty();
        java.util.List<java.lang.String> strList3 = rmArgsParser0.getDirectories();
        java.util.List<java.lang.String> strList4 = rmArgsParser0.getDirectories();
        java.lang.Boolean boolean5 = rmArgsParser0.isRecursive();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertNotNull(strList4);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
    }

    @Test
    public void test159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test159");
        sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser rmArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser();
        java.lang.Boolean boolean1 = rmArgsParser0.isRecursive();
        java.util.List<java.lang.String> strList2 = rmArgsParser0.getDirectories();
        java.util.List<java.lang.String> strList3 = rmArgsParser0.getDirectories();
        sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner applicationRunner4 = null;
        sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver argumentResolver5 = null;
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand callCommand6 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand(strList3, applicationRunner4, argumentResolver5);
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertNotNull(strList3);
    }

    @Test
    public void test160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test160");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication4 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray6 = mvArgsParser5.getSourceFiles();
        java.lang.String str7 = echoApplication4.constructResult(strArray6);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication8 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean13 = cutArgsParser12.hasByteFlag();
        java.lang.Boolean boolean14 = cutArgsParser12.hasByteFlag();
        java.lang.String[] strArray16 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser12.parse(strArray16);
        java.lang.String str18 = wcApplication8.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray16);
        java.lang.String str19 = echoApplication4.constructResult(strArray16);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str20 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray16);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "\n" + "'", str7, "\n");
        org.junit.Assert.assertEquals("'" + boolean13 + "' != '" + false + "'", boolean13, false);
        org.junit.Assert.assertEquals("'" + boolean14 + "' != '" + false + "'", boolean14, false);
        org.junit.Assert.assertNotNull(strArray16);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "wc: No such file or directory\n" + "'", str18, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "Exception Caught\n" + "'", str19, "Exception Caught\n");
    }

    @Test
    public void test161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test161");
        sg.edu.nus.comp.cs4218.impl.app.CutApplication cutApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CutApplication();
        int[][] intArray3 = new int[][] {};
        java.util.ArrayList<int[]> intArrayList4 = new java.util.ArrayList<int[]>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<int[]>) intArrayList4, intArray3);
        java.io.InputStream inputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str7 = cutApplication0.cutFromStdin((java.lang.Boolean) false, (java.lang.Boolean) false, (java.util.List<int[]>) intArrayList4, inputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CutException; message: cut: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CutException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNotNull(intArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test162");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication4 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str6 = mvArgsParser5.getDestFile();
        java.lang.String[] strArray7 = mvArgsParser5.getSourceFiles();
        java.lang.String str8 = echoApplication4.constructResult(strArray7);
        java.lang.String str9 = wcApplication0.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray7);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication13 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser17 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean18 = cutArgsParser17.hasByteFlag();
        java.lang.Boolean boolean19 = cutArgsParser17.hasByteFlag();
        java.lang.String[] strArray21 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser17.parse(strArray21);
        java.lang.String str23 = wcApplication13.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray21);
        java.lang.String str24 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray21);
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication25 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser28 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray29 = mvArgsParser28.getSourceFiles();
        rmApplication25.remove((java.lang.Boolean) true, (java.lang.Boolean) false, strArray29);
        java.io.InputStream inputStream31 = null;
        java.io.OutputStream outputStream32 = null;
        // The following exception was thrown during execution in test generation
        try {
            wcApplication0.run(strArray29, inputStream31, outputStream32);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\n" + "'", str8, "\n");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\n" + "'", str9, "\n");
        org.junit.Assert.assertEquals("'" + boolean18 + "' != '" + false + "'", boolean18, false);
        org.junit.Assert.assertEquals("'" + boolean19 + "' != '" + false + "'", boolean19, false);
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "wc: No such file or directory\n" + "'", str23, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "wc: No such file or directory\n" + "'", str24, "wc: No such file or directory\n");
        org.junit.Assert.assertNotNull(strArray29);
    }

    @Test
    public void test163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test163");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, "This is a directory", "Exception Caught");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: This is a directory: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test164");
        sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser lsArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.LsArgsParser();
        java.lang.Boolean boolean1 = lsArgsParser0.isSortByExt();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test165");
        sg.edu.nus.comp.cs4218.impl.app.MkdirApplication mkdirApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MkdirApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str2 = mvArgsParser1.getDestFile();
        java.lang.String[] strArray6 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser1.parse(strArray6);
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        mkdirApplication0.run(strArray6, inputStream8, outputStream9);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test166");
        sg.edu.nus.comp.cs4218.impl.app.TeeApplication teeApplication0 = new sg.edu.nus.comp.cs4218.impl.app.TeeApplication();
        java.io.InputStream inputStream2 = null;
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication3 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication6 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication10 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser11 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str12 = mvArgsParser11.getDestFile();
        java.lang.String[] strArray13 = mvArgsParser11.getSourceFiles();
        java.lang.String str14 = echoApplication10.constructResult(strArray13);
        java.lang.String str15 = wcApplication6.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray13);
        rmApplication3.remove((java.lang.Boolean) true, (java.lang.Boolean) false, strArray13);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str17 = teeApplication0.teeFromStdin((java.lang.Boolean) false, inputStream2, strArray13);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.TeeException; message: tee: InputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.TeeException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str12);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "\n" + "'", str14, "\n");
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "\n" + "'", str15, "\n");
    }

    @Test
    public void test167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test167");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        java.lang.String str4 = mvApplication0.mvSrcFileToDestFile((java.lang.Boolean) false, "illegal option -- ", "Null Pointer Exception");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication7 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser8 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray9 = mvArgsParser8.getSourceFiles();
        java.lang.String str10 = echoApplication7.constructResult(strArray9);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication11 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser15 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean16 = cutArgsParser15.hasByteFlag();
        java.lang.Boolean boolean17 = cutArgsParser15.hasByteFlag();
        java.lang.String[] strArray19 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser15.parse(strArray19);
        java.lang.String str21 = wcApplication11.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray19);
        java.lang.String str22 = echoApplication7.constructResult(strArray19);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str23 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "wc: No such file or directory\n", strArray19);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: /Users/rachelgina/Desktop/school_y4s2/cs4218/Project/cs4218-project-2024-team32/Null Pointer Exception/Exception Caught");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "Null Pointer Exception" + "'", str4, "Null Pointer Exception");
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "\n" + "'", str10, "\n");
        org.junit.Assert.assertEquals("'" + boolean16 + "' != '" + false + "'", boolean16, false);
        org.junit.Assert.assertEquals("'" + boolean17 + "' != '" + false + "'", boolean17, false);
        org.junit.Assert.assertNotNull(strArray19);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "wc: No such file or directory\n" + "'", str21, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "Exception Caught\n" + "'", str22, "Exception Caught\n");
    }

    @Test
    public void test168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test168");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromFile((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, "No folder names are supplied", "wc: No such file or directory\n");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: No folder names are supplied: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test169");
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication0 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser1 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray2 = mvArgsParser1.getSourceFiles();
        java.lang.String str3 = echoApplication0.constructResult(strArray2);
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray5 = mvArgsParser4.getSourceFiles();
        java.lang.String str6 = mvArgsParser4.getDestFile();
        java.lang.String[] strArray7 = mvArgsParser4.getSourceFiles();
        java.lang.String str8 = echoApplication0.constructResult(strArray7);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "\n" + "'", str3, "\n");
        org.junit.Assert.assertNotNull(strArray5);
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\n" + "'", str8, "\n");
    }

    @Test
    public void test170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test170");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        java.io.InputStream inputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str6 = uniqApplication0.uniqFromStdin((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream4, "illegal option -- ");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
    }

    @Test
    public void test171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test171");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean5 = cutArgsParser4.hasByteFlag();
        java.lang.Boolean boolean6 = cutArgsParser4.hasByteFlag();
        java.lang.String[] strArray8 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser4.parse(strArray8);
        java.lang.String str10 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray8);
        java.io.InputStream inputStream14 = null;
        sg.edu.nus.comp.cs4218.impl.app.RmApplication rmApplication15 = new sg.edu.nus.comp.cs4218.impl.app.RmApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser18 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray19 = mvArgsParser18.getSourceFiles();
        rmApplication15.remove((java.lang.Boolean) true, (java.lang.Boolean) false, strArray19);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str21 = wcApplication0.countFromFileAndStdin((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) false, inputStream14, strArray19);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertEquals("'" + boolean6 + "' != '" + false + "'", boolean6, false);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "wc: No such file or directory\n" + "'", str10, "wc: No such file or directory\n");
        org.junit.Assert.assertNotNull(strArray19);
    }

    @Test
    public void test172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test172");
        sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser rmArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.RmArgsParser();
        java.lang.Boolean boolean1 = rmArgsParser0.isRemoveEmpty();
        java.util.List<java.lang.String> strList2 = rmArgsParser0.getDirectories();
        java.lang.Boolean boolean3 = rmArgsParser0.isRemoveEmpty();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNotNull(strList2);
        org.junit.Assert.assertEquals("'" + boolean3 + "' != '" + false + "'", boolean3, false);
    }

    @Test
    public void test173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test173");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication1 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str6 = mvArgsParser5.getDestFile();
        java.lang.String[] strArray10 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser5.parse(strArray10);
        java.lang.String[] strArray12 = mvArgsParser5.getSourceFiles();
        java.lang.String str13 = sortApplication1.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray12);
        java.io.InputStream inputStream14 = null;
        java.io.OutputStream outputStream15 = null;
        // The following exception was thrown during execution in test generation
        try {
            uniqApplication0.run(strArray12, inputStream14, outputStream15);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertNotNull(strArray12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "\n" + "'", str13, "\n");
    }

    @Test
    public void test174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test174");
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication1 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser2 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray3 = mvArgsParser2.getSourceFiles();
        java.lang.String str4 = echoApplication1.constructResult(strArray3);
        mkdirArgsParser0.parse(strArray3);
        java.util.List<java.lang.String> strList6 = mkdirArgsParser0.getDirectories();
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "\n" + "'", str4, "\n");
        org.junit.Assert.assertNotNull(strList6);
    }

    @Test
    public void test175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test175");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str5 = mvArgsParser4.getDestFile();
        java.lang.String[] strArray9 = new java.lang.String[] { "illegal option -- ", "Invalid syntax", "Pattern should not be empty." };
        mvArgsParser4.parse(strArray9);
        java.lang.String[] strArray11 = mvArgsParser4.getSourceFiles();
        java.lang.String str12 = sortApplication0.sortFromFiles((java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray11);
        java.io.InputStream inputStream16 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str17 = sortApplication0.sortFromStdin((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, inputStream16);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SortException; message: sort: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.SortException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str5);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertNotNull(strArray11);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "\n" + "'", str12, "\n");
    }

    @Test
    public void test176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test176");
        sg.edu.nus.comp.cs4218.impl.app.MvApplication mvApplication0 = new sg.edu.nus.comp.cs4218.impl.app.MvApplication();
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication3 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication8 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser12 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean13 = cutArgsParser12.hasByteFlag();
        java.lang.Boolean boolean14 = cutArgsParser12.hasByteFlag();
        java.lang.String[] strArray16 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser12.parse(strArray16);
        java.lang.String str18 = wcApplication8.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray16);
        java.lang.String str19 = grepApplication3.grepFromFiles("Could not create directory", (java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray16);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str20 = mvApplication0.mvFilesToFolder((java.lang.Boolean) true, "", strArray16);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.MvException; message: mv: Exception Caught is already in ");
        } catch (sg.edu.nus.comp.cs4218.exception.MvException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean13 + "' != '" + false + "'", boolean13, false);
        org.junit.Assert.assertEquals("'" + boolean14 + "' != '" + false + "'", boolean14, false);
        org.junit.Assert.assertNotNull(strArray16);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "wc: No such file or directory\n" + "'", str18, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "Exception Caught: No such file or directory\n" + "'", str19, "Exception Caught: No such file or directory\n");
    }

    @Test
    public void test177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test177");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.Boolean boolean1 = grepArgsParser0.isInvert();
        java.lang.String[] strArray2 = grepArgsParser0.getFileNames();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str3 = grepArgsParser0.getPattern();
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertNull(strArray2);
    }

    @Test
    public void test178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test178");
        sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser grepArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.GrepArgsParser();
        java.lang.Boolean boolean1 = grepArgsParser0.isInvert();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = grepArgsParser0.getPattern();
            org.junit.Assert.fail("Expected exception of type java.lang.IndexOutOfBoundsException; message: Index 0 out of bounds for length 0");
        } catch (java.lang.IndexOutOfBoundsException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
    }

    @Test
    public void test179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test179");
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean1 = cutArgsParser0.hasByteFlag();
        java.lang.Boolean boolean2 = cutArgsParser0.hasByteFlag();
        java.lang.String[] strArray4 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser0.parse(strArray4);
        java.lang.String str6 = cutArgsParser0.getExp();
        java.lang.String str7 = cutArgsParser0.getExp();
        java.lang.Boolean boolean8 = cutArgsParser0.hasCharFlag();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strArray4);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "Exception Caught" + "'", str6, "Exception Caught");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "Exception Caught" + "'", str7, "Exception Caught");
        org.junit.Assert.assertEquals("'" + boolean8 + "' != '" + false + "'", boolean8, false);
    }

    @Test
    public void test180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test180");
        sg.edu.nus.comp.cs4218.impl.app.UniqApplication uniqApplication0 = new sg.edu.nus.comp.cs4218.impl.app.UniqApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication1 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication5 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str7 = mvArgsParser6.getDestFile();
        java.lang.String[] strArray8 = mvArgsParser6.getSourceFiles();
        java.lang.String str9 = echoApplication5.constructResult(strArray8);
        java.lang.String str10 = wcApplication1.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray8);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication14 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser18 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean19 = cutArgsParser18.hasByteFlag();
        java.lang.Boolean boolean20 = cutArgsParser18.hasByteFlag();
        java.lang.String[] strArray22 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser18.parse(strArray22);
        java.lang.String str24 = wcApplication14.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray22);
        java.lang.String str25 = wcApplication1.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray22);
        java.io.InputStream inputStream26 = null;
        java.io.OutputStream outputStream27 = null;
        // The following exception was thrown during execution in test generation
        try {
            uniqApplication0.run(strArray22, inputStream26, outputStream27);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.UniqException; message: uniq: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.UniqException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\n" + "'", str9, "\n");
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "\n" + "'", str10, "\n");
        org.junit.Assert.assertEquals("'" + boolean19 + "' != '" + false + "'", boolean19, false);
        org.junit.Assert.assertEquals("'" + boolean20 + "' != '" + false + "'", boolean20, false);
        org.junit.Assert.assertNotNull(strArray22);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "wc: No such file or directory\n" + "'", str24, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str25 + "' != '" + "wc: No such file or directory\n" + "'", str25, "wc: No such file or directory\n");
    }

    @Test
    public void test181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test181");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray1 = mvArgsParser0.getSourceFiles();
        java.lang.String str2 = mvArgsParser0.getDestFile();
        java.lang.String[] strArray3 = mvArgsParser0.getSourceFiles();
        java.lang.Class<?> wildcardClass4 = strArray3.getClass();
        org.junit.Assert.assertNotNull(strArray1);
        org.junit.Assert.assertNull(str2);
        org.junit.Assert.assertNotNull(strArray3);
        org.junit.Assert.assertNotNull(wildcardClass4);
    }

    @Test
    public void test182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test182");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication4 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String[] strArray6 = mvArgsParser5.getSourceFiles();
        java.lang.String str7 = echoApplication4.constructResult(strArray6);
        java.lang.String str8 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray6);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "\n" + "'", str7, "\n");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\n" + "'", str8, "\n");
    }

    @Test
    public void test183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test183");
        sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser wcArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.WcArgsParser();
        java.util.List<java.lang.String> strList1 = wcArgsParser0.getFileNames();
        java.lang.Boolean boolean2 = wcArgsParser0.isByteCount();
        java.util.List<java.lang.String> strList3 = wcArgsParser0.getFileNames();
        org.junit.Assert.assertNotNull(strList1);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
    }

    @Test
    public void test184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test184");
        sg.edu.nus.comp.cs4218.impl.app.SortApplication sortApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SortApplication();
        sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser teeArgsParser4 = new sg.edu.nus.comp.cs4218.impl.parser.TeeArgsParser();
        java.lang.Boolean boolean5 = teeArgsParser4.isAppend();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser6 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str7 = mvArgsParser6.getDestFile();
        java.lang.String[] strArray8 = mvArgsParser6.getSourceFiles();
        java.lang.Boolean boolean9 = mvArgsParser6.isOverwrite();
        java.lang.String[] strArray10 = mvArgsParser6.getSourceFiles();
        teeArgsParser4.parse(strArray10);
        java.lang.String str12 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray10);
        org.junit.Assert.assertEquals("'" + boolean5 + "' != '" + false + "'", boolean5, false);
        org.junit.Assert.assertNull(str7);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertEquals("'" + boolean9 + "' != '" + true + "'", boolean9, true);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "\n" + "'", str12, "\n");
    }

    @Test
    public void test185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test185");
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.Boolean boolean1 = mvArgsParser0.isOverwrite();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + true + "'", boolean1, true);
    }

    @Test
    public void test186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test186");
        sg.edu.nus.comp.cs4218.impl.app.GrepApplication grepApplication0 = new sg.edu.nus.comp.cs4218.impl.app.GrepApplication();
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication5 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser9 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean10 = cutArgsParser9.hasByteFlag();
        java.lang.Boolean boolean11 = cutArgsParser9.hasByteFlag();
        java.lang.String[] strArray13 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser9.parse(strArray13);
        java.lang.String str15 = wcApplication5.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray13);
        java.lang.String str16 = grepApplication0.grepFromFiles("Could not create directory", (java.lang.Boolean) true, (java.lang.Boolean) true, (java.lang.Boolean) true, strArray13);
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser21 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean22 = cutArgsParser21.hasByteFlag();
        java.lang.Boolean boolean23 = cutArgsParser21.hasByteFlag();
        java.lang.String[] strArray25 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser21.parse(strArray25);
        java.lang.String str27 = grepApplication0.grepFromFiles("Invalid syntax", (java.lang.Boolean) false, (java.lang.Boolean) true, (java.lang.Boolean) false, strArray25);
        java.io.InputStream inputStream32 = null;
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str33 = grepApplication0.grepFromStdin("", (java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, inputStream32);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.GrepException; message: grep: grep: Pattern should not be empty.");
        } catch (sg.edu.nus.comp.cs4218.exception.GrepException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + boolean10 + "' != '" + false + "'", boolean10, false);
        org.junit.Assert.assertEquals("'" + boolean11 + "' != '" + false + "'", boolean11, false);
        org.junit.Assert.assertNotNull(strArray13);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "wc: No such file or directory\n" + "'", str15, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "Exception Caught: No such file or directory\n" + "'", str16, "Exception Caught: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + boolean22 + "' != '" + false + "'", boolean22, false);
        org.junit.Assert.assertEquals("'" + boolean23 + "' != '" + false + "'", boolean23, false);
        org.junit.Assert.assertNotNull(strArray25);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "Exception Caught: No such file or directory\n" + "'", str27, "Exception Caught: No such file or directory\n");
    }

    @Test
    public void test187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test187");
        sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser mkdirArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.MkdirArgsParser();
        java.lang.Boolean boolean1 = mkdirArgsParser0.shouldCreateParents();
        java.lang.Boolean boolean2 = mkdirArgsParser0.shouldCreateParents();
        java.util.List<java.lang.String> strList3 = mkdirArgsParser0.getDirectories();
        java.lang.Boolean boolean4 = mkdirArgsParser0.shouldCreateParents();
        org.junit.Assert.assertEquals("'" + boolean1 + "' != '" + false + "'", boolean1, false);
        org.junit.Assert.assertEquals("'" + boolean2 + "' != '" + false + "'", boolean2, false);
        org.junit.Assert.assertNotNull(strList3);
        org.junit.Assert.assertEquals("'" + boolean4 + "' != '" + false + "'", boolean4, false);
    }

    @Test
    public void test188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test188");
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray0 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList1 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1, callCommandArray0);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand3 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        pipeCommand3.evaluate(inputStream4, outputStream5);
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray7 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList8 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList8, callCommandArray7);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand10 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList8);
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray11 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList12 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean13 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList12, callCommandArray11);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand14 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList12);
        java.io.InputStream inputStream15 = null;
        java.io.OutputStream outputStream16 = null;
        pipeCommand14.evaluate(inputStream15, outputStream16);
        sg.edu.nus.comp.cs4218.Command[] commandArray18 = new sg.edu.nus.comp.cs4218.Command[] { pipeCommand3, pipeCommand10, pipeCommand14 };
        java.util.ArrayList<sg.edu.nus.comp.cs4218.Command> commandList19 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.Command>();
        boolean boolean20 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.Command>) commandList19, commandArray18);
        sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand sequenceCommand21 = new sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand((java.util.List<sg.edu.nus.comp.cs4218.Command>) commandList19);
        java.util.List<sg.edu.nus.comp.cs4218.Command> commandList22 = sequenceCommand21.getCommands();
        sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand sequenceCommand23 = new sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand(commandList22);
        sequenceCommand23.terminate();
        org.junit.Assert.assertNotNull(callCommandArray0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(callCommandArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(callCommandArray11);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(commandArray18);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + true + "'", boolean20 == true);
        org.junit.Assert.assertNotNull(commandList22);
    }

    @Test
    public void test189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test189");
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser0 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        boolean boolean1 = uniqArgsParser0.isAllRepeated();
        java.lang.String[] strArray2 = uniqArgsParser0.getFileNames();
        boolean boolean3 = uniqArgsParser0.isCount();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNotNull(strArray2);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
    }

    @Test
    public void test190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test190");
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.app.EchoApplication echoApplication4 = new sg.edu.nus.comp.cs4218.impl.app.EchoApplication();
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser5 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str6 = mvArgsParser5.getDestFile();
        java.lang.String[] strArray7 = mvArgsParser5.getSourceFiles();
        java.lang.String str8 = echoApplication4.constructResult(strArray7);
        java.lang.String str9 = wcApplication0.countFromFiles((java.lang.Boolean) true, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray7);
        sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication13 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
        sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser cutArgsParser17 = new sg.edu.nus.comp.cs4218.impl.parser.CutArgsParser();
        java.lang.Boolean boolean18 = cutArgsParser17.hasByteFlag();
        java.lang.Boolean boolean19 = cutArgsParser17.hasByteFlag();
        java.lang.String[] strArray21 = new java.lang.String[] { "Exception Caught" };
        cutArgsParser17.parse(strArray21);
        java.lang.String str23 = wcApplication13.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray21);
        java.lang.String str24 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray21);
        sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser mvArgsParser28 = new sg.edu.nus.comp.cs4218.impl.parser.MvArgsParser();
        java.lang.String str29 = mvArgsParser28.getDestFile();
        java.lang.String[] strArray30 = mvArgsParser28.getSourceFiles();
        java.lang.Boolean boolean31 = mvArgsParser28.isOverwrite();
        java.lang.String[] strArray32 = mvArgsParser28.getSourceFiles();
        java.lang.String str33 = wcApplication0.countFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) false, strArray32);
        java.io.InputStream inputStream34 = null;
        // The following exception was thrown during execution in test generation
        try {
            long[] longArray35 = wcApplication0.getCountReport(inputStream34);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException; message: wc: Null Pointer Exception");
        } catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(str6);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "\n" + "'", str8, "\n");
        org.junit.Assert.assertEquals("'" + str9 + "' != '" + "\n" + "'", str9, "\n");
        org.junit.Assert.assertEquals("'" + boolean18 + "' != '" + false + "'", boolean18, false);
        org.junit.Assert.assertEquals("'" + boolean19 + "' != '" + false + "'", boolean19, false);
        org.junit.Assert.assertNotNull(strArray21);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "wc: No such file or directory\n" + "'", str23, "wc: No such file or directory\n");
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "wc: No such file or directory\n" + "'", str24, "wc: No such file or directory\n");
        org.junit.Assert.assertNull(str29);
        org.junit.Assert.assertNotNull(strArray30);
        org.junit.Assert.assertEquals("'" + boolean31 + "' != '" + true + "'", boolean31, true);
        org.junit.Assert.assertNotNull(strArray32);
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "\n" + "'", str33, "\n");
    }

    @Test
    public void test191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test191");
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray0 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList1 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1, callCommandArray0);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand3 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand4 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList5 = pipeCommand4.getCallCommands();
        java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList6 = pipeCommand4.getCallCommands();
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        pipeCommand4.evaluate(inputStream7, outputStream8);
        org.junit.Assert.assertNotNull(callCommandArray0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(callCommandList5);
        org.junit.Assert.assertNotNull(callCommandList6);
    }

    @Test
    public void test192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test192");
        sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] callCommandArray0 = new sg.edu.nus.comp.cs4218.impl.cmd.CallCommand[] {};
        java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList1 = new java.util.ArrayList<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>();
        boolean boolean2 = java.util.Collections.addAll((java.util.Collection<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1, callCommandArray0);
        sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand pipeCommand3 = new sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand((java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand>) callCommandList1);
        java.util.List<sg.edu.nus.comp.cs4218.impl.cmd.CallCommand> callCommandList4 = pipeCommand3.getCallCommands();
        org.junit.Assert.assertNotNull(callCommandArray0);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertNotNull(callCommandList4);
    }
}
