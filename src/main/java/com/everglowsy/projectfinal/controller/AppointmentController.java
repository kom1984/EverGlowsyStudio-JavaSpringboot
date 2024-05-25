package com.everglowsy.projectfinal.controller;

import com.everglowsy.projectfinal.model.AppointmentModel;
import com.everglowsy.projectfinal.model.ServiceHandledModel;
import com.everglowsy.projectfinal.model.UserModel;
import com.everglowsy.projectfinal.service.AppointmentService;
import com.everglowsy.projectfinal.service.CategoryService;
import com.everglowsy.projectfinal.service.ServiceHandledService;
import com.everglowsy.projectfinal.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppointmentController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ServiceHandledService serviceHandledService;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @GetMapping("/bookService/{id}")
    public String serviceId(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "EverGlowsyStudio-PageRDV");
        // Retrieve current user
        UserModel currentUser = userService.getCurrentUser();

        // Get service by ID
        ServiceHandledModel service = serviceHandledService.getServiceById(id);

        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setServiceHandledModel(service); // Set the service
        appointmentModel.setUserModel(currentUser); // Set the current user

        // Add attributes to the model
        model.addAttribute("service", service);
        model.addAttribute("user", currentUser);
        model.addAttribute("appointmentModel", appointmentModel);


        System.out.println("User ID: " + currentUser.getId_user());
        System.out.println("User Username: " + currentUser.getFirstName());
        System.out.println("User Email: " + currentUser.getEmail());
        return "publicTemplates/bookService";
    }



    @PostMapping("/saveAppointment")
    public String saveAppointment(@ModelAttribute AppointmentModel appointmentModel, Model model,BindingResult result,
        RedirectAttributes redirectAttributes) {

            if (result.hasErrors()) {


                System.out.println("Service ID: " + appointmentModel.getServiceHandledModel().getId_service());
                System.out.println("User ID: " + appointmentModel.getUserModel().getId_user());
                System.out.println("Appointment Model: " + appointmentModel);
                return "publicTemplates/bookService";
            }
            appointmentModel.setStatus_rdv("Pending");
            System.out.println("Status: " + appointmentModel.getStatus_rdv());
        // Save the appointment

        AppointmentModel savedAppointment = appointmentService.saveAppointment(appointmentModel);

        // Add success message and redirect to appointment details

        redirectAttributes.addFlashAttribute("successMessage", "Votre réservation a réussi! Vous recevrez un e-mail indiquant le statut de votre réservation.");
        return "redirect:/appointmentDetails/" + savedAppointment.getId_appointment();
    }




    @GetMapping("/appointmentDetails/{id}")
    public String appointmentDetails(@PathVariable Long id, Model model) {
        model.addAttribute("pageTitle", "DétailsRDV- Everglowsy Studio");
        AppointmentModel appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("services",serviceHandledService.getAllServices());

        model.addAttribute("appointment", appointment);
        return "publicTemplates/appointmentDetails";
    }

    private List<LocalDate> getWorkingDays() {
        List<LocalDate> workingDays = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        int count = 0;
        while (count < 5) {
            if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDays.add(currentDate);
                count++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return workingDays;
    }

    private List<String> generateTimeSlots() {
        List<String> timeSlots = new ArrayList<>();
        LocalTime startTime1 = LocalTime.of(10, 0); // Start time at 10 am
        LocalTime endTime1 = LocalTime.of(13, 0); // End time at 1 pm
        LocalTime startTime2 = LocalTime.of(14, 0); // Start time at 2 pm
        LocalTime endTime2 = LocalTime.of(19, 0); // End time at 7 pm

        generateTimeSlotsForPeriod(startTime1, endTime1, timeSlots);
        generateTimeSlotsForPeriod(startTime2, endTime2, timeSlots);

        return timeSlots;
    }

    private void generateTimeSlotsForPeriod(LocalTime startTime, LocalTime endTime, List<String> timeSlots) {
        while (startTime.isBefore(endTime)) {
            timeSlots.add(startTime.format(DateTimeFormatter.ofPattern("hh:mm a")));
            startTime = startTime.plusMinutes(15); // Increment by 15 minutes
        }
    }

    @GetMapping("/admin/Appointments")
    public String adminAppointment(Model model) {
        List<AppointmentModel> appointments = appointmentService.getAllAppointment();
        model.addAttribute("appointments", appointments);
        return "adminTemplates/Appointment/appointmentDashboard";
    }

    @GetMapping("/admin/Appointments/addAppointment")
    public String addAppointment(Model model) {
        model.addAttribute("services", serviceHandledService.getAllServices());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("appointments", new AppointmentModel());
        return "adminTemplates/Appointment/addAppointment";
    }
    @GetMapping("/saveAppointments")
    public String validerAppointment(Model model)
    {

        model.addAttribute("services",serviceHandledService.getAllServices());
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("appointments",new AppointmentModel());
        return "adminTemplates/Appointment/addAppointment";
    }
    @PostMapping("/saveAdminAppointment")
    public String saveAppointment(@Valid @ModelAttribute("appointments") AppointmentModel appointmentModel, Model model) {
        appointmentService.saveAppointment(appointmentModel);
        return "redirect:/admin/Appointments";
    }

    @GetMapping("/admin/Appointments/editAppointment/{id}")
    public String editAppointment(@PathVariable Long id, Model model) {
        AppointmentModel appointmentModel = appointmentService.getAppointmentById(id);
        model.addAttribute("appointments", appointmentModel);
        model.addAttribute("services", serviceHandledService.getAllServices());
        model.addAttribute("users", userService.getAllUsers());
        return "adminTemplates/Appointment/addAppointment";
    }

    @GetMapping("/admin/Appointments/deleteAppointment/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/admin/Appointments";
    }
}

