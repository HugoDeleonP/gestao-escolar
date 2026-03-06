package br.net.gestaoescolar.repository;

import br.net.gestaoescolar.model.Curso;
import br.net.gestaoescolar.model.Professor;
import infra.database.DatabaseConnect;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepository {

    public Curso salvaCurso(Curso curso) throws SQLException {
        String sql = """
                INSERT INTO curso
                (nome, codigo)
                VALUES
                (?, ?)
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                curso.setId(rs.getLong(1));

                return curso;
            }

            return null;
        }
    }

    public Curso listaCursoPorId(Long id) throws SQLException{

        String sql = """
                SELECT id, nome, codigo
                FROM curso
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");

                return new Curso(id, nome, codigo);

            }

            return null;

        }

    }

    public List<Curso> listaCursos() throws SQLException{

        String sql = """
                SELECT id, nome, codigo
                FROM curso
                """;

        List<Curso> Cursos = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");

                Cursos.add(new Curso(id, nome, codigo)) ;
            }

            return Cursos;

        }

    }

    public Curso atualizaCurso(Curso curso) throws SQLException {
        String sql = """
                UPDATE curso
                SET
                nome = ?,
                codigo = ?
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.setLong(3, curso.getId());

            stmt.executeUpdate();

            return curso;
        }
    }

    public void deletaCurso(Long id) throws SQLException{
        String sql = """
                DELETE FROM curso
                WHERE id = ?
                """;

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<String> listaNomeProfessoresPorCurso(Long id) throws SQLException{
        String sql = """
                SELECT DISTINCT p.nome AS professor_nome
                FROM turma t
                LEFT JOIN professor p
                ON t.professor_id = p.id
                WHERE t.curso_id = ?;
                """;

        List<String> nomeProfessores = new ArrayList<>();

        try(Connection conn = DatabaseConnect.connection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                nomeProfessores.add(rs.getString("professor_nome"));
            }

            return nomeProfessores;
        }
    }



}
