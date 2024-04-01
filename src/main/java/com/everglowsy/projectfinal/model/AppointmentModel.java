package com.everglowsy.projectfinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name="Appointment")
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_APPOINTMENT")
    private Long id_appointment;

    @Column(name="Date_APPOINTMENT")
    private Date date_appointment;

    @Column(name="Time_APPOINTMENT")
    private String Time_Appointment;

    @Column(name="STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name="id_service",referencedColumnName = "id_service",insertable = true, updatable = true)
    private ServiceHandledModel serviceHandledModel;

}
