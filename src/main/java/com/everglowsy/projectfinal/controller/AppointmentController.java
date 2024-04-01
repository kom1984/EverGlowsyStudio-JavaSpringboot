package com.everglowsy.projectfinal.controller;

import com.everglowsy.projectfinal.model.AppointmentModel;
import com.everglowsy.projectfinal.model.ServiceHandledModel;
import com.everglowsy.projectfinal.service.AppointmentService;
import com.everglowsy.projectfinal.service.CategoryService;
import com.everglowsy.projectfinal.service.ServiceHandledService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private ServiceHandledService serviceHandledService;
    @GetMapping("/appointment1")
    public String rdv()
    {
        return "publicTemplates/appointment1";
    }
    @GetMapping("/appointment1/{id}")
    public String serviceId(@PathVariable Long id, Model model)

    {
        model.addAttribute("categories",categoryService.getAllCategory());
        model.addAttribute("services",serviceHandledService.getAllServices());
        model.addAttribute("service1",serviceHandledService.getServiceById(id));
        List<LocalDate> workingDays = getWorkingDays();
        Map<LocalDate, List<String>> timeSlotsByDate = new LinkedHashMap<>();
        for (LocalDate date : workingDays) {
            List<String> timeSlots = generateTimeSlots();
            timeSlotsByDate.put(date, timeSlots);
        }
        model.addAttribute("workingDays", workingDays);
        model.addAttribute("timeSlotsByDate", timeSlotsByDate);

        return "publicTemplates/appointment1";
    }

    @GetMapping("/admin/Appointment")
    public String adminAppointment( Model model)
    {

        model.addAttribute("appointments",appointmentService.getAllAppointment());
        return "adminTemplates/Appointment/appointmentDashboard";
    }

    @GetMapping("/admin/Appointment/addAppointment")
    public String addAppointment(Model model)
    {
        model.addAttribute("services",new ServiceHandledModel());
        model.addAttribute("appointments",new AppointmentModel());
        return "adminTemplates/Appointment/addAppointment";
    }
    @GetMapping("/saveAppointment")
    public String validerAppointment(Model model)
    {


        model.addAttribute("services",new ServiceHandledModel());
        model.addAttribute("appointments",new AppointmentModel());
        return "adminTemplates/Appointment/addAppointment";
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


    @PostMapping("/saveAppointment")
    public String saveAppointment(@Valid @ModelAttribute("appointments") AppointmentModel appointmentModel) {


        appointmentService.saveAppointment(appointmentModel);
        return "redirect:/admin/Appointment";

    }


    @GetMapping("/adminDashboard/Appointment/editAppointment/{id}")
    public String editAppointment(@PathVariable Long id, Model model)
    {  //(get)trouver les detail de Appointment  pour particular id
        AppointmentModel appointmentModel = appointmentService.getAppointmentById(id);
        // (set) placer Appointment AppointmentModel comme model attribute à pre-populate la form
        model.addAttribute("appointments",appointmentModel);
        return "adminTemplates/Appointment/editAppointment";
    }

    @GetMapping("/admin/Appointment/deleteAppointment/{id}")
    public String deleteAppointment(@PathVariable Long id)
    {
        appointmentService.deleteAppointment(id);
        return "redirect:/admin/Appointment";
    }
}

