package sg.edu.nus.comp.cs4218.exception;

public class CutException extends AbstractApplicationException {

    private static final long serialVersionUID = 548939428340949445L; // random UID

    public CutException(String message) {
        super("cut: " + message);
    }

}
