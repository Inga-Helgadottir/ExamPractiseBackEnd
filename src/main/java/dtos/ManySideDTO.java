package dtos;

import entities.ManySide;
import entities.OneSide;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManySideDTO {
    private int id;
    private String name;
    private OneSide oneSide;

    public ManySideDTO(String name, OneSide oneSide) {
        this.name = name;
        this.oneSide = oneSide;
    }

    public static List<ManySideDTO> getDtos(List<ManySide> m){
        List<ManySideDTO> rmdtos = new ArrayList();
        m.forEach(ms->rmdtos.add(new ManySideDTO(ms)));
        return rmdtos;
    }

    public ManySideDTO(ManySide m) {
        if(m != null){
            this.id = m.getId();
            this.oneSide = m.getOneSide();
            this.name = m.getName();
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

    public OneSide getOneSide() {
        return oneSide;
    }

    public void setOneSide(OneSide oneSide) {
        this.oneSide = oneSide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManySideDTO that = (ManySideDTO) o;
        return id == that.id && name.equals(that.name) && oneSide.equals(that.oneSide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, oneSide);
    }

    @Override
    public String toString() {
        return "ManySideDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", oneSide=" + oneSide +
                '}';
    }
}
