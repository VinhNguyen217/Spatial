package com.java.spatial;

import com.vividsolutions.jts.geom.Polygon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "spatial")
public class Spatial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "geometry(Polygon,4326)")
    private Polygon geom;

    private String name;

    private String area;
}
