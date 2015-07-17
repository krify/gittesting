//
//  porterRegistrationStep5.swift
//  porter
//
//  Created by Manohar Kola on 16/07/15.
//  Copyright (c) 2015 Manohar Kola. All rights reserved.
//

import UIKit

class porterRegistrationStep5: UIViewController {

    @IBOutlet var check1: UIButton!
    @IBOutlet var check2: UIButton!
    @IBOutlet var check3: UIButton!
    
    
      var days = [0,0,0]
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func previous(sender: AnyObject)
    {
        self.navigationController?.popViewControllerAnimated(true)
    }
    
    @IBAction func Next(sender: AnyObject)
    {
        println(days)
    }
    
    @IBAction func CheckButton(sender: AnyObject)
    {
        if(sender.tag == 1)
        {
            if(days[sender.tag-1] == 0)
            {
                days[sender.tag-1] = 1
                check1.setImage(UIImage(named: "selected-checkbox"), forState: UIControlState.Normal)
                check2.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
                check3.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
                days[1] = 0
                days[2] = 0
            }
            else
            {
                days[sender.tag-1] = 0
                check1.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
            }
        }
        else if(sender.tag == 2)
        {
            if(days[sender.tag-1] == 0)
            {
                days[sender.tag-1] = 1
                check2.setImage(UIImage(named: "selected-checkbox"), forState: UIControlState.Normal)
                check1.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
                check3.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
                days[0] = 0
                days[2] = 0
            }
            else
            {
                days[sender.tag-1] = 0
                check2.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
            }
        }
        else if(sender.tag == 3)
        {
            if(days[sender.tag-1] == 0)
            {
                days[sender.tag-1] = 1
                check3.setImage(UIImage(named: "selected-checkbox"), forState: UIControlState.Normal)
                check1.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
                check2.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
                days[0] = 0
                days[1] = 0
            }
            else
            {
                days[sender.tag-1] = 0
                check3.setImage(UIImage(named: "check"), forState: UIControlState.Normal)
            }
        }

    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
