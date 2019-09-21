package com.hqinjun.myboot.repository;

import com.hqinjun.myboot.domain.Suser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SuserRepository extends JpaRepository<Suser,Integer> {
    public Suser findSuserByUsername( String name);
}
