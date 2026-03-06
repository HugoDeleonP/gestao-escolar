package br.net.gestaoescolar.repository;

import br.net.gestaoescolar.model.Aula;
import infra.database.DatabaseConnect;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AulaRepository {

    public Aula salvaAula(Aula aula) throws SQLException {
        String sql = """
                INSERT INTO aula
                (turma_id, data_hora, assunto)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, aula.getTurma_id());
            stmt.setObject(2, aula.getData_hora());
            stmt.setString(3, aula.getAssunto());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                aula.setId(rs.getLong(1));

                return aula;
            }

            return null;
        }
    }

    public Aula listaAulaPorId(Long id) throws SQLException{

        String sql = """
                SELECT id, turma_id, data_hora, assunto
                FROM aula
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                Long turma_id = rs.getLong("turma_id");
                LocalDateTime data_hora = rs.getObject("data_hora", LocalDateTime.class);
                String assunto = rs.getString("assunto");

                return new Aula(id, turma_id, data_hora, assunto);

            }

            return null;

        }

    }

    public List<Aula> listaAulas() throws SQLException{

        String sql = """
                SELECT id, turma_id, data_hora, assunto
                FROM aula
                """;

        List<Aula> aulas = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                Long turma_id = rs.getLong("turma_id");
                LocalDateTime data_hora = rs.getObject("data_hora", LocalDateTime.class);
                String assunto = rs.getString("assunto");

                aulas.add(new Aula(id, turma_id, data_hora, assunto)) ;

            }

            return aulas;

        }

    }

    public Aula atualizaAula(Aula aula) throws SQLException {
        String sql = """
                UPDATE aula
                SET
                turma_id = ?,
                data_hora = ?,
                assunto = ?
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, aula.getTurma_id());
            stmt.setObject(2, aula.getData_hora());
            stmt.setString(3, aula.getAssunto());
            stmt.setLong(4, aula.getId());

            stmt.executeUpdate();

            return aula;
        }
    }

    public void deletaAula(Long id) throws SQLException{
        String sql = """
                DELETE FROM aula
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

}
