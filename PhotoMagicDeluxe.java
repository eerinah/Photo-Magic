public class PhotoMagicDeluxe {
   public static String toBinary(int x, int len) {
      if (len > 0) {
         return String.format("%" + len + "s",
                            Integer.toBinaryString(x)).replaceAll(" ", "0");
      } 
      return null;
   }
   
   public static String getBitKey(String alphaKey) { 
      String bitKey = "";
      String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
      for (int i = 0; i < alphaKey.length(); i++) { 
         int index = base64.indexOf(alphaKey.charAt(i));
         bitKey += toBinary(index, 1);
      } 
      return bitKey; 
   }
   public static void main(String[] args) { 
      String bitKey = getBitKey("OPENSESAME");
      Picture pic = new Picture("mystery.png");
      pic.show();
      LFSR lfsr = new LFSR(bitKey, 58);
      Picture decryptedPic = PhotoMagic.transform(pic, lfsr);
      decryptedPic.show();
   }  
}