import java.awt.Color;
public class PhotoMagic { 

   public static Picture transform(Picture pic, LFSR lfsr) { 
      for (int x = 0; x < pic.width(); x++) { 
         for (int y = 0; y < pic.height(); y++) { 
            Color rgb = pic.get(x, y);
            int red = rgb.getRed();
            red = red ^ lfsr.generate(8);
            int green = rgb.getGreen();
            green = green ^ lfsr.generate(8);
            int blue = rgb.getBlue();
            blue = blue ^ lfsr.generate(8);
            Color xorRgb = new Color(red, green, blue);
            pic.set(x, y, xorRgb);
         }
      }
      return pic;
   }

   public static void main(String[] args) { 
      Picture pic = new Picture("pipe.png");
      pic.show();
      LFSR lfsr = new LFSR("01101000010100010000", 16);
      Picture encryptedPic = transform(pic, lfsr);
      encryptedPic.show();
      lfsr = new LFSR("01101000010100010000", 16);
      Picture decryptedPic = transform(encryptedPic, lfsr);
      decryptedPic.show();
      
      
   }


}