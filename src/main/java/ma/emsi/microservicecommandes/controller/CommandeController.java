package ma.emsi.microservicecommandes.controller;

import ma.emsi.microservicecommandes.domain.Commande;
import ma.emsi.microservicecommandes.service.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Commande create(@RequestBody Commande commande) {
        return commandeService.create(commande);
    }

    // READ ALL
    @GetMapping
    public List<Commande> findAll() {
        return commandeService.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Commande findById(@PathVariable Long id) {
        return commandeService.findById(id);
    }

    @GetMapping("/last")
    public List<Commande> last() {
        return commandeService.findLastCommandes();
    }


    // UPDATE
    @PutMapping("/{id}")
    public Commande update(@PathVariable Long id, @RequestBody Commande commande) {
        return commandeService.update(id, commande);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        commandeService.delete(id);
    }
}
