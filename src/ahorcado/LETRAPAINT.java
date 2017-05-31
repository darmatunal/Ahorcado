/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahorcado;

/**
 *
 * @author Dario Arango
 */
public class LETRAPAINT {
  
private char Letra;
private int [] Posicion;
public  LETRAPAINT( int [] Posi, char Letra){
this.Letra=Letra;
this.Posicion=Posi;
}

    /**
     * @return the Letra
     */
    public char getLetra() {
        return Letra;
    }

    /**
     * @param Letra the Letra to set
     */
    public void setLetra(char Letra) {
        this.Letra = Letra;
    }

    /**
     * @return the Posicion
     */
    public int[] getPosicion() {
        return Posicion;
    }

    /**
     * @param Posicion the Posicion to set
     */
    public void setPosicion(int[] Posicion) {
        this.Posicion = Posicion;
    }

    
}
