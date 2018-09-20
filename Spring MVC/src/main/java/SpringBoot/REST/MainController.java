package SpringBoot.REST;

import SpringBoot.Extras.AjaxResponseBody;
import SpringBoot.Extras.MyImage;
import SpringBoot.Extras.Result;
import SpringBoot.Extras.IDCriteria;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@PropertySource("classpath:application.properties")
public class MainController
{
    @Value("${folderPath}")
    private String path;
    private List<MyImage> listOfImages;

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

    private MediaType getContentType( String name )
    {
        int index = name.lastIndexOf( "." );

        if( index == -1 )
            return null;

        String extension = name.substring( index + 1 );

        switch( extension.toLowerCase() )
        {
            case "png": return MediaType.IMAGE_PNG;
            case "gif": return MediaType.IMAGE_GIF;
            case "jpg": return MediaType.IMAGE_JPEG;
        }

        return null;
    }

    private ResponseEntity<byte[]> createImageResponse( MediaType type, byte[] data )
    {
        return createImageResponse( type, data, HttpStatus.OK );
    }

    private ResponseEntity<byte[]> createImageResponse( MediaType type, byte[] data, HttpStatus status )
    {
        HttpHeaders headers = new HttpHeaders();


        return new ResponseEntity<>( data, headers, status );
    }

    private MyImage getImage( int index )
    {
        if( index > 0 && index < this.listOfImages.size() + 1 )
        {
            for( MyImage el : this.listOfImages )
            {
                if( index == el.getIndex() )
                    return el;
            }
        }

        return null;
    }

    @RequestMapping(value = "/gallery/pic/{index}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getPicture( @PathVariable( value = "index" ) int index )
    {
        MyImage image = this.getImage( index );

        if( image != null )
        {
            MediaType type = this.getContentType( image.getName() );

            if( type != null )
            {
                File file = new File( this.path + "/" + image.getName() );

                try
                {
                    byte[] data = new byte[ (int) file.length() ];

                    try( FileInputStream stream = new FileInputStream( file ) )
                    {
                        stream.read( data );
                    }

                    return this.createImageResponse( type, data );
                }
                catch ( Exception e )
                {
                    e.printStackTrace();
                }
            }
        }

        return this.createImageResponse( MediaType.IMAGE_JPEG, new byte[ 0 ], HttpStatus.NOT_FOUND );
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

    @RequestMapping(value = { "/", "/gallery" }, method = RequestMethod.GET)
    public ModelAndView getFile() throws IOException {
        refreshList();
        return new ModelAndView("images", "images", this.listOfImages );
    }

    @RequestMapping(value = "/gallery/picture/{index}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getImg(@PathVariable(value = "index") int index) {
        return new ModelAndView("image", "index", index );
    }


    @RequestMapping(value="/api/delete", method = RequestMethod.POST)
    public ResponseEntity<?> getDeleteResultViaAjax(
            @Valid @RequestBody IDCriteria idToDel, Errors errors) {
        refreshList();

        AjaxResponseBody result = new AjaxResponseBody();

        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        deleteImage(Integer.parseInt(idToDel.getIdToDelete()));

        result.setMsg("success");

        result.setResult(listOfImages);

        return ResponseEntity.ok(result);

    }

}

