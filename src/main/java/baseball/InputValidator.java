package baseball;

import java.util.ArrayList;
import java.util.List;

public class InputValidator {
    public static List<Integer> parseUserInput(int input) throws InvalidInputException {
        // 입력된 숫자를 세 자리로 분리
        if (input < 100 || input > 999) {
            throw new InvalidInputException("세 자리의 숫자를 입력해야 합니다.");
        }

        List<Integer> userList = new ArrayList<>();
        boolean[] usedDigits = new boolean[10]; // 1~9 사용 여부 체크

        int temp = input;
        for (int i = 2; i >= 0; i--) {
            int digit = temp % 10;
            temp /= 10;
            if (digit < 1) {
                throw new InvalidInputException("각 자리 숫자는 1부터 9 사이여야 합니다.");
            }
            if (usedDigits[digit]) {
                throw new InvalidInputException("중복된 숫자는 입력할 수 없습니다.");
            }
            usedDigits[digit] = true;
            userList.add(0, digit); // 앞에 추가하여 순서 유지
        }

        return userList;
    }
}