import slanglex.lexer.*;
import slanglex.node.*;
import slanglex.analysis.*;
import java.util.*;
import java.io.*;
 
public class SLangLexer {
  
	public static void main(String []args) {       
		PrintWriter outputStream = null;
		List<String> vector = new ArrayList<String>();//creates an array list to store all the tokens
		try {
			
			if(args.length>0) {
				//creates scanner that will transform a source file containing a stream of characters into meaningful tokens. 
				Lexer lexer = new Lexer (new PushbackReader(new BufferedReader(new FileReader(args[0].toString())), 1024)); 
				StringTokenizer tokenizer = new StringTokenizer(args[0].toString(),".");
   			String outputFilename = tokenizer.nextToken()+".out";
  				outputStream = new PrintWriter(new File(outputFilename)); //creates an outputstream object that writes to the file
				
				while(!(lexer.peek() instanceof EOF))//reads all the tokens if its not the end of the file and adds them to a vector
				{	
					//checks for all the tokens and adds them to corresponding array list.
					Token tok = lexer.next();

					if(tok instanceof TKeywords)
					{
					 vector.add((tok.toString().toUpperCase()).trim());
					}
					else if(tok instanceof TWhitespace)
					{
					 vector.add("WHITESPACE");
					}
					else if(tok instanceof TId)
					{
					 vector.add(("ID"+ "("+ tok.toString().trim()+ ")"));			
					}
					else if(tok instanceof TIntegerLiteral)
					{
					 vector.add("INT_LITERAL"+"("+tok.toString().trim()+")");
					}
					else if(tok instanceof TFloatingLiteral)
					{
					 vector.add("FLOAT_LITERAL"+"("+tok.toString().trim()+")");
					}
					else if(tok instanceof TOperators)
					{
					 vector.add(tok.toString().trim());
					}
					else if(tok instanceof TComments)
					{
					 vector.add("COMMENT");
					}
				} // end while
				  
			} // end if
				
			String [] AllTokens  = new String[vector.size()];// creates to store all the tokens read.

			vector.toArray(AllTokens); // converts the arraylist into an array
			for(int i= 0 ; i<AllTokens.length; i++)
			{
				outputStream.println(AllTokens[i]);//writes the output of all tokens  to the file
			}

			outputStream.close(); // closes output stream object
			System.out.println("output file created");
		} // end try
		catch (Exception e)
		{
				System.out.println("Error has occured lexer scanner generator can't read tokens");//handles exception
		} // end catch
  	} // end main
} // end class
