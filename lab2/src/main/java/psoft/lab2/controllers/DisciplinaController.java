package psoft.lab2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psoft.lab2.entities.Disciplina;
import psoft.lab2.services.DisciplinaService;


import java.util.ArrayList;


@RestController
public class DisciplinaController {

    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping("/v1/api/disciplinas")
    public ResponseEntity<Disciplina> addDisciplina(@RequestBody Disciplina disciplina) {
        return new ResponseEntity<Disciplina>(disciplinaService.addDisciplina(disciplina.getNome(), disciplina.getNota()), HttpStatus.CREATED);
    }

    @RequestMapping("/v1/api/disciplinas")
    public ResponseEntity<ArrayList<Disciplina>> getdisciplinas() {
        return new ResponseEntity<ArrayList<Disciplina>>(disciplinaService.getDisciplinas(), HttpStatus.OK);
    }

    @RequestMapping("/v1/api/disciplinas/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable("id") long id){
        try {
            ResponseEntity<Disciplina> response = new ResponseEntity<Disciplina>(disciplinaService.getDisciplinaById(id), HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/v1/api/disciplinas/{id}/nome")
    public ResponseEntity<Disciplina> setNomeDisciplina(@PathVariable("id") long id, @RequestBody String novoNome) {
        try {
            ResponseEntity<Disciplina> response = new ResponseEntity<Disciplina>(disciplinaService.setNomeDisciplina(id, novoNome), HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/v1/api/disciplinas/{id}/nota")
    public ResponseEntity<Disciplina> setNotaDisciplina(@PathVariable("id") long id, @RequestBody String novaNota) {
        try {
            Double d = Double.parseDouble(novaNota);
            ResponseEntity<Disciplina> response = new ResponseEntity<Disciplina>(disciplinaService.setNotaDisciplina(id, d), HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/v1/api/disciplinas/{id}")
    public ResponseEntity<Disciplina> deleteDisciplina(@PathVariable("id") long id) {
        try {
            ResponseEntity<Disciplina> response = new ResponseEntity<Disciplina>(disciplinaService.deleteDisciplina(id), HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/v1/api/disciplinas/ranking")
    public ResponseEntity<ArrayList<Disciplina>> getdisciplinasOrdenadas() {
        return new ResponseEntity<ArrayList<Disciplina>>(disciplinaService.getDisciplinasOrdenadas(), HttpStatus.OK);
    }




}
