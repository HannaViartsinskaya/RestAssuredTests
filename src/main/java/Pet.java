import java.io.File;

public class Pet {
    private int id;
    private String name;
    private String status;
    private File image;

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' + "file: "+image+
                '}';
    }

    public Pet(int id, String name, String status) {
        super();
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Pet(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
