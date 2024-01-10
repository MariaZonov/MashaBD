package tables;

import objectDB.Curator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CuratorsTable extends AbsTable{
    private static final String NAME = "curators";

    public CuratorsTable() {
        super(NAME);
    }
}
