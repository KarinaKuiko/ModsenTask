package org.example.modsentask.repository;

import org.example.modsentask.entity.Library;
import org.example.modsentask.entity.enumeration.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    Optional<Library> findByBookId(Long id);
    List<Library> findByStatus(BookStatus bookStatus);

    @Modifying
    @Query(
            value = """
                    UPDATE Library l
                    SET l.status = 'OVERDUE'
                    WHERE l.needReturn < ?1
                    """
    )
    int updateOverduedBooks(Instant time);
}
