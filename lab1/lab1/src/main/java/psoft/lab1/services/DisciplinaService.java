package psoft.lab1.services;

import org.springframework.stereotype.Service;
import psoft.lab1.entities.Disciplina;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DisciplinaService {

    private final AtomicLong cont_id = new AtomicLong();
    private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();


    public Disciplina addDisciplina(String nome, double nota ) {
        Disciplina novaDisc = new Disciplina(cont_id.incrementAndGet(), nome, nota);
        disciplinas.add(novaDisc);
        return novaDisc;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    public Disciplina getDisciplinaById(long id) throws Exception {
        for(Disciplina d: disciplinas){
            if (d.getId() == id) {
                return d;
            }
        }
        throw new Exception("not Found");
    }

    public Disciplina setNomeDisciplina(long id, String novoNome) throws Exception {
        for(Disciplina d: disciplinas){
            if (d.getId() == id) {
                d.setNome(novoNome);
                return d;
            }
        }
        throw new Exception("not Found");
    }

    public Disciplina setNotaDisciplina(long id, double novaNota) throws Exception {
        for(Disciplina d: disciplinas){
            if (d.getId() == id) {
                d.setNota(novaNota);
                return d;
            }
        }
        throw new Exception("not Found");
    }


    public Disciplina deleteDisciplina(long id) throws Exception {
        for(Disciplina d: disciplinas){
            if (d.getId() == id) {
                Disciplina removida = d;
                disciplinas.remove(d);
                return removida;
            }
        }
        throw new Exception("not Found");
    }

    public ArrayList<Disciplina> getDisciplinasOrdenadas() {
        Collections.sort(disciplinas);
        return disciplinas;
    }
}
