<?php
function getItemsWithIdWithArrayItems($id, $titulo, $items, $url) {
    if ($items != null) {
      /* 	if (strcasecmp($id, "SBConsejos") == 0) {
            return configItemsConsejos($items);
        }*/
    } else {
        if (strcasecmp($id, "SBOutfit") == 0) {
            return configItemsOutfit();
        } /*if (
            strcasecmp($id, "rgfdg") == 0 ||
            strcasecmp($id, "fdgg") == 0 ||
            strcasecmp($id, "dgf") == 0 ||
            strcasecmp($id, "dgf") == 0 ||
            strcasecmp($id, "dfgd") == 0 ||
            strcasecmp($id, "dfg") == 0 ||
            strcasecmp($id, "dfgd") == 0
        ) {
            return configItemsEditionWithIdTitulo($url, $titulo);
        } else if (
            strcasecmp($id, "fg")  == 0 ||
            strcasecmp($id, "fdg")  == 0 ||
            strcasecmp($id, "dfg")  == 0
        ) {
            return configItemsSectionWithId($url, $titulo);
        } else if (strcasecmp($id, "milenioMujeres")  == 0||
            strcasecmp($id, "milenioSalud")  == 0){*/
          // return configSectionAlone($url, $titulo);
       // }
    }
    return array();
}

function configItemsOutfit() {
    $res = array();
    $res[count($res)] = new SBParentItem(
        "SBOutfits",
        "Outfits",
        "",
        0,
        "",
        0,
        "",
        0,
        3
    );
    return $res;
}
?>