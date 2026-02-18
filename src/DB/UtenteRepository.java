package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Beans.Utente;

public class UtenteRepository {
	private DataSource dataSource;

	// === Costanti letterali per non sbagliarsi a scrivere !!! ============================

	private static final String TABLE = "utente";

	// -------------------------------------------------------------------------------------
	private static final String ID = "id";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String NOME = "nome";
	private static final String COGNOME = "cognome";
	private static final String NAZIONALITA = "nazionalita";

	// == STATEMENT SQL ====================================================================

	// create table
	private static String create = "CREATE TABLE " + TABLE + " ( " +
			ID + " INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1), " +
			NOME + " VARCHAR(20) NOT NULL, " +
			COGNOME + " VARCHAR(20) NOT NULL, " +
			USERNAME + " VARCHAR(20) NOT NULL, " +
			PASSWORD + " VARCHAR(20) NOT NULL, " +
			NAZIONALITA + " VARCHAR(20) NOT NULL, " +
			"PRIMARY KEY (" + USERNAME + "), " +
			") ";

	// drop table
	private static String drop = "DROP TABLE "+TABLE;

	// -------------------------------------------------------------------------------------

	private static final String insert = "INSERT INTO " + TABLE + " ( " +
			NOME + ", " + COGNOME + ", " + USERNAME + ", " + PASSWORD + ", " + NAZIONALITA + " " +
			") " +
			"VALUES (?,?,?,?,?) ";

	// DELETE FROM table WHERE idcolumn = ?;
	static String delete = "DELETE " +
			"FROM " + TABLE + " " +
			"WHERE " + USERNAME + " = ? ";

	// UPDATE table SET xxxcolumn = ?, ... WHERE idcolumn = ?;
	/*static String update = "UPDATE " + TABLE + " SET " +
			DATA + " = ?, " +
			NUMEROPERSONE + " = ?, " +
			CELLULARE + " = ?, " +
			TAVOLO + " = ? " +
			"WHERE " + COGNOME + " = ? ";*/

	// SELECT check postinsert
	static String check_query = "SELECT " + USERNAME
			+ " FROM " + TABLE + " " +
			" WHERE " + USERNAME + " = ?";
	
	static String read_all = "SELECT * " 
			+ " FROM " + TABLE;
	// =====================================================================================

	public UtenteRepository(int databaseType) {//mettiamo 2 sempre per mysql
		dataSource = new DataSource(2);
	}
	
	public void dropTable() throws PersistenceException {
		Connection conn = this.dataSource.getConnection();
		Statement stmt = null;
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate(drop);
		}catch (SQLException e) {
			// the table does not exist
		}finally {
			try {
			if(stmt!=null) {
				stmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
			}catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void createTable() throws PersistenceException {
		Connection connection = this.dataSource.getConnection();

		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(create);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public void persist(Utente u) throws PersistenceException {
		Connection connection = null;
		PreparedStatement pstmtInsert = null;
		PreparedStatement pstmtCheck = null;

		try {
			connection = this.dataSource.getConnection();
			pstmtInsert = connection.prepareStatement(insert);
			pstmtInsert.setString(1, u.getNome() + "");
			pstmtInsert.setString(2, u.getCognome());
			pstmtInsert.setString(3, u.getUsername());
			pstmtInsert.setString(4, u.getPassword());
			pstmtInsert.setString(5, u.getNazionalita());
			pstmtInsert.executeUpdate();

			pstmtCheck = connection.prepareStatement(check_query);
			pstmtCheck.setString(1, u.getUsername());
			ResultSet rs = pstmtCheck.executeQuery();
			rs.next();
			/*
			int idprenotazione = rs.getInt(1);
			pr.setIdPrenotazione(idprenotazione);
			*/
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (pstmtInsert != null)
					pstmtInsert.close();
				if (pstmtCheck != null)
					pstmtCheck.close();
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	/*public void update(PrenotazioneRistorante pr) throws PersistenceException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = this.dataSource.getConnection();
			statement = connection.prepareStatement(update);

			statement.setString(1, pr.getCognomePrenotazione());
			statement.setDate(2, pr.getDataPrenotazione());
			statement.setInt(3, pr.getNumeroPersonePrenotazione());
			statement.setString(4, pr.getCellularePrenotazione());
			statement.setInt(5, pr.getIdTavoloPrenotazione());
			statement.setInt(5, pr.getIdPrenotazione());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}*/
	public List<Utente> readAll() throws PersistenceException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<Utente> result =  new ArrayList<>();;
		Utente u = null;
		try {
			connection = this.dataSource.getConnection();
			pstmt = connection.prepareStatement(read_all);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				u = new Utente();
				u.setId(rs.getLong(ID));
				u.setNome(rs.getString(NOME));
				u.setCognome(rs.getString(COGNOME));
				u.setUsername(rs.getString(USERNAME));
				u.setPassword(rs.getString(PASSWORD));
				u.setNazionalita(rs.getString(NAZIONALITA));
				result.add(u);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return result;
	}
	
	public Optional<Utente> findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT id, nome, cognome, username, password, nazionalita FROM utenti WHERE username = ? AND password_hash = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Ottieni una connessione dal pool del DataSource
            conn = dataSource.getConnection();

            // 2. Prepara lo statement SQL con i parametri
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // 3. Esegui la query
            rs = stmt.executeQuery();

            // 4. Elabora il risultato
            if (rs.next()) { // Se un record viene trovato, significa che le credenziali sono valide
                Utente utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setUsername(rs.getString("username"));
                utente.setPassword(rs.getString("password")); // Recupera l'hash memorizzato
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setNazionalita(rs.getString("nazionalita"));
                return Optional.of(utente); // Restituisce l'utente trovato
            }
        } catch (SQLException e) {
            // Gestione degli errori:
            // - Logga l'errore completo per il debugging (NON mostrarlo all'utente finale per motivi di sicurezza)
            System.err.println("Errore di database durante la verifica credenziali utente: " + e.getMessage());
            e.printStackTrace();
            // - Puoi scegliere di lanciare un'eccezione custom (es. DatabaseAccessException) o un'eccezione di runtime
            //   per la gestione a un livello superiore dell'applicazione.
            throw new RuntimeException("Errore interno del server durante la verifica delle credenziali.", e);
        } catch (PersistenceException e) {
            // Gestione degli errori:
            // - Logga l'errore completo per il debugging (NON mostrarlo all'utente finale per motivi di sicurezza)
            System.err.println("Errore di database durante la verifica credenziali utente: " + e.getMessage());
            e.printStackTrace();
            // - Puoi scegliere di lanciare un'eccezione custom (es. DatabaseAccessException) o un'eccezione di runtime
            //   per la gestione a un livello superiore dell'applicazione.
            throw new RuntimeException("Errore interno del server durante la verifica delle credenziali.", e);
        } 
        
        finally {
            // 5. Chiudi le risorse JDBC nel blocco finally per assicurarti che vengano liberate
            //    anche in caso di eccezioni. L'ordine di chiusura è inverso rispetto all'apertura.
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close(); // Restituisce la connessione al pool del DataSource
            } catch (SQLException e) {
                System.err.println("Errore durante la chiusura delle risorse JDBC: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return Optional.empty(); // Se nessun record è stato trovato, l'utente non esiste o la password non corrisponde
    

	}

}
