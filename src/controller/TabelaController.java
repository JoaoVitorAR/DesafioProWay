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
    
    public boolean InserirJogos (TabelaModel jogo){
        boolean retorno = false;
        Conexao c = new Conexao();
        calcularDados(jogo);
        
        String sql = "insert into jogos (placar, minTemp, maxTemp, minRecorde, maxRecorde) values (?,?,?,?,?)";
        try{
            PreparedStatement sentenca = c.con.prepareStatement(sql);
            sentenca.setInt(1,jogo.getPlacar());
            sentenca.setInt(2,jogo.getMinTemp());
            sentenca.setInt(3,jogo.getMaxTemp());
            sentenca.setInt(4,jogo.getMinRecorde());
            sentenca.setInt(5,jogo.getMaxRecorde());
            
            if(!sentenca.execute())
                retorno = true;
            c.desconectar();
        }catch(SQLException erro){
            System.out.println("Erro na sentenca: "+ erro.getMessage());
        }
        return retorno;
    }
    
    public void calcularDados(TabelaModel tabela){
        ArrayList<TabelaModel> todosItens = new ArrayList<>();
        todosItens = selecionarTodosJogos();

        int cont = 0;
        int apoio = 0;
        int entrou = 0;
        for (TabelaModel x:todosItens) {
            cont++;
        }
        for (TabelaModel x:todosItens) {
            apoio++;
            if (cont == apoio){

                if(tabela.getPlacar() < x.getMinTemp()){
                  tabela.setMinTemp(tabela.getPlacar());
                  tabela.setMaxTemp(x.getMaxTemp());
                  tabela.setMinRecorde(x.getMinRecorde()+ 1);
                  tabela.setMaxRecorde(x.getMaxRecorde());
                  entrou = 1;
                }
                if(tabela.getPlacar() > x.getMaxTemp()){
                  tabela.setMinTemp(x.getMinTemp());
                  tabela.setMaxTemp(tabela.getPlacar());
                  tabela.setMinRecorde(x.getMinRecorde());
                  tabela.setMaxRecorde(x.getMaxRecorde() + 1);
                  entrou = 1;
                }
                if(entrou == 0){
                  tabela.setMinTemp(x.getMinTemp());
                  tabela.setMaxTemp(x.getMaxTemp());
                  tabela.setMinRecorde(x.getMinRecorde());
                  tabela.setMaxRecorde(x.getMaxRecorde()); 
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
