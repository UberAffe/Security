/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sha1;

/**

 @author tianb
 */
import java.security.Security;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.*;
import java.security.MessageDigest;


/**
 * Basic IO example using SHA1
 */
public class SHA1 {
  public static void main(String[] args) throws Exception {
    Security.addProvider(new BouncyCastleProvider());        
    byte input[] = {0x30, 0x31, 0x32, 0x33, 0x34};

      try
      {
            //prepare the input
            MessageDigest hash =
               MessageDigest.getInstance("SHA-1", "BC");
            hash.update(input);

            //proceed ....
            byte[] digest = hash.digest();

            //show us the result
            System.out.println("input: " +
                   new String(Hex.encode(input)));
            System.out.println("result: " +
                   new String(Hex.encode(digest)));
      }
      catch (NoSuchAlgorithmException e)
      {
            System.err.println("No such algorithm");
            e.printStackTrace();
      }
      catch (NoSuchProviderException e)
      {
            System.err.println("No such provider");
            e.printStackTrace();
      }
  }
}

