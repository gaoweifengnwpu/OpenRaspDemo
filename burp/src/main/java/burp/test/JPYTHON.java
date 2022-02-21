package burp.test;

import org.python.util.PythonInterpreter;

public class JPYTHON {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("a='hello world'; ");
        interpreter.exec("print(a);");
    }
}
