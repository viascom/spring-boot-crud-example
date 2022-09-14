package io.viascom.springbootcrudexample.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class GameEntity {
    private @Id @GeneratedValue Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GameEntity gameEntity = (GameEntity) o;
        return id != null && Objects.equals(id, gameEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
