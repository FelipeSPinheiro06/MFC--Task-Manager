package com.devops.mfc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devops.mfc.model.Tarefa;
import com.devops.mfc.repository.TarefaRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/tarefas")
@Slf4j
public class TarefaController {
    
    @Autowired
    TarefaRepository tarefaRepository;

    @GetMapping
    public List<Tarefa> getMethod() {
        log.info("Pegando todos os registros");
        return tarefaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Tarefa postMethod(@RequestBody Tarefa t) {
        log.info("Postando um registro no banco");
        return tarefaRepository.save(t);
    }

    @PutMapping(path = "/{id}")
    public Tarefa putMethod(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        log.info("Atualizando um registro no banco");
        checkExistence(id);
        tarefa.setId(id);
        return tarefaRepository.save(tarefa);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteMethod(@PathVariable Long id) {
        log.info("Excluindo um registro no banco");
        tarefaRepository.deleteById(id);
    }

    public Tarefa checkExistence(Long id) {
        return tarefaRepository.findById(id)
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe Tarefa com este id"));
    }
}
