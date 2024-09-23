package baseball;

import camp.nextstep.edu.missionutils.Console;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BaseballGameTest {

    private BaseballGame baseballGame;

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    @BeforeEach
    void setUp() {
        baseballGame = new BaseballGame();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void generateAnswer_정답을_answer에_채워넣는다() {
        baseballGame.generateAnswer();

        assertThat(baseballGame.getAnswer()).isNotEqualTo(0);
    }

    @Test
    void generateAnswer_정답은_세자리수_이다() {
        baseballGame.generateAnswer();

        int length = baseballGame.getAnswer().toString().length();
        assertThat(length).isEqualTo(3);
    }

    @Test
    void receiveUserInput_유저의_입력을_반환한다() {
        String input = "123";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        int result = baseballGame.receiveUserInput();

        assertThat(result).isEqualTo(Integer.parseInt(input));
    }

    @Test
    void score_한개만_맞으면_1스트라이크를_출력한다() {
        boolean result = baseballGame.score(123, 145);

        String output = outputStream.toString().trim();

        assertThat(result).isFalse();
        assertThat(output).isEqualTo("1스트라이크");
    }

    @Test
    void score_다른_자리에_같은_숫자가_있으면_1볼을_출력한다() {
        boolean result = baseballGame.score(123, 245);

        String output = outputStream.toString().trim();

        assertThat(result).isFalse();
        assertThat(output).isEqualTo("1볼");
    }

    @Test
    void score_3숫자_모두_맞추면_정답메세지를_출력하고_true를_반환한다() {
        boolean result = baseballGame.score(123, 123);

        String output = outputStream.toString().trim();

        assertThat(result).isTrue();
        assertThat(output).isEqualTo("3스트라이크 \n" +
                "3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }
}