package xml;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Scanner;
import static xml.LeerArchivo.NombreArchivo;

public class Convertir {
    public Convertir() {
         System.out.println("Introduce una cadena de texto a buscar: ");
	 String texto1 = "![CDATA[";
         String texto2="]]";
         int j=0;
	 	try {
			final BufferedReader reader = new BufferedReader(new FileReader(NombreArchivo));
			String line = "";
			while((line = reader.readLine())!= null){
                            System.out.println(texto1);
				//if(line.indexOf(texto1)!= -1){
				if(line.contains(line)){
                                    line.charAt(1);
                                   // j=j+1;
                                    
                                    //if(j!=0){
                                System.out.println("\n"+line. charAt(j));
                                }//}
				//}
			}reader.close();
	 	} catch (FileNotFoundException e) {e.printStackTrace();
		} catch (IOException e) {e.printStackTrace();
		}
    }
}
