package br.com.clone.unionmangas.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "scan")
public class Scan {

    @Id
    @Column(name = "id_scan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScan;

    @Column(name = "name")
    private String name;

    @Column(name = "website")
    private String website;

    @Column(name = "number_of_affiliates")
    private Integer numberOfAffiliates;

    @Column(name = "number_of_shipments")
    private Integer numberOfShipments;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fk_scans_categories_category", joinColumns = @JoinColumn(name = "category"), inverseJoinColumns = @JoinColumn(name = "scan"))
    private Set<Category> categories;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "fk_scans_users_scan", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "scan"))
    private Set<Scan> scans;

    public Scan() { }

    public Scan(String name, String website, Integer numberOfAffiliates, Integer numberOfShipments,
            LocalDate lastUpdate) {
        this.name = name;
        this.website = website;
        this.numberOfAffiliates = numberOfAffiliates;
        this.numberOfShipments = numberOfShipments;
        this.lastUpdate = lastUpdate;
    }

    public Long getIdScan() {
        return idScan;
    }

    public void setIdScan(Long idScan) {
        this.idScan = idScan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getNumberOfAffiliates() {
        return numberOfAffiliates;
    }

    public void setNumberOfAffiliates(Integer numberOfAffiliates) {
        this.numberOfAffiliates = numberOfAffiliates;
    }

    public Integer getNumberOfShipments() {
        return numberOfShipments;
    }

    public void setNumberOfShipments(Integer numberOfShipments) {
        this.numberOfShipments = numberOfShipments;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

}
