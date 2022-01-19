/*
 * Team GrandMa's Squad - ENI
 * Script JS pour disabled mes Checkboxs 
 * selon selection du Radio Bouton (Vente / Achat)
 */
    var enchOuvertes = document.getElementById("enchOuvertes");
    var mesEnch = document.getElementById("mesEnch");
    var enchRemportees = document.getElementById("enchRemportees");

    var ventesEnCours = document.getElementById("ventesEnCours");
    var ventesNonDebut = document.getElementById("ventesNonDebut");
    var ventesTerminees = document.getElementById("ventesTerminees");


function onClickVentes() {
  	enchOuvertes.checked = false;
 	enchOuvertes.disabled = true;
 	
	mesEnch.checked = false;
    mesEnch.disabled = true;
	 
 	enchRemportees.checked = false;
    enchRemportees.disabled = true;

    ventesEnCours.disabled = false;
    ventesNonDebut.disabled = false;
    ventesTerminees.disabled = false;


}

function onClickAchats() {
	ventesEnCours.checked = false;
    ventesEnCours.disabled = true;

	ventesNonDebut.checked = false;
    ventesNonDebut.disabled = true;
	
	ventesTerminees.checked = false;
    ventesTerminees.disabled = true;
    
	enchOuvertes.disabled = false;
    mesEnch.disabled = false;
    enchRemportees.disabled = false;

    
}