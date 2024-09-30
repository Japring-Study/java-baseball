package baseball;

import camp.nextstep.edu.missionutils.Console;

import java.io.IOException;
import java.util.*;

public class GameStart {

    public static void checkTwoList(List<Integer> randList, List<Integer> userList){
        int s = 0 ;
        int b;

        // 스트라이크 계산
        for (int i = 0; i < randList.size(); i++) {
            if (randList.get(i).equals(userList.get(i))) {
                s++;
            }
        }
        // 볼 계산
        Set<Integer> randSet = new HashSet<>(randList);
        Set<Integer> userSet = new HashSet<>(userList);
        randSet.retainAll(userSet);
        int totalMatches = randSet.size();

        b = totalMatches - s;

        // 결과 출력
        if (s == 0 && b == 0) {
            System.out.println("낫싱");
        } else {
            StringBuilder result = new StringBuilder();
            if (b > 0) {
                result.append(b).append("볼");
            }
            if (s > 0) {
                result.append(s).append("스트라이크");
            }
            System.out.println(result.toString().trim());
        }
    }

    public static void start() throws IOException, InvalidInputException {
        List<Integer> goal = CreatNum.generateNum();
        while (true)
        {
                String userInput = UserInput.userInput();
                List<Integer> userList = InputValidator.parseUserInput(Integer.parseInt(userInput));
                checkTwoList(goal, userList);

                // 스트라이크 개수 확인
                int s = 0;
                for (int i = 0; i < goal.size(); i++) {
                    if (goal.get(i).equals(userList.get(i))) {
                        s++;
                    }
                }

                if (s == 3) {
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
                    String choice = Console.readLine();

                    if (choice.equals("1")) {
                        goal = CreatNum.generateNum();
                    } else if (choice.equals("2")) {
                        System.out.println("게임 종료");
                        break;
                    } else {
                        throw new IllegalArgumentException("잘못된 입력입니다. 1 또는 2를 입력해야 합니다.");
                    }
                }

            }
        }
    }