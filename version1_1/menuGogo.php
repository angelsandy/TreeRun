<?php
header('Content-Type: application/json');

require_once "SBParentItem.php";
require_once "configItemsWithIds.php";

$secciones = array();
$arrayDiccionario['menu'] = array();

$arrayDiccionario['menu'][count($arrayDiccionario['menu'])] =
	new SBParentItem(
		"SBHome", //ID
		"Portada", //Titulo
		"1", //URL
		0, //Tipo Icono
		"", //Url Icono
		0,  //Tipo Contenido
		0, //Tipo Clase
		0  //Items
	);

$arrayDiccionario['menu'][count($arrayDiccionario['menu'])] =
	new SBParentItem(
		"SBOutfits",
		"Outfits",
		"11",
		3,
		"",
		1,
		3
	);

$arrayDiccionario['menu'][count($arrayDiccionario['menu'])] =
	new SBParentItem(
		"SBConsejos",
		"Consejos",
		"5",
		2,
		"",
		6,
		"",
		0,
		0
	);

$arrayDiccionario['menu'][count($arrayDiccionario['menu'])] =
	new SBParentItem(
		"SBRutina",
		"Rutina",
		"2",
		2,
		"",
		0,
		"",
		2,
		0
	);
echo json_encode($arrayDiccionario);

?>