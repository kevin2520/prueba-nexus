package com.prueba.service.dto;

import com.prueba.model.Mercancia;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class MercanciaDTO {


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

    public MercanciaDTO() {
    }

    public MercanciaDTO(Mercancia mercancia) {
        this.id = mercancia.getId();
        this.cantidad= mercancia.getCantidad();
        this.nombreProducto = mercancia.getNombreProducto();
        this.fechaIngreso = mercancia.getFechaIngreso();
        this.creadoPor = mercancia.getCreadoPor();
        this.fechaCreacion = mercancia.getFechaCreacion();
        this.modificadoPor = mercancia.getModificadoPor();
        this.fechaModificacion = mercancia.getFechaModificacion();
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
}
