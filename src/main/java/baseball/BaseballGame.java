package baseball;

import camp.nextstep.edu.missionutils.Randoms;

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

    public String receiveUserInput() {
        return null;
    }

    public boolean score(int answer, int userAnswer) {
        return false;
    }
}
