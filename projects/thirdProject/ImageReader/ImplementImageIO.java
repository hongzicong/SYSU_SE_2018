
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.iamge.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import imagereader.IImageIO;  

public class ImplementImageIO implements IImageIO {

    // const used to denote as the shift offset
    // used to shift 3 bytes 
    private static final int OFFSET_24BIT = 24;
    
    // used to shift 2 bytes
    private static final int OFFSET_16BIT = 16;
    
    // used to shift 1 bytes
    private static final int OFFSET_8BIT = 8;

    // used to specify the size of the head
    private static final int HEAD_SIZE = 54;

    // can combine 4 bytes into a int
    private int byte2int(byte b1, byte b2, byte b3, byte b4){
        int value1 = ((int)b1 & 0xff)<<OFFSET_24BIT;  
        int value2 = ((int)b2 & 0xff)<<OFFSET_16BIT;  
        int value3 = ((int)b3 & 0xff)<<OFFSET_8BIT;  
        int value4 = (int)b4 & 0xff;  
        return value1|value2|value3|value4;
    }

    
    public Image myRead(String filePath){
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        try{
            byte[] bytes = new byte[HEAD_SIZE];
            inputStream.read(bytes);

            int width = byte2int(bytes[21], bytes[20], bytes[19], bytes[18]);  
            int height = byte2int(bytes[25], bytes[24], bytes[23], bytes[22]);  
            int size = byte2int(bytes[37], bytes[36], bytes[35], bytes[34]);
            
            int zero = 0;  
            if(width * 3 % 4 != 0){  
                zero = 4 - width * 3 % 4;  
            }

            byte[] temp = new byte[size];
            inputStream.read(temp);

            int pos = 0;
            int[] pix = new int[height * width]; 
            for(int i = height - 1; i >= 0; --i){
                for(int j = 0; j < width; ++j){       
                    pix[i * width + j] = byte2int((byte)0xff, temp[pos + 2], temp[pos + 1], temp[pos]);
                    pos += 3;
                }
                pos += zero;
            }
            Image resultImage = Toolkit.getDefaultToolkit()
                                        .createImage(new MemoryImageSource(width, height, pix, 0, width));
            return resultImage;
        } catch(IOException e){
            return null;
        }
    }

    public Image myWrite(Image image, String filePath){
        File imageFile = new File(filePath + "bmp");
        try{
            BufferedImage buffer = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            
            // used to draw the image into buffered image
            Graphics2D graph = buffer.createGraphics();
            graph.drawImage(image, 0, 0, null);
            graph.dispose();
            
            ImageIO.write(buffer, "bmp", imageFile);
            return image;
        }catch(IOException e){
            return image;
        }
    }

}