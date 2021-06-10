import java.util.Arrays;
/**
 * This class makes a linear-feedback shift register (LFSR) that holds a register of
 * bits and manipulates those bits through step-wise operations. 
 * <p>
 * These steps include shifting the bits one position to the left then replacing the 
 * emptied slot in the register with a new bit.
 * The new bit is derived by the exclusive-or operator of the 
 * shifted off bit and the bit at the <i>tap</i> position in the register. 
 * </p>
 * @author Eerina Haque 
 */
public class LFSR 
{
   /** Number of bits in the LFSR */
   private int   N;
   
   /** Register that is represented by an Array */
   private int[] reg; // reg[i] = ith bit of LFSR, reg[0] is leftmost bit
   
   /** Index of the tap bit */
   private int tap;

   /** 
    * Loads the LSFR using the seed and initializes the tap position.
    * @param seed String of 0s and 1s representing bits.
    * @param tap  Index of the tap bit.
    */
   public LFSR(String seed, int tap)  
   { 
      this.N = seed.length();
      this.reg = new int[N];
      
      // bits are "indexed" left to right so the tap position must be adjusted accordingly
      this.tap = reg.length - 1 - tap; 
      
      // loads array with bits from the seed 
      for (int i = 0; i < reg.length; i++) 
      { 
         reg[i] = Integer.parseInt(seed.substring(i, i + 1));
      }
   }
  
   /** 
    * Simulates one step by shifting the bits one position to the left within
    * the LFSR and replacing the vacated slot with a new bit.
    * @return The new bit derived by the XOR operator of the shifted off bit 
    *         and the bit at the tap position.
    */
   public int step()   
   { 
      int newBit = reg[0] ^ reg[tap]; 
      for (int i = 0; i < reg.length - 1; i++) 
      { 
         reg[i] = reg[i + 1];
      }	
      reg[reg.length - 1] = newBit;
      return newBit;
   }
  
   /** 
    * Extracts several bits at once by simulating k number of steps within 
    * the LFSR.
    * @param k The number of step simulations to be executed.
    * @return A k-bit integer that is represented by the bits within the LFSR.
    */
   public int generate(int k) 
   { 
      int integer = 0;
      while(k > 0) 
      { 
         integer *= 2;
         integer += step();
         k--;
      }
      return integer;	
   }
   
   /**
    * Creates a String representation of the LFSR.
    * @return A String representation of the bits held in the LFSR.
    */
   @Override
    public String toString()  {
      String s = "";
      for (int i : reg) { 
         s += i; 
      }
      return s; 
   }
   
   /**
    * Test client.
    * @param args The command line arguments.
    */
   public static void main(String[] args)  
   {
   //    	test01(); // PASS
   //    	test02(); // PASS
   //       test03(); // PASS
   //       test04(); // PASS
   }
    
    /** Tests the toString() method and the constructor */
   public static void test01()
   {
      LFSR lfsr = new LFSR("01101000010", 8);
      System.out.println(lfsr + "\n"); 
    	
    	//should output: 01101000010
   }
    
    /** Tests the step() method */
   public static void test02()
   {
      LFSR lfsr = new LFSR("01101000010", 8);
    	
      for (int i = 0; i < 10; i++) {
         int bit = lfsr.step();
         System.out.println(lfsr + " " + bit);
      }
    	/*
    	   should output:
    	   
    	   11010000101 1
   		10100001011 1
   		01000010110 0
   		10000101100 0
   		00001011001 1
   		00010110010 0
   		00101100100 0
   		01011001001 1
   		10110010010 0
   		01100100100 0
    	 */
   }
    
    /** Tests the generate() method */
   public static void test03()
   {
      System.out.println();
   
      LFSR lfsr = new LFSR("01101000010", 8);
    	
      for (int i = 0; i < 10; i++) 
      {
         int r = lfsr.generate(5);
         System.out.println(lfsr + " " + r);
      }
//     	   should output:
//     	   
//     	   00001011001 25
//    		01100100100 4
//    		10010011110 30
//    		01111011011 27
//    		01101110010 18
//    		11001011010 26
//    		01101011100 28
//    		01110011000 24
//    		01100010111 23
//    		01011111101 29

   }
    
    /** Tests the generate() method again */
   public static void test04()
   {
      System.out.println();
    	
      LFSR lfsr = new LFSR("01101000010100010000", 16);
    	
      for (int i = 0; i < 10; i++) {
         int r = lfsr.generate(8);
         System.out.println(lfsr + " " + r);
      }
    	/*
    	   should output:
    	   01010001000000101010 42
   		00000010101011011001 217
   		10101101100100010111 23
   		10010001011111000001 193
   		01111100000100011010 26
   		00010001101010011100 156
   		10101001110010011100 156
   		11001001110011100111 231
   		11001110011110000111 135
   		01111000011110111101 189
   	*/
   }
}
