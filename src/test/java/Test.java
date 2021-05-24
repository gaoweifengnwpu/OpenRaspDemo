import com.alibaba.fastjson.JSON;

public class Test {
    public static void main(String[] args) {
        User user = new User("zane");
        System.out.println(JSON.toJSON(user));
        System.out.println(user.toString());
    }
}
