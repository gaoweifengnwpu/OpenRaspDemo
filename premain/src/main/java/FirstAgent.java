import java.lang.instrument.Instrumentation;

public class FirstAgent {
    //    agentArgs代表传递过来的参数，inst则是agent技术主要使用的API，我们可以使用它来改变和重新定义类的行为
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("FirstAgent is Start.");
        inst.addTransformer(new FirstTransformer());
    }
}