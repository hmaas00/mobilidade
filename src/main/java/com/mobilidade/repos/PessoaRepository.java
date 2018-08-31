package com.mobilidade.repos;

import com.mobilidade.entidade.Pessoa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

}
