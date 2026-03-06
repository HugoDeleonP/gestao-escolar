package br.net.gestaoescolar.repository;

import br.net.gestaoescolar.model.Nota;
import infra.database.DatabaseConnect;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotaRepository {

    public Nota salvaNota(Nota nota) throws SQLException {
        String sql = """
                INSERT INTO nota
                (aluno_id, aula_id, valor)
                VALUES
                (?, ?, ?)
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, nota.getAluno_id());
            stmt.setLong(2, nota.getAula_id());
            stmt.setDouble(3, nota.getValor());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                nota.setId(rs.getLong(1));

                return nota;
            }

            return null;
        }
    }

    public Nota listaNotaPorId(Long id) throws SQLException{

        String sql = """
                SELECT id, aluno_id, aula_id, valor
                FROM nota
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                Long aluno_id = rs.getLong("aluno_id");
                Long aula_id = rs.getLong("aula_id");
                double valor = rs.getDouble("valor");

                return new Nota(id, aluno_id, aula_id, valor);

            }

            return null;

        }

    }

    public List<Nota> listaNotas() throws SQLException{

        String sql = """
                SELECT id, aluno_id, aula_id, valor
                FROM nota
                """;

        List<Nota> notas = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                Long aluno_id = rs.getLong("aluno_id");
                Long aula_id = rs.getLong("aula_id");
                double valor = rs.getDouble("valor");

                notas.add(new Nota(id, aluno_id, aula_id, valor)) ;

            }

            return notas;

        }

    }

    public Nota atualizaNota(Nota nota) throws SQLException {
        String sql = """
                UPDATE nota
                SET
                aluno_id = ?,
                aula_id = ?,
                valor = ?
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, nota.getAluno_id());
            stmt.setLong(2, nota.getAula_id());
            stmt.setDouble(3, nota.getValor());
            stmt.setLong(4, nota.getId());

            stmt.executeUpdate();

            return nota;
        }
    }

    public void deletaNota(Long id) throws SQLException{
        String sql = """
                DELETE FROM nota
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
