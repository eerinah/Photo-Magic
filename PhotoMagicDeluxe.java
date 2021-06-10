public class PhotoMagicDeluxe {
   private static final String BASE64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
   public static String toBinary(int x, int len) {
      if (len > 0) {
         return String.format("%" + len + "s",
                            Integer.toBinaryString(x)).replaceAll(" ", "0");
      } 
      return null;
   }
   
   public static String getBitKey(String alphaKey) { 
      String bitKey = "";
      for (int i = 0; i < alphaKey.length(); i++) { 
         int index = base64.indexOf(alphaKey.charAt(i));
         int bitLength = 6;
         bitKey += toBinary(index, bitLength);
      } 
      return bitKey; 
   }
   public static void main(String[] args) { 
      String passKey = "OPENSESAME";
      String bitKey = getBitKey(passKey);
      Picture pic = new Picture("mystery.png");
      pic.show();
      LFSR lfsr = new LFSR(bitKey, 58);
      Picture decryptedPic = PhotoMagic.transform(pic, lfsr);
      decryptedPic.show();
   }  
}
