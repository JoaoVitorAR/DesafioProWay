/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jvalv
 */
public class TabelaModel {
    private int idJogo;
    private int placar;
    private int minTemp;
    private int maxTemp;
    private int minRecorde;
    private int maxRecorde;
    
    

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getPlacar() {
        return placar;
    }

    public void setPlacar(int placar) {
        this.placar = placar;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinRecorde() {
        return minRecorde;
    }

    public void setMinRecorde(int minRecorde) {
        this.minRecorde = minRecorde;
    }

    public int getMaxRecorde() {
        return maxRecorde;
    }

    public void setMaxRecorde(int maxRecorde) {
        this.maxRecorde = maxRecorde;
    }
    
    
}
