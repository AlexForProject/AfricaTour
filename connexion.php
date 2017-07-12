<?php
	try {
		$bdd = new PDO('mysql:host=localhost;dbname=echappee_australe', 'root', '2588');
		$bdd->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	}
	catch (Exception $e) {
		die ('Erreur : ' . $e->getMessage());
	}
?>