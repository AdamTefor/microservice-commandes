package ma.emsi.microservicecommandes.service;

import ma.emsi.microservicecommandes.domain.Commande;
import ma.emsi.microservicecommandes.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.time.LocalDate;
import java.util.List;

@RefreshScope
@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    @Value("${mes-config-ms.commandes-last:10}")
    private int commandesLast;

    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> findLastCommandes() {
        LocalDate minDate = LocalDate.now().minusDays(commandesLast);
        return commandeRepository.findByDateAfter(minDate);
    }

    public Commande create(Commande commande) {
        return commandeRepository.save(commande);
    }

    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    public Commande findById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec id = " + id));
    }

    public Commande update(Long id, Commande newData) {
        Commande existing = findById(id);
        existing.setDescription(newData.getDescription());
        existing.setQuantite(newData.getQuantite());
        existing.setDate(newData.getDate());
        existing.setMontant(newData.getMontant());
        return commandeRepository.save(existing);
    }

    public void delete(Long id) {
        if (!commandeRepository.existsById(id)) {
            throw new RuntimeException("Commande introuvable avec id = " + id);
        }
        commandeRepository.deleteById(id);
    }
}
