package com.example.gameloft.repo;

import com.example.gameloft.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account,Integer> {
    @NonNull
    Optional<Account> findById(@NonNull Integer id);
}
