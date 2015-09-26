package kr.rang2ne.triprec;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import org.junit.Test;

import java.io.File;

/**
 * Created by rang on 2015-09-25.
 */
public class ImageMetaTagInfoTest {

    @Test
    public void getImageMetaInfo() throws Exception {
//        System.out.println("==================my_old_camera==================");
//        String my_old_camera = "C:\\Users\\rang\\Desktop\\testimage\\my_old_camera.jpg";
//        printMetaData(my_old_camera);
//        System.out.println("==================my_camera==================");
//        String my_camera = "C:\\Users\\rang\\Desktop\\testimage\\my_camera.jpg";
//        printMetaData(my_camera);
//        System.out.println("==================first_externel==================");
//        String first_externel = "C:\\Users\\rang\\Desktop\\testimage\\first_externel.jpg";
//        printMetaData(first_externel);
//        System.out.println("==================second_externel==================");
//        String second_externel = "C:\\Users\\rang\\Desktop\\testimage\\second_externel.jpg";
//        printMetaData(second_externel);
        System.out.println("==================gif1==================");
        String gif1 = "C:\\Users\\rang\\Desktop\\testimage\\gif1.gif";
        printMetaData(gif1);
        System.out.println("==================gif2==================");
        String gif2 = "C:\\Users\\rang\\Desktop\\testimage\\gif2.gif";
        printMetaData(gif2);
        System.out.println("==================mixed==================");
        String mixed = "C:\\Users\\rang\\Desktop\\testimage\\mixed.jpg";
        printMetaData(mixed);

    }
    private void printMetaData(String imagePath) throws Exception {
        File imageFile = new File(imagePath);
        Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
        for (Directory directory : metadata.getDirectories()) {
            System.out.println("[" + directory.getName() + "] ");
            directory.getTags().forEach(tag -> {
                System.out.print("   " + tag.getTagType() + " || ");
                System.out.print(tag.getTagName() + " || ");
                System.out.print(tag.getDescription());
                System.out.println("");
            });
        }
    }
}
