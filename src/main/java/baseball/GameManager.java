package baseball;

import camp.nextstep.edu.missionutils.Console;

public class GameManager {

    private BaseballGame game;

    public GameManager() {
        this.game = new BaseballGame();
    }

    public void start() {

        System.out.println("숫자 야구 게임을 시작합니다.");
        game.generateAnswer();

        boolean result = false;
        while (!result) {
            int userInput = game.receiveUserInput();
            result = game.score(game.getAnswer(), userInput);
        }

        String endInput = askRestart();
        if (endInput.equals("1")) {
            start();
        }
        if (endInput.equals("2")) {
            return;
        }
    }

    private String askRestart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        return Console.readLine();
    }
}
