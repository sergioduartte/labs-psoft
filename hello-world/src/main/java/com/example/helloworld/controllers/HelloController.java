package com.example.helloworld.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
public class HelloController {

    static class Saudacao {
        String saudacao;
        String serverTime;
        public Saudacao(String saudacao, String serverTime) {
            this.saudacao = saudacao;
            this.serverTime = serverTime;
        }

        public String getSaudacao() {
            return saudacao;
        }

        public String setNovaSaudacao(String novaSaudacao) {
            this.saudacao = novaSaudacao + "!" ;
            return this.saudacao;
        }

        public String getServerTime() {
            return serverTime;
        }
    }

    static Saudacao saudacao = new Saudacao("Essa galera", getHora());

    @RequestMapping("/v1/iae")
    public ResponseEntity<Saudacao> iae(@RequestParam(name="name", required = false, defaultValue = "Bença") String name, Model model){
        model.addAttribute("name", name);
        String s = defineSaudacao();
        String complemento = name + ", meu bacano!" ;
        String hora = getHora();
        this.saudacao = new Saudacao(s + complemento, hora);

        return new ResponseEntity<Saudacao>(saudacao, HttpStatus.OK);
    }

    @PostMapping("/v1/iaeNovo")
    public ResponseEntity<Saudacao> iaeNovo(@RequestBody Saudacao novaSaudacao){
        System.out.println("A nova saudacao agora é...." + novaSaudacao.getSaudacao());
        saudacao.setNovaSaudacao(novaSaudacao.getSaudacao());
        return new ResponseEntity<Saudacao>(saudacao, HttpStatus.CREATED);

    }

    @GetMapping("/qhoras")
    public String hora_boa(){
        return getHora();
    }

    private static String getHora() {
        String hora = "agora são ";
        hora += (LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss"))) + " aqui no serv camada8";
        return hora;
    }

    private String defineSaudacao() {
        LocalTime hora = LocalTime.now();
        if (ehManha(hora)) {
            return "Bom Dia,";
        } else if (ehTarde(hora)) {
            return "Boa Tarde,";
        } else if (ehNoite(hora)) {
            return "Boa Noite,";
        } else {
            return "Bom apocalipse,";
        }
    }

    private boolean ehManha(LocalTime hora) {
        if (hora.isBefore(LocalTime.NOON) && hora.isAfter(LocalTime.MIDNIGHT.plusHours(6))){
            return true;
        }
        return false;
    }

    private boolean ehTarde(LocalTime hora) {
        if(!ehManha(hora) && !ehNoite(hora)){
            return true;
        }
        return false;
    }
    private boolean ehNoite(LocalTime hora) {
        if(hora.isAfter(LocalTime.MIDNIGHT.minusHours(6))){
            return true;
        }
        return false;
    }

//    private Greet novaSaudacao = new Greet("");
//
//    public Greeting getSaudacao(String nome) {
//        String saudacao = null;
//        LocalTime hora = LocalTime.now();
//
//        if (hora.isBefore(LocalTime.NOON) && hora.isAfter(LocalTime.MIDNIGHT.plusHours(6)))
//            saudacao = "Bom dia";
//        else if (hora.isAfter(LocalTime.MIDNIGHT.minusHours(6)))
//            saudacao = "Boa noite";
//        else
//            saudacao = "Boa tarde";
//        return new Greeting(nome, saudacao);
//    }
//
//
//    public Greet setNovaSaudacao(Greet novaSaudacao2) {
//        this.novaSaudacao = novaSaudacao2;
//        return this.novaSaudacao;
//    }
//
//    public Greeting getNovaSaudacao(String nome) {
//        return new Greeting(novaSaudacao.getGreet(), nome);
//    }

}
