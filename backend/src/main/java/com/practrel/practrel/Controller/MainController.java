package com.practrel.practrel.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.practrel.practrel.Entity.Biblioteca;
import com.practrel.practrel.Entity.Libro;
import com.practrel.practrel.Repository.BibliotecaRepository;
import com.practrel.practrel.Repository.LibroRepository;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {
    
    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private LibroRepository libroRepository;

    @PostMapping("/biblioteca")
    public ResponseEntity<?> altaBiblioteca(@RequestBody Biblioteca biblioteca){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bibliotecaRepository.save(biblioteca));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
        }
    }

    @PostMapping("/libro")
    public ResponseEntity<?> altaLibro(@RequestBody Libro libro){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(libroRepository.save(libro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
        }
    }

    @PutMapping("/biblioteca")
    public ResponseEntity<?> modificacionBiblioteca(@RequestBody Biblioteca biblioteca){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bibliotecaRepository.save(biblioteca));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
        }
    }

    @PutMapping("/libro")
    public ResponseEntity<?> modificacionLibro(@RequestBody Libro libro){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(libroRepository.save(libro));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
        }
    }

    @DeleteMapping("/biblioteca/{id}")
    public ResponseEntity<?> eliminacionBiblioteca(@PathVariable int id){
        try {
            bibliotecaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminado");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
        }
    }

    @DeleteMapping("/libro/{codLibro}")
    public ResponseEntity<?> eliminacionLibro(@PathVariable int codLibro){
        try {
            libroRepository.deleteById(codLibro);
            return ResponseEntity.status(HttpStatus.OK).body("Eliminado");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
        }
    }

    @GetMapping("/biblioteca/{id}")
    public ResponseEntity<?> conseguirBiblioteca(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bibliotecaRepository.findById(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
        }
    }

    @GetMapping("/libro/{codLibro}")
    public ResponseEntity<?> conseguirLibro(@PathVariable int codLibro){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(libroRepository.findById(codLibro));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
        }
    }

    @GetMapping("/biblioteca")
    public ResponseEntity<?> conseguirBibliotecas(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(bibliotecaRepository.findAll());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
        }
    }

    @GetMapping("/libro")
    public ResponseEntity<?> conseguirLibros(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(libroRepository.findAll());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
        }
    }

    @PutMapping("/{codLibro}/asignarBiblioteca/{idBiblioteca}")
    public ResponseEntity<?> asignarBiblioteca(@PathVariable int codLibro, @PathVariable int idBiblioteca){
    try {
        Biblioteca biblioteca;
        Libro libro;
        if(bibliotecaRepository.existsById(idBiblioteca))
            biblioteca = bibliotecaRepository.findById(idBiblioteca).get();
        else
            throw new Exception();
        if(libroRepository.existsById(codLibro))
            libro = libroRepository.findById(codLibro).get();
        else
            throw new Exception();
        
        libro.setBiblioteca(biblioteca);
        libroRepository.save(libro);
        return ResponseEntity.status(HttpStatus.OK).body("Biblioteca asignada");
    }
    catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema interno en el servidor");
    }
    }
}
