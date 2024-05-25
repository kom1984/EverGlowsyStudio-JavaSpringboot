package com.everglowsy.projectfinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
@Data
@Entity
@Table(name="Appointment")
public class AppointmentModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID_APPOINTMENT")
        private Long id_appointment;

        @Column(name = "DATE_APPOINTMENT")
        @DateTimeFormat(pattern =  "yyyy-MM-dd")
        private Date date_appointment;

        @NotEmpty
        @Column(name = "TIME_APPOINTMENT")
        private String time_appointment;



        @Column(name = "STATUS_RDV")
        private String status_rdv;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "id_service", referencedColumnName = "id_service")
        private ServiceHandledModel serviceHandledModel;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "id_user", referencedColumnName = "id_user")
        private UserModel userModel;

        public AppointmentModel() {

        }
        // Constructor with arguments
        public AppointmentModel(Long id_appointment, Date date_appointment, String time_appointment,  String status_rdv, ServiceHandledModel serviceHandledModel, UserModel userModel) {
                this.id_appointment = id_appointment;
                this.date_appointment = date_appointment;
                this.time_appointment = time_appointment;
                this.status_rdv = status_rdv;
                this.serviceHandledModel = serviceHandledModel;
                this.userModel = userModel;
        }
        public Long getId_appointment() {
                return id_appointment;
        }

        public void setId_appointment(Long id_appointment) {
                this.id_appointment = id_appointment;
        }

        public Date getDate_appointment() {
                return date_appointment;
        }

        public void setDate_appointment(Date date_appointment) {
                this.date_appointment = date_appointment;
        }

        public String getTime_appointment() {
                return time_appointment;
        }

        public void setTime_appointment(String time_appointment) {
                this.time_appointment = time_appointment;
        }



        public String getStatus_rdv() {
                return status_rdv;
        }

        public void setStatus_rdv(String status_rdv) {
                this.status_rdv = status_rdv;
        }

        public ServiceHandledModel getServiceHandledModel() {
                return serviceHandledModel;
        }

        public void setServiceHandledModel(ServiceHandledModel serviceHandledModel) {
                this.serviceHandledModel = serviceHandledModel;
        }

        public UserModel getUserModel() {
                return userModel;
        }

        public void setUserModel(UserModel userModel) {
                this.userModel = userModel;
        }
}
