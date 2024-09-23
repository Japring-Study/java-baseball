package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaseballGame {

    private Integer answer;

    public BaseballGame() {
        this.answer = 0;
    }

    public Integer getAnswer() {
        return answer;
    }

    public int generateAnswer() {

        AtomicInteger index = new AtomicInteger();

        LinkedHashSet<Integer> numbers = new LinkedHashSet<>();
        while (numbers.size() < 3) {
            numbers.add(Randoms.pickNumberInRange(1, 9));
        }

        answer = numbers.stream().reduce(0, (x, y) -> {
            int result = (int) (x + y * Math.pow(10, 2 - index.doubleValue()));
            index.getAndIncrement();
            return result;
        });
        return answer;
    }

    public int receiveUserInput() {
        System.out.print("숫자를 입력해주세요 : ");

        String input = Console.readLine();
        validateInput(input);

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 입력 형식입니다.");
        }
    }

    private void validateInput(String input) {
        if (input.length() != 3) {
            throw new IllegalArgumentException("잘못된 입력 형식입니다.");
        }
    }

    public boolean score(int answer, int userAnswer) {

        List<Integer> answerDigits = getDigits(answer);
        List<Integer> userAnswerDigits = getDigits(userAnswer);

        int strike = 0;
        int ball = 0;
        for (int index = 0; index < 3; index++) {
            int num = userAnswerDigits.get(index);

            if (num == answerDigits.get(index)) {
                strike++;
                continue;
            }

            if (answerDigits.contains(num)) {
                ball++;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (strike == 0 && ball == 0) {
            sb.append("낫싱");
        }

        if (ball > 0) {
            sb.append(ball).append("볼 ");
        }
        if (strike > 0) {
            sb.append(strike).append("스트라이크 ");
        }
        System.out.println(sb);

        if (strike == 3) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return true;
        }
        return false;
    }

    private static List<Integer> getDigits(int num) {
        List<Integer> answerNums = new ArrayList<>();
        int current = num;
        for (int i = 0; i < 3; i++) {
            answerNums.add(0, current % 10);
            current /= 10;
        }
        return answerNums;
    }
}
