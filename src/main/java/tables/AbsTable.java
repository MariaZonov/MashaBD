package tables;

import dbConnect.DBConnector;
import dbConnect.IDBConnector;
import objectDB.TableObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbsTable implements ITable{
    protected IDBConnector dbConnector = null;
    private String tableName = "";

    public AbsTable(String tableName) {
        dbConnector = new DBConnector();
        this.tableName = tableName;
    }

    @Override
    public void create(List<String> columns) {
        delete();
        dbConnector.execute(String.format("CREATE TABLE %s (%s);", tableName, String.join(",", columns)));
    }
    @Override
    public void delete() {

        dbConnector.execute(String.format("drop table if exists %s;",this.tableName));
    }
    public void insert(List <String> columns, TableObject tableObject) {
        String str = tableObject.toSting();
        dbConnector.execute(String.format("INSERT INTO %s (%s) VALUES (%s);",this.tableName,String.join(",",columns),str));
    }
    public ResultSet selectAllStudentsGroupsCurators(String colRes, String tableName2, String tableCol, String tableCol2, String tableName3, String tableCol3 ) {
        return dbConnector.executeQuery(String.format("SELECT %s FROM %s INNER JOIN %s ON %s.%s=%s.%s INNER JOIN %s ON %s.%s = %s.%s;",
                colRes,this.tableName,
                tableName2, this.tableName,tableCol,tableName2,tableCol2,
                tableName3,tableName2,tableCol3, tableName3,tableCol2));
    }

    public ResultSet selectCountStudents(){
       return dbConnector.executeQuery(String.format("SELECT COUNT(*) FROM %s;",this.tableName));
    }

    public ResultSet selectWithWhere(String colRes, String colCon, String valCon) {
        return dbConnector.executeQuery(String.format("SELECT %s FROM %s WHERE %s = '%s';",colRes,this.tableName,colCon,valCon));
    }

    public ResultSet selectCountWithWhere() {
        return dbConnector.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE sex = 'w';",this.tableName));
    }

    public void update(String colUpd, String valUpd, String colCond, String valCond) {
        dbConnector.execute(String.format("UPDATE %s SET %s=%s WHERE %s=%s;", this.tableName, colUpd, valUpd, colCond, valCond));
    }
    public ResultSet selectWithJoin(String colRes, String tableCol, String tableName2, String tableCol2) {
        return dbConnector.executeQuery(String.format("SELECT %s FROM %s JOIN %s ON %s.%s = %s.%s;",
                colRes, this.tableName, tableName2, this.tableName, tableCol, tableName2, tableCol2));
    }

    public ResultSet selectWithSubRequest(String colRes, String tableCol, String colRes2, String tableName2, String tableCol2, String valCond) {
        return dbConnector.executeQuery(String.format("SELECT %s FROM %s WHERE %s = (SELECT %s FROM %s where %s = '%s');",
                colRes, this.tableName, tableCol, colRes2, tableName2, tableCol2, valCond));
    }

    public void resultDisplay(ResultSet resultSet) {
        try {
            int countColumns = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i <= countColumns; i++) {
                    stringBuilder.append(resultSet.getString(i)).append(" ");
                    if (i == countColumns) {
                        System.out.println(stringBuilder);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
//
}