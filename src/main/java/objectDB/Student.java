package objectDB;

public class Student implements TableObject {
    private int id;
    private String fio;
    private String sex;
    private int id_group;

    public Student(int id, String fio, String sex, int id_group) {
        this.id = id;
        this.fio = fio;
        this.sex = sex;
        this.id_group = id_group;
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getSex() {
        return sex;
    }

    public int getId_group() {
        return id_group;
    }

    @Override
    public String toSting() {
        return String.format("'%s', '%s', '%s', '%s'", getId(), getFio(), getSex(), getId_group());
    }
}
