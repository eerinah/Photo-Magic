import java.awt.Color;
/**
 * This class encrypts and decrypts pictures using a linear-feedback shift register (LFSR).
 * It takes an image of a pipe, encrypts it by scrambiling the colors of the pixels, then 
 * decrypts the image back to the original image of the pipe. 
 * 
 * @see LFSR
 * @author Eerina Haque
 */
public class PhotoMagic { 

   /**
    * Encrypts an image by extracting the red, blue, and green color components of 
    * each pixel within the image and using the exclusive-or operator of the 
    * LFSR and the color components to generate a new color for each pixel. 
    * @param pic  The image that is being encrypted.
    * @param lfsr The linear-feedback shift register that encrypts 
    *              the color componets by generating a new color.
    * @return An encrypted image of the original image.
    */
   public static Picture transform(Picture pic, LFSR lfsr) 
   { 
      for (int x = 0; x < pic.width(); x++) 
      { 
         for (int y = 0; y < pic.height(); y++) 
         { 
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
   /**
    * Intializes the linear-feedback shift register and displays the encrypted image
    * to the user.
    * @param args The command line arguments.
    */
   public static void main(String[] args) 
   { 
      Picture pic = new Picture("pipe.png");
      pic.show();
      LFSR lfsr = new LFSR("01101000010100010000", 16);
      Picture encryptedPic = transform(pic, lfsr);
      encryptedPic.show();
      
     /* decrypts the encrypted image back to the original image */
//          lfsr = new LFSR("01101000010100010000", 16);
//          Picture decryptedPic = transform(encryptedPic, lfsr);
//          decryptedPic.show();
         
   }
}