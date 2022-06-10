package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Entity
@NamedQuery(name = "OneSide.deleteAllRows", query = "DELETE from OneSide os")
@Table(name = "OneSide")
public class OneSide {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "oneSide", cascade = CascadeType.ALL)
    private List<ManySide> manySides = new ArrayList<>();

    public OneSide() {
    }

    public OneSide(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ManySide> getManySides() {
        return manySides;
    }

    public void setManySides(List<ManySide> manySides) {
        this.manySides = manySides;
    }

    public void addToManySides(ManySide manySide) {
        this.manySides.add(manySide);
    }

    @Override
    public String toString() {
        return "OneSide{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manySides=" + manySides +
                '}';
    }
}
