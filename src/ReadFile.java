import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** This will read the input file and produce the out put 
 * with the correct information. The information displayed
 * would be the the length and the longest common Subsequence
 * It will also display the amount of time it took for the comparisons
 * between the two sequences.
 * @author thomas
 *
 */
public class ReadFile 
{
   private static int numComparisons = 0;
   private static double totalTime = 0;
   /*
    * This will read the input file and store that information that is in the input file
    * into an linkedlist so I can use it later for comparison
    */
   private static LinkedList<String> inputFile(String filename) 
   {
      LinkedList<String> sequences = new LinkedList<String>();
      String line;
      try 
      {
         BufferedReader input = new BufferedReader(new FileReader(filename));

         while ((line = input.readLine()) != null) 
         {
            if(!line.isEmpty()) 
            {
               /*
                * Best way i could find to allow the input file to just
                * read the sequences on each line.
                */
               Pattern seq2 = Pattern.compile("[ATGC]");
               Matcher letters = seq2.matcher(line);
               String sequence = "";
               while(letters.find()) 
               {
                  sequence += letters.group(0);
               }
               if(sequence.length()>0) 
               {
                  sequences.add(sequence);
               }else
               {
                  System.out.println("sequence is not correct");
               }                 
            }
         }
         input.close();
         
      } catch (Exception e) 
      {
         System.out.println(e.toString());
         System.exit(1);
      }
      /*
       * when there aren't enough sequences to compare so only 0 or 1 
       */
      if (sequences.size() < 2) 
      {
         System.out.println("Error not enough sequences to compare");
         System.exit(0);
      }
      return sequences;
   }
   //produces the output file and takes the linked list input sequences to produce the output
   public static void main(String[] args) 
   {
      //if there wasnt any arguments entered then it will give an error 
      if (args.length == 0) 
      {
         System.out.println("Error the file name was not given ");
         System.exit(0);
      }
      
      try 
      {
         String outputName = args[1];
         //produces the output file and produces error if it cannot
         System.setOut(new PrintStream(new FileOutputStream(outputName)));
      }catch(Exception e) 
      {
         System.out.println("Error in output file ");
      }
      LinkedList<String> inputSequences = inputFile(args[0]);
      
      // this double nested for loop allows for it to compare the right sequence.
      for (int i = 0; i < inputSequences.size(); i++) 
      {
         //j=i+1 allows for it to not compare the same sequence to each other
         for (int j = 1+i; j < inputSequences.size(); j++) 
         {
            numComparisons++;
            System.out.println("Comparison Number " + numComparisons + " for sequences " 
                           + (i + 1) + " and " + (j+1) + ":");
            
            try 
            {
               long startTime = System.nanoTime();
               LongestCommonSubsequence lcs = new LongestCommonSubsequence(inputSequences.get(i), inputSequences.get(j));
               long endTime = System.nanoTime();
               totalTime = (endTime - startTime);
               lcs.displayValues();
               System.out.println("Execution Time: " + (totalTime) + " ns\n");
               totalTime=0;
            } catch (StackOverflowError e) 
            {
               System.out.println("Stack over flow ");
            }
         }
      }
   }
}
