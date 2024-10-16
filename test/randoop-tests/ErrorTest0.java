import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ErrorTest0 {   //NOPMD - Suppressed for automatically generated test cases

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "ErrorTest0.test1");
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
        sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser uniqArgsParser23 = new sg.edu.nus.comp.cs4218.impl.parser.UniqArgsParser();
        boolean boolean24 = uniqArgsParser23.isAllRepeated();
        java.lang.String[] strArray25 = uniqArgsParser23.getFileNames();
        // during test generation this statement threw an exception of type java.lang.NullPointerException in error
        java.lang.String str26 = sortApplication0.sortFromFiles((java.lang.Boolean) false, (java.lang.Boolean) false, (java.lang.Boolean) true, strArray25);
    }
}

