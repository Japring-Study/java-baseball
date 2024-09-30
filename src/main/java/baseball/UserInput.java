package baseball;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class UserInput {
    public static String userInput() throws IOException {
        System.out.println("숫자를 입력해주세요 : ");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        return bf.readLine();
    }
}
