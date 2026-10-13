//throw

package Day08;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Exception5 {
    static void readFile() throws IOException{
        FileReader file = new FileReader("data.txt");
        System.out.println("file opened");
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e){
            System.out.println("could not find the file");
        }
    }
}
