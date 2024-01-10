import dbConnect.DBConnector;
import objectDB.Curator;
import objectDB.Group;
import objectDB.Student;
import tables.AbsTable;
import tables.CuratorsTable;
import tables.GroupsTable;
import tables.StudentsTable;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        AbsTable studentsTable = new StudentsTable();
        AbsTable curatorsTable = new CuratorsTable();
        AbsTable groupsTable = new GroupsTable();

        try {
            List<String> columnsStudentTable = new ArrayList<>();
            columnsStudentTable.add("id INT PRIMARY KEY");
            columnsStudentTable.add("fio VARCHAR(50)");
            columnsStudentTable.add("sex VARCHAR(1)");
            columnsStudentTable.add("id_group INT");
            studentsTable.create(columnsStudentTable);

            List<String> columnsStudentIns = new ArrayList<>();
            columnsStudentIns.add("id");
            columnsStudentIns.add("fio");
            columnsStudentIns.add("sex");
            columnsStudentIns.add("id_group");

            studentsTable.insert(columnsStudentIns, new Student(1, "Richard Davis", "m", 1));
            studentsTable.insert(columnsStudentIns, new Student(2, "Mark Coleman", "m", 1));
            studentsTable.insert(columnsStudentIns, new Student(3, "Michael Guzman", "m", 1));
            studentsTable.insert(columnsStudentIns, new Student(4, "Bruce Jenkins", "m", 1));
            studentsTable.insert(columnsStudentIns, new Student(5, "Roberta Yates", "w", 1));
            studentsTable.insert(columnsStudentIns, new Student(6, "Katherine Chavez", "w", 2));
            studentsTable.insert(columnsStudentIns, new Student(7, "Janet Saunders", "w", 2));
            studentsTable.insert(columnsStudentIns, new Student(8, "Linda Dean", "w", 2));
            studentsTable.insert(columnsStudentIns, new Student(9, "Margaret Alexander", "w", 2));
            studentsTable.insert(columnsStudentIns, new Student(10, "Anna Gates", "w", 2));
            studentsTable.insert(columnsStudentIns, new Student(11, "Christopher Stevenson", "m", 3));
            studentsTable.insert(columnsStudentIns, new Student(12, "James Palmer", "w", 3));
            studentsTable.insert(columnsStudentIns, new Student(13, "Charles Rivera", "w", 3));
            studentsTable.insert(columnsStudentIns, new Student(14, "Rose White", "w", 3));
            studentsTable.insert(columnsStudentIns, new Student(15, "Rick McDonald", "m", 3));


            List<String> columnsGroupTable = new ArrayList<>();
            columnsGroupTable.add("id INT PRIMARY KEY");
            columnsGroupTable.add("name_group VARCHAR(150)");
            columnsGroupTable.add("id_curator INT");
            groupsTable.create(columnsGroupTable);

            List<String> columnsGroup = new ArrayList<>();
            columnsGroup.add("id");
            columnsGroup.add("name_group");
            columnsGroup.add("id_curator");

            groupsTable.insert(columnsGroup, new Group(1, "Economy", 1));
            groupsTable.insert(columnsGroup, new Group(2, "Mathematics", 2));
            groupsTable.insert(columnsGroup, new Group(3, "Physics", 3));


            List<String> columnsCuratorTable = new ArrayList<>();
            columnsCuratorTable.add("id INT PRIMARY KEY");
            columnsCuratorTable.add("fio VARCHAR(50)");
            curatorsTable.create(columnsCuratorTable);

            List<String> columnsCurator = new ArrayList<>();
            columnsCurator.add("id");
            columnsCurator.add("fio");

            curatorsTable.insert(columnsCurator, new Curator(1, "Gordeev I.I."));
            curatorsTable.insert(columnsCurator, new Curator(2, "Tarasevich Y.Y."));
            curatorsTable.insert(columnsCurator, new Curator(3, "Kolomina M.V."));
            curatorsTable.insert(columnsCurator, new Curator(4, "Smirnov A.S."));

            System.out.println(" ");
            System.out.println("* Вывод информации о студентах с названием группы и именем куратора *");

            studentsTable.resultDisplay(studentsTable.selectAllStudentsGroupsCurators("students.fio, groups.name_group, curators.fio", "`groups`", "id_group",
                    "id", "curators", "id_curator"));

            System.out.println(" ");
            System.out.println("* Вывод общего кол-ва студентов *");
            studentsTable.resultDisplay(studentsTable.selectCountStudents());

            System.out.println(" ");
            System.out.println("* Вывод списка студенток *");
            studentsTable.resultDisplay(studentsTable.selectWithWhere("fio", "sex", "w"));

            System.out.println(" ");
            System.out.println("* Вывод кол-ва студенток *");
            studentsTable.resultDisplay(studentsTable.selectCountWithWhere());

            System.out.println(" ");
            System.out.println("* Обновление информации по группе со сменой куратора *");
            System.out.println("ДО:");
            groupsTable.resultDisplay(groupsTable.selectWithWhere("*", "id", "2"));
            groupsTable.update("id_curator", "4", "id", "2");

            System.out.println("ПОСЛЕ:");
            groupsTable.resultDisplay(groupsTable.selectWithWhere("*", "id", "2"));

            System.out.println(" ");
            System.out.println("* Вывод списка групп с их кураторами *");
            groupsTable.resultDisplay(groupsTable.selectWithJoin("groups.name_group, curators.fio", "id_curator", "curators", "id"));

            System.out.println(" ");
            System.out.println("* Студенты из группы (Physics) *");
            studentsTable.resultDisplay(studentsTable.selectWithSubRequest("fio", "id_group", "id", "`groups`", "name_group", "Physics"));

            System.out.println(" ");
            System.out.println("* Студенты из группы (Mathematics) *");
            studentsTable.resultDisplay(studentsTable.selectWithSubRequest("fio", "id_group", "id", "`groups`", "name_group", "Mathematics"));

            System.out.println(" ");
            System.out.println("* Студенты из группы (Economy) *");
            studentsTable.resultDisplay(studentsTable.selectWithSubRequest("fio", "id_group", "id", "`groups`", "name_group", "Economy"));

        } finally {
            DBConnector.close();
        }
    }
}

