package com.alura.hotelAlura;

import java.util.Base64;

import static com.alura.hotelAlura.verify.JWTUtil.isTokenValid;

public class verify {

    public static void main(String[] args) {

        System.out.println(isTokenValid("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkaWVnby5yb2phcyIsImlzcyI6ImhvdGVsQWx1cmEiLCJpZCI6Mn0.x9P8y1TUh5514Cbj4C_wc45Sml6guN_Z1OtoZvVcGis"));
    }

    public static class JWTUtil {
        public static boolean isTokenValid(String token) {
            String[] parts = token.split("\\.");

            if (parts.length != 3) {
                return false; // El token no tiene tres partes
            }

            try {
                Base64.getUrlDecoder().decode(parts[0]);
                Base64.getUrlDecoder().decode(parts[1]);
            } catch (IllegalArgumentException e) {
                return false; // La decodificación Base64 falló
            }

            // Verificar firma utilizando la clave secreta o el algoritmo de firma correspondiente

            // Si la firma es válida, el token es válido y auténtico
            return true;
        }
    }

}
