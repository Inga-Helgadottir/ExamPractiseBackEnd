package dtos;

import entities.ManySide;
import entities.OneSide;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OneSideDTO {
    private int id;
    private String name;
    private List<ManySideDTO> manySides;

    public OneSideDTO(String name) {
        this.name = name;
    }

    public static List<OneSideDTO> getDtos(List<OneSide> o){
        List<OneSideDTO> rmdtos = new ArrayList();
        o.forEach(os->rmdtos.add(new OneSideDTO(os)));
        return rmdtos;
    }

    public OneSideDTO(OneSide o) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneSideDTO that = (OneSideDTO) o;
        return id == that.id && name.equals(that.name) && manySides.equals(that.manySides);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manySides);
    }

    @Override
    public String toString() {
        return "OneSideDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manySides=" + manySides +
                '}';
    }
}
