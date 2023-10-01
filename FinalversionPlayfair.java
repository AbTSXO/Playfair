package javaapplication1;
import java.util.Scanner;
/**
 *
 * @author Abenezer Ashenafi
 */
public class FinalversionPlayfair {
 private static char   key_matrix[][] = new char[5][5];
   
 public static void generate_key(String keyword){
        char[] keywordChar = keyword.toCharArray();
        char[] tempChar = new char[keywordChar.length];
        boolean flag = false;
        int j = 0;
        int t = 0;
        for (int i = 0; i < tempChar.length; i++) {
            if (i < 1) {
                if (keywordChar[i] == 'j'){tempChar[t] = 'i';}
                    else{tempChar[t] = keywordChar[i];}
                    t++;
            } else {
                flag = checkRepeated(tempChar, keywordChar[i]);
                if (flag) {
                    j++;
                }
                else{
                    if (keywordChar[i] == 'j'){tempChar[t] = 'i';}
                    else{tempChar[t] = keywordChar[i];}
                    t++;
                }
            }}
                fill_matrix(tempChar,(keywordChar.length-j));         
    }
    private static void fill_matrix(char [] tempChar, int length){
        char c = 'a'; // the character to load into the matrix
        int temp = 0;
        boolean flag= false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
        
        if(temp<length){
           
                     key_matrix[i][j] = tempChar[temp];
                     temp++;
        }
        else{
           flag = checkRepeated(tempChar,c);
           if((flag) || (c == 'j')){
            j--;
            c++;
           }
           else{
               key_matrix[i][j] = c; // load the character into each cell of the matrix
               c++; // increment the character for the next cell
           }}}}}
    public void formatter(String input){
        char [] formatted_input  = input.toCharArray();
        char [] final_formatted = new char[1000];
        int counter = 0;
       for (int i = 0; i < formatted_input.length; i++) {
    if (i < formatted_input.length - 1 && formatted_input[i] == formatted_input[i+1]) {
        final_formatted[counter] = formatted_input[i];
        final_formatted[counter+1] = 'x';
        counter+=2;
    } 
   
    else {
        if (formatted_input[i] == 'j'){final_formatted[counter] = 'i';}
        else{final_formatted[counter] = formatted_input[i];}
        counter++;
    }}
       if(counter%2 == 1){
       final_formatted[counter] = 'x';
       counter++;
       }
       
        display(final_formatted,counter);
        finalEncrypt(final_formatted,counter);
    }
    
// this function is responsible for displaying the keyword key in a matrix     
    public void display(char [] disp, int counter){
        for (int i = 0; i < counter; i++)
        {
            System.out.print(disp[i] + ",");
        
        }
        
    }
    
    // encryption is done in this function 
    public void finalEncrypt(char [] disp,int counter){
        char temp1,temp2;
        String final_encrypt="";
        for (int i = 0; i < counter; i+=2)
        {
            temp1=disp[i];
            temp2=disp[i+1];
            final_encrypt += encode(temp1,temp2);
        }
        
        System.out.println("final encrypt:" + final_encrypt );
        
    
    }
    public String encode(char temp1, char temp2){
    String tempStr="";
    int iOfTemp1 = 0,jOfTemp1= 0;
    int iOfTemp2 = 0,jOfTemp2 = 0;
    
    
    for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++){
                if( key_matrix[i][j] == temp1){
                    iOfTemp1 = i;//row
                    jOfTemp1 = j;//col
                }
                else if(key_matrix[i][j] == temp2) {
                    iOfTemp2 = i;
                    jOfTemp2 = j;
                }
            }
    }
    tempStr = translate(iOfTemp1,jOfTemp1,iOfTemp2,jOfTemp2);
    
        return tempStr;
    }
   public String translate(int iOfTemp1,int jOfTemp1, int iOfTemp2, int jOfTemp2){
   String translated="";
   int temp;
   if(iOfTemp1 == iOfTemp2){
       jOfTemp1++;
       jOfTemp2++;
       if(jOfTemp1>4){
           jOfTemp1=0;
       }
       if(jOfTemp2>4){
           jOfTemp2=0;
       }
       translated += key_matrix[iOfTemp1][jOfTemp1];
       translated += key_matrix[iOfTemp2][jOfTemp2];
   }
   else if (jOfTemp1== jOfTemp2){
       
       iOfTemp1++;
       iOfTemp2++;
       if(iOfTemp1 > 4){
           iOfTemp1=0;
       }
       if(iOfTemp2 > 4){
           iOfTemp2=0;
       }
       translated += key_matrix[iOfTemp1][jOfTemp1];
       translated += key_matrix[iOfTemp2][jOfTemp2];   
       }
   else{
       temp =  jOfTemp1;
       jOfTemp1 = jOfTemp2;
       jOfTemp2 = temp;
       translated += key_matrix[iOfTemp1][jOfTemp1];
       translated += key_matrix[iOfTemp2][jOfTemp2];
    }
   return translated;
   }
 private void matrix()
    {
        int counter = 0;
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                System.out.print(key_matrix[i][j] + " ");
                counter++;
            }
            System.out.println();
        }
    }
 public static boolean checkRepeated(char[] tempChar, char c) {
    for (int i = 0; i < tempChar.length; i++) {
        if (tempChar[i] == c) {
            return true; // character is repeated
        }
    }
    return false; // character is not repeated
}   
    public static void main(String[] args) {
        FinalversionPlayfair fp = new FinalversionPlayfair(); 
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a keyword:");
        String keyword = sc.next();
        String readykeyword = keyword.replaceAll("\\s+", ""); // remove all spaces
        generate_key(readykeyword);
        fp.matrix();
        System.out.println("Enter word to encrypt:");
        String plain_input = sc.next();
        plain_input = plain_input.replaceAll("\\s+", ""); // remove all spaces
        fp.formatter(plain_input);
 }   
}
