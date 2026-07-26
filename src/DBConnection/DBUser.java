package DBConnection;

public class DBUser {
    private static DBUser instance = new DBUser();
    private String schemaName;
    private String userName;
    private String pwd;

    private DBUser() {
        schemaName = "myshop";
        userName = "root";
        pwd = "";
    }

    public static DBUser getInstance() {
        return instance;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPwd() {
        return pwd;
    }
}
