import java.io.*;
import java.util.*;

public class Palindrome{
	public static void main(String[] args){
		for(String arg: args){
			String str = arg;
			boolean fpal = true;

			//optional task
			str = str.toLowerCase();
			str = str.replaceAll("[^a-zа-я]","");
			
			//System.out.println(str);

			//

			for(int i = 0; i<(str.length()/2); i++){
				if (!str.substring(i, i+1).equals(str.substring(str.length()-1-i, str.length()-i))) fpal = false;
			}
			if (fpal) System.out.println("\""+arg+"\"" + " is Palindrome");
			else System.out.println("\""+arg+"\"" + " is not Palindrome");
		} 
	}
}