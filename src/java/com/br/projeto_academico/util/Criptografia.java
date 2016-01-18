/*
 * Criptografia.java
 *
 * Created on 05/01/2016, 16:38:21
 */

package com.br.projeto_academico.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Renato Borges Cardoso
 * @version 1.00 05/01/2016
 */
public class Criptografia
{
    private static Criptografia instance;

    public static Criptografia getInstance(){
        if(instance == null){
            instance = new Criptografia();
        }
        return instance;
    }

    public static String sha256(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
        byte[] messageDigest = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for(byte b : messageDigest){
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhaCriptografada = hexString.toString();
        return senhaCriptografada;
    }

    public static void main(String[]args){
        try
        {
            System.out.println(sha256("renato"));

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
