/** This Class produces the results for the longest common Subsequence and
 * the length of the longest common Subsequence is also displayed.
 * @author thomas
 *
 */
public class LongestCommonSubsequence 
{
   // initializing variables
   private static String X;
   private static String Y;
   private static int m;
   private static int n;
   private static int[][]b;
   private static int lengthOfLCS;
   private static String seq = "";
   private static int printComparisons = 0;
  //basically creates the matrix that has the set of values X, Y, m, and n.
   public LongestCommonSubsequence(String X, String Y) 
   {
         LongestCommonSubsequence.X = X;
         LongestCommonSubsequence.Y= Y;
         LongestCommonSubsequence.m = X.length();
         LongestCommonSubsequence.n = Y.length();
         LongestCommonSubsequence.b = new int[X.length()+1][Y.length()+1];
         
         lengthOfl();
         printLCS(LongestCommonSubsequence.m, LongestCommonSubsequence.n);   
   }
   // this will return the length of the longest common subsequence
   public void lengthOfl() 
   {
      for(int i = 1; i<=LongestCommonSubsequence.m; i++) 
      {
         for(int j = 1; j<=LongestCommonSubsequence.n;j++) 
         {
            if(X.charAt(i-1) == Y.charAt(j-1)) 
            {
               b[i][j] = b[i-1][j-1]+1;
            }else if(b[i-1][j] >= b[i][j-1])
            {
               b[i][j] = b[i-1][j];
            }else 
            {
               b[i][j] = b[i][j-1];
            }
         }
      }
      lengthOfLCS = b[m][n];
   }
   /* printLCS basically the recursive function from the book with
    * a few changes that will print out the long common subsequence
    */
   public void printLCS(int i, int j) 
   {
      printComparisons++;
      if(i==0||j==0) 
      {
         return;
      }
      if(X.charAt(i-1) == Y.charAt(j-1)) 
      {
         printLCS(i-1, j-1);
         seq += X.charAt(i-1);
      }
      else if(b[i-1][j]>= b[i][j-1]) 
      {
         printLCS(i-1, j);
      }else 
      {
         printLCS(i,j-1); 
      }    
   }
   // produces the values for the LCS into the output file
   public void displayValues() 
   {
      System.out.println("Longest Common Subsequence: " + seq);
      System.out.println("Length: " + lengthOfLCS); 
      System.out.println("Number of Comparsions: "+ printComparisons);
      printComparisons = 0;
   }
}