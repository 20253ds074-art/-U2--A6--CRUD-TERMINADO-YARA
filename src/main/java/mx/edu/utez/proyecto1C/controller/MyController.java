package mx.edu.utez.proyecto1C.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.dto.RequestBodyDTO;
import mx.edu.utez.proyecto1C.controller.dto.RequestCalculadoraDTO;
import mx.edu.utez.proyecto1C.service.MyService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"}) //todos los origenes de peticiones
@RequestMapping("/my-services")


public class MyController {
    private final MyService service;
    // inyeccion de dependencias
    public MyController(MyService service) {
        this.service = service;
    }


    @GetMapping
    public String miPrimerServicio(){
        return "Hello world";
    }

    @GetMapping("/servicio2")
    public String servicio2(){
        return "segundo servicio";
    }
    @PostMapping
    public String servicip3(){
        return "Este es el servicio 3";
    }

    ///
    @GetMapping("/path/{id}")
    public String pathvariable(@PathVariable String id){
        return "El path variable es " + id;

    }
    //ResponseEntity lo requiere mucho
    @PostMapping("/body")
    public ResponseEntity<RequestBodyDTO> body(@RequestBody @Valid RequestBodyDTO payload){
        System.out.println(payload.getEdad());
        System.out.println(payload.getNombre());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(payload);
    }
    @GetMapping("/fizzbuzz/{n}")
    public String fizzbuzz(@PathVariable int n) {
        for (int i = 1; i <= n; i++) {
            int multiploTres = i % 3;
            int multiploCinco = i % 5;
            if (multiploTres == 0 && multiploCinco == 0) {
                System.out.println("FizzBuzz");
            } else if (multiploTres == 0) {
                System.out.println("Fizz");
            } else if (multiploCinco == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);

            }

        }

        return "Yara Ayme Pacheco Mendoza";
    }

    @GetMapping("/fibonacci/{n}")
    public String fibonacci(@PathVariable int n){
        int a=0;
        int b=1;
        for (int i = 1; i <= n; i++) {
            if (i==1){
                System.out.println(a);
            } else if (i==2) {
                System.out.println(b);
            }
            else {
                int siguiente=a+b;
                System.out.println(siguiente);
                a=b;
                b=siguiente;
            }


        }
        return "Yara Ayme Pacheco Mendoza";
    }

    @PostMapping("/calculadora")
    public Double calculadora(@RequestBody @Valid RequestCalculadoraDTO payload) throws BadRequestException {
        return service.calculadora(payload);
    }






}
