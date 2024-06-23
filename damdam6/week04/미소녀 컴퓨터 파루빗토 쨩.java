package y24Jun;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek28086 {

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String input = bf.readLine();


        char op = ' ';
        if (input.contains("+")) {
            op = '+';
        } else if (input.contains("*")) {
            op = '*';
        } else if (input.contains("/")) {
            op = '/';
        } else if (input.contains("-")) {
            op = '-';
        }

        // 되는지 확인

        int opIndx = input.indexOf(op);
        if (opIndx == 0) {
            opIndx = input.indexOf(op, opIndx + 1);
        }

        boolean opNum1Minus = false;
        boolean opNum2Minus = false;
        String opNum1 = input.substring(0, opIndx);
        String opNum2 = input.substring(opIndx + 1);
        if (opNum1.startsWith("-")) {
            opNum1Minus = true;
            opNum1 = opNum1.substring(1);

        }
        if (opNum2.startsWith("-")) {
            opNum2Minus = true;
            opNum2 = opNum2.substring(1);
        }

        int num1 = Integer.parseInt(opNum1, 8);
        int num2 = Integer.parseInt(opNum2, 8);

        if (opNum1Minus) {
            num1 = -num1;
        }
        if (opNum2Minus) {
            num2 = -num2;
        }

        //System.out.println(num1 + " " + num2);
        int result = 0;
        if (op == '+') {
            result = num1 + num2;
        } else if (op == '-') {
            result = num1 - num2;
        } else if (op == '*') {
            result = num1 * num2;
        } else if (op == '/') {
            if (num2 == 0) {
                System.out.println("invalid");
                return;
            }
            result = Math.floorDiv(num1, num2);
        }

        boolean resultMinus = false;
        if (result < 0) {
            resultMinus = true;
            result = -result;
        }
        String answer = Integer.toOctalString(result);
        if (resultMinus) {
            answer = "-" + answer;
        }
        System.out.println(answer);
    }
}
