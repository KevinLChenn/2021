/* 
Kevin Chen

Febuary 28, 2020

This program translates hex, bin, dec, and oct to hex, bin, dec, and oct from numbers 1 through 15.
*/
import java.util.Scanner; 
public class part2{

	/* converts a binary number to a decimal number */
	public static int bindec(String input){
		int bin = Integer.parseInt(input);;
		int first = bin%10;
		int sec = bin%100/10;
		int third = (bin%1000)/100;
		int fourth = bin/1000;
		int dec = (first*1) + (sec*2) + (third*4) + (fourth*8);
		return dec;
	}

	/* converts an octal number to a decimal number */
	public static int octdec(String input){
		int oct = Integer.parseInt(input);
		int first = oct%10;
		int sec = oct%100/10;
		int dec = (first*1) + (sec*8);
		return dec;
	}

	/* converts a hexadecimal to a decimal number */
	public static int hexdec(String input){
		int dec = 0;
		if (input.equals("A")){
			dec = 10;
			return dec;
		}
		else if (input.equals("B")){
			dec = 11;
			return dec;
		}
		else if (input.equals("C")){
			dec = 12;
			return dec;
		}
		else if (input.equals("D")){
			dec = 13;
			return dec;
		}
		else if (input.equals("E")){
			dec = 14;
			return dec;
		}
		else if (input.equals("F")){
			dec = 15;
			return dec;
		}		
		else{
			return Integer.parseInt(input);
		}
	}

	/* converts decimal numbers to binary numbers */
	public static String decbin(int input){
		int dec = input;
		int first = dec%2;
		int sec = (dec/2)%2;
		int third = (dec/4)%2;
		int fourth = (dec/8)%2;
		String bin = String.valueOf(fourth) + String.valueOf(third) + String.valueOf(sec) + String.valueOf(first);
		return bin;
	}

	/* converts decimal numbers to octal numbers */
	public static String decoct(int input){
		int dec = input;
		int first = dec%8;
		int sec = (dec/8)%8;
		int third = (dec/64)%8;
		String oct = String.valueOf(third) + String.valueOf(sec) + String.valueOf(first);
		return oct;
	}

	/* converts decimal numbers to hexadecimal numbers */
	public static String dechex(int input){
		String hex = String.valueOf(input);
		if(input == 10){
			hex = "A";
		}
		if(input == 11){
			hex = "B";
		}
		if(input == 12){
			hex = "C";
		}
		if(input == 13){
			hex = "D";
		}
		if(input == 14){
			hex = "E";
		}
		if(input == 15){
			hex = "F";
		}
		return hex;
	}


	public static void main(String [] args){

	Scanner input = new Scanner(System.in);

	/* prompts the user for the number system they are converting from */
	System.out.println("Enter the name of the number system to convert from: bin, or dec, or oct or hex: ");
	String system = input.nextLine();

	/* prompts the user for the number as a string */
	System.out.println("Enter number as a string: ");
	String number = input.next();

	/* prompts the user for the number system they want to convert to */
	System.out.println("Enter the name of the number system you want to convert to: bin, or dec, or oct or hex: ");
	String convert = input.next();

	int dec = 0;

	/* converts binary to decimal */
	if(system.equals("bin")){
		dec = bindec(number);
	}

	/* converts octal to decimal*/
	if(system.equals("oct")){
		dec = octdec(number);
	}

	/* converts hexadecimal to decimal */
	if(system.equals("hex")){
		dec = hexdec(number);
	}

	/* converts decimal as a string to an integer */
	if(system.equals("dec")){
		dec = Integer.parseInt(number);
	}

	/* prints a decimal */
	if(convert.equals("dec")){
		System.out.println(dec);
	}

	/* converts a decimal to a binary and prints it */
	if(convert.equals("bin")){
		System.out.println(decbin(dec));
	}

	/* converts a decimal to an octal and prints it */
	if(convert.equals("oct")){
		System.out.println(decoct(dec));
	}

	/* converts a decimal to a hexadecimal and prints it */
	if(convert.equals("hex")){
		System.out.println(dechex(dec));
	}
}
}