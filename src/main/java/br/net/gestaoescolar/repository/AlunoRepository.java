package br.net.gestaoescolar.repository;

import br.net.gestaoescolar.model.Aluno;
import infra.database.DatabaseConnect;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepository {

    public Aluno salvaAluno(Aluno aluno) throws SQLException {
        String sql = """
                INSERT INTO aluno 
                (nome, email, matricula, data_nascimento)
                VALUES
                (?, ?, ?, ?)
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, Date.valueOf(aluno.getData_nascimento()) );

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                aluno.setId(rs.getLong(1));

                return aluno;
            }

            return null;
        }
    }

    public Aluno listaAlunoPorId(Long id) throws SQLException{

        String sql = """
                SELECT id, nome, email, matricula, data_nascimento
                FROM aluno
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                LocalDate data_nascimento = rs.getObject("data_nascimento", LocalDate.class);

                return new Aluno(id, nome, email, matricula, data_nascimento);

            }

            return null;

        }

    }

    public List<Aluno> listaAlunos() throws SQLException{

        String sql = """
                SELECT id, nome, email, matricula, data_nascimento
                FROM aluno
                """;

        List<Aluno> alunos = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                LocalDate data_nascimento = rs.getObject("data_nascimento", LocalDate.class);


                alunos.add(new Aluno(id, nome, email, matricula, data_nascimento)) ;

            }

            return alunos;

        }

    }

    public Aluno atualizaAluno(Aluno aluno) throws SQLException {
        String sql = """
                UPDATE aluno
                SET
                nome = ?,
                email = ?,
                matricula = ?,
                data_nascimento = ?
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setDate(4, Date.valueOf(aluno.getData_nascimento()) );
            stmt.setLong(5, aluno.getId());

            stmt.executeUpdate();

            return aluno;
        }
    }

    public void deletaAluno(Long id) throws SQLException{
        String sql = """
                DELETE FROM aluno
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }


}
