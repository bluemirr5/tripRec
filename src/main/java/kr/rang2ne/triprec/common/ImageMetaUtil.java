package kr.rang2ne.triprec.common;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import java.io.*;
import java.util.Iterator;

/**
 * Created by rang on 2015-09-25.
 */
public class ImageMetaUtil {
    public static void read2(String fileName) throws Exception {
        File file = new File(fileName);
        ImageInputStream iis = ImageIO.createImageInputStream(
                new BufferedInputStream(
                        new FileInputStream(file)));
        Iterator readers =
                ImageIO.getImageReadersByMIMEType("image/jpeg");
        IIOImage image = null;
        if (readers.hasNext()) {
            ImageReader reader = (ImageReader) readers.next();
            reader.setInput(iis, true);
            try {
                image = reader.readAll(0, null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            IIOMetadata metadata = image.getMetadata();
            String[] names = metadata.getMetadataFormatNames();
            for (int i = 0; i < names.length; i++) {
                System.out.println("Format name: " + names[i]);
                displayMetadata(metadata.getAsTree(names[i]));
            }
        }
    }

    public static void readAndDisplayMetadata( String fileName ) throws IOException {
//        try {

    File file = new File( fileName );
    ImageInputStream iis = ImageIO.createImageInputStream(file);
    Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

    while (readers.hasNext()) {

        // pick the first available ImageReader
        ImageReader reader = readers.next();

        // attach source to the reader
        reader.setInput(iis, true);

        // read metadata of first image
        IIOMetadata metadata = null;
        try {

            metadata = reader.getImageMetadata(0);
//            metadata = reader.getStreamMetadata();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(metadata != null) {
            String[] names = metadata.getMetadataFormatNames();
            int length = names.length;
            for (int i = 0; i < length; i++) {
                System.out.println( "Format name: " + names[ i ] );
                displayMetadata(metadata.getAsTree(names[i]));
            }
            break;
        }
    }
//        }
//        catch (Exception e) {
//
//            e.printStackTrace();
//        }
    }

    public static void displayMetadata(Node root) {
        displayMetadata(root, 0);
    }

    public static void indent(int level) {
        for (int i = 0; i < level; i++)
            System.out.print("    ");
    }

    public static void displayMetadata(Node node, int level) {
        // print open tag of element
        indent(level);
        System.out.print("<" + node.getNodeName());
        NamedNodeMap map = node.getAttributes();
        if (map != null) {

            // print attribute values
            int length = map.getLength();
            for (int i = 0; i < length; i++) {
                Node attr = map.item(i);
                System.out.print(" " + attr.getNodeName() +
                        "=\"" + attr.getNodeValue() + "\"");
            }
        }

        Node child = node.getFirstChild();
        if (child == null) {
            // no children, so close element and return
            System.out.println("/>");
            return;
        }

        // children, so close current tag
        System.out.println(">");
        while (child != null) {
            // print children recursively
            displayMetadata(child, level + 1);
            child = child.getNextSibling();
        }

        // print close tag of element
        indent(level);
        System.out.println("</" + node.getNodeName() + ">");
    }
}
