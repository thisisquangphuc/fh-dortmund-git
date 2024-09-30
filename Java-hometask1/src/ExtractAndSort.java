
public class ExtractAndSort {

	public static void main(String[] args) {
		String text = "To be or not to be, that is the question;"
        		+"Whether `tis nobler in the mind to suffer"
        		+" the slings and arrows of outrageous fortune,"
        		+" or to take arms against a sea of troubles,"
        		+" and by opposing end them?";

		String regexNonAl = "[^a-zA-Z]+";
        String[] words = text.split(regexNonAl);

        bubbleSort(words);

        System.out.println("Sorted Words:");
        for (String word : words) {
            if (!word.isEmpty()) {
                System.out.println(word);
            }
        }
    }

    public static void bubbleSort(String[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j].compareToIgnoreCase(array[j + 1]) > 0) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
