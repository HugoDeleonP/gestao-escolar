package br.net.gestaoescolar.repository;

import br.net.gestaoescolar.model.Turma;
import infra.database.DatabaseConnect;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaRepository {

    public Turma salvaTurma(Turma turma) throws SQLException {
        String sql = """
                INSERT INTO turma
                (nome, curso_id, professor_id)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, turma.getNome());
            stmt.setLong(2, turma.getCurso_id());
            stmt.setLong(3, turma.getProfessor_id());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                turma.setId(rs.getLong(1));

                return turma;
            }

            return null;
        }
    }

    public Turma listaTurmaPorId(Long id) throws SQLException{

        String sql = """
                SELECT id, nome, curso_id, professor_id
                FROM turma
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                String nome = rs.getString("nome");
                Long curso_id = rs.getLong("curso_id");
                Long professor_id = rs.getLong("professor_id");

                return new Turma(id, nome, curso_id, professor_id);

            }

            return null;

        }

    }

    public List<Turma> listaTurmas() throws SQLException{

        String sql = """
                SELECT id, nome, curso_id, professor_id
                FROM turma
                """;

        List<Turma> turmas = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                Long curso_id = rs.getLong("curso_id");
                Long professor_id = rs.getLong("professor_id");

                turmas.add(new Turma(id, nome, curso_id, professor_id)) ;
            }

            return turmas;

        }

    }

    public Turma atualizaTurma(Turma turma) throws SQLException {
        String sql = """
                UPDATE turma
                SET
                nome = ?,
                curso_id = ?,
                professor_id = ?
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, turma.getNome());
            stmt.setLong(2, turma.getCurso_id());
            stmt.setLong(3, turma.getProfessor_id());
            stmt.setLong(4, turma.getId());

            stmt.executeUpdate();

            return turma;
        }
    }

    public void deletaTurma(Long id) throws SQLException{
        String sql = """
                DELETE FROM turma
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void salvaTurmaAluno(Long turmaId, Long alunoId) throws SQLException {
        String sql = """
                INSERT INTO turma_aluno
                (turma_id, aluno_id)
                VALUES
                (?, ?)
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, turmaId);
            stmt.setLong(2, alunoId);

            stmt.executeUpdate();

        }
    }

    public List<String> listaNomeAlunos(Long turmaId) throws SQLException{
        String sql = """
                SELECT a.nome
                FROM turma_aluno t
                LEFT JOIN aluno a ON
                a.id = t.aluno_id
                WHERE turma_id = ?;
                """;

        List<String> alunoNomes = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, turmaId);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                alunoNomes.add(rs.getString(1));

            }

            return alunoNomes;
        }
    }


}
