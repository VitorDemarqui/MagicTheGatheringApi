package com.zappts.magic_the_gathering_API.repository;

import com.zappts.magic_the_gathering_API.entity.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<Lista, Integer> {

}