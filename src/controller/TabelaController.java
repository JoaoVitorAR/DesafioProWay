/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.TabelaModel;
import util.Conexao;

/**
 *
 * @author jvalv
 */
public class TabelaController {

    public TabelaController() {
    }
    
    public boolean InserirJogos (TabelaModel jogo){
        boolean retorno = false;
        Conexao c = new Conexao();
        calcularDados(jogo);
        
        String sql = "insert into jogos (idJogo, placar, minTemp, maxTemp, minRecorde, maxRecorde) values (?,?,?,?,?,?)";
        try{
            PreparedStatement sentenca = c.con.prepareStatement(sql);
            sentenca.setInt(1,jogo.getIdJogo());
            sentenca.setInt(2,jogo.getPlacar());
            sentenca.setInt(3,jogo.getMinTemp());
            sentenca.setInt(4,jogo.getMaxTemp());
            sentenca.setInt(5,jogo.getMinRecorde());
            sentenca.setInt(6,jogo.getMaxRecorde());
            
            if(!sentenca.execute())
                retorno = true;
            c.desconectar();
        }catch(SQLException erro){
            System.out.println("Erro na sentenca: "+ erro.getMessage());
        }
        return retorno;
    }
    
    public void calcularDados(TabelaModel jogo){
        ArrayList<TabelaModel> todosJogos = new ArrayList<>();
        todosJogos = selecionarTodosJogos();

        int cont = 0;
        int apoio = 0;
        int entrou = 0;
        for (TabelaModel x:todosJogos) {
            cont++;
        }
        for (TabelaModel x:todosJogos) {
            apoio++;
            if (cont == apoio){
                if(jogo.getPlacar() < x.getMinTemp()){
                  jogo.setMinTemp(jogo.getPlacar());
                  jogo.setMaxTemp(x.getMaxTemp());
                  jogo.setMinRecorde(x.getMinRecorde()+ 1);
                  jogo.setMaxRecorde(x.getMaxRecorde());
                  entrou = 1;
                }
                if(jogo.getPlacar() > x.getMaxTemp()){
                  jogo.setMinTemp(x.getMinTemp());
                  jogo.setMaxTemp(jogo.getPlacar());
                  jogo.setMinRecorde(x.getMinRecorde());
                  jogo.setMaxRecorde(x.getMaxRecorde() + 1);
                  entrou = 1;
                }
                if(entrou == 0){
                  jogo.setMinTemp(x.getMinTemp());
                  jogo.setMaxTemp(x.getMaxTemp());
                  jogo.setMinRecorde(x.getMinRecorde());
                  jogo.setMaxRecorde(x.getMaxRecorde()); 
                }
            }
        }
    }
    
    
    public boolean editarJogos(TabelaModel jogo){
        boolean retorno = false;
        Conexao c = new Conexao();

        String sql = "update jogos set placar = ? "
                + "where idJogo = ?";
        try{
            PreparedStatement sentenca = c.con.prepareStatement(sql);
            sentenca.setInt(1, jogo.getPlacar());
            if(sentenca.executeUpdate()==1)
                retorno = true;
            c.desconectar();
        }catch(SQLException erro){
            System.out.println("Erro na sentenca: "+ erro.getMessage());
        }
        return retorno;
    }
    
    
    public boolean excluirJogo(TabelaModel jogo){
        boolean retorno = false;
        Conexao c = new Conexao();
        String sql = "delete from jogos where idJogo = ?";
        try{
            PreparedStatement sentenca = c.con.prepareStatement(sql);
            sentenca.setInt(1, jogo.getIdJogo());
            if(!sentenca.execute())
                retorno = true;
            c.desconectar();
        }catch(SQLException erro){
            System.out.println("Erro na sentenca: "+ erro.getMessage());
        }
        return retorno;
    }
    
    public TabelaModel selecionarJogo(TabelaModel jogo){
        TabelaModel j = null;
        Conexao c = new Conexao();
        String sql = "select * from jogos where idJogo = ?";
        try{
            PreparedStatement sentenca = c.con.prepareStatement(sql);
            sentenca.setInt(1, jogo.getIdJogo());
            ResultSet rs = sentenca.executeQuery();
            if(rs.next()){
                j = new TabelaModel();
                j.setIdJogo(rs.getInt("idJogo"));
                j.setPlacar(rs.getInt("placar"));
                j.setMinTemp(rs.getInt("minTemp"));
                j.setMaxTemp(rs.getInt("maxTemp"));
                j.setMinRecorde(rs.getInt("minRecorde"));
                j.setMaxRecorde(rs.getInt("maxRecorde"));
            }
            c.desconectar();
        }catch(SQLException erro){
            System.out.println("Erro na sentenca: "+ erro.getMessage());
        }
        return j;
    }
    
    public ArrayList<TabelaModel> selecionarTodosJogos(){
        ArrayList<TabelaModel> lista = new ArrayList<>();
        Conexao c = new Conexao();
        String sql = "select * from jogos";
        try{
            PreparedStatement sentenca = c.con.prepareStatement(sql);
            ResultSet rs = sentenca.executeQuery();
            while(rs.next()){
                TabelaModel j = new TabelaModel();
                j.setIdJogo(rs.getInt("idJogo"));
                j.setPlacar(rs.getInt("placar"));
                j.setMinTemp(rs.getInt("minTemp"));
                j.setMaxTemp(rs.getInt("maxTemp"));
                j.setMinRecorde(rs.getInt("minRecorde"));
                j.setMaxRecorde(rs.getInt("maxRecorde"));
                lista.add(j);
            }
            c.desconectar();
        }catch(SQLException erro){
            System.out.println("Erro na sentenca: "+ erro.getMessage());
        }
        return lista;
    }
    public int ultimoIdJogos(){
        int codigo = 0;
        Conexao c = new Conexao();
        String sql = "select max(idJogo) from jogos";
        try{
            PreparedStatement sentenca = c.con.prepareStatement(sql);
            ResultSet rs = sentenca.executeQuery();
            if(rs.next())
                codigo = rs.getInt(1);
            c.desconectar();
        }catch(SQLException erro){
            System.out.println("Erro na sentença: "+ erro.getMessage());
        }
        return codigo;
    }
    
}
