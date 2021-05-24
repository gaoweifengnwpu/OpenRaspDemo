import javassist.*;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class FirstTransformer implements ClassFileTransformer {
    //    一个提供此接口的实现以转换类文件的代理。转换在 JVM 定义类之前发生。
//    参数：
//    loader - 定义要转换的类加载器；如果是引导加载器，则为 null
//    className - 完全限定类内部形式的类名称和 The Java Virtual Machine Specification 中定义的接口名称。例如，"java/util/List"。
//    classBeingRedefined - 如果是被重定义或重转换触发，则为重定义或重转换的类；如果是类加载，则为 null
//    protectionDomain - 要定义或重定义的类的保护域
//    classfileBuffer - 类文件格式的输入字节缓冲区（不得修改）
//    返回：
//    一个格式良好的类文件缓冲区（转换的结果），如果未执行转换,则返回 null。
//    抛出：
//    IllegalClassFormatException - 如果输入不表示一个格式良好的类文件
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        //只修改自定义的User类
        if (className.equals("User")) {
            try {
                ClassPool classPool = ClassPool.getDefault();
                classPool.appendClassPath(new LoaderClassPath(loader));
                CtClass clazz = classPool.makeClass(new ByteArrayInputStream(classfileBuffer), false);
                //定义一个String类型的sex属性
                CtField param = new CtField(classPool.get("java.lang.String"), "sex", clazz);
                //设置属性为private
                param.setModifiers(Modifier.PRIVATE);
                //将属性加到类中，并设置属性的默认值为male
                clazz.addField(param, CtField.Initializer.constant("male"));
                //为刚才的sex属性添加GET SET 方法
                clazz.addMethod(CtNewMethod.setter("setSex", param));
                clazz.addMethod(CtNewMethod.getter("getSex", param));
                //重写toString方法，将sex属性加入返回结果中。
                CtMethod method = clazz.getDeclaredMethod("toString");
                method.setBody("return \"User{\" +\n" +
                        "                \"name='\" + name + '\\',' +\n" +
                        "                \"sex='\" + sex + '\\'' +\n" +
                        "                '}';");
                return clazz.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}