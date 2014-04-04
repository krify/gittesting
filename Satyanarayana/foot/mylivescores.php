<?php
header('Content-type:application/json');
$path = $_SERVER['DOCUMENT_ROOT'];
chdir($path."/");
define('DRUPAL_ROOT', getcwd()); //the most important line
require_once './includes/bootstrap.inc';
drupal_bootstrap(DRUPAL_BOOTSTRAP_FULL);
    function Parse ($url) {
        $fileContents= file_get_contents($url);
        $fileContents = str_replace(array("\n", "\r", "\t"), '', $fileContents);
        $fileContents = trim(str_replace('"', "'", $fileContents));
        $simpleXml = simplexml_load_string($fileContents);
        $json = json_encode($simpleXml);

        return $json;
    }
    
$date=$_GET['date'];
$uid=$_GET['uid'];


$goals = my_goals_by_date($date);

function get_minutes($all,$team_name){
foreach($all as $adet){
if($adet['team_name']==$team_name){
	$allminutes[]=$adet['goal_minutes'];
}
}
return $allminutes;
}




  $xml_feed_url = 'http://xml1.livescore.si/Fixtures.aspx?sport_id=1&userID=20&fromDate='.$date.'&toDate='.$date;
$xml = Parse($xml_feed_url);
$xmls = json_decode($xml,TRUE);

foreach($xmls['Sports']['Sport']['Category'] as $categories){
/**************************** TOURNAMENT OPEN ***********************************/

$allcompetitors=$categories['Tournament']['Match'];
if(is_null($allcompetitors['Competitors']['Competitor'])){ /**************************** ARRAY OPEN ***********************************/
//echo "array";

foreach($categories['Tournament']['Match'] as $loopmatches){
$i=0;
foreach($loopmatches['Competitors']['Competitor'] as $bothteams){
if($i=="1"){
	$team['team_2_id']= $bothteams['@attributes']['ID'];
	$team['team_2_name']= $bothteams['@attributes']['name'];
	$team['team_2_logo']= "https://footballbingo.footballbuster.com/sites/default/files/team-logos/".$bothteams['@attributes']['ID'].".png";
	$ateams	= ($goals[$bothteams['@attributes']['ID']]['name'])?$goals[$bothteams['@attributes']['ID']]['name']:"";
if($ateams!=""){

$allteams[]="";
foreach($ateams as $fdet){

if(in_array($fdet['team_name'],$allteams)){
}
else{
$minutes=get_minutes($ateams,$fdet['team_name']);
$edet['team_name']=$fdet['team_name'];
$edet['goal_minutes']=implode(",", $minutes);
$sdet[]=$edet;
}
unset($minutes);
$allteams[]=$fdet['team_name'];
}
}
	$team['team_2_goals']=(!empty($sdet))?$sdet:"";
unset($sdet);
unset($allteams);
	$team['team_2_count'] = ($team['team_2_goals']=="")?"0":count($team['team_2_goals']);
}
else{
	$team['team_1_id']= $bothteams['@attributes']['ID'];
	$team['team_1_name']= $bothteams['@attributes']['name'];
	$team['team_1_logo']= "https://footballbingo.footballbuster.com/sites/default/files/team-logos/".$bothteams['@attributes']['ID'].".png";
	$ateams	= ($goals[$bothteams['@attributes']['ID']]['name'])?$goals[$bothteams['@attributes']['ID']]['name']:"";
if($ateams!=""){

$allteams[]="";
foreach($ateams as $fdet){

if(in_array($fdet['team_name'],$allteams)){
}
else{
$minutes=get_minutes($ateams,$fdet['team_name']);
$edet['team_name']=$fdet['team_name'];
$edet['goal_minutes']=implode(",", $minutes);
$sdet[]=$edet;
}
unset($minutes);
$allteams[]=$fdet['team_name'];
}
}
	$team['team_1_goals']=(!empty($sdet))?$sdet:"";
unset($sdet);
unset($allteams);


	$team['team_1_count'] = ($team['team_1_goals']=="")?"0":count($team['team_1_goals']);
}
$i=1;
}
$teams[]=$team;

}

} /**************************** ARRAY CLOSE ***********************************/
else{ /**************************** OBJECT OPEN ***********************************/
//echo "object";
$i=0;
foreach($allcompetitors['Competitors']['Competitor'] as $bothteams){
if($i=="1"){
	$team['team_2_id']= $bothteams['@attributes']['ID'];
	$team['team_2_name']= $bothteams['@attributes']['name'];
	$team['team_2_logo']= "https://footballbingo.footballbuster.com/sites/default/files/team-logos/".$bothteams['@attributes']['ID'].".png";
	$ateams	= ($goals[$bothteams['@attributes']['ID']]['name'])?$goals[$bothteams['@attributes']['ID']]['name']:"";
if($ateams!=""){

$allteams[]="";
foreach($ateams as $fdet){

if(in_array($fdet['team_name'],$allteams)){
}
else{
$minutes=get_minutes($ateams,$fdet['team_name']);
$edet['team_name']=$fdet['team_name'];
$edet['goal_minutes']=implode(",", $minutes);
$sdet[]=$edet;
}
unset($minutes);
$allteams[]=$fdet['team_name'];
}
}
	$team['team_2_goals']=(!empty($sdet))?$sdet:"";
unset($sdet);
unset($allteams);
	$team['team_2_count'] = ($team['team_2_goals']=="")?"0":count($team['team_2_goals']);

}
else{
	$team['team_1_id']= $bothteams['@attributes']['ID'];
	$team['team_1_name']= $bothteams['@attributes']['name'];
	$team['team_1_logo']= "https://footballbingo.footballbuster.com/sites/default/files/team-logos/".$bothteams['@attributes']['ID'].".png";
$ateams	= ($goals[$bothteams['@attributes']['ID']]['name'])?$goals[$bothteams['@attributes']['ID']]['name']:"";
if($ateams!=""){

$allteams[]="";
foreach($ateams as $fdet){

if(in_array($fdet['team_name'],$allteams)){
}
else{
$minutes=get_minutes($ateams,$fdet['team_name']);
$edet['team_name']=$fdet['team_name'];
$edet['goal_minutes']=implode(",", $minutes);
$sdet[]=$edet;
}
unset($minutes);
$allteams[]=$fdet['team_name'];
}
}
	$team['team_1_goals']=(!empty($sdet))?$sdet:"";
unset($sdet);
unset($allteams);

	$team['team_1_count'] = ($team['team_1_goals']=="")?"0":count($team['team_1_goals']);
}
$i=1;
}
$teams[]=$team;

}/**************************** OBJECT CLOSE ***********************************/

$adds['id']="1";
$adds['name']=$categories['Tournament']['@attributes']['name'];

$alldata['names']=$adds;

//$alldata['tourn_name']=$categories['Tournament']['@attributes']['name'];
$alldata['teams']=$teams;
unset($teams);
$fulldata[]=$alldata;

}/**************************** TOURNAMENT CLOSE ***********************************/

$fdata['top']=$fulldata;
$fdata['stake']="10";
$fdata['balance']=userpoints_get_current_points($uid);
$fdata['bonus_pot_amt']="0";

$fd[]=$fdata;
echo json_encode($fd);




function my_goals_by_date($date) {
  error_reporting(0);
  $fixtures = get_fixtures($date,NULL,NULL,TRUE);
  $matches = array_siblings_by_key($fixtures, 'Competitors');
  $points = array();
  foreach ($matches as $key => $match) {
    $ht = explode(':', $match['Result']['ScoreInfo']['Score'][0]['@attributes']['name']);
    $ft = explode(':', $match['Result']['ScoreInfo']['Score'][1]['@attributes']['name']);
    $team_0_id = $match['Competitors']['Competitor'][0]['@attributes']['ID'];
    $team_1_id = $match['Competitors']['Competitor'][1]['@attributes']['ID'];
    
    $info[$team_0_id]['ht'] = $ht[0];
    $info[$team_0_id]['ft'] = $ft[0];
    $info[$team_1_id]['ht'] = $ht[1];
    $info[$team_1_id]['ft'] = $ft[1];
    
    $info[$team_0_id]['status'] = $info[$team_1_id]['status'] = $match['@attributes']['status'];
    $info[$team_0_id]['date'] = $info[$team_1_id]['date'] = $match['@attributes']['date'];
    
    if (is_array($match['Result']['ScoreInfo']['comment']['Goals']['Goal'][0])) {
      foreach ($match['Result']['ScoreInfo']['comment']['Goals']['Goal'] as $goal) {
        if ($goal['@attributes']['team'] == 1) {
		$narr['team_name']=$goal['@attributes']['surname'];
		$narr['goal_minutes']=$goal['@attributes']['minute'];
		$nss[]=$narr;
          $info[$team_0_id]['name'][]=$narr;
        }
        if ($goal['@attributes']['team'] == 2) {
		$narr['team_name']=$goal['@attributes']['surname'];
		$narr['goal_minutes']=$goal['@attributes']['minute'];
		$nss[]=$narr;
          $info[$team_1_id]['name'][]=$narr;
        }
      }
      unset($nss);
    }
    // There is only a single result so process that one goal
    elseif ($match['Result']['ScoreInfo']['comment']['Goals']['Goal']['@attributes']['goalid']) {
      $goal = $match['Result']['ScoreInfo']['comment']['Goals']['Goal'];
      if ($goal['@attributes']['team'] == 1) {
		$narr['team_name']=$goal['@attributes']['surname'];
		$narr['goal_minutes']=$goal['@attributes']['minute'];
		$nss[]=$narr;
          $info[$team_0_id]['name'][]=$narr;
      }
      if ($goal['@attributes']['team'] == 2) {
		$narr['team_name']=$goal['@attributes']['surname'];
		$narr['goal_minutes']=$goal['@attributes']['minute'];
		$nss[]=$narr;
          $info[$team_1_id]['name'][]=$narr;
      }
      unset($nss);
    }
    
    if (isset($info[$team_0_id]['scores'])) {
      // We sort the scores by minute so that we can be assured that the first score is the first item in the array.
      sort($info[$team_0_id]['scores']);
      // Get the first item off the array which is the first score for this team.
      $info[$team_0_id]['first_score'] = array_slice($info[$team_0_id]['scores'], 0, 1);
    }
    if (isset($info[$team_1_id]['scores'])) {
      sort($info[$team_1_id]['scores']);
      $info[$team_1_id]['first_score'] = array_slice($info[$team_1_id]['scores'], 0 , 1);
    }
    
    // Leaving this in place just in case we need to debug on a team level later.
    //if ($team_0_id == 1100000180 || $team_1_id == 1100000180) {
    //  dpm($match);
    //}
  }
  return $info;
}









  
?>
