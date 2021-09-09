/* 
Kevin Chen

Febuary 28, 2020

This program creates a 30 x 30 Kilim Carpet using for loops that include diamonds, squares, checkerboards, and lines
*/
public class part1{

	/* creates a line of = signs that is 30 long */
	public static String line(){
		String line = "";
		for (int i = 0; i<30 ; i++){
			line = line + "=";
		}
		return line;
	}

	/* creates a line of - with spaces in between that is 30 long */
	public static String checkline(){
		String check = "";
		for(int i = 0; i<15 ; i++){
			check = check + " -";
		}
		return check;
	}

	/* creates a line of - with spaces in between that is 30 long */
	public static String checklinetwo(){
		String check = "";
		for(int i = 0; i<14 ; i++){
			check = check + " -";
		}
		return check;
	}

	/* creates a line that makes up the diamond and square design with a diamond first that is 30 long */
	public static String diasqua(int width){
		String line = "";
		for(int n = 0 ; n<3 ; n++){

			for (int i = 0; i<width ; i++){
				line = line + " ";
				}
			line = line + "+";
			for (int k = 0; k<2-width ; k++){
				line = line + "++";
				}
			for (int l = 0; l<width ; l++){
				line = line + " ";
			}
			line = line + "+++++";
		}
		return line;
	}
	
	/* creates a line that makes up the diamond and square design with a diamond first that is 30 long */
	public static String squadia(int width){
		String line = "";
		for(int n = 0 ; n<3 ; n++){
			line = line + "+++++";
			for (int i = 0; i<width ; i++){
				line = line + " ";
				}
			line = line + "+";
			for (int k = 0; k<2-width ; k++){
				line = line + "++";
				}
			for (int l = 0; l<width ; l++){
				line = line + " ";
			}
		}
		return line;
	}

	public static void main(String [] args){

		/* creates the rug using designs with coresponding methods */
		System.out.println(line());
		System.out.println(checkline());
		System.out.println(" " + checklinetwo());
		System.out.println(checkline());
		System.out.println(line());

		/* creates the diamond and square mid section */
		for(int l = 0 ; l<2 ; l++){
			for(int i = 2 ; i>=0 ; i--){
				System.out.println(diasqua(i));
			}

			for(int k = 1; k<=2 ; k++){
				System.out.println(diasqua(k));
			}
			for(int m = 2 ; m>=0 ; m--){
				System.out.println(squadia(m));
			}

			for(int n = 1; n<=2 ; n++){
				System.out.println(squadia(n));
			}
		}

		System.out.println(line());
		System.out.println(checkline());
		System.out.println(" " + checklinetwo());
		System.out.println(checkline());
		System.out.println(line());
	
	}
}