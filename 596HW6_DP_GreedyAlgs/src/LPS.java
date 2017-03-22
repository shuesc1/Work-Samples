//package basicjava;

class Tuple {
	int length;
	String palindrome;

	Tuple(int length, String palindrome) {
		this.length = length;
		this.palindrome = palindrome;
	}
}

public class LPS {

	public static Tuple[][] longestPalindromicSubsequence(String inputString) {
		int length = inputString.length();
		Tuple[][] LPS = new Tuple[length][length];
		for (int i =0; i < length; i++) {
			LPS[i][i] = new Tuple(1, "" + inputString.charAt(i));
		}

		for (int i = 0; i < length; i++) { 
			for (int j = 0; j < i; j++) {
				LPS[i][j] = new Tuple(0, "");
			}
		}
		for (int substringLength = 2; substringLength < length + 1; substringLength++) {
			for (int i = 0; i < length - substringLength + 1; i++) {
				int j = i + substringLength - 1;
				if (inputString.charAt(i) == inputString.charAt(j)) {
					LPS[i][j] = new Tuple(LPS[i+1][j-1].length + 2, inputString.charAt(i) + LPS[i+1][j-1].palindrome + 
							inputString.charAt(i));
				}
				else {
					if (LPS[i+1][j].length > LPS[i][j-1].length) {
						LPS[i][j] = new Tuple(LPS[i+1][j].length, LPS[i+1][j].palindrome);
					}
					else {
						LPS[i][j] = new Tuple(LPS[i][j-1].length, LPS[i][j-1].palindrome);
					}
				}
			}
		}
		return LPS;
	}

	public static void main(String[] args) {
		String inputString = "revolutionary";
		Tuple[][] lpsAnswer = longestPalindromicSubsequence(inputString);
		System.out.println("The max palindromic sequence is " +  lpsAnswer[0][inputString.length() - 1].palindrome);
	}

}
