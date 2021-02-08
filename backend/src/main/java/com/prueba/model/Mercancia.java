package com.prueba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prueba.security.util.SecurityUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "mercancia", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "nombreProducto"
        })
})
public class Mercancia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 80)
    private String nombreProducto;

    @NotNull
    private Integer cantidad;

    @NotNull
    private LocalDate fechaIngreso;

    private LocalDate fechaCreacion;

    private LocalDate fechaModificacion;

    private String modificadoPor;

    private String creadoPor;



    @PreUpdate
    private void update(){

        if (SecurityUtils.getCurrentUserLogin().isPresent()){
            this.modificadoPor = SecurityUtils.getCurrentUserLogin().get();
            this.fechaModificacion = LocalDate.now();
        }

    }

    @PrePersist
    private void crear(){

        if (SecurityUtils.getCurrentUserLogin().isPresent()){
            this.creadoPor = SecurityUtils.getCurrentUserLogin().get();
            this.fechaCreacion = LocalDate.now();
            this.modificadoPor = SecurityUtils.getCurrentUserLogin().get();
            this.fechaModificacion = LocalDate.now();
        }

    }

    public Mercancia() {
    }

    public Mercancia(Long id) {
        this.id = id;
    }


    public Mercancia(@NotBlank @Size(min = 3, max = 80) String nombreProducto, @NotNull Integer cantidad, @NotNull LocalDate fechaIngreso) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.fechaIngreso = fechaIngreso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    


    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }


    public String getModificadoPor() {
        return modificadoPor;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }
}
