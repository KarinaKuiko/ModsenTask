package org.example.modsentask.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.modsentask.repository.LibraryRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
public class LibraryScheduler {
    private final LibraryRepository libraryRepository;

    @Scheduled(fixedDelayString = "PT${app.library-scheduler.interval}")
    @Transactional
    public void updateStatuses() {
        int countBooks = libraryRepository.updateOverduedBooks(Instant.now());
        log.trace("Updated statuses of %d books".formatted(countBooks));
    }
}
