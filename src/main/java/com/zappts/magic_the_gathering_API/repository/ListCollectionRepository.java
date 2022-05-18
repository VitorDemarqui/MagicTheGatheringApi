package com.zappts.magic_the_gathering_API.repository;

import com.zappts.magic_the_gathering_API.entity.Carta;
import com.zappts.magic_the_gathering_API.entity.ListCollection;
import com.zappts.magic_the_gathering_API.entity.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListCollectionRepository extends JpaRepository<ListCollection, Integer> {
    ListCollection findByListAndCarta(Lista lista, Carta carta);

    List<ListCollection> findAllByList(Lista lista);

    @Query(
            value = "SELECT list_collection.* FROM list_collection " +
                    "LEFT JOIN carta on (list_collection.id_carta = carta.id) " +
                    "WHERE list_collection.id_List = :idList ORDER BY carta.value",
            nativeQuery = true
    )
    List<ListCollection> findAllByListOrderByCardValue(Integer idList);

    @Query(
            value = "SELECT list_collection.* FROM list_collection " +
                    "LEFT JOIN carta on (list_collection.id_carta = carta.id) " +
                    "WHERE list_collection.id_List = :idList ORDER BY carta.name",
            nativeQuery = true
    )
    List<ListCollection> findAllByListOrderByName(Integer idList);
}