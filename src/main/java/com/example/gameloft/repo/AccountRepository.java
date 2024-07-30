package com.example.gameloft.repo;

import com.example.gameloft.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account,Integer> {
    Optional<Account> findById(Integer id);
}
