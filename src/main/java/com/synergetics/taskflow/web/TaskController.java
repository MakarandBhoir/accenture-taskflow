package com.synergetics.taskflow.web;

import com.synergetics.taskflow.model.Priority;
import com.synergetics.taskflow.model.Task;
import com.synergetics.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tasks", service.findAll());
        model.addAttribute("total", service.count());
        model.addAttribute("done", service.countCompleted());
        return "tasks/list";
    }

    @GetMapping("tasks/new")
    public String newForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("priorities", Priority.values());
        return "tasks/form";
    }

    @PostMapping("tasks")
    public String create(@Valid @ModelAttribute("task") Task task,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("priorities", Priority.values());
            return "tasks/form";
        }
        service.create(task);
        return "redirect:/";
    }

    @PostMapping("tasks/{id}/toggle")
    public String toggle(@PathVariable Long id) {
        service.toggle(id);
        return "redirect:/";
    }

    @PostMapping("tasks/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
