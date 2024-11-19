package org.example.graduatemanage.repostory;


import org.example.graduatemanage.dox.ProcessScore;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoresRepository extends CrudRepository<ProcessScore,String> {

    @Query("""
    SELECT * FROM my_process_score
    where  teacher_id=:tid;
    """)
    public List<ProcessScore> findStudentsScoresById(String tid);
}
