package xml;

import java.io.File;
import java.util.StringTokenizer;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class Xml {
    private String Proveedor="",Comprador="",Factura="",Detalle="",Producto="";
    private Conexion con=new Conexion();
    public static void main(String[] args){
        new Xml();
    }
    public Xml(){
        con.Conexion();
        leerConfiguracion();
    }
public void leerConfiguracion(){
    Proveedor="INSERT INTO Proveedor VALUES (";
    Comprador="INSERT INTO Comprador VALUES (";
    Factura="INSERT INTO Factura VALUES (";
    Detalle="INSERT INTO Detalle (Detalle_Factura,Cantidad_Comprada,Precio_Unitario,Valor_Producto,Cod_Factura,Cod_Producto)  VALUES (";
    Producto="INSERT INTO Producto VALUES (";
        try{
    File fXmlFile = new File("C:\\Users\\Jorge\\Documents\\NetBeansProjects\\Xmlv2\\Xml\\src\\xml\\libres.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(fXmlFile);
    //optional, but recommended
    //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
    doc.getDocumentElement().normalize();
 
    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 // nombre de la cabecera del xml 
    NodeList nList = doc.getElementsByTagName("infoTributaria");
    
    System.out.println("----------------------------");
 
    for (int temp = 0; temp < nList.getLength(); temp++) {
 
        Node nNode = nList.item(temp);
        //System.out.println("\nDatos referentes a :" + nNode.getNodeName());
      //  if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        Element eElement = (Element) nNode;
        // detalles comprador
        // System.out.println(eElement.getElementsByTagName("secuencial").item(0).getTextContent());
        // informacion de la factura  
       NodeList nList1 = doc.getElementsByTagName("infoFactura");
       Node nNode1 = nList1.item(temp);
        //System.out.println("\nDatos referentes a :" + nNode1.getNodeName());
            Element eElement1 = (Element) nNode1;
            // detalle factura  
            NodeList nList2 = doc.getElementsByTagName("detalles");
                   Node nNode2 = nList2.item(temp);
              //System.out.println("\nDatos referentes a :" + nNode1.getNodeName());
     //   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement2 = (Element) nNode2;
        //ruc proveedor rucp
        Proveedor+="'"+eElement.getElementsByTagName("ruc").item(0).getTextContent()+"'";
        //razon social rs
        Proveedor+=",'"+eElement.getElementsByTagName("razonSocial").item(0).getTextContent()+"')";
        //codigo factura
        Factura+="'"+eElement.getElementsByTagName("secuencial").item(0).getTextContent()+"'";
        // direccion proveedor dirp
        //System.out.println(eElement.getElementsByTagName("dirMatriz").item(0).getTextContent());    
       // }
       // cedula cliente idc
            Comprador+="'"+eElement1.getElementsByTagName("identificacionComprador").item(0).getTextContent()+"'";
            //nombre cliente nomc
            Comprador+=",'"+eElement1.getElementsByTagName("razonSocialComprador").item(0).getTextContent()+"'";
            //direccion cliente dirc
            Comprador+=",'"+eElement1.getElementsByTagName("dirEstablecimiento").item(0).getTextContent()+"','999999999')";
            // emision factura ff
            Factura+=",'"+intercalarFecha(eElement1.getElementsByTagName("fechaEmision").item(0).getTextContent())+"'";
            //total sin impuestos tsi
            Factura+=","+eElement1.getElementsByTagName("totalSinImpuestos").item(0).getTextContent();
            // valor impuesto iva 
            Factura+=","+eElement1.getElementsByTagName("valor").item(0).getTextContent();
            // valor total total
            Factura+=","+eElement1.getElementsByTagName("baseImponible").item(0).getTextContent();
            Factura+=",'"+eElement.getElementsByTagName("ruc").item(0).getTextContent()+"'";
            Factura+=",'"+eElement1.getElementsByTagName("identificacionComprador").item(0).getTextContent()+"')";
            Producto+="'"+eElement2.getElementsByTagName("codigoPrincipal").item(1).getTextContent()+"'";
            //descripcion factura desf
            Detalle+="'"+eElement2.getElementsByTagName("descripcion").item(1).getTextContent()+"'";
            // cantidad de elementos ele
            Detalle+=","+eElement2.getElementsByTagName("cantidad").item(1).getTextContent();
            //precio unirtario  preciou
            Detalle+=","+eElement2.getElementsByTagName("precioUnitario").item(1).getTextContent();
            // valor total del detalle vtd
            Detalle+=","+eElement2.getElementsByTagName("baseImponible").item(1).getTextContent();
            Detalle+=",'"+eElement.getElementsByTagName("secuencial").item(0).getTextContent()+"'";
            Detalle+=",'"+eElement2.getElementsByTagName("codigoPrincipal").item(1).getTextContent()+"')";
            Producto+=",'"+eElement2.getElementsByTagName("descripcion").item(1).getTextContent()+"')";
            con.InsertDB(Proveedor);
            con.InsertDB(Comprador);
            con.InsertDB(Factura);
            con.InsertDB(Producto);
            con.InsertDB(Detalle);
    }
    } catch (Exception e) {
    e.printStackTrace();
    }
  }
    private String intercalarFecha(String fecha){
        String fe[]=new String[3];
        int i=0;
	StringTokenizer tokens=new StringTokenizer(fecha);
	while(tokens.hasMoreTokens())
            fe[i++]=tokens.nextToken("/");
        fecha="";
        for(int l=2;l>=0;l--){
            fecha+=fe[l];
            if(l>0)
                fecha+="-";
        }
        return fecha;
    }
}
