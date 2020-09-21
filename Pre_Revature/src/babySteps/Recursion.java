package babySteps;

public class Recursion {
	
		public static void main(String[] args) {
			
			char input = 'z';
			
			Recursion re = new Recursion();
			
			System.out.println(re.alphaRecur(input));
			
		}
		
		public String alphaRecur(char input) {
			
			if (input == 'a') {
				
				return "a";
				
			}
			
			return input + alphaRecur(--input);
			
		}
}
