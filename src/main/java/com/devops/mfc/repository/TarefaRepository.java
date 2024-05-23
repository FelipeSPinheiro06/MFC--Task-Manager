package com.devops.mfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devops.mfc.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
}
