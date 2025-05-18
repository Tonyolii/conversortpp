package convertert.conversortapp.controller;

import convertert.conversortapp.dto.RescisaoRequest;
import convertert.conversortapp.dto.RescisaoResponse;
import convertert.conversortapp.service.TrabalhistaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rescisao")
public class TrabalhistaController {

    @PostMapping
    public RescisaoResponse calcularRescisao(@RequestBody RescisaoRequest req) {
        return TrabalhistaService.calcularRescisao(req);
    }
}
