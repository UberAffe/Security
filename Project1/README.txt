README

In this folder you will find the following files:

Java sample codes of DES, RSA and SHA-1 are added.

- JDES.java : is a sample code for encrypting / decrypting using DES
- JRSA.java : is a sample code for encrypting / decrypting using RSA
- SHA1.java : is a sample code for digest generation using SHA1

You will also need to download the BouncyCastle library.

https://www.bouncycastle.org/download/bcprov-jdk15on-152.jar

and save it to a convenient location.

Using NetBean, it is very easy to add the jar library to your project. 
Simply right click on library and choose Add JAR.

- test.txt,test2.txt,test3.txt: are sample plain text files to test your code
- test.des,test2.des,test3.des: are DES encrypted versions of files test.txt,test2.txt,test3.txt. 
  Key and IV values used are as follows.
	- Key = 40fedf386da13d57 (Hexadecimal values)
	- IV  = fedcba9876543210 (Hexadecimal values)