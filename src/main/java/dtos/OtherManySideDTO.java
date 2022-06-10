package dtos;

import entities.OtherManySide;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OtherManySideDTO {
    private int id;
    private String name;
    private List<ManySideDTO> manySides;

    public OtherManySideDTO(String name) {
        this.name = name;
    }

    public static List<OtherManySideDTO> getDtos(List<OtherManySide> oms){
        List<OtherManySideDTO> rmdtos = new ArrayList();
        oms.forEach(o->rmdtos.add(new OtherManySideDTO(o)));
        return rmdtos;
    }

    public OtherManySideDTO(OtherManySide o) {
        if(o != null){
            this.id = o.getId();
            this.name = o.getName();
            this.manySides = ManySideDTO.getDtos(o.getManySides());
        }
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

    public List<ManySideDTO> getManySides() {
        return manySides;
    }

    public void setManySides(List<ManySideDTO> manySides) {
        this.manySides = manySides;
    }

    public void addToManySides(ManySideDTO manySide) {
        this.manySides.add(manySide);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OtherManySideDTO that = (OtherManySideDTO) o;
        return id == that.id && name.equals(that.name) && manySides.equals(that.manySides);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manySides);
    }

    @Override
    public String toString() {
        return "OtherManySideDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manySides=" + manySides +
                '}';
    }
}
