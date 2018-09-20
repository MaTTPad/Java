package SpringBoot.REST;

import SpringBoot.Extras.MyImage;
import SpringBoot.Extras.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;


@Component
@PropertySource("classpath:application.properties")
@RestController
public class MainController
{
    @Value("${folderPath}")
    private String path;
    private List<MyImage> listOfImages;

    @EventListener(ApplicationReadyEvent.class)
    public void refreshList() {
        listOfImages=new ArrayList<MyImage>();
        BufferedImage readImage = null;
        String name="";
        int h=0,w=0;
        String resulution="";
        long size=0;
        MyImage.clearid();

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles!=null)
        {
            for (File f: listOfFiles) {
                MyImage tempImage;
                try {
                    readImage = ImageIO.read(f);
                    name = f.getName();
                    size = f.length();
                    w = readImage.getWidth();
                    h = readImage.getHeight();
                } catch (IOException e) {
                    readImage = null;
                    e.printStackTrace();
                }

                BasicFileAttributes attr;
                try {
                    attr = Files.readAttributes(Paths.get(f.getPath()), BasicFileAttributes.class);
                    String time= String.valueOf(attr.creationTime());
                    resulution=w+"x"+h;
                    tempImage = new MyImage(name, resulution, size, time);
                    listOfImages.add(tempImage);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        }

    @RequestMapping(value = "/gallery/pictures", method = RequestMethod.GET)
    @ResponseBody
    public List<MyImage> allPictures() {
        listOfImages=new ArrayList<MyImage>();
        BufferedImage readImage = null;
        String name="";
        int h=0,w=0;
        String resulution="";
        long size=0;
        MyImage.clearid();

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles!=null)
        {
            for (File f: listOfFiles) {
                MyImage tempImage;
                try {
                    readImage = ImageIO.read(f);
                    name = f.getName();
                    size = f.length();
                    w = readImage.getWidth();
                    h = readImage.getHeight();
                } catch (IOException e) {
                    readImage = null;
                    e.printStackTrace();
                }

                BasicFileAttributes attr;
                try {
                    attr = Files.readAttributes(Paths.get(f.getPath()), BasicFileAttributes.class);
                    String time= String.valueOf(attr.creationTime());
                    resulution=w+"x"+h;
                    tempImage = new MyImage(name, resulution, size, time);
                    listOfImages.add(tempImage);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return listOfImages;

    }



    @RequestMapping(value = "/gallery/picture/{index}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteImage(@PathVariable(value = "index") int index) {
        String nameToDelete="";
        boolean resDel=false;
        Result result=new Result();
        System.out.println(listOfImages);
        for(MyImage img: listOfImages)
        {
            MyImage tempImage=img;
            if(tempImage.getIndex()==index) {
                nameToDelete = tempImage.getName();
                break;
            }
        }



        if(nameToDelete.equals(""))
            result.setResult(false);

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File f: listOfFiles) {
            if(f.getName().equals(nameToDelete)) {
                resDel=f.delete();
            }
        }
        refreshList();
        if(resDel==true)
            result.setResult(true);
        else
            result.setResult(false);

        return result;
    }


    @RequestMapping(value = "/gallery/picture/{index}", method = RequestMethod.GET, produces = "image/jpg")
    public @ResponseBody byte[] getFile(@PathVariable(value = "index") int index)  {
        try {
            // Retrieve image from the classpath.
            InputStream is=null;

            String nameToShow="";
            for(Object img: listOfImages)
            {
                MyImage tempImage=(MyImage)img;
                if(tempImage.getIndex()==index) {
                    nameToShow = tempImage.getName();
                    break;
                }
            }

            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            for (File f: listOfFiles) {
                if(f.getName().equals(nameToShow)) {
                    is = new FileInputStream(f);                }
            }

            if(is!=null) {
                BufferedImage img = ImageIO.read(is);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", bao);

                return bao.toByteArray();
            }
            else{
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/gallery/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(@RequestParam("data") String source){
        File f = new File(source);
        String name=f.getName();
        boolean resUpload=false;
        Result result=new Result();

        Path sourceDirectory = Paths.get(source);
        String target=(path+ "\\" + name);
        System.out.println(target);
        Path targetDirectory = Paths.get(target);

        try {
            Files.copy(sourceDirectory, targetDirectory);
            resUpload=true;
        } catch (IOException e) {
            resUpload=false;
            e.printStackTrace();
        }

        refreshList();
        result.setResult(resUpload);
        return result;
    }

}

