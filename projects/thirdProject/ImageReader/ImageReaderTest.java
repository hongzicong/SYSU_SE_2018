import org.junit.Test;
import org.junit.Before;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import static org.junit.Assert.*;

import java.util.Arrays;

public class ImageReaderTest{

    private ImplementImageProcessor imageProcessor;
    private ImplementImageIO imageIO;

    private Image image1;
    private Image image2;

    private Image image1Blue;
    private Image image1Gray;
    private Image image1Green;
    private Image image1Red;

    private Image image2Blue;
    private Image image2Gray;
    private Image image2Green;
    private Image image2Red;

    /**
     * used to get the picture before each case
     */
    @Before
    public void init(){
        imageProcessor = new ImplementImageProcessor();
        imageIO = new ImplementImageIO();
    
        image1 = imageIO.myRead("bmptest/1.bmp");
        image2 = imageIO.myRead("bmptest/2.bmp");
    
        image1Blue = imageIO.myRead("bmptest/goal/1_blue_goal.bmp");
        image1Gray = imageIO.myRead("bmptest/goal/1_gray_goal.bmp");
        image1Green = imageIO.myRead("bmptest/goal/1_green_goal.bmp");
        image1Red = imageIO.myRead("bmptest/goal/1_red_goal.bmp");
    
        image2Blue = imageIO.myRead("bmptest/goal/2_blue_goal.bmp");
        image2Gray = imageIO.myRead("bmptest/goal/2_gray_goal.bmp");
        image2Green = imageIO.myRead("bmptest/goal/2_green_goal.bmp");
        image2Red = imageIO.myRead("bmptest/goal/2_red_goal.bmp");
    }

    /**
     * used to test if the code can pick the correct width of picture successfully
     */
    @Test
    public void testWidth(){
        assertTrue(image1.getWidth(null) == 400 && image2.getWidth(null) == 715);
    }

    /**
     * used to test if the code can pick the correct height of picture successfully
     */
    @Test
    public void testHeight(){
        assertTrue(image1.getHeight(null) == 400 && image2.getHeight(null) == 1024);
    }

    /**
     * used to test if the code can pick the correct red picture successfully
     */
    @Test
    public void testPixRed(){
        // used to store red orgin pixel
        int[] pix1Red = getPixels(image1Red);
        int[] pix2Red = getPixels(image2Red);

        Image redImage1 = imageProcessor.showChanelR(image1);
        int[] pixGoal1 = getPixels(redImage1);

        Image redImage2 = imageProcessor.showChanelR(image2);
        int[] pixGoal2 = getPixels(redImage2);

        assertTrue(Arrays.equals(pix1Red, pixGoal1) && Arrays.equals(pix2Red, pixGoal2));
    }

    /**
     * used to test if the code can pick the correct green picture successfully
     */
    @Test
    public void testPixGreen(){
        // used to store green orgin pixel
        int[] pix1Green = getPixels(image1Green);
        int[] pix2Green = getPixels(image2Green);

        Image greenImage1 = imageProcessor.showChanelR(image1);
        int[] pixGoal1 = getPixels(greenImage1);

        Image greenImage2 = imageProcessor.showChanelR(image2);
        int[] pixGoal2 = getPixels(greenImage2);

        assertTrue(Arrays.equals(pix1Green, pixGoal1) && Arrays.equals(pix2Green, pixGoal2));
    }

    /**
     * used to test if the code can pick the correct blue picture successfully
     */
    @Test
    public void testPixBlue(){
        // used to store blue orgin pixel
        int[] pix1Blue = getPixels(image1Blue);
        int[] pix2Blue = getPixels(image2Blue);

        Image blueImage1 = imageProcessor.showChanelR(image1);
        int[] pixGoal1 = getPixels(blueImage1);

        Image blueImage2 = imageProcessor.showChanelR(image2);
        int[] pixGoal2 = getPixels(blueImage2);

        assertTrue(Arrays.equals(pix1Blue, pixGoal1) && Arrays.equals(pix2Blue, pixGoal2));
    }

    /**
     * used to test if the code can pick the correct grey picture successfully
     */
    @Test
    public void testPixGrey(){
        // used to store grey orgin pixel
        int[] pix1Gray = getPixels(image1Gray);
        int[] pix2Gray = getPixels(image2Gray);

        Image grayImage1 = imageProcessor.showChanelR(image1);
        int[] pixGoal1 = getPixels(grayImage1);

        Image grayImage2 = imageProcessor.showChanelR(image2);
        int[] pixGoal2 = getPixels(grayImage2);

        assertTrue(Arrays.equals(pix1Gray, pixGoal1) && Arrays.equals(pix2Gray, pixGoal2));
    }

    /**
     * used to change Image into pixel array
     */
    public static int[] getPixels(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return convertToBufferedFrom(image).getRGB(0, 0, width, height, null, 0, width);
    }

    /**
     * used to convert Image into Buffered Image
     */
    public static BufferedImage convertToBufferedFrom(Image image){
        BufferedImage buffer = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        // used to draw the image into buffered image
        Graphics2D graph = buffer.createGraphics();
        graph.drawImage(image, 0, 0, null);
        graph.dispose();
        return buffer;
    }

}