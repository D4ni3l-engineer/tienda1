package com.web.tienda.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProveedorRequestDTO {

    private Integer idProveedor;

    @NotBlank(message = "La razón social no puede estar vacía")
    @Size(max = 50)
    private String razonSocial;

    @Size(max = 100)
    private String direccion;

    @Size(max = 100)
    private String pagWeb;

    @Size(max = 15)
    private String telefono;

    @Email(message = "Correo inválido")
    @Size(max = 100)
    private String correo;

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPagWeb() {
        return pagWeb;
    }

    public void setPagWeb(String pagWeb) {
        this.pagWeb = pagWeb;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}

