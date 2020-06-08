
public class Theory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 10;
		int sum = 0;
		int count = 0;
		
		for (int i=1; i<n; i++) {
			System.out.println("i ="+ i);
			for(int j=1; j<i*i; j++) {
				count++;
				System.out.println("i ="+ i +", j =" + j+ ",count:" + count);
				if(j%i == 0) {
					for(int k=0; k<j; k++) {
						sum = sum + 1;
						System.out.println("i ="+ i +", j =" + j + ", k=" + k + ", sum=" + sum);
					}
				}
			}
		}


	}

}
