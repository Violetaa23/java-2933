package com.uberits.uberitspring.controladores;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uberits.uberitspring.dto.CrearRestauranteDto;
import com.uberits.uberitspring.dto.RestauranteForm;
import com.uberits.uberitspring.servicios.RestauranteService;

@Controller
public class RestauranteMvcController {

    private final RestauranteService restauranteService;

    public RestauranteMvcController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping("/restaurantes")
    public String mostrarRestaurantes(Model model) {
        model.addAttribute("restaurantes", restauranteService.listar());
        return "restaurantes";
    }

    @GetMapping("/restaurantes/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("restauranteForm", new RestauranteForm());
        return "restaurante-form";
    }

    @PostMapping("/restaurantes")
    public String crearRestaurante(
            @Valid @ModelAttribute("restauranteForm") RestauranteForm formulario,
            BindingResult resultado,
            RedirectAttributes redirectAttributes) {

        if (resultado.hasErrors()) {
            return "restaurante-form";
        }

        restauranteService.crear(
            new CrearRestauranteDto(formulario.getNombre().trim())
        );

        redirectAttributes.addFlashAttribute(
            "mensaje", "Restaurante creado correctamente"
        );

        return "redirect:/restaurantes";
    }
}
