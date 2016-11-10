
import bureau.Fournisseur;
import bureau.Medicament;
import bureau.Pharmacie;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mgros
 */
public class pharmacieTest {

    @Test
    public void testAcheterMedicament() {
        Fournisseur four = new Fournisseur(1, "Pierre Fabre");
        Medicament med = new Medicament( "nom", "molecule", 20);
        Pharmacie pharma = new Pharmacie(1, "pharma");
        int quantite = 100;
        four.ajouterMedicament(med, quantite);
        assert (pharma.getStock().isEmpty());
        pharma.acheterMedicament(med, 50, four);
        assert (pharma.getStock().size() == 1);
        assert (pharma.getStock().get(0).getMed().equals(med));
        assert (pharma.getStock().get(0).getQuantite() == 50);
        assert (four.getProposition().get(0).getQuantite() == 50);
        pharma.acheterMedicament(med, 20, four);
        assert (pharma.getStock().size() == 1);
        assert (pharma.getStock().get(0).getQuantite() == 70);
        assert (four.getProposition().get(0).getQuantite() == 30);
        
        pharma.acheterMedicament(med, 200, four);
        assert (pharma.getStock().get(0).getQuantite() == 70);
        assert (four.getProposition().get(0).getQuantite() == 30);

        Medicament med2 = new Medicament( "Boura", "Alex", 200);
        quantite = 200;
        four.ajouterMedicament(med2, quantite);
        pharma.acheterMedicament(med2, 100, four);
        assert (pharma.getStock().size() == 2);
        assert (pharma.getStock().get(1).getMed().equals(med2));
        assert (pharma.getStock().get(1).getQuantite() == 100);
        assert (four.getProposition().get(1).getQuantite() == 100);

    }
}
