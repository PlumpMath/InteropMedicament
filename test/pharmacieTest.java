
import bureau.Fournisseur;
import bureau.Medicament;
import bureau.Pharmacie;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    Fournisseur four = new Fournisseur("Pierre Fabre");
    Medicament med = new Medicament("nom", "molecule", 20);
    Medicament med2 = new Medicament("Boura", "Alex", 200);
    Pharmacie pharma = new Pharmacie("pharma");
    int quantite = 100;

    @Test
    public void testAcheterMedicament() throws Exception {
        //initialisation
        four.ajouterMedicament(med, quantite);
        quantite = 200;
        four.ajouterMedicament(med2, quantite);
        assert (pharma.getStock().isEmpty());
        //création du médicament dans le stock et calcul des stocks
        pharma.acheterMedicament(med, 50, four);
        assert (pharma.getStock().size() == 1);
        assert (pharma.getStock().get(0).getMed().equals(med));
        assert (pharma.getStock().get(0).getQuantite() == 50);
        assert (four.getProposition().get(0).getQuantite() == 50);
        //mise à jour des stocks
        pharma.acheterMedicament(med, 20, four);
        assert (pharma.getStock().size() == 1);
        assert (pharma.getStock().get(0).getQuantite() == 70);
        assert (four.getProposition().get(0).getQuantite() == 30);
        //achat impossible, il doit lever une exception

        //achat d'un deuxième médicament
        pharma.acheterMedicament(med2, 100, four);
        assert (pharma.getStock().size() == 2);
        assert (pharma.getStock().get(1).getMed().equals(med2));
        assert (pharma.getStock().get(1).getQuantite() == 100);
        assert (four.getProposition().get(1).getQuantite() == 100);

    }

    @Test
    public void testException() throws Exception {
        four.ajouterMedicament(med, quantite);
        exception.expect(Exception.class);
        pharma.acheterMedicament(med, 200, four);
    }
}
