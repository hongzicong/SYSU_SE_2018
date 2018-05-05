import imagereader.IImageProcessor;  
  
import java.awt.Toolkit;  
import java.awt.image.FilteredImageSource;  
import java.awt.image.RGBImageFilter;  

import java.awt.Image;

public class ImplementImageProcessor implements IImageProcessor {

    public Image showChanelR(Image sourceImage){
        RGBFilter grayFilter = new RGBFilter(ColorNum.RED);  
        Toolkit toolKit = Toolkit.getDefaultToolkit();  
        Image img = toolKit.createImage(new FilteredImageSource(sourceImage.getSource(), grayFilter));  
        return img; 
    }

    public Image showChanelG(Image sourceImage){
        RGBFilter grayFilter = new RGBFilter(ColorNum.GREEN);  
        Toolkit toolKit = Toolkit.getDefaultToolkit();  
        Image img = toolKit.createImage(new FilteredImageSource(sourceImage.getSource(), grayFilter));  
        return img; 
    }

    public Image showChanelB(Image sourceImage){
        RGBFilter grayFilter = new RGBFilter(ColorNum.BLUE);  
        Toolkit toolKit = Toolkit.getDefaultToolkit();  
        Image img = toolKit.createImage(new FilteredImageSource(sourceImage.getSource(), grayFilter));  
        return img; 
    }

    public Image showGray(Image sourceImage){
        RGBFilter grayFilter = new RGBFilter(ColorNum.GREY);  
        Toolkit toolKit = Toolkit.getDefaultToolkit();  
        Image img = toolKit.createImage(new FilteredImageSource(sourceImage.getSource(), grayFilter));  
        return img; 
    }

}

enum ColorNum{
    RED, GREEN, BLUE, GREY
}

class RGBFilter extends RGBImageFilter{  

    
    // const used to denote as the shift offset
    // used to shift 3 bytes 
    private static final int OFFSET_24BIT = 24;
    
    // used to shift 2 bytes
    private static final int OFFSET_16BIT = 16;
    
    // used to shift 1 bytes
    private static final int OFFSET_8BIT = 8;

    // used to specify the color num
    private ColorNum colorNum;  

    // The filter's operation does not depend on the
    // pixel's location, so IndexColorModels can be
    // filtered directly.

    public RGBFilter(ColorNum c){  
        colorNum = c;  
        canFilterIndexColorModel = true;  
    }  
      
    public int filterRGB(int x, int y, int rgb){   
        if(colorNum == ColorNum.RED){  
            return ( rgb & 0xffff0000 );  
        }else if(colorNum == ColorNum.GREEN){  
            return ( rgb & 0xff00ff00 );  
        }else if(colorNum == ColorNum.BLUE){  
            return ( rgb & 0xff0000ff );  
        }else if(colorNum == ColorNum.GREY){  
            int g = (int)( ((rgb & 0x00ff0000) >> OFFSET_16BIT) * 0.299 + ((rgb & 0x0000ff00) >> OFFSET_8BIT) * 0.587  
                    + ((rgb & 0x000000ff)) * 0.114 );  
            return (rgb & 0xff000000) + (g << OFFSET_16BIT) + (g << OFFSET_8BIT) + g;  
        }  
    }  
}