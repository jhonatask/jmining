package br.com.jproject.cotacao.scheduler;

import br.com.jproject.cotacao.service.QuotationService;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class QuotationScheduler {

    @Inject
    QuotationService quotationService;

    @Transactional
    @Scheduled(every = "35s", identity = "task-job")
    void scheduleQuotation() {
        quotationService.getCurrentPrice();
    }
}
