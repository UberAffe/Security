/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**

 @author tianb
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.bouncycastle.util.encoders.*;
 
public class JDES
{    
    public static void main(String[] argv) 
    {
        try
        {
            // This generate a DES key based on your key
            String key = hexToASCII(argv[3]);
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec mydeskeyspec = new DESKeySpec(key.getBytes());
            SecretKey myDesKey = keyfactory.generateSecret(mydeskeyspec);


            // This automatically generate a DES key
            //KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            //SecretKey myDesKey = keygenerator.generateKey();

            System.out.println("My Key: " + key);
            System.out.println("DES Key: " + new String(Hex.encode(myDesKey.getEncoded())));

            Cipher desCipher;
 
            // Create the cipher 
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Initialize the cipher for encryption
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);

            //sensitive information
            byte[] iv = hexToASCII(argv[1]).getBytes();
            String fileLocation = argv[5];
            File file = new File(fileLocation);
            Scanner scnr = new Scanner(file);
            String line = scnr.nextLine();
            boolean notDone = true;
            //start of loop
            while(notDone)
            {
                byte[] text = new byte[key.length()];
                for(int i = 0; i < key.length(); i++)
                    text[i] = (byte) line.charAt(i);


                System.out.println("Text [Hex Format] : " + new String(Hex.encode(text)) + " length = "+text.length);
                System.out.println("Text : " + new String(text));
                for(int i = 0; i < text.length; i++)
                {
                    text[i] = (byte) (text[i] ^ iv[i]);
                }


                // Encrypt the text
                byte[] textEncrypted = desCipher.doFinal(text);

                System.out.println("Text Encryted : " + new String(Hex.encode(textEncrypted)));

                // Initialize the same cipher for decryption
                desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

                // Decrypt the text
                byte[] textDecrypted = desCipher.doFinal(textEncrypted);
                for(int i = 0; i < textDecrypted.length; i++)
                {
                    textDecrypted[i] = (byte) (textDecrypted[i] ^ iv[i]);
                }
                System.out.println("Text Decryted : " + new String(textDecrypted));
                iv = text;
                notDone = false;
            }
            //End of Loop
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
	}
        catch(NoSuchPaddingException e){
            e.printStackTrace();
	}
        catch(InvalidKeyException e){
            e.printStackTrace();
	}
        catch(IllegalBlockSizeException e){
            e.printStackTrace();
	}
        catch(BadPaddingException e){
            e.printStackTrace();
	} 	
        catch(InvalidKeySpecException e){
            e.printStackTrace();
	}
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
	
    public static byte [] hexStringToByteArray (final String s) 
    {
        if (s == null || (s.length () % 2) == 1)
            throw new IllegalArgumentException ();
        final char [] chars = s.toCharArray ();
        final int len = chars.length;
        final byte [] data = new byte [len / 2];
        for (int i = 0; i < len; i += 2) 
        {
            data[i / 2] = (byte) ((Character.digit (chars[i], 16) << 4) + Character.digit (chars[i + 1], 16));
        }
        return data;
      }

    private static String hexToASCII(String hexValue)
    {
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < hexValue.length(); i += 2)
        {
            String str = hexValue.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }
	
}
