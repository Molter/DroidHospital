package droidhospital.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import droidhospital.service.DroidHospitalException;

public class Util {

	private static String digits = "0123456789abcdef";

	public static byte[] codificaBCD( String dadosNumericos ) {

		short tam = (short) dadosNumericos.length();
		
		if( ( tam & 0x01 ) == 1 ) {
			tam++;
		}

		byte[] result = new byte[ tam >> 1 ];
		int r = 0;

		for( int i = 0; i < dadosNumericos.length(); i += 2 ) {

			testaCaractereValido( dadosNumericos.charAt( i ) );
			testaCaractereValido( dadosNumericos.charAt( i ) );

			int b1 = dadosNumericos.charAt( i ) - '0';
			int b2 = 16;
			try {
				b2 = dadosNumericos.charAt( i + 1 ) - '0';
			} catch( Exception e ) {
			}

			result[ r++ ] = (byte) ( ( b1 << 4 ) | ( b2 & 0x0f ) );
		}

		return result;
	}

	public static String decodificaBCD( byte[] dadosBCD, int inicio, int tam ) {

		StringBuilder dados = new StringBuilder( tam << 2 );

		for( int i = 0; i < dadosBCD.length; i++ ) {

			int indice = inicio + i;

			byte b1 = (byte) ( ( dadosBCD[ indice ] >> 4 ) & 0x0f );
			byte b2 = (byte) ( dadosBCD[ indice ] & 0x0f );

			if( b1 < 10 ) {
				dados.append( (char) ( b1 + '0' ) );
			} else {
				geraErroTipo( b1 );
			}

			if( b2 < 10 ) {
				dados.append( (char) ( b2 + '0' ) );
			} else {
				geraErroTipo( b2 );
			}
		}

		return dados.toString();
	}

	public static int decodificaLong( byte[] bTam ) {
		return decodificaLong( bTam[ 0 ], bTam[ 1 ], bTam[ 2 ], bTam[ 3 ] );
	}

	public static int decodificaLong( byte b1, byte b2, byte b3, byte b4 ) {

		int vlr = ( ( ( b1 << 24 ) & 0xff000000 ) | ( b2 << 16 & 0x00ff0000 ) | ( b3 << 8 & 0x0000ff00 ) | ( b4 & 0x000000ff ) );
		return vlr;
	}

	public static byte[] codificaLong( int vlr ) {

		byte[] result = new byte[ 4 ];

		result[ 0 ] = (byte) ( ( vlr >> 24 ) & 0xff );
		result[ 1 ] = (byte) ( ( vlr >> 16 ) & 0xff );
		result[ 2 ] = (byte) ( ( vlr >> 8 ) & 0xff );
		result[ 3 ] = (byte) ( vlr & 0xff );

		return result;
	}

	public static byte[] codificaInt( int vlr ) {

		if( vlr > 65535 )
			throw new RuntimeException( "Valor muito alto para codificar: " + vlr );

		byte[] result = new byte[ 2 ];

		result[ 0 ] = (byte) ( ( vlr >> 8 ) & 0xff );
		result[ 1 ] = (byte) ( vlr & 0xff );

		return result;
	}

	public static int decodificaInt( byte b1, byte b2 ) {

		int vlr = ( ( ( b1 << 8 ) & 0xff00 ) | ( b2 & 0x00ff ) );
		return vlr;
	}

	private static void geraErroTipo( byte bt ) {

		byte[] cErr = new byte[ 1 ];
		cErr[ 0 ] = bt;
		throw new RuntimeException( "Erro de decodificacao BCD - caractere invalido: 0x" + toHex( cErr ) );
	}

	private static void testaCaractereValido( char ch ) {

		if( ch < '0' || ch > '9' ) {
			throw new RuntimeException( "Erro de conversao para bcd - caractere invalido: '" + ch + "'" );
		}
	}

	public static String toHexArray( byte[] data, int length ) {

		StringBuilder sb = new StringBuilder( length * 4 + 10 );

		sb.append( "{ " );

		for( int i = 0; i < length; i++ ) {
			int v = data[ i ] & 0xff;

			if( i > 0 )
				sb.append( ", " );

			if( v >= 0x80 ) {
				sb.append( "(byte) " );
			}

			sb.append( "0x" );
			sb.append( digits.charAt( v >> 4 ) );
			sb.append( digits.charAt( v & 0xf ) );
		}

		sb.append( " }" );
		return sb.toString();
	}

	public static String toHexArray( byte[] data ) {
		return toHexArray( data, data.length );
	}

	public static byte[] toByte( String dados ) {

		byte[] result = new byte[ dados.length() / 2 ];
		int j = 0;

		for( int i = 0; i < dados.length(); i += 2 ) {

			char ch1 = dados.charAt( i );
			char ch2 = dados.charAt( i + 1 );

			result[ j++ ] = (byte) ( ( digits.indexOf( ch1 ) << 4 ) | ( digits.indexOf( ch2 ) ) );
		}

		return result;
	}

	public static String toHex( byte[] data, int length ) {

		StringBuilder sb = new StringBuilder( length * 2 + 10 );

		for( int i = 0; i < length; i++ ) {
			int v = data[ i ] & 0xff;

			sb.append( digits.charAt( v >> 4 ) );
			sb.append( digits.charAt( v & 0xf ) );
		}

		return sb.toString();
	}

	public static String toHex( byte[] data ) {
		return toHex( data, data.length );
	}

	public static boolean hashOk( String msg ) throws DroidHospitalException {

		int pos = msg.lastIndexOf( ":" );

		if( pos > 0 ) {

			String base = msg.substring( 0, pos );
			String hash = msg.substring( pos + 1 );

			String hashCalc = md5( base );

			return hash.equals( hashCalc );
		}

		return false;
	}

	public static String md5( String str ) throws DroidHospitalException {

		MessageDigest md = null;

		try {

			md = MessageDigest.getInstance( "MD5" );
			md.update( str.getBytes() );
		} catch( NoSuchAlgorithmException e ) {
			throw new DroidHospitalException( e );
		}

		return hash( md );
	}

	private static String hash( MessageDigest md ) {

		byte[] hash = md.digest();

		StringBuilder hexString = new StringBuilder( hash.length );

		for( int i = 0; i < hash.length; i++ ) {
			if( ( 0xff & hash[ i ] ) < 0x10 ) {
				hexString.append( "0" );
			}
			hexString.append( Integer.toHexString( 0xFF & hash[ i ] ) );
		}

		return hexString.toString();
	}

	public static String strZero( double vl, int tam ) {

		StringBuffer result = new StringBuffer( tam );

		result.append( vl );

		while( result.length() < tam ) {
			result.insert( 0, '0' );
		}

		return result.toString();
	}

	public static String strZero( int vl, int tam ) {

		StringBuffer result = new StringBuffer( tam );

		result.append( vl );

		while( result.length() < tam ) {
			result.insert( 0, '0' );
		}

		return result.toString();
	}

	public static String strZero( String vl, int tam ) {

		StringBuffer result = new StringBuffer( tam );

		result.append( vl );

		while( result.length() < tam ) {
			result.insert( 0, '0' );
		}

		return result.toString();
	}

	public static String soNumeros( String str ) {

		StringBuilder nros = new StringBuilder( str.length() );

		for( int i = 0; i < str.length(); i++ ) {

			char ch = str.charAt( i );

			if( ch >= '0' && ch <= '9' ) {
				nros.append( ch );
			}
		}

		return nros.toString();
	}

	public static Integer forcaSenha( String senha ) {

		String letras = "qwertyuiopasdfghjklzxcvbnm";
		String numeros = "1234567890";
		int count = 0;

		boolean nums = true;
		boolean letrasMa = true;
		boolean letrasMi = true;
		boolean simbolos = true;

		for( int i = 0; i < senha.length(); i++ ) {
			if( numeros.contains( senha.charAt( i ) + "" ) ) {
				if( nums ) {
					count++;
					nums = false;
				}
			} else if( letras.contains( senha.charAt( i ) + "" ) ) {
				if( letrasMi ) {
					count++;
					letrasMi = false;
				}
			} else if( letras.toUpperCase().contains( senha.charAt( i ) + "" ) ) {
				if( letrasMa ) {
					count++;
					letrasMa = false;
				}
			} else {
				if( simbolos ) {
					count++;
					simbolos = false;
				}
			}
		}

		return count;
	}

	public static String formataData( String dt ) {

		String[] campos = dt.split( "-" );

		if( campos.length == 3 ) {

			StringBuilder dtf = new StringBuilder();

			int dia = Integer.parseInt( campos[ 2 ] );
			int mes = Integer.parseInt( campos[ 1 ] );
			int ano = Integer.parseInt( campos[ 0 ] );

			dtf.append( dia < 10 ? "0" : "" );
			dtf.append( dia );
			dtf.append( '/' );
			dtf.append( mes < 10 ? "0" : "" );
			dtf.append( mes );
			dtf.append( '/' );
			dtf.append( ano );

			return dtf.toString();
		} else {
			return "";
		}
	}

	public static String getDsData() {

		Calendar c = Calendar.getInstance();
		return getDsData( c );
	}

	private static String getDsData( Calendar c ) {

		StringBuilder dtf = new StringBuilder();

		int dia = c.get( Calendar.DAY_OF_MONTH );
		int mes = c.get( Calendar.MONTH ) + 1;
		int ano = c.get( Calendar.YEAR );

		dtf.append( dia < 10 ? "0" : "" );
		dtf.append( dia );
		dtf.append( '/' );
		dtf.append( mes < 10 ? "0" : "" );
		dtf.append( mes );
		dtf.append( '/' );
		dtf.append( ano );

		return dtf.toString();
	}

	public static String getDsDataHora() {
		Calendar c = Calendar.getInstance();
		return getDsDataHora( c );
	}

	private static String getDsDataHora( Calendar c ) {

		StringBuilder dtf = new StringBuilder();

		int dia = c.get( Calendar.DAY_OF_MONTH );
		int mes = c.get( Calendar.MONTH ) + 1;
		int ano = c.get( Calendar.YEAR );
		int hora = c.get( Calendar.HOUR_OF_DAY );
		int mint = c.get( Calendar.MINUTE );
		int scds = c.get( Calendar.SECOND );

		dtf.append( dia < 10 ? "0" : "" );
		dtf.append( dia );
		dtf.append( '/' );
		dtf.append( mes < 10 ? "0" : "" );
		dtf.append( mes );
		dtf.append( '/' );
		dtf.append( ano );
		dtf.append( ' ' );
		dtf.append( hora < 10 ? "0" : "" );
		dtf.append( hora );
		dtf.append( ':' );
		dtf.append( mint < 10 ? "0" : "" );
		dtf.append( mint );
		dtf.append( ':' );
		dtf.append( scds < 10 ? "0" : "" );
		dtf.append( scds );

		return dtf.toString();
	}
}