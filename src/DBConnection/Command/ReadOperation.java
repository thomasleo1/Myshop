package DBConnection.Command;

import DBConnection.DbConnection;

import java.sql.ResultSet;

public class ReadOperation implements IDbOperation {

    private DbConnection conn = DbConnection.getInstance();
    private String sql;

    public ReadOperation(String sql) {
        this.sql = sql;
    }

    @Override
    public DbOperationResult execute() {

        DbOperationResult result = new DbOperationResult();
        result.setResultSet(conn.executeQuery(sql));
        return result;
    }
}
