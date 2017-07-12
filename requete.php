<?php
	//Connexion
	include_once('connexion.php');
	
	global $bdd;
		
	$req = $bdd->prepare('	SELECT *
							FROM clients
							');
		
	$req->execute();

	$evts = $req->fetchAll(PDO::FETCH_ASSOC);

	//var_dump($evts);
	
	echo json_encode($evts);

?>