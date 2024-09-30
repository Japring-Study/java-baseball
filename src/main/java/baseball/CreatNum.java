package baseball;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatNum {
    public static List<Integer> numToList(int randNum) {
        List<Integer> numList = new ArrayList<>();
        while (randNum > 0) {
            numList.add(0, randNum % 10); // 앞쪽에 추가
            randNum /= 10;
        }
        return numList;
    }

    public static List<Integer> checkNum(int randNum) {
        List<Integer> checkList = numToList(randNum);
        if (checkList.contains(0)|checkList.size() != checkList.stream().distinct().count()) {
            return generateNum();
        } else {
            return checkList;
        }
    }

    public static List<Integer> generateNum() {
        Random random = new Random();
        int randNum = random.nextInt(999)+100;
        return  checkNum(randNum);
    }
}
