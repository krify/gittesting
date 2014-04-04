<?php
header('Content-type:application/json');
$path = $_SERVER['DOCUMENT_ROOT'];
chdir($path."/");
define('DRUPAL_ROOT', getcwd()); //the most important line
require_once './includes/bootstrap.inc';
drupal_bootstrap(DRUPAL_BOOTSTRAP_FULL);
$date=$_GET['date'];
$uid=$_GET['uid'];
    $goals = my_goals_by_date($date);
    foreach ($goals as $team => $info) {
      if ($info['first_score']) {
        $teams[] = $team;
        $st[$team] = $info['first_score'][0];
      }
    }
    
    
echo json_encode($goals); 





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
		$narr['team_name']=$goal['@attributes']['name'];
		$narr['goal_minutes']=$goal['@attributes']['minute'];
		$nss[]=$narr;
          $info[$team_0_id]['name'][]=$narr;
        }
        if ($goal['@attributes']['team'] == 2) {
		$narr['team_name']=$goal['@attributes']['name'];
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
		$narr['team_name']=$goal['@attributes']['name'];
		$narr['goal_minutes']=$goal['@attributes']['minute'];
		$nss[]=$narr;
          $info[$team_0_id]['name'][]=$narr;
      }
      if ($goal['@attributes']['team'] == 2) {
		$narr['team_name']=$goal['@attributes']['name'];
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




//echo "Testing.........";
//$res=fb_get_countries();
//print_r($res);
$res=bingo_pla($form, $form_state, $date);
$nres=theme_bingo_play_car($res,$uid);



function theme_bingo_play_car($form,$uid) 
{
  global $base_url, $user;
  $path = drupal_get_path('module', 'fb_bingo');



  $pot_amount=$form['pot']['#value']->amount ? $form['pot']['#value']->amount: 0 ;  

$z=1;
foreach($form['arr'] as $keys => $line) {
$rows = array();
$row =array();
preg_match('/#(\w+)/', $keys, $match);
if(empty($match)) { 
//$rows = array();
//$rows['league'] =$keys; 
 $mysta['id'] =$z;
 $mysta['name']=$keys;
 $output .= '<div id="id_'.$z.'" class="leagues_teams">';
  foreach($line['lineups'] as $key => $lineup) {
            
    if (is_numeric($key)) {
      $team_1 = array_shift(array_values($lineup[0]));
      $team_2 = array_shift(array_values($lineup[1]));
      $row['team_1_id']	= $team_1['#ide'];
      $row['team_1_name']	= $team_1['#title'];
	  $team_1_logo = 'sites/default/files/team-logos/'.$team_1['#ide'].'.png';
      if(file_exists($team_1_logo)){
        $row['team_1_logo']	= 'https://footballbingo.footballbuster.com/sites/default/files/team-logos/'.$team_1['#ide'].'.png';
      }else{
        $row['team_1_logo']	= 'https://footballbingo.footballbuster.com/sites/default/files/team-logos/no-image.png';
      }
     // $row['team_1_check']	= $lineup[0];
//      $row['vs']			= "vs";
     // $row['team_2_check']	= $lineup[1];
      $team_2_logo = 'sites/default/files/team-logos/'. $team_2['#ide'] . '.png'; 
      $row['team_2_id']	= $team_2['#ide'];
	 $row['team_2_name']	=$team_2['#title'];
      if(file_exists($team_2_logo)){ 
        $row['team_2_logo']	= 'https://footballbingo.footballbuster.com/sites/default/files/team-logos/'.$team_2['#ide'].'.png';
      }else{
        $row['team_2_logo']	= 'https://footballbingo.footballbuster.com/sites/default/files/team-logos/no-image.png';
      }      
      $rows[] = $row;
      drupal_render($form['lineups'][$key]);
    }
  }
  $status['names']=$mysta;
  $status['teams']=$rows;
  $alldata[]=$status; 

  $z=$z+1;
 } 
 }
  

  
  foreach($form['random'] as $key => $random) {
    if (is_numeric($key)) {
      $output .= '<div class="random_ball">';
      $randomkeys[]= $form['random'][$key]['team'];
      $output .= '</div>';
    }
  }

$fdata['top']=$alldata;
$fdata['bottom']=$randomkeys;
$fdata['stake']="10";
$fdata['balance']=userpoints_get_current_points($uid);
$fdata['bonus_pot_amt']=$pot_amount;

$ffdata[]=$fdata;
//echo json_encode($ffdata); 




  $bingo_settings = get_bingo_settings();
  $output .= '</div>';
 /* $output .= '<div class="box-footer clearfix"><div class="facebook">
              <iframe scrolling="no" frameborder="0" src="http://www.facebook.com/plugins/like.php?href=http%3A%2F%2Ffootballbuster.hd%2F12%2F&amp;layout=button_count&amp;show_faces=false&amp;width=200&amp;action=like&amp;colorscheme=light&amp;height=21" style="border:none;&lt;br /&gt;&lt;br /&gt;
              overflow:hidden; width:200px; height:21px;" allowtransparency="true"></iframe>
            </div>';
	$output .= '</div>';*/

//print_r($bingo_settings); die;
	drupal_add_js(array('fb_bingo' => array('completed_card_refund' => $bingo_settings['completed_card_refund'],'bingo_game_cost'=>$bingo_settings['bingo_game_cost'],'max_stake_gbp'=>$bingo_settings['max_stake_gbp'])), 'setting');
	
  $output .= '<div class="clearer"></div>';

	//User can bet here
 	$output .= '<div class="stake_bar">';
/*	
	$output .= '<div class="max_stake"><label>Max Stake GBP&pound;'. $bingo_settings['max_stake_gbp'].'</label>&nbsp;<span>&pound;</span><input type="text" id="stake_amt" name="stake_amt" autocomplete="off" value="'.$bingo_settings['bingo_game_cost']. '"></div><div class="tooltip">Minimum &pound; '.$bingo_settings['bingo_game_cost'].' requires </div>';
	$output .= '<div class="return_stake"><label>Returns </label><strong>&pound;</strong><span id="return_amt">'. ($bingo_settings['bingo_game_cost'] * $bingo_settings['completed_card_refund']) .'</span></div>';
 */ 

  $output .= '</div>';
  return $output;
}

//echo $nres;




function bingo_pla($form, $form_state, $date = NULL) {
  global $base_url;
  $form = array();
  if ($date) {
    $date = str_replace('-', '/', $date);
  }
  else {
    $date = get_gmt();
  }
 // print $date; die;
  $nine_pm=FALSE; 
  $bingo_settings = get_bingo_settings();    
  if($date==get_gmt()) {
   $nine_pm_bst = isset($bingo_settings['cutoff_time']) && $bingo_settings['cutoff_time'] != "" ? strtotime('today '.$bingo_settings['cutoff_time']):strtotime('today 20:59:59');         
   //$nine_pm_bst =strtotime('today 05:41:56'); 
   $current_time=strtotime(date('Y-m-d H:i:s'));
		 if($nine_pm_bst < $current_time) {
		  form_set_error('date',t('You should have got a card for today\'s fixture before @time BST' ,array('@time' => isset($bingo_settings['cutoff_time'])?$bingo_settings['cutoff_time']:'21:00:00')));
      $nine_pm=TRUE;
		 } 
   }   

  drupal_set_title(t('Fixtures for @date', array('@date' => $date)));

  $leagues = get_lineups_by_date($date, FALSE,TRUE); 
 //echo "<pre>"; print_r($leagues); die;
   $arr=array();
  $lineups=array();

  foreach($leagues as $y=>$ll){
    $lea[]=$ll;
   foreach($ll as  $a => $ss) { 
        if(is_numeric($a)){
         $lea[]=$leagues[$y][$a];
        // print_r($leagues[$y][$a]);
        // $id=$ss['@attributes']['id'];
        // $name=$ss['@attributes']['name'];  
         
         unset($leagues[$y][$a]);
         unset($lea[$y][$a]);
      }
    }    
 }
 //die;
  /*foreach($leagues as $yy=>$e){
  
   foreach($e as $ll=>$f){ 
      if(is_numeric($ll)){
      //  die;
       $leagues[$yy]=$f; 
       unset($leagues[$yy][$ll]);
    }
    } 
  } */
   //echo "<pre>"; print_r($leagues); 
  //die;
  $y=0;
  $c=array(); 
  //echo "<pre>"; print_r($leagues); die;
  foreach($lea as $y=>$l){
 $id=$l['@attributes']['id'];
  $name=$l['@attributes']['name'];
//echo "<pre>"; print_r($l);
   
   //print_r($leagues);

  
  //$id=$l['@attributes']['id'];
  //$name=$l['@attributes']['name'];
  //print $id;
 $check=array_find_key($l, 'Competitors', 'Result', 'negative'); 
   if(!empty($name) && !empty($check)){ 
    $na[$name]=$check;      
    $na[$name]['att'] = $l['@attributes']; 
   }else if(empty($name) && !empty($check)) {
    $c=array_merge($c,$check);       
    $na["Others"]=$c;
   } 
   $lineups= array_merge($lineups,array_find_key($l, 'Competitors', 'Result', 'negative'));
   $arr=$na;      
  } 

  $count = count($lineups);
  $remaining = $count - 8;
	$line=$_SESSION['lineups'][$date];
	$ran=$_SESSION['random'][$date];
  // The user chooses 8 teams and those teams cannot be chosen twice with the random balls
  // therefore there must be at least 7 left over after subtracting those 8 in order to have a complete game.
  // Not that this is the bare minimum and in the case where the remaining is 7
  // then the user really doesn't have any choice since all available teams will be utilized.

  //dvm(($count * 2));
  //dvm($bingo_settings['bingo_minimum_teams']);
  if (($count * 2) < $bingo_settings['bingo_minimum_teams']) {
    // Returns an array of only valid dates
    $valid_dates = get_valid_dates(TRUE);
    if(empty($valid_dates)){
      drupal_set_message("There is no valid fixtures",'error');
      drupal_goto($base_url);
    }else if(!in_array("valid", $valid_dates)){
      drupal_set_message("There is no valid fixtures",'error');
      drupal_goto($base_url);
    }
    //print_r($valid_dates); die;
    if (is_array($valid_dates)) {
      foreach($valid_dates as $v=>$v_dates){
      if($v_dates=="valid"){
        $vali_dates[$v]=$v_dates;
      }
      }
      $next_valid = array_shift(array_keys($vali_dates));
     // print_r($next_valid); die;

    }

    drupal_set_message(t('There are too few games available to complete a bingo card for @date. Please choose another date from the list of fixtures.', array('@date' => $date)));

    $form['to_few'] = array(
      '#type' => 'value',
      '#value' => TRUE,
    );
  }

  $form['arr'] = array(
    '#tree' => TRUE,
    '#type' => 'fieldset',
    '#attributes'=> array('class' => 'line-up-wrap'),
  );

/*Sorting of array League id wise 
foreach($arr as $r =>$k) {
	if(!empty($k['att']['id'])){ 
		$f[$r]=$k['att']['id'];
	 }
	else {
		$other[$r]="Others";
	}
}
	//asort($f);
	$all=array_merge($f,$other);
foreach($all as $sort_key=>$v) {
 $arrr[$sort_key]=$arr[$sort_key];
}*/

/*
foreach($arr as $r =>$k) {
  if(!empty($k['att']['id'])){ 
    $f[$r]=$k['att']['id'];
   }
  else {
    $other[$r]="Others";
  }
}
$all=$f;
*/

//Code modification done for league name order change.
foreach($arr as $r =>$k) {
  if(!empty($k['att']['id'])){ 
     //$f[$r]=$k['att']['id']; //- It contains all league names. Need to sort the league names in such a order like England Premier league, followed by championship, league 1, 2, blue sq premier. 

			if(strpos($r, "England") !== false)
				$eng[$r] =  $k['att']['id'];
			else if(strpos($r, "Scotland") !== false)
				$scot[$r] =  $k['att']['id'];
			elseif(strpos($r, "Spain") !== false)
				$spain[$r] =  $k['att']['id'];
			elseif(strpos($r, "Germany") !== false)
				$germany[$r] =  $k['att']['id'];
			elseif(strpos($r, "France") !== false)
				$france[$r] =  $k['att']['id'];
			elseif(strpos($r, "Netherlands") !== false)
				$dutch[$r] =  $k['att']['id'];
			elseif(strpos($r, "USA") !== false)
				$usa[$r] =  $k['att']['id'];
			elseif(strpos($r, "Australia") !== false)
				$australia[$r] =  $k['att']['id'];
			elseif(strpos($r, "China") !== false)
				$china[$r] =  $k['att']['id'];
		  else
				$other_leagues[$r] = $k['att']['id'];
   }
  else {
    $other[$r]="Others";
  }
}
$league_names = array();

if(count($eng) > 0) {
	asort($eng);
	$league_names += $eng;
}
if(count($scot) > 0) {
	asort($scot);
  $league_names += $scot;
}
if(count($spain) > 0) {
	asort($spain);
  $league_names += $spain;

}
if(count($germany) > 0) {
	asort($germany);
  $league_names += $germany;
}
if(count($france) > 0) {
	asort($france);
  $league_names += $france;
}
if(count($dutch) > 0) {
	asort($dutch);
  $league_names += $dutch;
}
if(count($usa) > 0) { 
	asort($usa);
  $league_names += $usa;
}
if(count($australia) > 0) {
	asort($australia);
  $league_names += $australia;
}
if(count($china) > 0) {
	asort($china);
  $league_names += $china;
}
if(count($other_leagues) > 0) {
	asort($other_leagues);
  $league_names += $other_leagues;
}

//$all=$f; // Correct. but order changed

$all = $league_names;

/* Code added up to this for sorting */


foreach($all as $sort_key=>$v) {
 $arrr[$sort_key]=$arr[$sort_key];
}

if(is_array($arrr)) {
	foreach($arrr as $keys => $lineups){
		 $form['arr'][$keys] = array(
		      '#tree' => TRUE,
		      '#type' => 'fieldset',
		       '#title' =>$keys,  
		      );  
		 $form['arr'][$keys]['lineups'] = array(
		      '#tree' => TRUE,
		      '#type' => 'fieldset',
		    );     
		if (is_array($lineups)) {
		  foreach ($lineups as $key => $lineup) {
       if(is_numeric($key)) {
		    $form['arr'][$keys]['lineups'][$key] = array(
		      '#tree' => TRUE,
		      '#type' => 'fieldset',
		       '#title' => "check",        
		      '#attributes'=> array('class' => 'line-up-item'),
		    );
		    foreach ($lineup['Competitor'] as $comp_key => $competitor) {
		      $competitor = $competitor['@attributes'];
					fb_insert_team($competitor);
		       if(!empty($line['arr'][$keys]['lineups'][$key][$comp_key][$competitor['ID']])) {  
						  $form['arr'][$keys]['lineups'][$key][$comp_key][$competitor['ID']] = array(
						    '#type'  => 'checkbox',
						    '#title' => $competitor['name'],
						    '#id' => $competitor['ID'],						    
						    '#value' =>$line['arr'][$keys]['lineups'][$key][$comp_key][$competitor['ID']],
		            		  '#disabled' => $nine_pm,      
						  );
		       }
		     else {
				      $form['arr'][$keys]['lineups'][$key][$comp_key][$competitor['ID']] = array(
				      '#type'  => 'checkbox',
						    '#ide' => $competitor['ID'],						    
				      '#title' => $competitor['name'],
		          	'#disabled' => $nine_pm,       
				     );
		     } 
		    }
		  } 
     }
		}
	}
}
  $x = 1;
  $form['random'] = array(
    '#tree' => TRUE,
    '#type' => 'fieldset',
  );
  while (($x < $remaining) && $x <= 60) {
    $form['random'][$x] = array(
      '#tree' => TRUE,
      '#type' => 'fieldset',
    );
		  if(!empty($ran[$x]['team'])) {   
				$form['random'][$x]['team'] = array(
				  '#type'  => 'checkbox',
				  '#title' => $x,
				  '#value' =>$ran[$x]['team'],
          	   	  '#disabled' => $nine_pm,         
				);
		  }
		  else {
				 $form['random'][$x]['team'] = array(
				  '#type'  => 'checkbox',
				  '#title' => $x,
          		  '#disabled' => $nine_pm,       
				);
		  }
    $x++;
  }
  $form['date'] = array(
    '#type' => 'value',
    '#value' => $date,
  );
  $pot = get_current_pot('bingo', NULL, $date,TRUE);
  // We need to come up with a way to have projected pot winnings when the current pot is at 0.
  $form['pot'] = array(
    '#type' => 'value',
    '#value' => $pot,
  );


	$stake_amt = !empty($_SESSION['stake_amt']) ? $_SESSION['stake_amt'] :  $bingo_settings['bingo_game_cost'];
	$form['stake_amt'] = array(
		'#type' => 'textfield',
		'#id' => 'stake_amt',		
   	'#default_value' => $stake_amt,
		'#attributes' => array('autocomplete' => 'off')
		
	);

  $form['submit'] = array(
    '#type' => 'submit',
    '#value' => t('Place bet & Build bingo card'),
    '#attributes'=> array('class' => 'big', 'style' => 'display:block; margin:0 auto'),

  );
  $form['#theme'] = 'bingo_play_card';

	//die();
  return $form;
}
?>
