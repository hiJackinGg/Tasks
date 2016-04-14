import java.util.Arrays;

public class Task_1_3 {

    static final String[] words = new String[]{
            "twofivetwo",
            "fivefivefivefive",
            "two",
            "five",
            "threetenfive",
            "six",
            "sevennine",
            "fourfourfour",
            "twofivesixsevennine"
    };

    /**
     * Sorts array of words.
     *
     * String is wrap class around array of chars.
     *
     * char arrays are comparing by :
     * public int compareTo(String anotherString) {
     *        int len1 = value.length;
     *        int len2 = anotherString.value.length;
     *        int lim = Math.min(len1, len2);
     *        char v1[] = value;
     *        char v2[] = anotherString.value;
     *
     *        int k = 0;
     *        while (k < lim) {
     *        char c1 = v1[k];
     *        char c2 = v2[k];
     *        if (c1 != c2) {
     *        return c1 - c2;
     *        }
     *        k++;
     *        }
     *        return len1 - len2;
     *        }
     */
    static{
        Arrays.sort(words);
        System.out.println(Arrays.toString(words));
    }

    /**
     * Checks if current word is composite.
     *
     * Checks each word of the array to define if it is a sub-word. If this word is a sub-word,
     * starts to search from the end of this sub-word (moving cursor by the length of the sub-word).
     * if cursor reaches the end of the main word then it's composite.
     *
     * indexOf(String str, int fromIndex) function returns the index within string of the first occurrence of the
     * specified substring, starting at the specified index.
     *
     * @param i index of word in the array.
     * @return true if the word is composite.
     */
    public static boolean find(int i) {

        //starting position of searching sub-word
        int cursor = 0;
        int k = 0;

            while(k < words.length) {

                if(words[i].indexOf(words[k], cursor) == cursor && i != k) {
                    cursor += words[k].length();

                   if( words[i].length() == cursor ){
                       System.out.println("* " + words[i]);
                       return true;
                   }

                    //after finding the sub-word, start search new sub-word from the beginning
                    k = 0;

                }else {
                    k++;
                }
            }

        return false;

    }

    public static void main(String[] args) {
        int max = -1;   //array index with the longest composite word
        int temp = -1;  //temp var for comparing

        //checking each word
        for(int i = 0; i < words.length; i++) {

            if(find(i) == true)
                temp = i;
            else
                continue;

            if(max == -1 || (words[temp].length() > words[max].length()))
                max = temp;

        }

        if(max != -1 )
            System.out.println("-> "+words[max]);

    }
}
