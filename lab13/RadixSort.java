/**
 * Class for doing Radix sort
 *
 * @author Mandy Zhou
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        String[] unsorted = new String[asciis.length];
        System.arraycopy(asciis, 0, unsorted, 0, asciis.length);

        // find the longest string
        int maxLength = 0;
        for (int i = 0; i < asciis.length; i++) {
            maxLength = Math.max(maxLength, asciis[i].length());
        }
        // pad '_' at the end of strings whose length is not the longest
        for (int i = 0; i < asciis.length; i++) {
            while (unsorted[i].length() < maxLength) {
                unsorted[i] += "_";
            }
        }

        // for debug
        for (int i = 0; i < asciis.length; i++) {
            System.out.print(unsorted[i] + " ");
        }
        System.out.println();

        // helper method to do the sort by using count sort
        for (int i = unsorted.length - 1; i >= 0; i--) {
            sortHelperLSD(unsorted, i);
        }

        // delete '_' at the end
        for (int i = 0; i < asciis.length; i++) {
            while (unsorted[i].endsWith("_")) {
                unsorted[i] = unsorted[i].substring(0, unsorted[i].length() - 1);
            }
        }

        // for debug
        for (int i = 0; i < asciis.length; i++) {
            System.out.print(unsorted[i] + " ");
        }
        System.out.println();
        return unsorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int[] count = new int[256];
        int[] starts = new int[256];
        int pos = 0;

        for (String s : asciis) {
            count[s.charAt(index)]++;
        }

        for (int i = 0; i < count.length; i++) {
            starts[i] = pos;
            pos += count[i];
        }

        String[] sorted = new String[asciis.length];
        for (String s: asciis) {

            int val = (int)s.charAt(index);
            int place = starts[val];
            sorted[place] = s;
            starts[val]++;
        }
        // copy the sorted array back to original array asciis
        System.arraycopy(sorted, 0, asciis, 0, sorted.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    // main
    public static void main(String[] args) {
        // generate a random string[]
        String[] asciis = new String[3];
        asciis[0] = "Z";
        asciis[1] = "C8hij";
        asciis[2] = "Cds";
        String[] sorted = sort(asciis);
    }
}
