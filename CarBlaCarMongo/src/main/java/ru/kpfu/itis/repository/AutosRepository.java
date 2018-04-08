package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Automobile;

//CrudRepository
//PagingAndSortingRepository
//JpaRepository

@Repository
public interface AutosRepository extends JpaRepository<Automobile, Long> {
}
