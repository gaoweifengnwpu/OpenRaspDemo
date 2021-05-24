import java.lang.instrument.Instrumentation;

public class FirstAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("FirstAgent is Start.");
        inst.addTransformer(new FirstTransformer());
    }
}