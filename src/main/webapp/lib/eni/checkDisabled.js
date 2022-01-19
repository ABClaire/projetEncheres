/*
 * Team GrandMa's Squad - ENI
 * Script JS pour disabled mes Checkboxs 
 * selon s�lection du Radio Bouton (Vente / Achat)
 */

function onClickVentes() {
    var enchOuvertes = document.getElementById("enchOuvertes");
    enchOuvertes.checked = false;
    enchOuvertes.disabled = true;

    var mesEnch = document.getElementById("mesEnch");
    mesEnch.checked = false;
    mesEnch.disabled = true;

    var enchRemportees = document.getElementById("enchRemportees");
    enchRemportees.checked = false;
    enchRemportees.disabled = true;

  }

function onClickAchats() {
    console.log("click achats");
    var ventesEnCours = document.getElementById("ventesEnCours");
    ventesEnCours.checked = false;
    ventesEnCours.disabled = true;

    var ventesNonDebut = document.getElementById("ventesNonDebut");
    ventesNonDebut.checked = false;
    ventesNonDebut.disabled = true;

    var ventesTerminees = document.getElementById("ventesTerminees");
    ventesTerminees.checked = false;
    ventesTerminees.disabled = true;

 
}