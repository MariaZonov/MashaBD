package tables;

import objectDB.Group;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupsTable extends AbsTable {
    private static final String NAME = "`groups`";
    public GroupsTable() {
        super(NAME);
    }
}
