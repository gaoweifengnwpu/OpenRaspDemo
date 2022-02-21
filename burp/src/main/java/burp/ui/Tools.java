package burp.ui;


public class Tools {
    /**
     * 去掉字符串左右换行
     *
     * @param str 原字符串
     * @return 转换后的字符串
     */
    public static String trimN( String str){
        int len = str.length();
        int st = 0;
        char[] val = str.toCharArray();

        while ((st < len) && (val[st] <= '\r')) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= '\r')) {
            len--;
        }
        return ((st > 0) || (len < str.length())) ? str.substring(st, len) : str;
    }
}
