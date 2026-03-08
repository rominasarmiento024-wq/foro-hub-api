package com.romina.forohub;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TopicoController {

    private final TopicoRepository repository;

    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/topicos")
    public List<Topico> listarTopicos() {
        return repository.findAll();
    }

    @PostMapping("/topicos")
    public Topico crearTopico(@RequestBody Topico topico) {
        topico.setFechaCreacion(LocalDateTime.now());
        return repository.save(topico);
    }

    @GetMapping("/topicos/{id}")
    public Topico obtenerTopicoPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/topicos/{id}")
    public Topico actualizarTopico(@PathVariable Long id, @RequestBody Topico datos) {
        Topico topico = repository.findById(id).orElse(null);

        if (topico != null) {
            topico.setTitulo(datos.getTitulo());
            topico.setMensaje(datos.getMensaje());
            topico.setStatus(datos.getStatus());
            topico.setAutor(datos.getAutor());
            topico.setCurso(datos.getCurso());

            return repository.save(topico);
        }

        return null;
    }

    @DeleteMapping("/topicos/{id}")
    public String eliminarTopico(@PathVariable Long id) {
        repository.deleteById(id);
        return "Topico eliminado";
    }
}