package baseball;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        // TODO: 프로그램 구현
        try {
            System.out.println("숫자 야구 게임을 시작합니다.\n");
            GameStart.start();
        } catch (IllegalArgumentException e) {
            // 예외가 발생하면 프로그램 종료
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }
    }
}
