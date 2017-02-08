import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

public class Task_1_1 {

    final static String FILE_NAME = "array.yaml";


    public static void main(String[] args) throws IOException {

        //array size
        int size = produceSize();
        int []arr = produceArray(size);

        System.out.println(Arrays.toString(arr));

        write(arr);

        arr = null;

        arr = read();

        int lonelyNumber = findLonelyInteger(arr);

        System.out.println(lonelyNumber);

    }

    /**
     * Generates array size in [3;21] range.
     * Minimum size should be 3 (at least one number has a pair and one has no).
     * Size must be uneven (one number has no pair).
     * @return size of the array
     */
    static int produceSize(){
        int size = 0;

        //generates array size in [3;21] range
        //minimum size should be 3 (at least one number has a pair and one has no)
        size = 3 + (int) (Math.random() * ((21 - 3) + 1));

        //size must be uneven (one number has no pair)
        if (size % 2 == 0)
            size++;

        return size;

    }

    /**
     * Generates array that must have random integers each of which has a pair except one.
     * Left half of array numbers are generated randomly in range [1;1000] after that
     * using fast copy left half of array is copied to right.
     *  For example: new array [0,0,0,0,0]
     *               after generating nums [1,2,3,0,0]
     *               after copy [1,2,3,1,2]
     *  Next step will be to shuffle the array in order to just
     *  randomize the positions of elements.
     *
     * @param size of array to be created
     * @return array
     */
    static int [] produceArray(int size) {

        int arr[] = new int[size];
        int i = 0;

        while(true) {

            if(i == arr.length/2 + 1) {
                System.arraycopy(arr, 0, arr, arr.length/2 + 1, arr.length / 2);
                break;
            }
            arr[i] = 1 + (int) (Math.random() * ((1000 - 1) + 1));

            i++;
        }

        shuffle(arr);

        return arr;
    }


    /**
     * Shuffles an array.
     * @param array
     */
    static void shuffle(int[] array) {
        int n = array.length;
        int i, random, randomElement;
        for (i = 0; i < array.length; i++) {

            random = i + (int) (Math.random() * (n - i));

            randomElement = array[random];
            array[random] = array[i];
            array[i] = randomElement;
        }
    }


    /**
     * Writes array to file in .yaml format.
     * To generate file it's used character stream with ASCII charset.
     *
     * Writes text in the following format:
     *  size: 3
     *  array:
     *   - 0: 207
     *   - 1: 571
     *   - 2: 207
     *
     * @param arr
     * @throws IOException
     */
    static void write(int []arr) throws IOException {

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_NAME), "ASCII"));

        StringBuilder size = new StringBuilder("size: ").append(arr.length).append("\n");
        StringBuilder array = new StringBuilder("array:\n");

        int i = 0;
        for (i = 0; i < arr.length; i++) {
            array.append("- ").append(String.valueOf(i)).append(": ").append(String.valueOf(arr[i])).append("\n");
        }

        out.write(size.toString());
        out.write(array.toString());

        out.close();
    }

    /**
     * Reads file to retrieve array.
     *
     * @return
     * @throws IOException
     */
    static int[] read() throws IOException {
        int []arr = null;

        BufferedReader out= new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), "ASCII"));

        String str;

        while(true) {

            str = out.readLine();

            if(str.contains("size")) {
                int index = str.indexOf(':');
                index += 2;

                String size = str.substring(index, str.length());

                arr = new int[Integer.valueOf(size)];
            }

            if(str.contains("array")){
                while((str = out.readLine()) != null){

                    String index = str.substring(str.indexOf('-') + 2, str.indexOf(':'));
                    String value = str.substring(str.indexOf(':') + 2, str.length());
                    arr[Integer.valueOf(index)] = Integer.valueOf(value);
                }

            }

            if(str == null)
                break;
        }

        out.close();

        return arr;
    }

    /**
     * Finds integer without pair.
     * At first sort an array after what numbers will be grouped by pairs.
     * After it it's easy to find the number.
     *
     * @param arr
     * @return
     */
    static int findLonelyInteger(int []arr){

        //using quick sort
        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));

        int lonelyNumber, i;

        lonelyNumber = arr[0];
        for (i = 1; i<arr.length; i+=2) {
            if(lonelyNumber == arr[i])
                lonelyNumber = arr[i+1];
            else
                break;
        }

        return lonelyNumber;

    }
}
