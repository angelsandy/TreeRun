<?php

class SBParentItem {
	var $identificador;
	var $titulo;
	var $url;
	var $tipoIcono;
	var $urlIcono;
	var $tipoContenido;
	var $tipoClase;
	var $componentes;
	function __construct(
		$identificador,
		$titulo,
		$url,
		$tipoIcono,
		$urlIcono,
		$tipoContenido,
		$tipoClase,
		$items = null) {
			$this -> identificador = $identificador;
			$this -> titulo = $titulo;
			$this -> url = $url;
			$this -> tipoIcono = $tipoIcono;
			$this -> urlIcono = $urlIcono;
			$this -> tipoContenido = $tipoContenido;
			$this -> tipoClase = $tipoClase;
			$this -> componentes = getItemsWithIdWithArrayItems($this -> identificador, $this -> titulo, $items, $this -> url);
	}
}
?>