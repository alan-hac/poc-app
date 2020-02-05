package br.com.falconer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.falconer.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{ }
