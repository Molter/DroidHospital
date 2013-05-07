package br.feevale.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializador {
	
	public static byte[] serialize( Object obj ) throws IOException{

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream( baos );
		
		out.writeObject( obj );
		out.close();
		
		byte[] buf = baos.toByteArray();
		
		return buf;
    }
	
	public static Object deserialize( byte[] buf ) throws IOException, ClassNotFoundException {
		
		ObjectInputStream objectIn = new ObjectInputStream( new ByteArrayInputStream( buf ) );
		Object obj = objectIn.readObject();
		
		return obj;
	}
}