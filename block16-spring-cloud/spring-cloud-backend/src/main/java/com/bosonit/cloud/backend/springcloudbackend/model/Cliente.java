package com.bosonit.cloud.backend.springcloudbackend.model;


import com.bosonit.cloud.backend.springcloudbackend.model.dto.ClienteInputDto;
import com.bosonit.cloud.backend.springcloudbackend.model.dto.ClienteOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private int id;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "edad")
    private int edad;

    @Column(name = "email")
    private String email;
    @Column(name = "telefono")
    private int telefono;

    @ManyToOne()
    @JoinColumn(name = "viaje_id")
    private Viaje viaje;


    public Cliente (ClienteInputDto clienteInputDto){
        this.id = clienteInputDto.getId();
        this.nombre = clienteInputDto.getNombre();
        this.apellido = clienteInputDto.getApellido();
        this.edad = clienteInputDto.getEdad();
        this.email = clienteInputDto.getEmail();
        this.telefono = clienteInputDto.getTelefono();
    }

    public ClienteOutputDto clienteInputDtoToOutputDto(){
        return new ClienteOutputDto(
                this.id,
                this.nombre,
                this.apellido,
                this.edad,
                this.email,
                this.telefono
        );
    }

}
