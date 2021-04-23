/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.compcrud;

/**
 *
 * @author Maya
 */
public class competition {
    private Integer id;
     private String nom;
      private String description;
      private String categorie ;
       private Integer nombredeplace;
       private String image;
       private String adresse;
       private Integer date ;

    public competition(Integer id, String nom, String description, String categorie, Integer nombredeplace, String image, String adresse, Integer date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.nombredeplace = nombredeplace;
        this.image = image;
        this.adresse = adresse;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getNombredeplace() {
        return nombredeplace;
    }

    public void setNombredeplace(Integer nombredeplace) {
        this.nombredeplace = nombredeplace;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "competition{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", categorie=" + categorie + ", nombredeplace=" + nombredeplace + ", image=" + image + ", adresse=" + adresse + ", date=" + date + '}';
    }

}
