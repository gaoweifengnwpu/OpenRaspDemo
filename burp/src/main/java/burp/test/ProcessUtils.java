package burp.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessUtils {
    public static void main(String args[]) {
        String cmdstr = "python C:\\Users\\lenovo\\Desktop\\微隔离内核\\OpenRaspDemo\\burp\\src\\main\\java\\burp\\test\\demo1.py gwf";
        String s = ProcessUtils.execProcess(cmdstr);
        System.out.println(s); // 输出： 你好,小强
    }
    public static String execProcess(String cmdStr) {
        Process process = null;
        String result = "";
        try {
            process = Runtime.getRuntime().exec(cmdStr);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result = line;
            }
            in.close();
            process.waitFor();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            process.destroy();
        }
    }

}

