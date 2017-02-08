import java.util.ArrayList;
import java.util.Arrays;

public class Task_1_2 {

    public static void main(String[] args) {

        int a = 12345;

        int []digits = getNumberInArray(a);

        System.out.println(Arrays.toString(digits));

        parse(digits);

        System.out.println(Arrays.toString(digits));

        System.out.println(convertToNumber(digits));


    }

    /**
     * Finds the smallest palindrome number which is bigger then current number.
     * Number is represented with array of digits. So the found palindrome
     * will be save in the source array.
     *
     * In palindrome left half of digits must be equal to right half. As
     * we must find the the smallest palindrome we have to compare digits from middle of the array.
     *
     * There are different situations in searching the palindrome while comparing digits:
     * 1. When appropriated digits are equal we just skip them.
     * 2. If digit in the left half is bigger then appropriated digit in the right
     *    just make copy left to right.
     *      For example: array [4,3,5,7,7,4,2,1] in this case will be [4,3,5,7,7,5,3,4]
     *                   because arr[2] > arr[5].
     * 3. If digit in the right half is bigger then appropriated digit in the left
     *    we have to increment digits left to the bigger one (addition emulations),
     *    after it, if exist, digits with "9" value will gain "10" (that is unacceptable) that's why
     *    next step will be to replace "10" by "0" and increment least significant digit.
     *    Next we just copy unequal digits to make right half be equal to the left
     *
     *      For example: array [4,3,5,9,9,9,2,1] in this case will be [4,3,6,0,0,6,3,4]
     *                   because arr[2] < arr[5].
     *
     *
     * @param dig array with digits
     */
    static void parse(int []dig){

        int a,b;
        boolean isEven;

        //index boundaries (a, b) depend on digits parity
        if( dig.length % 2 == 0 ) {
            a = (dig.length / 2) - 1;
            b = dig.length / 2;
            isEven = true;
        }
        else{
            a = dig.length / 2 - 1;
            b = (dig.length / 2) + 1;
            isEven = false;
        }

        //comparing appropriated digits from both halves
        for(int j = 0; j<dig.length/2; j++) {


            if (dig[a] > dig[b]) {
                copy(dig, a, b);
                return;

            } else if (dig[a] < dig[b]) {
                subLoop(dig, a, b);
                exceptTens(dig, a, b, isEven);
                copy(dig, a, b);
                return;
            }
            else if (dig[a]==dig[b]){
                a--;
                b++;
                continue;}


        }
    }

    /**
     * Copies left side of the array to right.
     * Left side ends with "a" index, right starts with "b".
     * For example: array [1,2,4,4,5,6], with params: a=1, b=4 will produce
     * array [1,2,4,5,2,1].
     * @param dig
     * @param a left side index of the array
     * @param b right side index of the array
     */
    static void copy(int []dig, int a, int b){
        while (a >= 0 && b < dig.length) {
            dig[b] = dig[a];
            a--;
            b++;
        }
    }

    /**
     * Increment elements by one in the right side of the array
     * from b index to the middle of the array.
     * Uses when one of the digits in the right array side is bigger then
     * appropriate digit in the left. When cursor reaches the middle
     * copies left middle element to right.
     * For example: array [1,2,3,4,4,3,5,6] with params a=1, b=6 will produce
     * array [1,2,3,5,5,4,5,6]
     * @param dig array
     * @param a array index
     * @param b array index
     */
    static void subLoop(int []dig, int a, int b){

        while (true){

            if (b - a == 1){ //if digits are even (a,b in the middle of array)

                dig[a]++;
                dig[b] = dig[a];
                break;

            }else if(b - a == 2){ //if digits are uneven (a,b in the middle of array)

                dig[a + 1]++;
                dig[b] = dig[a + 1];
                break;
            }

            dig[b-1]++;
            a++;
            b--;
        }

    }

    /**
     * Replaces "10" - digits by "0" and increments left digit.
     * For example: array [1,2,3,10,10,10,2,1] with params a=2, b=5 will produce
     * array [1,2,4,0,0,0,2,1]
     * @param dig array with digits
     * @param a array index
     * @param b array index
     * @param isEven true if number of digits are even
     */
    static void exceptTens(int []dig, int a, int b, boolean isEven){
        int lCursor, rCursor;

        //determine the middle of the array
        if(isEven == true) {

            lCursor = dig.length / 2 - 1;
            rCursor = dig.length / 2;

        }else{

            lCursor = dig.length / 2;
            rCursor = dig.length / 2 + 1;

        }

        while(lCursor >= a && rCursor <= b){

            if(dig[lCursor] == 10) {
                dig[lCursor - 1]++;
                dig[lCursor] = 0;
            }

            if(dig[rCursor] == 10) {
                dig[rCursor] = 0;
            }

            lCursor--;
            rCursor++;
        }

    }


    /**
     * Counts the number of digits of the specified number
     * @param number number to be checked
     * @return count of digits
     */
    static int digitCount(int number) {
        int digitsCount= 0;
        for (; number != 0; number /= 10)
            digitsCount++;

        return digitsCount;
    }

    /**
     * Creates new array and fill it with digits of the specified number.
     * @param number number to be converted to array
     * @return array representation of this number
     */
    static int[] getArray(int number) {
        int digits[] = new int[digitCount(number)];
        for (int i = digits.length-1; number > 0; number /= 10, i--) {
            digits[i] = number % 10;
        }
        return digits;
    }

    /**
     * Rotate received number and compare the result with primal number
     * @param number number to be checked
     * @return true if this number is palindrome
     */
    static boolean isPalindrome(int number){

        int cursor = 0;
        int temp = number;

        //rotate received number
        while (temp != 0)
        {
            cursor = (cursor * 10) + (temp % 10);
            temp /= 10;
        }

        return (number == cursor) ? true : false;
    }

    /**
     * Returns digits representation of the specified number in array.
     * If the specified number is already palindrome then just increases it by one
     * because parse algorithm will return the same number (which is already palindrome
     * but not the minimal palindrome that bigger then this one).
     *
     * @param number number to be converted to array
     * @return array representation
     */
    static int[] getNumberInArray(int number)
    {
        //increases received number by one if this number is already palindrome
        if (isPalindrome(number)) {
            number++;
        }

        return getArray(number);
    }

    /**
     * Converts number from the specified array to integer type.
     * @param digits the array which stores number by digits
     * @return a number representation of digits
     */
    static int convertToNumber(int digits[]) {
        int number = 0;
        int i = 0;
        for (i = 0; i < digits.length; i++)
            number = 10 * number + digits[i];

        return number;
    }
}
