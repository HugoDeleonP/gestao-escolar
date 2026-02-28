package br.net.gestaoescolar.repository;

import br.net.gestaoescolar.model.Professor;
import infra.database.DatabaseConnect;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorRepository {

    public Professor salvaProfessor(Professor professor) throws SQLException {
        String sql = """
                INSERT INTO professor
                (nome, email, disciplina)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                professor.setId(rs.getLong(1));

                return professor;
            }

            return null;
        }
    }

    public Professor listaProfessorPorId(Long id) throws SQLException{

        String sql = """
                SELECT id, nome, email, disciplina
                FROM professor
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String disciplina = rs.getString("disciplina");

                return new Professor(id, nome, email, disciplina);

            }

            return null;

        }

    }

    public List<Professor> listaProfessores() throws SQLException{

        String sql = """
                SELECT id, nome, email, disciplina
                FROM professor
                """;

        List<Professor> professores = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String disciplina = rs.getString("disciplina");


                professores.add(new Professor(id, nome, email, disciplina)) ;

            }

            return professores;

        }

    }

    public Professor atualizaProfessor(Professor professor) throws SQLException {
        String sql = """
                UPDATE professor
                SET
                nome = ?
                email = ?
                disciplina = ?
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.setLong(4, professor.getId());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                professor.setId(rs.getLong(1));

                return professor;
            }

            return null;
        }
    }

    public void deletaProfessor(Long id) throws SQLException{
        String sql = """
                DELETE FROM professor
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
