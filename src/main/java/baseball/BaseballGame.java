package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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

        answer = Randoms.shuffle(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)).stream()
                .limit(3)
                .reduce(0, (x, y) -> {
                    int result = (int) (x + y * Math.pow(10, index.doubleValue()));
                    index.getAndIncrement();
                    return result;
                });
        return answer;
    }

    public int receiveUserInput() {
        return Integer.parseInt(Console.readLine());
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
            sb.append("닛싱");
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

    private static List<Integer> getDigits(int answer) {
        List<Integer> answerNums = new ArrayList<>();
        int current = answer;
        for (int i = 0; i < 3; i++) {
            answerNums.add(0, current % 10);
            current /= 10;
        }
        return answerNums;
    }
}
