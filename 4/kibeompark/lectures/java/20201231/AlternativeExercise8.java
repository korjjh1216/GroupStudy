package Third;

public class AlternativeExercise8 {
    public static void main(String[] args) {
        int twoThreeSum = 0;
        int fiveSum = 0;

        for(int i = 1; i < 31; i++) {
            // 더하기 파트
            if(i % 2 == 0 || i % 3 == 0) {
                twoThreeSum += i;
                System.out.println("더하는 값 = " + i);
            }
        }

        for(int i = 1; i < 31; i++) {
            // 더하기 파트
            if(i % 5 == 0) {
                fiveSum += i;
                System.out.println("빼는 값 = " + i);
            }
        }

        System.out.println("더하는 값 = " + twoThreeSum);
        System.out.println("빼는 값 = " + fiveSum);
        System.out.println("최종값 = " + (twoThreeSum - fiveSum));
    }
}