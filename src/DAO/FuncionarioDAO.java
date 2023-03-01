package DAO;

import DTO.FuncionarioDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class FuncionarioDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<FuncionarioDTO> lista = new ArrayList<>();

    public void cadastrarFuncionario(FuncionarioDTO objfuncionariodto) {

        String sql = "insert into funcionario (nome_funcionario, endereco_funcionario) values (?, ?)";

        conn = new ConexaoDAO().conectaBD();

        //get pega , set atribui
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "FuncionarioDAO Cadastrar" + erro);

        }

    }

    public ArrayList<FuncionarioDTO> PesquisarFuncionario() {
        String sql = "select * from funcionario";
        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                FuncionarioDTO objFuncionarioDTO = new FuncionarioDTO();
                objFuncionarioDTO.setId_funcionario(rs.getInt("id_funcionario"));
                objFuncionarioDTO.setNome_funcionario(rs.getString("nome_funcionario"));
                objFuncionarioDTO.setEndereco_funcionario(rs.getString("endereco_funcionario"));

                lista.add(objFuncionarioDTO);

            }

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "FuncionarioDAO: " + erro);

        }
        return lista;

    }

    public void alterarFuncionario(FuncionarioDTO objfuncionariodto) {
        String sql = "update funcionario set nome_funcionario = ?, endereco_funcionario = ? where id_funcionario = ?";
        conn = new ConexaoDAO().conectaBD();

        //get pega , set atribui
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getNome_funcionario());
            pstm.setString(2, objfuncionariodto.getEndereco_funcionario());
            pstm.setInt(3, objfuncionariodto.getId_funcionario());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "FuncionarioDAO Alterar" + erro);

        }

    }

    public void excluirFuncioario(FuncionarioDTO objfuncionariodto) {
        String sql = "delete from funcionario where id_funcionario = ?";
        conn = new ConexaoDAO().conectaBD();

        //get pega , set atribui
        try {
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, objfuncionariodto.getId_funcionario());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "FuncionarioDAO Excluir" + erro);

        }

    }

}
