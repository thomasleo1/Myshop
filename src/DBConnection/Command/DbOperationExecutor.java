package DBConnection.Command;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbOperationExecutor {

    private final List<IDbOperation> dbOperationsList = new ArrayList<>();

    public DbOperationResult executeOperation(IDbOperation dbOperation) {
        dbOperationsList.add(dbOperation);
        return dbOperation.execute();
    }
}
