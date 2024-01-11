package tables;

import objectDB.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsTable extends AbsTable {
    private static final String NAME = "students";
    public StudentsTable() {
        super(NAME);

    }
}
