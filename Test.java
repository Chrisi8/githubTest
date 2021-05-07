package src.githubTest;

public class Test {
private final static String NN = "Her";
public static char getCh(int i) {
	char[] ch = NN.toCharArray();
	return ch[i];
}

public static void main (String[] args) {
	for (int i =-1; i<=NN.length();i+=2) {
		try {
			System.out.println(""+getCh(i) +"\n");
		}catch(ArrayIndexOutOfBoundsException ae) {
			System.out.println(getCh(0));
			continue;
		}
		catch(Exception ex) {
			System.out.println(getCh(3));
			break;
		}
		finally {
			System.out.println(getCh(NN.length()-1));
		}
	}
}
}
