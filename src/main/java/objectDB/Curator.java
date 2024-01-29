package objectDB;

public class Curator implements TableObject {
    private int id;
    private String fio;

    public Curator(int id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public int getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    @Override
    public String toSting() {
        return String.format("'%s','%s'", getId(), getFio());
    }
}
