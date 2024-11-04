package ch.wiss.m295.lb_projekt.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.annotation.Nonnull;

@Entity
@Table(name = "team")
@Validated
public class Team {

    // Attribute
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Das Team muss einen Namen haben.")
    @Size(min = 2, max = 20, message = "mindestens 2, max 20 Zeichen")
    private String name;

    @NotEmpty(message = "Das Team muss einen Austragungsort haben.")
    private String austragungsort;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "liga_id", nullable = false)
    @Nonnull
    private Liga liga;

    @JsonManagedReference
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Spieler> spieler;

    // Konstruktor für "OneToMany Beziehung"
    public Team() {
        this.spieler = new ArrayList<>();
    }

    // Getter und Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAustragungsort() {
        return austragungsort;
    }

    public void setAustragungsort(String austragungsort) {
        this.austragungsort = austragungsort;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public List<Spieler> getSpieler() {
        return spieler;
    }

    public void setSpieler(List<Spieler> spieler) {
        this.spieler = spieler;
    }

}
