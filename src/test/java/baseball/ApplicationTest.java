package baseball;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("createRandomNumber 메서드는 3자리 숫자를 반환해야 한다")
    void testCreateRandomNumber() {
        for (int i = 0; i < 100; i++) {
            int number = Application.createRandomNumber();
            assertTrue(number >= 100 && number <= 999);
            String numberStr = String.valueOf(number);
            assertEquals(3, numberStr.chars().distinct().count());
            assertFalse(numberStr.contains("0"));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {123, 456, 789})
    @DisplayName("validateNumber 메서드는 유효한 입력을 받아들여야 한다")
    void testValidateNumberWithValidInput(int number) {
        assertDoesNotThrow(() -> Application.validateNumber(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 999, 12, 1000, 111, 990})
    @DisplayName("validateNumber 메서드는 잘못된 입력에 대해 예외를 발생시켜야 한다")
    void testValidateNumberWithInvalidInput(int number) {
        assertThrows(IllegalArgumentException.class, () -> Application.validateNumber(number));
    }

    @Test
    @DisplayName("checkNumber 메서드는 정확한 결과를 반환해야 한다")
    void testCheckNumber() {
        assertTrue(Application.checkNumber(123, 123));
        assertFalse(Application.checkNumber(123, 456));

        Application.checkNumber(123, 145);
        assertTrue(outContent.toString().contains("1스트라이크"));

        outContent.reset();
        Application.checkNumber(123, 321);
        assertTrue(outContent.toString().contains("2볼 1스트라이크"));

        outContent.reset();
        Application.checkNumber(123, 456);
        assertTrue(outContent.toString().contains("낫싱"));
    }

    // 입력을 숫자로 받는 법을 모르겠음
//    @Test
//    @DisplayName("게임이 정상적으로 종료되어야 한다")
//    void testGameFlow() {
//        int input = 123;
//        System.setIn(new ByteArrayInputStream(input.get()));
//
//        int input2 = 2;
//
//        Application.playBaseballGame();
//
//        String output = outContent.toString();
//        assertTrue(output.contains("숫자 야구 게임을 시작합니다."));
//        assertTrue(output.contains("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."));
//        assertTrue(output.contains("게임 종료"));
//    }
}