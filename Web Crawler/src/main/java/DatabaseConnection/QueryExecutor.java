package DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public final class QueryExecutor {

    private static final Logger LOGGER = Logger.getGlobal();

    private QueryExecutor() {
        throw new UnsupportedOperationException();
    }

    static {
        try {
            LOGGER.info("Creating table Article");
            create("CREATE TABLE IF NOT EXISTS article (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "website VARCHAR(50), " +
                    "url VARCHAR(100) NOT NULL, " +
                    "title VARCHAR(50), " +
                    "date VARCHAR(50), " +
                    "author VARCHAR(50), " +
                    "content VARCHAR(5000) " +
                    ");");

        } catch (SQLException e) {
            LOGGER.info("Error during create tables: " + e.getMessage());
            throw new RuntimeException("Cannot create tables");
        }
    }

    public static int createAndObtainId(final String insertSql) throws SQLException {
        try (final PreparedStatement statement = ConnectionProvider.getConnection().prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            statement.execute();
            try (final ResultSet resultSet = statement.getGeneratedKeys()) {
                return readIdFromResultSet(resultSet);
            }
        }
    }

    public static int readIdFromResultSet(final ResultSet resultSet) throws SQLException {
        return resultSet.next() ? resultSet.getInt(1) : -1;
    }

    public static void create(final String insertSql) throws SQLException {
        try (final PreparedStatement statement = ConnectionProvider.getConnection().prepareStatement(insertSql)) {
            statement.execute();
        }
    }

    public static ResultSet read(final String sql) throws SQLException {
        final Statement statement = ConnectionProvider.getConnection().createStatement();
        final ResultSet resultSet = statement.executeQuery(sql);
        LOGGER.info(String.format("Query: %s executed.", sql));
        return resultSet;
    }

    public static void delete(final String sql) throws SQLException {
        executeUpdate(sql);
    }

    private static void executeUpdate(final String... sql) throws SQLException {
        try (final Statement statement = ConnectionProvider.getConnection().createStatement()) {
            ConnectionProvider.getConnection().setAutoCommit(false);
            for (String s : sql) {
                statement.executeUpdate(s);
                LOGGER.info(String.format("Query: %s executed.", s));
            }
            ConnectionProvider.getConnection().commit();
            ConnectionProvider.getConnection().setAutoCommit(true);
        }
    }
}
