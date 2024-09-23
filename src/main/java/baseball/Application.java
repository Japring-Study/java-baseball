package baseball;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try {
            playBaseballGame();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void playBaseballGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("숫자 야구 게임을 시작합니다.");
        do {
            int computerNumber = createRandomNumber();
            boolean gameFinished = false;
            while (!gameFinished) {
                int userNumber = inputNumber(scanner);
                validateNumber(userNumber);
                gameFinished = checkNumber(userNumber, computerNumber);
            }
        } while (askForRestart(scanner));
        System.out.println("종료되었습니다.");
    }

    private static final int RESTART = 1;
    private static final int END = 2;
    public static boolean askForRestart(Scanner scanner) {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        try {
            int restart = scanner.nextInt();
            if (restart == RESTART) {
                return true;
            } else if (restart == END) {
                return false;
            } else {
                throw new IllegalArgumentException("잘못된 입력입니다. 1 또는 2를 입력해주세요.");
            }
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("숫자만 입력해주세요.");
        }
    }

    //숫자 비교하는 함수
    public static boolean checkNumber(int userNumber, int computerNumber) {
//        System.out.println("robot: " + computerNumber);

        String userNumberToString = String.valueOf(userNumber);
        String computerNumberToString = String.valueOf(computerNumber);

        int strikes = 0;
        int balls = 0;
        for (int i = 0; i < 3; i++) {
            if (userNumberToString.charAt(i) == computerNumberToString.charAt(i)) {
                strikes++;
            } else if (computerNumberToString.contains(String.valueOf(userNumberToString.charAt(i)))) {
                balls++;
            }
        }
        if (balls > 0 && strikes > 0) {
            System.out.println(balls + "볼 " + strikes + "스트라이크");
        } else if (balls > 0) {
            System.out.println(balls + "볼");
        } else if (strikes > 0) {
            System.out.println(strikes + "스트라이크");
        } else {
            System.out.println("낫싱");
        }

        if (strikes == 3) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return true;
        }

        return false;
    }

    //컴퓨터의 랜덤한 수 생성 함수
    public static int createRandomNumber() {
        Random random = new Random();
        int firstDigit = random.nextInt(9) + 1;
        int secondDigit, thirdDigit;

        do {
            secondDigit = random.nextInt(9) + 1;
        } while (secondDigit == firstDigit);

        do {
            thirdDigit = random.nextInt(9) + 1;
        } while (thirdDigit == firstDigit || thirdDigit == secondDigit);

        return firstDigit * 100 + secondDigit * 10 + thirdDigit;
    }

    // 숫자 입력 받는 함수
    public static int inputNumber(Scanner scanner) {
        System.out.print("숫자를 입력해주세요 : ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e ) {
            throw new IllegalArgumentException("입력한 값이 숫자가 아닙니다.");
        }
    }

    // 잘못된 입력 예외 처리 함수
    public static void validateNumber(int number) {
        if (number < 100 || number > 999) {
            throw new IllegalArgumentException("입력한 숫자는 3자리 숫자가 아닙니다.");
        }

        String numberToString = String.valueOf(number);
        char firstDigit = numberToString.charAt(0);
        char secondDigit = numberToString.charAt(1);
        char thirdDigit = numberToString.charAt(2);

        if (firstDigit == secondDigit || secondDigit == thirdDigit || thirdDigit == firstDigit) {
            throw new IllegalArgumentException("입력한 숫자는 각 자리가 중복됩니다.");
        }

        if (firstDigit == '0' || secondDigit == '0' || thirdDigit == '0') {
            throw new IllegalArgumentException("1부터 9까지 숫자를 입력해주세요.");
        }
    }
}
