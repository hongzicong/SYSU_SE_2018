import org.junit.Test;

import java.awt.Image;

import static org.junit.Assert.*;

import java.util.Arrays;

public class ImageReaderTest{

    ImplementImageProcessor imageProcessor;
    ImplementImageIO imageIO;

    Image image_1;
    Image image_2;

    Image image_1_blue;
    Image image_1_gray;
    Image image_1_green;
    Image image_1_red;

    Image image_2_blue;
    Image image_2_gray;
    Image image_2_green;
    Image image_2_red;

    int[] pix_1;
    int[] pix_2;

    int[] pix_1_blue;
    int[] pix_1_gray;
    int[] pix_1_green;
    int[] pix_1_red;

    int[] pix_2_blue;
    int[] pix_2_gray;
    int[] pix_2_green;
    int[] pix_2_red;

    @BeforeEach
    public void init(){
        imageProcessor = new ImplementImageProcessor();
        imageIO = new ImplementImageIO();
    
        image_1 = imageIO.myRead("bmptest/1.bmp");
        image_2 = imageIO.myRead("bmptest/2.bmp");
    
        image_1_blue = imageIO.myRead("bmptest/1_blue_goal.bmp");
        image_1_gray = imageIO.myRead("bmptest/1_gray_goal.bmp");
        image_1_green = imageIO.myRead("bmptest/1_green_goal.bmp");
        image_1_red = imageIO.myRead("bmptest/1_red_goal.bmp");
    
        image_2_blue = imageIO.myRead("bmptest/2_blue_goal.bmp");
        image_2_gray = imageIO.myRead("bmptest/2_gray_goal.bmp");
        image_2_green = imageIO.myRead("bmptest/2_green_goal.bmp");
        image_2_red = imageIO.myRead("bmptest/2_red_goal.bmp");
    }

    @Test
    public void testWidth(){
        assertTrue(image_1.getWidth(null) == 400 && image_2.getWidth(null) == 715);
    }

    @Test
    public void testHeight(){
        assertTrue(image_1.getHeight(null) == 400 && image_2.getHeight(null) == 1024);
    }

    @Test
    public void testPixRed(){
        pix_1_red = getPixels(image_1_red);
        pix_2_red = getPixels(image_2_red);

        Image redImage_1 = imageProcessor.showChanelR(image_1);
        int[] pix_goal_1 = getPixels(redImage_1);

        Image redImage_2 = imageProcessor.showChanelR(image_2);
        int[] pix_goal_2 = getPixels(redImage_2);

        assertTrue(Arrays.equals(pix_1_red, pix_goal_1) && Arrays.equals(pix_2_red, pix_goal_2));
    }

    @Test
    public void testPixGreen(){
        pix_1_green = getPixels(image_1_green);
        pix_2_green = getPixels(image_2_green);

        Image greenImage_1 = imageProcessor.showChanelR(image_1);
        int[] pix_goal_1 = getPixels(greenImage_1);

        Image greenImage_2 = imageProcessor.showChanelR(image_2);
        int[] pix_goal_2 = getPixels(greenImage_2);

        assertTrue(Arrays.equals(pix_1_green, pix_goal_1) && Arrays.equals(pix_2_green, pix_goal_2));
    }

    @Test
    public void testPixBlue(){
        pix_1_blue = getPixels(image_1_blue);
        pix_2_blue = getPixels(image_2_blue);

        Image blueImage_1 = imageProcessor.showChanelR(image_1);
        int[] pix_goal_1 = getPixels(blueImage_1);

        Image blueImage_2 = imageProcessor.showChanelR(image_2);
        int[] pix_goal_2 = getPixels(blueImage_2);

        assertTrue(Arrays.equals(pix_1_blue, pix_goal_1) && Arrays.equals(pix_2_blue, pix_goal_2));
    }

    @Test
    public void testPixGrey(){
        pix_1_gray = getPixels(image_1_gray);
        pix_2_gray = getPixels(image_2_gray);

        Image grayImage_1 = imageProcessor.showChanelR(image_1);
        int[] pix_goal_1 = getPixels(grayImage_1);

        Image grayImage_2 = imageProcessor.showChanelR(image_2);
        int[] pix_goal_2 = getPixels(grayImage_2);

        assertTrue(Arrays.equals(pix_1_gray, pix_goal_1) && Arrays.equals(pix_2_gray, pix_goal_2));
    }

    public static int[] getPixels(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[] pixels = convertToBufferedFrom(image).getRGB(0, 0, width, height, null, 0, width);
        return pixels;
    }

}