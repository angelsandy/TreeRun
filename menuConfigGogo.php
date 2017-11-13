<?php
header('Content-Type: application/json');
if(!isset($_GET['version'])) {
	include("version1_1/menuGogo.php");
} else {
	$rutaMenu = ".:/";
	if(strcmp($_GET['version'], '1.0') === 0) {
		$rutaMenu = $rutaMenu."version1_0/menuGogo.php";
	} else if(strcmp($_GET['version'], '1.1') === 0) {
		$rutaMenu = $rutaMenu."version1_1/menuGogo.php";
    } else {
		$rutaMenu = $rutaMenu."version1_0/menuGogo.php";
	}
	include($rutaMenu);
}
?>