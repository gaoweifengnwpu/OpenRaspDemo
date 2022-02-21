package burp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String args[]) {
        String DATE_STRING = "GET / HTTP/1.1\n" +
                "Host: 192.30.3.158:3111\n" +
                "Cookie: d2admin-1.20.1-lang=zh-chs\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:97.0) Gecko/20100101 Firefox/97.0\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8\n" +
                "Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "Sec-Fetch-Dest: document\n" +
                "Sec-Fetch-Mode: navigate\n" +
                "Sec-Fetch-Site: none\n" +
                "Sec-Fetch-User: ?1\n" +
                "If-Modified-Since: Thu, 06 Jan 2022 11:34:41 GMT\n" +
                "If-None-Match: \"61d6d3d1-17af\"\n" +
                "Cache-Control: max-age=0\n" +
                "Te: trailers\n" +
                "Connection: close";
        String P_COMM = "(.*)\n(.*)\n(.*)";

        Pattern pattern = Pattern.compile(P_COMM);
        Matcher r = pattern.matcher(DATE_STRING);
        if (r.find()) {
            System.out.println(r.group(0));
        }
    }


}
