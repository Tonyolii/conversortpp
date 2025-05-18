package convertert.conversortapp.controller;

import convertert.conversortapp.service.TemperaturaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/temperatura")

public class TemperaturaController {
    @GetMapping("/converter")
    public double converter(
            @RequestParam String de,
            @RequestParam String para,
            @RequestParam double valor){

        return TemperaturaService.converter(de, para, valor);
    }

}
