package newdemo;

import java.io.File;
import java.io.FileInputStream;
import  java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;


@WebService

public class FileBrowser {

    private List<String> files= new ArrayList<>();
    private String path="src/main/java/newdemo/";
    private File ParentDirectory=new File("src/main/java/newdemo/ServerShare");
    private File ServerShare=new File("src/main/java/newdemo/ServerShare");
    
    // The browse function below works like the command line browse system where you go back using ./ and going into files by specifying
    // the name of sub folders tahtg existin the folder you are in. If you enter a sub folder that is not contained in the current folder
    // then you won't be able to access it.

    public List<String> Browse(String directoryName) {
        files.clear();
        if (directoryName == null) {
           files.clear();
            return files;
        } else{
            if (directoryName.equals("")) {
            ParentDirectory = new File(path+"ServerShare");
            } else if (directoryName.equals("./")) {
                if (!(ParentDirectory.getAbsolutePath().equals(ServerShare.getAbsolutePath()))) {
                    ParentDirectory = new File(ParentDirectory.getParent());
                }
            }else if(directoryName.equals(ParentDirectory.getName())){
                ParentDirectory = new File(ParentDirectory.getAbsolutePath());
            } else {
                ParentDirectory = new File(ParentDirectory.getAbsolutePath() +'/'+ directoryName);
            }
            File[] fileList = ParentDirectory.listFiles();

            System.out.println(ParentDirectory.getAbsolutePath());
            if (fileList != null) {
                for (File file : fileList) {
                    if (file.isFile() || file.isDirectory()) {
                        files.add(file.getName());
                    }
                }
                return files;
            }
        files.clear();
        return files;
    }
    }



    public boolean Rename(String PreviousName, String NewName) {
        File[] fileList = ParentDirectory.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.getName().equals(PreviousName)) {
                    File rename = new File(ParentDirectory.getAbsolutePath() + '/' + NewName);
                    boolean flag = file.renameTo(rename);
                    return flag;
                }
            }
        }
        return false;
    }

    public byte[] download(String filename){
        byte[] fileContent;
        File[] fileList = ParentDirectory.listFiles();

        if(fileList != null){
            for (File file : fileList) {      
                if (file.isFile() && file.getName().equals(filename)) {
                try{
                    FileInputStream fl = new FileInputStream(file);
                    System.out.println("Path  "+file.getAbsolutePath());
                    fileContent = new byte[(int)file.length()];
 
                    fl.read(fileContent);
            
                    fl.close();
                    
                    System.out.println(fileContent.length);
                    
                    return fileContent;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        }
        fileContent= new byte[0];
        return fileContent;
    }
    
    public boolean upload(byte[] fi, String pth){

        File outfile =new File(path+"ServerShare/"+pth);
        System.out.println(outfile.getAbsolutePath());
        try (FileOutputStream fos = new FileOutputStream(outfile.getAbsolutePath())) {
            fos.write(fi);
            fos.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }


    public boolean delete (String filename){
        
        File[] fileList = ParentDirectory.listFiles();
       
        if(fileList != null){
            for (File file : fileList) {   

                if (file.isFile() && file.getName().equals(filename)) {
                   return file.delete();
                }
            }
        
        }
        return false;
    }

}

