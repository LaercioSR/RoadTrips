package br.uefs.ecomp.RoadTrips.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashingSenha {
    
    public static String codificar(String senha) {
        MessageDigest algorithm;
        
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            String senhahex = hexString.toString();
            
            return senhahex;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(HashingSenha.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
