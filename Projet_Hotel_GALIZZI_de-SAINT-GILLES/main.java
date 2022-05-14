class main {
		 
	public static String adresse = "sql11.freemysqlhosting.net";
	public static String bd ="sql11491980";
	public static String login = "sql11491980";
	public static String password = "akNmglgrFq";
	
	
	//requete de selection
	//entr�e : requete -> une requete sql de type select
	//sortie : 0 si rien existe 1 sinon
	public static int requeteSelect(String requete) {
		int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
		int res = BD.executerSelect(connexion, requete);
		
		//BD.fermerResultat(res);
		BD.fermerConnexion(connexion);
		return res;
	}

	//requete d'insertion
	//entr�e : requete -> une requete sql de type insert
	public static int requeteInsert(String requete) {
		int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
		int res = BD.executerUpdate(connexion, requete);
		
		//BD.fermerResultat(res);
		BD.fermerConnexion(connexion);
		return res;
		}
	 

	//permet d'afficher le menu
	public static void menu(){
		char choix;
		Ecran.afficherln("Menu Principal : \n");
		Ecran.afficherln("1. Inserer un client");
		Ecran.afficherln("2. Inserer une reservation");
		Ecran.afficherln("3. Editer une facture");
		Ecran.afficherln("4. Quitter\n");
		choix=Clavier.saisirChar();
		switch(choix){
			case '1':
				client();
			break;
			case '2':
				reservation();
			break;			
			case '3':
				facture();
			break;	
			case '4':
				Ecran.afficherln("Au revoir");
				System.exit(1);
			break;			
			default:
				Ecran.afficherln("Veuillez saisir un charactere valide !");
				menu();
			break;
		}
	}
	 
	
	//permet l'insertion d'un client
	public static void client(){
		Ecran.afficher("Veuillez saisir le nom de famille :");
		String nom=Clavier.saisirString();
		
		Ecran.afficher("Veuillez saisir le prenom :");
		String prenom=Clavier.saisirString();
		
		Ecran.afficher("Veuillez saisir la ville :");
		String ville=Clavier.saisirString();
		
		Ecran.afficher("Veuillez saisir le code postal :");
		int cp=Clavier.saisirInt();
		
		Ecran.afficher("Veuillez saisir le numero de rue :");
		int numRue=Clavier.saisirInt();
		
		Ecran.afficher("Veuillez saisir le nom de la rue :");
		String nomRue=Clavier.saisirString();

		String req = "INSERT INTO CLIENT (NomClient, PrenomClient, VilleCl, CPCl, NumCl, RueCl) VALUES ('"+nom+"','"+prenom+"','"+ville+"',"+cp+","+numRue+",'"+nomRue+"');" ;
		
		if(requeteInsert(req)!=-1){
			Ecran.afficherln("\nLe client a bien ete insere\n\n");
		}
		
		else{
			Ecran.afficherln("\nIl y a eu une erreur");
		}

		menu();
	}
	
	//Trouve l'ID d'un client
	public static int trouveIDClient(){
		int IDClient;

		Ecran.afficher("Veuillez saisir le nom de famille :");
		String nom=Clavier.saisirString();
		
		Ecran.afficher("Veuillez saisir le prenom :");
		String prenom=Clavier.saisirString();

		String req1 = "SELECT IDClient FROM CLIENT WHERE NomClient = '"+nom+"' AND PrenomClient = '"+prenom+"';";

		int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
		int res1 = BD.executerSelect(connexion, req1);
		if (res1 != -1){
			BD.suivant(res1);
			IDClient = BD.attributInt(res1, "IDClient");

			if (BD.suivant(res1)){

				Ecran.afficher("Veuillez saisir la ville :");
				String ville=Clavier.saisirString();
				
				Ecran.afficher("Veuillez saisir le code postal :");
				int cp=Clavier.saisirInt();
				
				Ecran.afficher("Veuillez saisir le numero de rue :");
				int numRue=Clavier.saisirInt();
				
				Ecran.afficher("Veuillez saisir le nom de la rue :");
				String nomRue=Clavier.saisirString();

				String req2 = "SELECT IDClient FROM CLIENT WHERE NomClient = '"+nom+"' AND PrenomClient = '"+prenom+"' AND VilleCl = '"+ville+"' AND CPCl = "+cp+" AND NumCl = "+numRue+" AND RueCl = '"+nomRue+"';";
				int res2 = BD.executerSelect(connexion, req2);
				BD.suivant(res2);
				IDClient = BD.attributInt(res2, "IDClient");
				BD.fermerResultat(res2);
			}
		}
		else IDClient = -1;

		BD.fermerResultat(res1);
		BD.fermerConnexion(connexion);
		return IDClient;
	}

	//Trouve l'ID d'un client
	public static int trouveIDHotel(){
		int IDHotel;

		Ecran.afficher("Veuillez saisir le nom de l'hotel :");
		String nom=Clavier.saisirString();
		
		Ecran.afficher("Veuillez saisir la ville de l'hotel :");
		String ville=Clavier.saisirString();

		String req1 = "SELECT IDHotel FROM HOTEL WHERE NomHotel = '"+nom+"' AND VilleH = '"+ville+"';";

		int connexion = BD.ouvrirConnexion(adresse, bd, login, password);
		int res1 = BD.executerSelect(connexion, req1);
		if (res1 != -1){
			BD.suivant(res1);
			IDHotel = BD.attributInt(res1, "IDHotel");

			if (BD.suivant(res1)){
				
				Ecran.afficher("Veuillez saisir le code postal de l'hotel:");
				int cp=Clavier.saisirInt();
				
				Ecran.afficher("Veuillez saisir le numero de rue :");
				int numRue=Clavier.saisirInt();
				
				Ecran.afficher("Veuillez saisir le nom de la rue :");
				String nomRue=Clavier.saisirString();

				String req2 = "SELECT IDHotel FROM HOTEL WHERE NomHotel = '"+nom+"' AND VilleH = '"+ville+"' AND CPH = "+cp+" AND NumRueH = "+numRue+" AND RueH = '"+nomRue+"';";
				int res2 = BD.executerSelect(connexion, req2);
				BD.suivant(res2);
				IDHotel = BD.attributInt(res2, "IDHotel");
				BD.fermerResultat(res2);
			}
		}
		else IDHotel = -1;

		BD.fermerResultat(res1);
		BD.fermerConnexion(connexion);
		return IDHotel;
	}

	//permet l'insertion d'une reservation
	public static void reservation(){

		int idClient = trouveIDClient(); 

		if (idClient == -1){
			Ecran.afficherln("Erreur dans la demande, retour au menu.");
			menu();
		}

		int idHotel = trouveIDHotel();
		
		if (idHotel == -1){
			Ecran.afficherln("Erreur dans la demande, retour au menu.");
			menu();
		}

		Ecran.afficherln("Veuillez saisir le numero de la chambre :");
		int numChambre=Clavier.saisirInt();
		
		Dates date = new Dates();
		date.saisirDate();
		
		String preReq = "SELECT * FROM RESERVATION WHERE IDClient="+idClient+" AND IDHotel="+idHotel+" AND NumeroChambre="+numChambre+" AND DateReservation='"+date.versChaine()+"';";
		
		if(requeteSelect(preReq)==0){
			String req = "INSERT IGNORE INTO RESERVATION (IDClient, IDHotel, NumeroChambre, DateReservation) VALUES ("+idClient+","+idHotel+","+numChambre+",'"+date.versChaine()+"');" ;
			requeteInsert(req);

			if(requeteInsert(req)!=-1){
				Ecran.afficherln("La reservation à bien été inseré\n\n");
			}
			else{
				Ecran.afficherln("\n\nIl y a eu une erreur\n\n");
			}
			menu();
		}
		else{
			Ecran.afficherln("\nErreur dans la reservation, retour au menu\n\n");
			menu();
		}
	}



	public static void facture(){
		
		int idClient=trouveIDClient();
		
		int idHotel=trouveIDHotel();
						
		Ecran.afficher("Veuillez saisir le numero de la chambre :");
		int numChambre=Clavier.saisirInt();
		
		Dates d=new Dates();
		d.saisirDate();
		
		String sql = "SELECT NomClient, PrenomClient, NomHotel, Prix, Paye FROM RESERVATION, CLIENT, HOTEL, CHAMBRE WHERE RESERVATION.IDClient = CLIENT.IDClient AND RESERVATION.IDHotel = HOTEL.IDHotel AND HOTEL.IDHotel = CHAMBRE.IDHotel AND RESERVATION.NumeroChambre = CHAMBRE.NumeroChambre AND RESERVATION.IDClient ="+idClient+" AND RESERVATION.IDHotel ="+idHotel+" AND RESERVATION.NumeroChambre ="+numChambre+" AND DateReservation ='"+d.versChaine()+"' ;";

		int connexion = BD.ouvrirConnexion(adresse, bd, login, password);

		if (BD.executerSelect(connexion, sql) == 0){
			System.out.println("Aucune reservation correspondante");
			menu();
		}
		else{
			int res = BD.executerSelect(connexion,sql);
			if (BD.suivant(res)){

				String nomClient = BD.attributString(res, "NomClient");
				String prenomClient = BD.attributString(res, "PrenomClient");
				String nomHotel = BD.attributString(res, "NomHotel");
				float prix = BD.attributInt(res, "Prix");
				int paye = BD.attributInt(res, "Paye");
			
				BD.fermerResultat(res);

				System.out.println("\n\nFacture du "+d.versChaine()+"\nFait a l'hotel : "+nomHotel+" \nLe client "+prenomClient+" "+nomClient+" a reserve la chambre numero "+numChambre);

				if(paye == 1) System.out.println("Prix payé : "+prix+" euros.");
				
				else System.out.println("\nPrix restant a payer : "+prix+" euros. Voulez vous regler maintenant ?(o/n)");
				char input = Clavier.saisirChar();

				if (input == 'o') {
					paye = 1;
					requeteInsert("UPDATE RESERVATION SET Paye = true WHERE idClient ="+idClient+" AND idHotel ="+idHotel+" AND NumeroChambre ="+numChambre+" AND DateReservation ='"+d.versChaine()+"' ;");
					System.out.println("\nPayement accepte\n");
				}
				
				else if (input == 'n'){
					System.out.println("\nOK");
				}

				else System.out.println("\nErreur payement refuse\n");
				menu();
				}
			else { 
				System.out.println("\nErreur dans la requete, retour au menu\n");
			}
		}
		BD.fermerConnexion(connexion);
		menu();
	}	
	//le main
	public static void main(String[] args) {
		menu();
	}
}