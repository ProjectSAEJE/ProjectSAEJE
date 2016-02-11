package file;

/**
 * Created by woodev01 on 2/11/16.
 */
public class LoadDialogBuilder {

    /*
    Tentative load code, based on the code from the following website:
    http://www.tutorialspoint.com/android/android_internal_storage.htm
    Better code probably exists for this, I'll do what I can to find it
    as soon as possible.

    public void load(String fileName) {

       FileInputStream fin = openFileInput(fileName);
       int c;
       String temp="";
       while( (c = fin.read()) != -1){
       temp = temp + Character.toString((char)c);
       }
       //string temp contains all the data of the file.
       fin.close();


       Idea: view saved files, input a file name & it'll load the file?

    }

    //rough sketch of what delete should do; code will come later
    public void delete(String fileName){

        input file name
       if file name equals file:
           delete it
       else:
           Toast.makeTest([text input] + " was not found, so it can't be deleted!", Toast.LENGTH_SHORT).show();

         //Idea #2:

       input file name
       try{
           delete it
       }catch (IOException e){
          Toast.makeText([text input] + " was not found, so it can't be deleted!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
       }

       } */
}
